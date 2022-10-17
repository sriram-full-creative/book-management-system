package com.fullcreative.servlet.books.doget;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoGetServiceTestCases {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
		@Before
		public void setUp() throws Exception {
			helper.setUp();
		}
	
		@After
		public void tearDown() throws Exception {
			helper.tearDown();
		}

		/**
		 * Tests for GET Methods
		 * 
		 * @throws EntityNotFoundException
		 */

		private LinkedHashMap<String, Object> createBookInTestEnv(LinkedHashMap<String, Object> ValidTestCaseMap)
				throws EntityNotFoundException {
			String inputString = BooksControllerUtilities.mapToJsonString(ValidTestCaseMap);
			LinkedHashMap<String, Object> createdBook = BooksControllerUtilities.createNewBook(inputString);
			return createdBook;
		}

		private LinkedHashMap<String, Object> stripBookInTestEnv(LinkedHashMap<String, Object> createdBook) {
			LinkedHashMap<String, Object> strippedMap = new LinkedHashMap<>();
			int code = Integer.parseInt(createdBook.remove("STATUS_CODE").toString());
			strippedMap.put("STATUS_CODE", code);
			if (createdBook.containsKey("id")) {
				String key = createdBook.remove("id").toString();
				strippedMap.put("id", key);
			}
			return strippedMap;
		}

		private LinkedHashMap<String, Object> createAndStrip(LinkedHashMap<String, Object> ValidTestCaseMap)
				throws EntityNotFoundException {
			LinkedHashMap<String, Object> createdBook = createBookInTestEnv(ValidTestCaseMap);
			return createdBook;
		}

		/**
		 * Getting all books at a time withoutQueryParameters
		 */
		@Test
		public void getAllBooks_withoutQueryParameters_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_withoutQueryParameters_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks();
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with defaultQueryParameters
		 */
		@Test
		public void getAllBooks_defaultQueryParameters_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_defaultQueryParameters_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("defaultQueryParameters");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with defaultParameters
		 */
		@Test
		public void getAllBooks_CreatedOrUpdatedAscending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_CreatedOrUpdatedAscending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook3String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("CreatedOrUpdatedAscending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with authorDescending
		 */
		@Test
		public void getAllBooks_authorDescending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_authorDescending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook3String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("authorDescending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with authorAscending
		 */
		@Test
		public void getAllBooks_authorAscending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_authorAscending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook2String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("authorAscending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with publicationDescending
		 */
		@Test
		public void getAllBooks_publicationDescending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_publicationDescending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("publicationDescending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with publicationAscending
		 */
		@Test
		public void getAllBooks_publicationAscending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_publicationAscending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook3String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("publicationAscending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with titleDescending
		 */
		@Test
		public void getAllBooks_titleDescending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_titleDescending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("titleDescending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with titleAscending
		 */
		@Test
		public void getAllBooks_titleAscending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_titleAscending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook3String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("titleAscending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with pagesDescending
		 */
		@Test
		public void getAllBooks_pagesDescending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_pagesDescending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook3String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("pagesDescending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with pagesAscending
		 */
		@Test
		public void getAllBooks_pagesAscending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_pagesAscending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("pagesAscending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with releaseYearDescending
		 */
		@Test
		public void getAllBooks_releaseYearDescending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_releaseYearDescending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook1String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("releaseYearDescending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with releaseYearAscending
		 */
		@Test
		public void getAllBooks_releaseYearAscending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_releaseYearAscending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("releaseYearAscending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with ratingDescending
		 */
		@Test
		public void getAllBooks_ratingDescending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_ratingDescending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook1String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook3String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("ratingDescending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with ratingAscending
		 */
		@Test
		public void getAllBooks_ratingAscending_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_ratingAscending_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestData.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestData.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);

			LinkedHashMap<String, String> queryParameters = DoGetServiceTestData.queryParametersTestCases
					.get("ratingAscending");
			System.out.println(queryParameters);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting one book at a time with Valid BookID
		 */
		@Test
		public void getOneBook_positive_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getOneBook_positive_Test()");

			LinkedHashMap<String, Object> validBookMap = DoGetServiceTestData.testCases.get("validBook1");
			// Creating a book using test utility method
			LinkedHashMap<String, Object> createdBook = createBookInTestEnv(validBookMap);
			LinkedHashMap<String, Object> strippedMap = stripBookInTestEnv(createdBook);
			int StatusCode = Integer.parseInt(strippedMap.get("STATUS_CODE").toString());
			String createdBookString = BooksControllerUtilities.mapToJsonString(createdBook);
			// Calling getOneBook
			LinkedHashMap<String, Object> actualBookMap = BooksControllerUtilities
					.getOneBook(strippedMap.get("id").toString());
			actualBookMap.remove("id");
			String actualBookString = BooksControllerUtilities.mapToJsonString(actualBookMap);

			assertEquals(200, StatusCode);
			assertEquals(createdBookString, actualBookString);
		}

		/**
		 * Getting one book at a time with Invalid BookID
		 */

		@Test
		public void getOneBook_negative_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getOneBook_negative_Test()");
			// Calling getOneBook
			LinkedHashMap<String, Object> actualBookMap = BooksControllerUtilities
					.getOneBook(DoGetServiceTestData.bookID.get("INVALID_BOOK_ID"));
			LinkedHashMap<String, Object> expectedResponseMap = DoGetServiceTestData.testCases.get("noBookResponse");
			assertEquals(expectedResponseMap.toString(), actualBookMap.toString());
		}

}

