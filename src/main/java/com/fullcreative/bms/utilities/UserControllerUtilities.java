package com.fullcreative.bms.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;

import com.fullcreative.bms.models.Login;
import com.fullcreative.bms.models.User;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.gson.Gson;

import java.util.regex.*;
import java.math.BigInteger;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

public class UserControllerUtilities {

	public static String payloadAsStringFromRequestSignup(HttpServletRequest request) throws IOException {

		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();

	}

	public static String payloadAsStringFromRequestLogin(HttpServletRequest request) throws IOException {

		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();

	}

	// Check For One User
	public static boolean isOneUser(String uri) {
		List<String> requestsArray = Arrays.asList(uri.split("/"));
		Integer count = requestsArray.size();
		if (count == 3) {
			return true;
		} else {
			return false;
		}
	}


	// Create Entity From User Pojo
	public static Entity entityFromUser(String userKey, User requestUserData)
			throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
		Entity entity = new Entity("User", userKey);
		entity.setProperty("username", requestUserData.getUserName());
		entity.setProperty("email", requestUserData.getEmail());
		entity.setProperty("password", CreatePasswordHash(requestUserData.getPassword()));
		entity.setProperty("timestamp", System.currentTimeMillis());
		return entity;
	}

	// Create Hashed Password
	public static String CreatePasswordHash(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		return toHex(salt) + ":" + toHex(hash);
	}

	// Hexdecimal Converter
	public static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);

		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	// Check For Existing Primary Email
	@SuppressWarnings("deprecation")
	private static boolean isEmailExists(String email) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User").setFilter(new FilterPredicate("email", FilterOperator.EQUAL, email));
		PreparedQuery pq = datastore.prepare(query);
		return pq.countEntities() > 0;
	}

	// Email Validator
	private static boolean EmailValidator(String email) {
		if (email == null)
			return false;
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(regex);
		return pat.matcher(email).matches();
	}

	// Password Validator
	public static boolean PasswordValidator(String password) {
		if (password == null)
			return false;
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
		Pattern pat = Pattern.compile(regex);
		return pat.matcher(password).matches();
	}


	// Credential Validator
	public static Map<String, Object> requestCredentialValidator(Login credentialData, Map<String, Object> errorMap) {
		if (!EmailValidator(credentialData.getEmail()))
			errorMap.put("ERROR_MESSAGE", "Please enter a valid email");
		if (!isEmailExists(credentialData.getEmail()))
			errorMap.put("ERROR_MESSAGE", "User not exists");
		else if (!PasswordValidator(credentialData.getPassword()))
			errorMap.put("ERROR_MESSAGE", "Please enter a valid password");

		if (errorMap.size() != 0) {
			errorMap.put("STATUS_CODE", 400);
		}
		return errorMap;
	}


	// Data Validation
	public static Map<String, Object> requestUserValidator(User requestUserData, Map<String, Object> errorMap) {
		// Primary Email Check
		if (!EmailValidator(requestUserData.getEmail()))
			errorMap.put("ERROR_MESSAGE", "Please enter a valid email");
		else {
			if (isEmailExists(requestUserData.getEmail()))
				errorMap.put("ERROR_MESSAGE", "User exists already, please login instead");
		}
		// Password Check
		if (!PasswordValidator(requestUserData.getPassword()))
			errorMap.put("ERROR_MESSAGE",
					"A minimum 8 characters password contains a combination of uppercase and lowercase letter, number and symbol are required.");
		if (errorMap.size() != 0) {
			errorMap.put("STATUS_CODE", 400);
		}
		return errorMap;
	}

	// Create User
	public static Map<String, Object> createUser(String userData) throws EntityNotFoundException {
		Gson gson = new Gson();
		Map<String, Object> responseMap;
		User requestUserData = gson.fromJson(userData, User.class);
		System.out.println(requestUserData);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		responseMap = requestUserValidator(requestUserData, new HashMap<>());
		if (responseMap.size() != 0)
			return responseMap;
		else {
			try {
				String userID = UUID.randomUUID().toString();
				Entity entity = entityFromUser(userID, requestUserData);
				Key keyObj = datastore.put(entity);
				Entity responseEntity = datastore.get(keyObj);
				responseMap = new HashMap<>(responseEntity.getProperties());
				responseMap.put("userId", keyObj.getName());
				responseMap.put("STATUS_CODE", 200);
			} catch (Exception e) {
				responseMap.put("ERROR_MESSAGE", "Something went wrong");
				responseMap.put("STATUS_CODE", 500);
				e.printStackTrace();
			}
			return responseMap;
		}
	}

	// Password Verification

	public static boolean verifyPassword(String originalPassword, String storedPassword)
			throws EntityNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {

		String[] parts = storedPassword.split(":");
		// int iterations = Integer.parseInt(parts[0]);

		byte[] salt = fromHex(parts[0]);
		byte[] hash = fromHex(parts[1]);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, 65536, 128);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	// User Log In process

	public static Map<String, Object> loginUtil(String userCreds) throws EntityNotFoundException {
		Gson gson = new Gson();
		Map<String, Object> responseMap;
		Login requestUserCreds = gson.fromJson(userCreds, Login.class);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		responseMap = requestCredentialValidator(requestUserCreds, new HashMap<>());
		if (responseMap.size() != 0)
			return responseMap;
		try {
			Query query = new Query("User")
					.setFilter(new FilterPredicate("email", FilterOperator.EQUAL, requestUserCreds.getEmail()));
			PreparedQuery pq = datastore.prepare(query);
			Entity entity = pq.asSingleEntity();
			responseMap = new HashMap<>(entity.getProperties());
			System.out.print(responseMap);
			if (verifyPassword(requestUserCreds.getPassword(), entity.getProperty("password").toString())) {
				responseMap = new HashMap<>(entity.getProperties());
				responseMap.put("STATUS_CODE", 200);
			} else {
				responseMap.put("ERROR_MESSAGE", "Password you entered is incorrect");
				responseMap.put("STATUS_CODE", 401);
				System.out.println(responseMap);
			}
			System.out.println(responseMap);
		} catch (Exception e) {
//			responseMap.remove("MESSAGE");
			responseMap.put("ERROR_MESSAGE", "Something went wrong");
			responseMap.put("STATUS_CODE", 500);
			e.printStackTrace();
		}
		return responseMap;
	}

	public static String getUserKeyFromUri(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String[] requestsArray = requestUri.split("/");
		String userID = requestsArray[requestsArray.length - 1];
		return userID;
	}

	// Update User

	public static Map<String, Object> updateUserTest(String jsonInputString, String userID)
			throws EntityNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {

		Gson gson = new Gson();
		User requestUserData = gson.fromJson(jsonInputString, User.class);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Map<String, Object> responseMap = new HashMap<>();
		Entity entity = entityFromUser(userID, requestUserData);
		try {
			datastore.get(entity.getKey());
			responseMap = requestUserValidator(requestUserData, responseMap);

			if (responseMap.size() != 0
					&& responseMap.get("ERROR_MESSAGE").toString() != "User exists already, please login instead") {
				return responseMap;
			} else {
				Key keyObj = datastore.put(entity);
				Entity responseEntity = datastore.get(keyObj);
				responseMap = new HashMap<>(responseEntity.getProperties());
				responseMap.put("STATUS_CODE", 200);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.put("ERROR_MESSAGE", "User not exists");
			responseMap.put("STATUS_CODE", 404);
		}
		return responseMap;
	}

	// Delete User
	public static Map<String, Object> deleteUser(String userID) throws EntityNotFoundException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key entityKey = KeyFactory.createKey("User", userID);
		HashMap<String, Object> responseMap = new HashMap<>();
		try {
			datastore.get(entityKey);
			datastore.delete(entityKey);
			responseMap.put("SUCCESS", "User deleted");
			responseMap.put("STATUS_CODE", 200);
		} catch (Exception e) {
			e.printStackTrace();
			responseMap.put("ERROR_MESSAGE", "User not exists");
			responseMap.put("STATUS_CODE", 404);
		}
		return responseMap;
	}

	// Fetch single user
	public static HashMap<String, Object> getOneUser(String userID) throws EntityNotFoundException {
		HashMap<String, Object> responseUser = new HashMap<>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key userKey = KeyFactory.createKey("User", userID);
		try {
			Entity responseEntity;
			responseEntity = datastore.get(userKey);
			responseUser = new HashMap<>(responseEntity.getProperties());
			responseUser.put("STATUS_CODE", 200);
		} catch (Exception e) {
			e.printStackTrace();
			responseUser.put("ERROR_MESSAGE", "User not exists");
			responseUser.put("STATUS_CODE", 404);
		}
		return responseUser;
	}

	// Fetch all users
	public static List<Map<String, Object>> getAllUsers() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User").addSort("username", SortDirection.ASCENDING);
		List<Entity> userEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		List<Map<String, Object>> users = new ArrayList<>();
		for (Entity entity : userEntities) {
			entity.removeProperty("password");
			users.add(entity.getProperties());
		}
		return users;
	}

}

