package com.fullcreative.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.time.Instant;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;

import com.fullcreative.pojo.Book;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ServletUtilities {

	/**
	 * @param map
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String mapToJsonString(LinkedHashMap<String, Object> map) {
		Gson gson = new Gson();
		Book book = new Book();
		book.setAuthor((LinkedList<String>) map.get("author"));
		book.setPublication((LinkedList<String>) map.get("publication"));
		book.setTitle((String) map.get("title"));
		book.setLanguage((String) map.get("language"));
		book.setPages((Integer) map.get("pages"));
		book.setReleaseYear((Integer) map.get("releaseYear"));
		book.setCountry((String) map.get("country"));
		book.setCoverImage((String) map.get("coverImage"));
		book.setBookLink((String) map.get("bookLink"));
		book.setRating((Integer) map.get("rating"));
		String jsonString = gson.toJson(book);
		return jsonString;
	}

	/**
	 * @param jsonInputString
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> createNewBook(String jsonInputString) throws EntityNotFoundException {
		Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation()
				.create();
		Book newBook = gson.fromJson(jsonInputString, Book.class);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			responseMap = requestBookValidator(newBook, responseMap);
		} catch (Exception e) {
			responseMap.put("REQUEST_BODY_ERROR", "The request body should contain a json body");
			responseMap.put("STATUS_CODE", 400);
		}
		if (responseMap.size() != 0) {
			return responseMap;
		} else {
			String bookID = UUID.randomUUID().toString();
			Entity entity = entityFromBook(newBook, bookID);
			Key keyObj = datastore.put(entity);
			try {
			Entity responseEntity = datastore.get(keyObj);
			Book responseBookData = new Book();
			responseBookData = bookFromEntity(responseEntity);
			responseMap = mapFromBook(responseBookData, responseMap);
			responseMap.put("BOOK_ID", keyObj.getName());
			responseMap.put("STATUS_CODE", 200);
		} catch (Exception e) {
			System.out.println("Thrown from createNewBook Method");
			responseMap.put("ERROR", "Book was not created");
			responseMap.put("STATUS_CODE", 503);
			e.printStackTrace();
		}
			return responseMap;
		}
	}

	public static Map<String, Object> createNewBook(String jsonInputString, InputStream fileInputStream,
			String imageFormat) throws IOException {
		Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
		Book newBook = gson.fromJson(jsonInputString, Book.class);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			responseMap = requestBookValidator(newBook, responseMap);
		} catch (Exception e) {
			responseMap.put("REQUEST_BODY_ERROR", "The request body should contain a json body");
			responseMap.put("STATUS_CODE", 400);
		}
		try {
			responseMap = imageValidator(fileInputStream, imageFormat, responseMap);
		} catch (Exception e) {
			responseMap.put("IMAGE_ERROR", "Invalid Image file or format");
			responseMap.put("STATUS_CODE", 400);
		}
		if (responseMap.size() != 0) {
			return responseMap;
		} else {
			String bookID = UUID.randomUUID().toString();
			String fileName = bookID + "." + imageFormat;
			// Upload the file and get its URL
			String uploadedFileUrl = ServletUtilities.uploadToCloudStorage(fileName, fileInputStream);
			newBook.setCoverImage(uploadedFileUrl);
			Entity entity = entityFromBook(newBook, bookID);
			Key keyObj = datastore.put(entity);
			try {
				Entity responseEntity = datastore.get(keyObj);
				Book responseBookData = new Book();
				responseBookData = bookFromEntity(responseEntity);
				responseMap = mapFromBook(responseBookData, responseMap);
				responseMap.put("BOOK_ID", keyObj.getName());
				responseMap.put("STATUS_CODE", 200);
			} catch (Exception e) {
				System.out.println("Thrown from createNewBook Method");
				responseMap.put("ERROR", "Book was not created");
				responseMap.put("STATUS_CODE", 503);
				e.printStackTrace();
			}
			return responseMap;
		}
	}

	private static LinkedHashMap<String, Object> imageValidator(InputStream fileInputStream,
			String imageFormat, LinkedHashMap<String, Object> responseMap) throws IOException {
		try {
			if (imageFormat.equalsIgnoreCase("jpeg") == false && imageFormat.equalsIgnoreCase("jpg") == false
					&& imageFormat.equalsIgnoreCase("png") == false) {
				throw new Exception();
			}
			ImageIO.read(fileInputStream).toString();

		} catch (NullPointerException e) {
			responseMap.put("IMAGE_ERROR", "Invalid Image file or format");
			responseMap.put("STATUS_CODE", 400);
		} catch (Exception e) {
			responseMap.put("IMAGE_ERROR", "Invalid Image file or format");
			responseMap.put("STATUS_CODE", 400);
		}
		return responseMap;
	}

	/**
	 * @param jsonInputString
	 * @param bookID
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> updateBook(String jsonInputString, String bookID)
			throws EntityNotFoundException {
		Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
		Book newBook = gson.fromJson(jsonInputString, Book.class);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			Entity entity = UpdatedEntityFromBookForUpdate(newBook, bookID);
			responseMap = requestBookValidatorForUpdation(newBook, responseMap);
			if (responseMap.size() != 0) {
				return responseMap;
			} else {
				Key keyObj = datastore.put(entity);
				Entity responseEntity = datastore.get(keyObj);
				Book responseBookData = new Book();
				responseBookData = bookFromEntity(responseEntity);
				responseMap = mapFromBook(responseBookData, responseMap);
				responseMap.put("BOOK_ID", keyObj.getName());
				responseMap.put("STATUS_CODE", 200);
			}
		} catch (Exception e) {
			System.out.println("Thrown from updateBook Method");
			responseMap.put("ERROR", "Book not Found. Invalid Key");
			responseMap.put("STATUS_CODE", 404);
			e.printStackTrace();
		}
		return responseMap;
	}


	/**
	 * @param queryParameters
	 * @return LinkedList<String>
	 */
	public static LinkedList<String> getAllBooks(Map<String, String> queryParameters) {
		LinkedHashMap<String, Object> bookAsMap = new LinkedHashMap<String, Object>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		String property = queryParameters.get("sortOnProperty");
		String direction = queryParameters.get("sortDirection");
		Query query = new Query("Books").addSort(property, SortDirection.DESCENDING);
		if (direction.equalsIgnoreCase("ascending")) {
			query = new Query("Books").addSort(property, SortDirection.ASCENDING);
		}
		List<Entity> bookEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		List<Book> booksFromEntities = ServletUtilities.booksFromEntities(bookEntities);
		LinkedList<String> books = new LinkedList<>();
		for (Book book : booksFromEntities) {
			books.add(mapToJsonString(mapFromBook(book, bookAsMap)));
		}
		return books;
	}

	/**
	 * @return LinkedList<String>
	 */
	public static LinkedList<String> getAllBooks() {
		LinkedHashMap<String, Object> bookAsMap = new LinkedHashMap<String, Object>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Books").addSort("CreatedOrUpdated", SortDirection.DESCENDING);
		List<Entity> bookEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		List<Book> booksFromEntities = ServletUtilities.booksFromEntities(bookEntities);
		LinkedList<String> books = new LinkedList<>();
		for (Book book : booksFromEntities) {
			books.add(mapToJsonString(mapFromBook(book, bookAsMap)));
		}
		return books;
	}

	/**
	 * @param bookID
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> getOneBook(String bookID) throws EntityNotFoundException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key bookKey = KeyFactory.createKey("Books", bookID);
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			Entity responseEntity = datastore.get(bookKey);
			Book responseBookData = ServletUtilities.bookFromEntity(responseEntity);
			responseMap = mapFromBook(responseBookData, responseMap);
			responseMap.put("STATUS_CODE", 200);
		} catch (Exception e) {
			System.out.println("Caught in getOneBook method");
			e.printStackTrace();
			responseMap.put("ERROR", "Book not Found. Invalid Key");
			responseMap.put("STATUS_CODE", 404);
		}
		return responseMap;
	}


	/**
	 * @param bookID
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> deleteBook(String bookID) throws EntityNotFoundException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key entityKey = KeyFactory.createKey("Books", bookID);
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			datastore.get(entityKey);
			datastore.delete(entityKey);
			responseMap.put("SUCCESS", "Book was deleted");
			responseMap.put("STATUS_CODE", 200);
		} catch (Exception e) {
			System.out.println("Caught in deleteBook Method");
			e.printStackTrace();
			responseMap.put("ERROR", "Book not Found. Invalid Key");
			responseMap.put("STATUS_CODE", 404);
		}
		return responseMap;
	}

	/**
	 * @param entities
	 * @return List<Book>
	 */
	public static List<Book> booksFromEntities(List<Entity> entities) {
		LinkedHashMap<String, Object> bookID = new LinkedHashMap<>();
		List<Book> books = new ArrayList<>();
		for (Entity entity : entities) {
			books.add(bookFromEntity(entity));
			bookID.put(entity.getKey().toString(), bookFromEntity(entity));
		}

		return books;
	}

	/**
	 * @param book
	 * @param map
	 * @return LinkedHashMap<String, Object>
	 */
	private static LinkedHashMap<String, Object> mapFromBook(Book book, LinkedHashMap<String, Object> map) {
		map.put("author", book.getAuthor());
		map.put("publication", book.getPublication());
		map.put("title", book.getTitle());
		map.put("language", book.getLanguage());
		map.put("pages", book.getPages());
		map.put("releaseYear", book.getReleaseYear());
		map.put("country", book.getCountry());
		map.put("coverImage", book.getCoverImage());
		map.put("bookLink", book.getBookLink());
		map.put("rating", book.getRating());
		return map;
	}

	/**
	 * @param entity
	 * @return Book
	 */
	private static Book bookFromEntity(Entity entity) {
		Book book = new Book();
		book.setAuthor(new LinkedList<String>(Arrays.asList(entity.getProperty("Author").toString().split(","))));
		book.setPublication(
				new LinkedList<String>(Arrays.asList(entity.getProperty("Publication").toString().split(","))));
		book.setTitle(entity.getProperty("Title").toString());
		book.setLanguage(entity.getProperty("Language").toString());
		book.setPages(Integer.parseInt(entity.getProperty("Pages").toString()));
		book.setReleaseYear(Integer.parseInt(entity.getProperty("ReleaseYear").toString()));
		book.setCountry(entity.getProperty("Country").toString());
		book.setCoverImage(entity.getProperty("CoverImage").toString());
		book.setBookLink(entity.getProperty("BookLink").toString());
		book.setRating(Integer.parseInt(entity.getProperty("Rating").toString()));

		return book;
	}

	/**
	 * @param book
	 * @param bookID
	 * @return Entity
	 */
	private static Entity entityFromBook(Book book, String bookID) {
		Entity entity = new Entity("Books", bookID);
		entity.setProperty("Author", book.getAuthor().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		entity.setProperty("Publication",
				book.getPublication().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		entity.setProperty("Title", book.getTitle());
		entity.setProperty("Language", book.getLanguage());
		entity.setProperty("Pages", book.getPages());
		entity.setProperty("ReleaseYear", book.getReleaseYear());
		entity.setProperty("Country", book.getCountry());
		entity.setProperty("CoverImage", book.getCoverImage());
		entity.setProperty("BookLink", book.getBookLink());
		entity.setProperty("Rating", book.getRating());
		entity.setProperty("CreatedOrUpdated", Time.from(Instant.now()));
		return entity;
	}

	/**
	 * @param book
	 * @param errorMap
	 * @return LinkedHashMap<String, Object>
	 */
	private static LinkedHashMap<String, Object> requestBookValidator(Book book,
			LinkedHashMap<String, Object> errorMap) {

		int flag = 0;

		LinkedList<String> authors = book.getAuthor();
		if (authors.size() == 0) {
			errorMap.put("AUTHOR_NAME_EMPTY_ERROR", "Author Name should contain atleast 1 character");
			flag = 1;
		} else {
			for (String author : authors) {
				if (author.replaceAll(" ", "").length() == 0 || author.length() <= 0 || author == null) {
					errorMap.put("AUTHOR_NAME_EMPTY_ERROR", "Author Name should contain atleast 1 character");
					flag = 1;
				} else if (author.replaceAll(" ", "").matches("[a-zA-Z.]+") == false
						&& author.replaceAll(" ", "").length() != 0) {
					errorMap.put("AUTHOR_NAME_FORMAT_ERROR", "Author Name should contain only alphabets");
					flag = 1;
				}
			}
		}

		LinkedList<String> publications = book.getPublication();
		if (publications.size() == 0) {
			errorMap.put("PUBLICATION_NAME_EMPTY_ERROR", "Author Name should contain atleast 1 character");
			flag = 1;
		} else {
			for (String publication : publications) {
				if (publication.replaceAll(" ", "").length() == 0 || publication.length() <= 0 || publication == null) {
					errorMap.put("PUBLICATION_NAME_EMPTY_ERROR", "Publication Name should contain atleast 1 character");
					flag = 1;
				}
			}
		}

		if (book.getTitle().replaceAll(" ", "").length() == 0 || book.getTitle().length() <= 0
				|| book.getTitle() == null) {
			errorMap.put("TITLE_NAME_ERROR", "Title Name should contain atleast 1 character");
			flag = 1;
		}

		if (book.getLanguage().replaceAll(" ", "").length() == 0 || book.getLanguage().length() <= 0
				|| book.getLanguage() == null) {
			errorMap.put("LANGUAGE_EMPTY_ERROR", "Language field can't be empty");
			flag = 1;
		}
		if (book.getLanguage().replaceAll(" ", "").matches("[a-zA-Z]+") == false
				&& book.getLanguage().replaceAll(" ", "").length() != 0) {
			errorMap.put("LANGUAGE_FORMAT_ERROR", "Language field can contain only alphabets");
			flag = 1;
		}


		if (book.getPages() < 20) {
			if (book.getPages() < 0) {
				errorMap.put("PAGES_ERROR", "Page Count should be Positive");
				flag = 1;
			} else {
				errorMap.put("PAGES_ERROR", "Book should have atleast 20 pages");
				flag = 1;
			}
		}

		if (book.getReleaseYear() <= 0) {
			errorMap.put("YEAR_NEGATIVE_VALUE_ERROR", "Year should be positive");
			flag = 1;
		}
		if (book.getReleaseYear() > Year.now().getValue()) {
			errorMap.put("YEAR_FUTURE_VALUE_ERROR",
					"Year should be less than or equal to the current year -> '" + Year.now().getValue() + "'");
			flag = 1;
		}

		if (book.getCountry().replaceAll(" ", "").length() < 3 || book.getCountry() == null) {
			errorMap.put("COUNTRY_FIELD_EMPTY_ERROR", "Country should atleast have 3 characters");
			flag = 1;
		}
		if (book.getCountry().replaceAll(" ", "").matches("[a-zA-Z,.]+") == false
				&& book.getCountry().replaceAll(" ", "").length() != 0) {
			errorMap.put("COUNTRY_FIELD_FORMAT_ERROR", "Country field can contain only alphabets");
			flag = 1;
		}


		if (book.getRating() < 0 || book.getRating() > 5) {
			errorMap.put("RATING_RANGE_ERROR", "Rating should be in the range of 0 to 5");
			flag = 1;
		}

		if (flag == 1) {
			errorMap.put("STATUS_CODE", 400);
		}
		return errorMap;

	}

	/**
	 * @param book
	 * @param errorMap
	 * @return LinkedHashMap<String, Object>
	 */
	private static LinkedHashMap<String, Object> requestBookValidatorForUpdation(Book book,
			LinkedHashMap<String, Object> errorMap) {
		int flag = 0;
		LinkedList<String> authors = book.getAuthor();
		if (authors != null) {
			if (authors.size() == 0) {
				errorMap.put("AUTHOR_NAME_EMPTY_ERROR", "Author Name should contain atleast 1 character");
				flag = 1;
			} else {
				for (String author : authors) {
					if (author.replaceAll(" ", "").length() == 0 || author.length() <= 0) {
						errorMap.put("AUTHOR_NAME_EMPTY_ERROR", "Author Name should contain atleast 1 character");
						flag = 1;
					} else if (author.replaceAll(" ", "").matches("[a-zA-Z.]+") == false
							&& author.replaceAll(" ", "").length() != 0) {
						errorMap.put("AUTHOR_NAME_FORMAT_ERROR", "Author Name should contain only alphabets");
						flag = 1;
					}
				}
			}
		}

		LinkedList<String> publications = book.getPublication();
		if (publications != null) {
			if (publications.size() == 0) {
				errorMap.put("PUBLICATION_NAME_EMPTY_ERROR", "Author Name should contain atleast 1 character");
				flag = 1;
			} else {
				for (String publication : publications) {
					if (publication.replaceAll(" ", "").length() == 0 || publication.length() <= 0) {
						errorMap.put("PUBLICATION_NAME_EMPTY_ERROR",
								"Publication Name should contain atleast 1 character");
						flag = 1;
					}
				}
			}
		}

		if (book.getTitle() != null) {
			if (book.getTitle().replaceAll(" ", "").length() == 0 || book.getTitle().length() <= 0) {
				errorMap.put("TITLE_NAME_ERROR", "Title Name should contain atleast 1 character");
				flag = 1;
			}
		}

		if (book.getLanguage() != null) {
			if (book.getLanguage().replaceAll(" ", "").length() == 0 || book.getLanguage().length() <= 0) {
				errorMap.put("LANGUAGE_EMPTY_ERROR", "Language field can't be empty");
				flag = 1;
			}
			if (book.getLanguage().replaceAll(" ", "").matches("[a-zA-Z]+") == false
					&& book.getLanguage().replaceAll(" ", "").length() != 0) {
				errorMap.put("LANGUAGE_FORMAT_ERROR", "Language field can contain only alphabets");
				flag = 1;
			}
		}

		if (book.getPages() != null) {

			if (book.getPages() < 20) {
				if (book.getPages() < 0) {
					errorMap.put("PAGES_ERROR", "Page Count should be Positive");
					flag = 1;
				} else {
					errorMap.put("PAGES_ERROR", "Book should have atleast 20 pages");
					flag = 1;
				}
			}
		}

		if (book.getReleaseYear() != null) {
			if (book.getReleaseYear() <= 0) {
				errorMap.put("YEAR_NEGATIVE_VALUE_ERROR", "Year should be positive");
				flag = 1;
			}
			if (book.getReleaseYear() > Year.now().getValue()) {
				errorMap.put("YEAR_FUTURE_VALUE_ERROR",
						"Year should be less than or equal to the current year -> '" + Year.now().getValue() + "'");
				flag = 1;
			}
		}

		if (book.getCountry() != null) {
			if (book.getCountry().replaceAll(" ", "").length() < 3) {
				errorMap.put("COUNTRY_FIELD_EMPTY_ERROR", "Country should atleast have 3 characters");
				flag = 1;
			}
			if (book.getCountry().replaceAll(" ", "").matches("[a-zA-Z,.]+") == false
					&& book.getCountry().replaceAll(" ", "").length() != 0) {
				errorMap.put("COUNTRY_FIELD_FORMAT_ERROR", "Country field can contain only alphabets");
				flag = 1;
			}
		}

		if (book.getRating() != null) {
			if (book.getRating() < 0 || book.getRating() > 5) {
				errorMap.put("RATING_RANGE_ERROR", "Rating should be in the range of 0 to 5");
				flag = 1;
			}
		}

		if (flag == 1) {
			errorMap.put("STATUS_CODE", 400);
		}
		return errorMap;

	}

	/**
	 * @param book
	 * @param bookID
	 * @return Entity
	 * @throws EntityNotFoundException
	 */
	private static Entity UpdatedEntityFromBookForUpdate(Book book, String bookID) throws EntityNotFoundException {
		Entity entity = new Entity("Books", bookID);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity newEntity = datastore.get(entity.getKey());

		if (book.getAuthor() != null) {
			entity.setProperty("Author",
					book.getAuthor().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		} else {
			entity.setProperty("Author", newEntity.getProperty("Author"));
		}
		if (book.getPublication() != null) {
			entity.setProperty("Publication",
					book.getPublication().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		} else {
			entity.setProperty("Publication", newEntity.getProperty("Publication"));
		}
		if (book.getTitle() != null) {
			entity.setProperty("Title", book.getTitle());
		} else {
			entity.setProperty("Title", newEntity.getProperty("Title"));
		}
		if (book.getLanguage() != null) {
			entity.setProperty("Language", book.getLanguage());
		} else {
			entity.setProperty("Language", newEntity.getProperty("Language"));
		}
		if (book.getPages() != null) {
			entity.setProperty("Pages", book.getPages());
		} else {
			entity.setProperty("Pages", newEntity.getProperty("Pages"));
		}
		if (book.getReleaseYear() != null) {
			entity.setProperty("ReleaseYear", book.getReleaseYear());
		} else {
			entity.setProperty("ReleaseYear", newEntity.getProperty("ReleaseYear"));
		}
		if (book.getCountry() != null) {
			entity.setProperty("Country", book.getCountry());
		} else {
			entity.setProperty("Country", newEntity.getProperty("Country"));
		}
		if (book.getCoverImage() != null) {
			entity.setProperty("CoverImage", book.getCoverImage());
		} else {
			entity.setProperty("CoverImage", newEntity.getProperty("CoverImage"));
		}
		if (book.getBookLink() != null) {
			entity.setProperty("BookLink", book.getBookLink());
		} else {
			entity.setProperty("BookLink", newEntity.getProperty("BookLink"));
		}
		if (book.getRating() != null) {
			entity.setProperty("Rating", book.getRating());
		} else {
			entity.setProperty("Rating", newEntity.getProperty("Rating"));
		}
		entity.setProperty("CreatedOrUpdated", Time.from(Instant.now()));
		return entity;
	}

	/**
	 * @param requestURI
	 * @return boolean
	 */
	public static boolean hasBookKey(String requestURI) {
		List<String> requestsArray = Arrays.asList(requestURI.split("/"));
		Integer count = requestsArray.size();
		if (count == 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param requestURI
	 * @return boolean
	 */
	public static boolean isValidEndPoint(String requestURI) {
		List<String> requestsArray = Arrays.asList(requestURI.split("/"));
		Integer count = requestsArray.size();
		if (count == 3 || count == 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param request
	 * @return String
	 */
	public static String getBookKeyFromUri(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String[] requestsArray = requestUri.split("/");
		String bookID = requestsArray[requestsArray.length - 1];
		return bookID;
	}

	/**
	 * @param responseMap
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> invalidRequestEndpoint(Map<String, Object> responseMap) {
		/**
		 * Status Code 422 means Unprocessable Entity The 422 (Unprocessable Entity)
		 * status code means the server understands the content type of the request
		 * entity (hence a 415 (Unsupported Media Type) status code is inappropriate),
		 * and the syntax of the request entity is correct (thus a 400 (Bad Request)
		 * status code is inappropriate) but was unable to process the contained
		 * instructions. For example, this error condition may occur if an XML request
		 * body contains well-formed (i.e., syntactically correct), but semantically
		 * erroneous, XML instructions.
		 */
		responseMap.put("ERROR", "Invalid End Point");
		responseMap.put("STATUS_CODE", 422);
		return responseMap;
	}

	/**
	 * @param incomingParameters
	 * @return Map<String, String>
	 */
	public static Map<String, String> processQueryParameters(Map<String, String[]> incomingParameters) {
		Map<String, String> queryParameters = new LinkedHashMap<>();
		String property;
		String direction;
		if (incomingParameters.containsKey("sortOnProperty")) {
			property = incomingParameters.get("sortOnProperty")[0];
			if (property.equalsIgnoreCase("author")) {
				queryParameters.put("sortOnProperty", "Author");
			} else if (property.equalsIgnoreCase("publication")) {
				queryParameters.put("sortOnProperty", "Publication");
			} else if (property.equalsIgnoreCase("title")) {
				queryParameters.put("sortOnProperty", "Title");
			} else if (property.equalsIgnoreCase("pages")) {
				queryParameters.put("sortOnProperty", "Pages");
			} else if (property.equalsIgnoreCase("releaseYear")) {
				queryParameters.put("sortOnProperty", "ReleaseYear");
			} else if (property.equalsIgnoreCase("rating")) {
				queryParameters.put("sortOnProperty", "Rating");
			} else {
				queryParameters.put("sortOnProperty", "CreatedOrUpdated");
			}
		} else {
			queryParameters.put("sortOnProperty", "CreatedOrUpdated");
		}

		if (incomingParameters.containsKey("sortDirection")) {
			direction = incomingParameters.get("sortDirection")[0];
			if (direction.equalsIgnoreCase("ascending")) {
				queryParameters.put("sortDirection", "ascending");
			} else {
				queryParameters.put("sortDirection", "descending");
			}
		} else {
			queryParameters.put("sortDirection", "descending");
		}
		return queryParameters;
	}

	/**
	 * @param fileName
	 * @param fileInputStream
	 * @return String
	 * 
	 *         Uploads a file to Cloud Storage and returns the uploaded file's URL.
	 */
	public static String uploadToCloudStorage(String fileName, InputStream fileInputStream) {
		String projectId = "book-management-system-362310";
		String bucketName = "book-management-system-362310.appspot.com";
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		@SuppressWarnings("deprecation")
		Blob blob = storage.create(blobInfo, fileInputStream);
		return blob.getMediaLink();
	}

}