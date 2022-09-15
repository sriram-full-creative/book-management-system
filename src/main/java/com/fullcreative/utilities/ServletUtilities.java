package com.fullcreative.utilities;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServletUtilities {

	/**
	 * @param linkedHashMap
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
	 * @return JsonObject
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> createNewBook(String jsonInputString) throws EntityNotFoundException {
		Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation()
				.create();
		Book newBook = gson.fromJson(jsonInputString, Book.class);
		System.out.println(newBook.toString());
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		responseMap = requestBookValidator(newBook, responseMap);
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

	/**
	 * @param jsonInputString
	 * @return JsonObject
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> updateBook(String jsonInputString, String bookID)
			throws EntityNotFoundException {
		Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
		Book newBook = gson.fromJson(jsonInputString, Book.class);
		System.out.println(newBook.toString());
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		Entity entity = entityFromBook(newBook, bookID);
		try {
			datastore.get(entity.getKey());
			responseMap = requestBookValidator(newBook, responseMap);
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
	 * @return List<LinkedHashMap<String, Object>>
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
	 * @param entities
	 * @return List<BookData>
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
	 * @param bookID
	 * @return String
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
		System.out.println(errorMap.toString());
		return errorMap;

	}

	public static boolean hasBookKey(String requestURI) {
		List<String> requestsArray = Arrays.asList(requestURI.split("/"));
		Integer count = requestsArray.size();
		if (count == 3) {
			return true;
		} else {
			return false;
		}
	}

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

}
