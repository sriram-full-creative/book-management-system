package com.fullcreative.utilities;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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


import javax.servlet.http.HttpServletRequest;

import com.fullcreative.models.Book;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Sriram
 *
 */
public class BooksControllerUtilities {

	/** Utility Methods for HttpServletRequest Processing and Validation **/

	/**
	 * <p>
	 * Checks if the HttpServletRequest URI has a ID or not.
	 * </p>
	 * 
	 * @param requestURI
	 * @return boolean
	 */
	public static boolean hasBookID(String requestURI) {
		List<String> requestsArray = Arrays.asList(requestURI.split("/"));
		Integer count = requestsArray.size();
		if (count == 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>
	 * Checks if the Requested End Point (URI) in the HttpServletRequest is valid or
	 * not. Although the HttpServlet is configured to handle requests sent to
	 * specific end points, This method verifies if the Sub Path is valid or not.
	 * <p>
	 * 
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
	 * <p>
	 * Extracts the ID from the HttpServletRequest URI.
	 * <p>
	 * 
	 * @param request
	 * @return String
	 */
	public static String getBookIDFromUri(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		String[] requestsArray = requestUri.split("/");
		String bookID = requestsArray[requestsArray.length - 1];
		return bookID;
	}

	/**
	 * <p>
	 * Extracts the JSON Payload from the HttpServletRequest
	 * </p>
	 * 
	 * @param request
	 * @return String
	 * @throws IOException
	 */
	public static String payloadFromRequest(HttpServletRequest request) throws IOException {
		StringBuffer jsonString = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			jsonString.append(line);
		}
		return jsonString.toString();
	}

	/**
	 * <p>
	 * Generates a Error Response Map for when a requested End Point is Invalid.
	 * </p>
	 * 
	 * @param responseMap
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> invalidRequestEndpointResponse(Map<String, Object> responseMap) {
		/**
		 * The HTTP 414 URI Too Long response status code indicates that the URI
		 * requested by the client is longer than the server is willing to interpret.
		 */
		responseMap.put("ERROR", "Recheck the URI");
		responseMap.put("STATUS_CODE", 400);
		return responseMap;
	}

	/**
	 * <p>
	 * Creates a Map of the Query Parameters which are passed in order to Sort Books
	 * in a specific order when fetching them.
	 * </p>
	 * 
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

	/** Utility Methods to Manipulate Data Structures and Data Types **/

	/**
	 * <p>
	 * Converts a LinkedHashMap into a JSON Formatted String.
	 * </p>
	 * 
	 * @param map
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String mapToJsonString(LinkedHashMap<String, Object> map) {
		Gson gson = new Gson();
		Book book = new Book();
		book.setId((String) map.get("id"));
		book.setAuthor((LinkedList<String>) map.get("author"));
		book.setPublication((LinkedList<String>) map.get("publication"));
		book.setTitle((String) map.get("title"));
		book.setLanguage((String) map.get("language"));
		book.setPages((Integer) map.get("pages"));
		book.setReleaseYear((Integer) map.get("releaseYear"));
		book.setCountry((String) map.get("country"));
		book.setCoverImage((String) map.get("coverImage"));
		book.setRating((Integer) map.get("rating"));
		String jsonString = gson.toJson(book);
		return jsonString;
	}


	/**
	 * <p>
	 * Creates ByteArrayOutputStream from the InputStream to create clones of the
	 * InputStream.
	 * </p>
	 * 
	 * @param inputStream
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	private static ByteArrayOutputStream cloneInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		while ((len = inputStream.read(buffer)) > -1) {
			baos.write(buffer, 0, len);
		}
		baos.flush();

		return baos;
	}

	/**
	 * <p>
	 * Creates a List of Book POJOs from a List of Entities.
	 * </p>
	 * 
	 * @param entities
	 * @return List<Book>
	 */
	public static List<Book> booksFromEntities(List<Entity> entities) {
		List<Book> books = new ArrayList<>();
		for (Entity entity : entities) {
			books.add(bookFromEntity(entity));
		}

		return books;
	}

	/**
	 * <p>
	 * Converts the Book POJO into a LinkedHashMap.
	 * </p>
	 * 
	 * @param book
	 * @param map
	 * @return LinkedHashMap<String, Object>
	 */
	private static LinkedHashMap<String, Object> mapFromBook(Book book, LinkedHashMap<String, Object> map) {
		map.put("id", book.getId());
		map.put("author", book.getAuthor());
		map.put("publication", book.getPublication());
		map.put("title", book.getTitle());
		map.put("language", book.getLanguage());
		map.put("pages", book.getPages());
		map.put("releaseYear", book.getReleaseYear());
		map.put("country", book.getCountry());
		map.put("coverImage", book.getCoverImage());
		map.put("rating", book.getRating());
		return map;
	}

	/**
	 * <p>
	 * Creates a Book POJO out of the Properties form the Entity.
	 * </p>
	 * 
	 * @param entity
	 * @return Book
	 */
	private static Book bookFromEntity(Entity entity) {
		Book book = new Book();
		book.setId(entity.getKey().getName());
		book.setAuthor(new LinkedList<String>(Arrays.asList(entity.getProperty("Author").toString().split(","))));
		book.setPublication(
				new LinkedList<String>(Arrays.asList(entity.getProperty("Publication").toString().split(","))));
		book.setTitle(entity.getProperty("Title").toString());
		book.setLanguage(entity.getProperty("Language").toString());
		book.setPages(Integer.parseInt(entity.getProperty("Pages").toString()));
		book.setReleaseYear(Integer.parseInt(entity.getProperty("ReleaseYear").toString()));
		book.setCountry(entity.getProperty("Country").toString());
		book.setCoverImage(entity.getProperty("CoverImage").toString());
		book.setRating(Integer.parseInt(entity.getProperty("Rating").toString()));

		return book;
	}

	/**
	 * <p>
	 * Converts the Properties of Book POJO into an Datastore Entity.
	 * </p>
	 * <p>
	 * Call this only when creating a Book.
	 * </p>
	 * 
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
		entity.setProperty("Rating", book.getRating());
		entity.setProperty("CreatedOrUpdated", Time.from(Instant.now()));
		return entity;
	}

	/**
	 * <p>
	 * Converts the Properties of Book POJO into an Datastore Entity.
	 * </p>
	 * <p>
	 * Call this only when updating an entity without updating the coverImage for
	 * the Book. If the Book POJO has a null value for a property, the corresponding
	 * entity property will be updated from the existing Datastore Entity.
	 * </p>
	 * 
	 * @param book
	 * @param bookID
	 * @return Entity
	 * @throws EntityNotFoundException
	 */
	private static Entity entityFromBookForUpdate(Book book, String bookID) throws EntityNotFoundException {
		Entity entity = new Entity("Books", bookID);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity datastoreEntity = datastore.get(entity.getKey());

		if (book.getAuthor() != null) {
			entity.setProperty("Author",
					book.getAuthor().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		} else {
			entity.setProperty("Author", datastoreEntity.getProperty("Author"));
		}
		if (book.getPublication() != null) {
			entity.setProperty("Publication",
					book.getPublication().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		} else {
			entity.setProperty("Publication", datastoreEntity.getProperty("Publication"));
		}
		if (book.getTitle() != null) {
			entity.setProperty("Title", book.getTitle());
		} else {
			entity.setProperty("Title", datastoreEntity.getProperty("Title"));
		}
		if (book.getLanguage() != null) {
			entity.setProperty("Language", book.getLanguage());
		} else {
			entity.setProperty("Language", datastoreEntity.getProperty("Language"));
		}
		if (book.getPages() != null) {
			entity.setProperty("Pages", book.getPages());
		} else {
			entity.setProperty("Pages", datastoreEntity.getProperty("Pages"));
		}
		if (book.getReleaseYear() != null) {
			entity.setProperty("ReleaseYear", book.getReleaseYear());
		} else {
			entity.setProperty("ReleaseYear", datastoreEntity.getProperty("ReleaseYear"));
		}
		if (book.getCountry() != null) {
			entity.setProperty("Country", book.getCountry());
		} else {
			entity.setProperty("Country", datastoreEntity.getProperty("Country"));
		}

		entity.setProperty("CoverImage", datastoreEntity.getProperty("CoverImage"));

		if (book.getRating() != null) {
			entity.setProperty("Rating", book.getRating());
		} else {
			entity.setProperty("Rating", datastoreEntity.getProperty("Rating"));
		}
		entity.setProperty("CreatedOrUpdated", Time.from(Instant.now()));
		return entity;
	}

	/**
	 * 
	 * <p>
	 * Converts the Properties of Book POJO into an Datastore Entity.
	 * </p>
	 * <p>
	 * Call this only when updating an entity when updating the coverImage of the
	 * Book. If the POJO has a null value for a property, the corresponding entity
	 * property will be updated from the existing Datastore Entity.
	 * </p>
	 * 
	 * @param datastoreEntity
	 * @param book
	 * @param bookID
	 * @param updatedFileUrl
	 * @return Entity
	 * @throws EntityNotFoundException
	 */
	private static Entity entityFromBookForUpdate(Entity datastoreEntity, Book book, String bookID,
			String updatedFileUrl) throws EntityNotFoundException {
		Entity entity = new Entity("Books", bookID);
		if (book.getAuthor() != null) {
			entity.setProperty("Author",
					book.getAuthor().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		} else {
			entity.setProperty("Author", datastoreEntity.getProperty("Author"));
		}
		if (book.getPublication() != null) {
			entity.setProperty("Publication",
					book.getPublication().toString().replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ","));
		} else {
			entity.setProperty("Publication", datastoreEntity.getProperty("Publication"));
		}
		if (book.getTitle() != null) {
			entity.setProperty("Title", book.getTitle());
		} else {
			entity.setProperty("Title", datastoreEntity.getProperty("Title"));
		}
		if (book.getLanguage() != null) {
			entity.setProperty("Language", book.getLanguage());
		} else {
			entity.setProperty("Language", datastoreEntity.getProperty("Language"));
		}
		if (book.getPages() != null) {
			entity.setProperty("Pages", book.getPages());
		} else {
			entity.setProperty("Pages", datastoreEntity.getProperty("Pages"));
		}
		if (book.getReleaseYear() != null) {
			entity.setProperty("ReleaseYear", book.getReleaseYear());
		} else {
			entity.setProperty("ReleaseYear", datastoreEntity.getProperty("ReleaseYear"));
		}
		if (book.getCountry() != null) {
			entity.setProperty("Country", book.getCountry());
		} else {
			entity.setProperty("Country", datastoreEntity.getProperty("Country"));
		}
		// Updating the Cover Image with the Latest URL
		entity.setProperty("CoverImage", updatedFileUrl);

		if (book.getRating() != null) {
			entity.setProperty("Rating", book.getRating());
		} else {
			entity.setProperty("Rating", datastoreEntity.getProperty("Rating"));
		}
		entity.setProperty("CreatedOrUpdated", Time.from(Instant.now()));
		return entity;
	}

	/**
	 * Utility Methods used to Perform Validations on data sent in
	 * HttpServletRequest.
	 **/

	/**
	 * <p>
	 * Validates the fileInputStream.
	 * </p>
	 * <p>
	 * Checks the MIME TYPE and also the stream to validate if the stream is of an
	 * Image using the ImageIo Class.
	 * </p>
	 * 
	 * @param fileInputStream
	 * @param imageFormat
	 * @param responseMap
	 * @return LinkedHashMap<String, Object>
	 * @throws IOException
	 */
	private static LinkedHashMap<String, Object> imageValidator(byte[] imageDataAsByteArray, String imageFormat,
			LinkedHashMap<String, Object> responseMap) throws IOException {
		try {
			imageFormat = getActualImageFormat(imageDataAsByteArray);
			System.out.println("Uploaded Image format: " + imageFormat);
			if (imageFormat.equalsIgnoreCase("undefined")) {
				throw new Exception();
			}
		} catch (IOException e) {
			responseMap.put("IMAGE_ERROR", "Invalid Image file or format");
			responseMap.put("STATUS_CODE", 400);
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
	 * Get the file type based on the file signature. Here restricted to only
	 * recognized file type jpeg, jpg, png where the magic numbers of jpg and jpeg
	 * files are the same.
	 *
	 * @param imageDataAsByteArray Byte array of the file.
	 * @return String of the file type.
	 */
	public static String getActualImageFormat(byte[] imageDataAsByteArray) {
		String type = "undefined";
		if (Byte.toUnsignedInt(imageDataAsByteArray[0]) == 0x89 && Byte.toUnsignedInt(imageDataAsByteArray[1]) == 0x50
				&& Byte.toUnsignedInt(imageDataAsByteArray[2]) == 0x4e
				&& Byte.toUnsignedInt(imageDataAsByteArray[3]) == 0x47) {
			type = "png";
		}
		else if (Byte.toUnsignedInt(imageDataAsByteArray[0]) == 0xFF
				&& Byte.toUnsignedInt(imageDataAsByteArray[1]) == 0xD8
				&& Byte.toUnsignedInt(imageDataAsByteArray[2]) == 0xff
				&& Byte.toUnsignedInt(imageDataAsByteArray[3]) == 0xe0) {
			type = "jpg";
		}
		return type;
	}

	/**
	 * <p>
	 * Validates the bookID by checking the datastore for the presence of the ID.
	 * </p>
	 * 
	 * @param bookID
	 * @return boolean
	 */
	public static boolean bookIDValidator(String bookID) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// Validating the bookID
		Entity entity = new Entity("Books", bookID);
		try {
			datastore.get(entity.getKey());
			return true;
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <p>
	 * Validates the book details created from the HttpServeltRequest Body.
	 * </p>
	 * 
	 * <p>
	 * Call this method to validate data only when creating a new book.
	 * </p>
	 * 
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
	 * <p>
	 * Validates the book details created from the HttpServeltRequest Body.
	 * </p>
	 * 
	 * <p>
	 * Call this method to validate data only when updating the book details.
	 * </p>
	 * 
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

	/** Utility Methods to Perform CRUD Operations for the Application **/

	/** 1. CREATE Operation **/

	/**
	 * <p>
	 * Creates a New Entity in the Datastore with all the properties of the Book
	 * POJO.
	 * </p>
	 * 
	 * @param jsonInputString
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> createNewBook(String jsonInputString) throws EntityNotFoundException {
		Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
		Book newBook = gson.fromJson(jsonInputString, Book.class);
		newBook.setCoverImage("");
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
	 * <p>
	 * Creates a New Entity in the Datastore with all the properties of the Book
	 * along with a Cover Image for the Book. Cover Image will be stored in the GCS.
	 * </p>
	 * 
	 * @param jsonInputString
	 * @param fileInputStream
	 * @param imageFormat
	 * @return Map<String, Object>
	 * @throws IOException
	 */
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
			// Cloning a the inputStream to validate
			ByteArrayOutputStream baos = cloneInputStream(fileInputStream);
			fileInputStream = new ByteArrayInputStream(baos.toByteArray());
			responseMap = imageValidator(baos.toByteArray(), imageFormat, responseMap);

		} catch (Exception e) {
			responseMap.put("IMAGE_ERROR", "Invalid Image file or format");
			responseMap.put("STATUS_CODE", 400);
		}
		if (responseMap.size() != 0) {
			return responseMap;
		} else {
			String bookID = UUID.randomUUID().toString();
			// Upload the file and get its URL
			String uploadedFileUrl = BooksControllerUtilities.uploadToCloudStorage(bookID, imageFormat, fileInputStream);
			newBook.setCoverImage(uploadedFileUrl);
			System.out.println(uploadedFileUrl);
			Entity entity = entityFromBook(newBook, bookID);
			Key keyObj = datastore.put(entity);
			try {
				Entity responseEntity = datastore.get(keyObj);
				Book responseBookData = new Book();
				responseBookData = bookFromEntity(responseEntity);
				responseMap = mapFromBook(responseBookData, responseMap);
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

	/** 2. READ Operation **/

	/**
	 * <p>
	 * Fetches all the Books from the Datastore.
	 * </p>
	 * <p>
	 * By default the recently updated or created books will be served first.
	 * </p>
	 * 
	 * @return LinkedList<String>
	 */
	public static LinkedList<String> getAllBooks() {
		LinkedHashMap<String, Object> bookAsMap = new LinkedHashMap<String, Object>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Books").addSort("CreatedOrUpdated", SortDirection.DESCENDING);
		List<Entity> bookEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		List<Book> booksFromEntities = BooksControllerUtilities.booksFromEntities(bookEntities);
		LinkedList<String> books = new LinkedList<>();
		for (Book book : booksFromEntities) {
			books.add(mapToJsonString(mapFromBook(book, bookAsMap)));
		}
		return books;
	}

	/**
	 * <p>
	 * Fetches all the Books from the Datastore.
	 * </p>
	 * 
	 * <p>
	 * By default the recently updated or created books will be served first. User
	 * can also send parameters to sort the books based on properties both in the
	 * order of ascending or descending.
	 * </p>
	 * 
	 * <p>
	 * Parameter Names and their accepted values.
	 * </p>
	 * <ol>
	 * <li>sortOnProperty = {"author", "publication", "title", "pages",
	 * "releaseYear", "rating"}
	 * <li>sortDirection = {"ASCENDING", "DESCENDING"}
	 * </ol>
	 * <p>
	 * ParameterNames are <b>Case-Sensitive</b> while Values are.
	 * <b>Case-Insensitive</b>
	 * </p>
	 * 
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
		List<Book> booksFromEntities = BooksControllerUtilities.booksFromEntities(bookEntities);
		LinkedList<String> books = new LinkedList<>();
		for (Book book : booksFromEntities) {
			books.add(mapToJsonString(mapFromBook(book, bookAsMap)));
		}
		return books;
	}

	/**
	 * <p>
	 * Serves the data of the Book when passed with the valid ID.
	 * </p>
	 * 
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
			Book responseBookData = BooksControllerUtilities.bookFromEntity(responseEntity);
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

	/** 3. UPDATE Operation **/

	/**
	 * <p>
	 * Updates the Datastore Entity alone.
	 * </p>
	 * 
	 * <p>
	 * Call this Method to Update Fields of the Book. You won't be able to update
	 * the coverImage field without Updating the Image in GCS. Method will ignore
	 * the coverImage field if sent in the request body.
	 * </p>
	 * 
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
			responseMap = requestBookValidatorForUpdation(newBook, responseMap);
			if (responseMap.size() != 0) {
				return responseMap;
			} else {
				Entity entity = entityFromBookForUpdate(newBook, bookID);
				Key keyObj = datastore.put(entity);
				Entity responseEntity = datastore.get(keyObj);
				Book responseBookData = new Book();
				responseBookData = bookFromEntity(responseEntity);
				responseMap = mapFromBook(responseBookData, responseMap);
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
	 * <p>
	 * Updates the Image in the GCS.
	 * </p>
	 * 
	 * <p>
	 * Call this Method to Update the Cover Image of the Book. This will update the
	 * coverImage Field in the Datastore Entity with the updated url of the Image.
	 * </p>
	 * 
	 * @param jsonInputString
	 * @param fileInputStream
	 * @param bookID
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> updateBook(InputStream fileInputStream, String imageFormat,
			String bookID) throws EntityNotFoundException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			Book newBook = new Book();
			// Validating the bookID
			Entity entity = new Entity("Books", bookID);
			Entity datastoreEntity = datastore.get(entity.getKey());
			// Cloning a the inputStream to validate
			ByteArrayOutputStream baos = cloneInputStream(fileInputStream);
			fileInputStream = new ByteArrayInputStream(baos.toByteArray());
			responseMap = imageValidator(baos.toByteArray(), imageFormat, responseMap);
			if (responseMap.size() != 0) {
				return responseMap;
			} else {
				// Updating the image in the GCS
				String uploadedFileUrl = BooksControllerUtilities.uploadToCloudStorage(bookID, imageFormat, fileInputStream);
				// Creating a entity from the updated POJO to update in Datastore.
				entity = entityFromBookForUpdate(datastoreEntity, newBook, bookID, uploadedFileUrl);
				Key keyObj = datastore.put(entity);
				Entity responseEntity = datastore.get(keyObj);
				Book responseBookData = new Book();
				responseBookData = bookFromEntity(responseEntity);
				responseMap = mapFromBook(responseBookData, responseMap);
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
	 * 
	 * <p>
	 * Updates Both the Datastore Entity and the Image in the GCS.
	 * </p>
	 * 
	 * <p>
	 * Call this method to update both book's data and coverImage. The coverImage
	 * field in the Entity will be updated with the new URL after updation of the
	 * image in the GCS.
	 * </p>
	 * 
	 * @param jsonInputString
	 * @param fileInputStream
	 * @param bookID
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> updateBook(String jsonInputString, InputStream fileInputStream,
			String imageFormat, String bookID) throws EntityNotFoundException {
		Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().create();
		Book newBook = gson.fromJson(jsonInputString, Book.class);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			// Validating the bookID
			Entity entity = new Entity("Books", bookID);
			Entity datastoreEntity = datastore.get(entity.getKey());
			// Validating the Data
			responseMap = requestBookValidatorForUpdation(newBook, responseMap);

			// Creating a clone of InputStream to Validate
			ByteArrayOutputStream baos = cloneInputStream(fileInputStream);
			fileInputStream = new ByteArrayInputStream(baos.toByteArray());

			responseMap = imageValidator(baos.toByteArray(), imageFormat, responseMap);
			if (responseMap.size() != 0) {
				return responseMap;
			} else {

				// Updating the image in the GCS
				String uploadedFileUrl = BooksControllerUtilities.uploadToCloudStorage(bookID, imageFormat, fileInputStream);
				// Creating a entity from the updated POJO to update in Datastore.
				entity = entityFromBookForUpdate(datastoreEntity, newBook, bookID, uploadedFileUrl);
				Key keyObj = datastore.put(entity);
				Entity responseEntity = datastore.get(keyObj);
				Book responseBookData = new Book();
				responseBookData = bookFromEntity(responseEntity);
				responseMap = mapFromBook(responseBookData, responseMap);
				// responseMap.put("BOOK_ID", keyObj.getName());
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

	/** 4. DELETE Operation **/

	/**
	 * <p>
	 * Deletes the Book Entity from Datastore and does not interact with the GCS.
	 * </p>
	 * 
	 * @param bookID
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> deleteBookWithoutImage(String bookID) throws EntityNotFoundException {
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
	 * <p>
	 * Deletes the Book Entity from Datastore and Deletes the Image in GCS if
	 * present.
	 * </p>
	 * 
	 * @param bookID
	 * @return LinkedHashMap<String, Object>
	 * @throws EntityNotFoundException
	 */
	@SuppressWarnings("unused")
	public static LinkedHashMap<String, Object> deleteBookWithImage(String bookID) throws EntityNotFoundException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key entityKey = KeyFactory.createKey("Books", bookID);
		LinkedHashMap<String, Object> responseMap = new LinkedHashMap<>();
		try {
			datastore.get(entityKey);
			datastore.delete(entityKey);
			boolean deleted = deleteImageInCloudStorage(bookID);
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


	/** Google Cloud Storage Methods **/

	/**
	 * <p>
	 * Uploads a file to Cloud Storage and returns the uploaded file's URL.
	 * </p>
	 * 
	 * @param fileName
	 * @param fileInputStream
	 * @return String
	 */
	public static String uploadToCloudStorage(String fileName, String fileFormat, InputStream fileInputStream) {
		String projectId = "book-management-system-362310";
		String bucketName = "book-management-system-362310.appspot.com";
		String publicUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/" + fileFormat)
				.setCacheControl("no-store")
				.setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER),
						Acl.of(new Acl.Project(Acl.Project.ProjectRole.OWNERS, projectId), Acl.Role.OWNER))))
				.build();
		@SuppressWarnings({ "deprecation", "unused" })
		Blob blob = storage.create(blobInfo, fileInputStream);
		return publicUrl;
	}

	/**
	 * <p>
	 * Uploads a file to Cloud Storage and returns the uploaded file's URL.
	 * </p>
	 * 
	 * @param fileName
	 * @param fileInputStream
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String updateImageInCloudStorage(String fileName, String fileFormat, InputStream fileInputStream) {
		String projectId = "book-management-system-362310";
		String bucketName = "book-management-system-362310.appspot.com";
		String publicUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, fileName);
		Blob blob = storage.get(blobId);
		blob = storage.get(blobId);
		if (blob != null) {
			storage.delete(bucketName, fileName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/" + fileFormat)
					.setCacheControl("no-store")
					.setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER),
							Acl.of(new Acl.Project(Acl.Project.ProjectRole.OWNERS, projectId), Acl.Role.OWNER))))
					.build();
			blob = storage.create(blobInfo, fileInputStream);
			return publicUrl;
		} else {
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/" + fileFormat)
					.setCacheControl("no-store")
					.setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER),
							Acl.of(new Acl.Project(Acl.Project.ProjectRole.OWNERS, projectId), Acl.Role.OWNER))))
					.build();
			blob = storage.create(blobInfo, fileInputStream);
			return publicUrl;
		}
	}

	/**
	 * <p>
	 * Deletes the coverImage of the book when the book is deleted.
	 * </p>
	 * 
	 * @param fileName
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static boolean deleteImageInCloudStorage(String fileName) {
		String projectId = "book-management-system-362310";
		String bucketName = "book-management-system-362310.appspot.com";
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, fileName);
		try {
			Blob blob = storage.get(blobId);
			blob = storage.get(blobId);
			if (blob != null) {
				storage.delete(bucketName, fileName);
				return true;
			} else if (blob == null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Caught in deleteImageInCloudStorage Method");
			return false;
		}
		return false;
	}


}