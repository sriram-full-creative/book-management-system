package com.fullcreative.bms.services;

import java.util.*;

import com.fullcreative.bms.utilities.UserControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;


public class AuthServices {

	// User Sign Up Service For Test
	public static Map<String, Object> signUp(String userData) throws EntityNotFoundException {
		Map<String, Object> successfulMap = new HashMap<String, Object>();

		Map<String, Object> responseMap = UserControllerUtilities.createUser(userData);
		if (responseMap.containsKey("ERROR_MESSAGE")) {
			successfulMap.put("ERROR_MESSAGE", responseMap.get("ERROR_MESSAGE"));
			System.out.println(successfulMap);
			successfulMap.put("STATUS_CODE", 400);
			return successfulMap;
		}
		else {
			successfulMap.put("id", responseMap.get("userId"));
			successfulMap.put("MESSAGE", "Signed Up Successful");
			successfulMap.put("STATUS_CODE", 200);
		}
		return successfulMap;
		}

	// User Log In Service

	public static Map<String, Object> Login(String userData) throws EntityNotFoundException {
		Map<String, Object> successfulMap = new HashMap<String, Object>();

		Map<String, Object> responseMap = UserControllerUtilities.loginUtil(userData);
		System.out.println(responseMap);
		if (responseMap.containsKey("ERROR_MESSAGE")) {
			successfulMap.put("ERROR_MESSAGE", responseMap.get("ERROR_MESSAGE"));
			successfulMap.put("STATUS_CODE", 400);
			return successfulMap;
		}
		else {
			successfulMap.put("id", responseMap.get("userId"));
			successfulMap.put("MESSAGE", "Logged In Successful");
			successfulMap.put("STATUS_CODE", 200);
		}
		return successfulMap;
		}
}