package com.fullcreative.servlet.books.doget;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
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
		 * Getting all books at a time withoutQueryParameters
		 */
		@SuppressWarnings("deprecation")
		@Test
		public void getAllBooksWithoutQueryParametersTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksWithoutQueryParametersTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook1String, createdBook2String,
					createdBook3String, expectedArrayOfBooks);
			LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks();
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		/**
		 * Getting all books at a time with defaultQueryParameters
		 */
		@Test
		public void getAllBooksDefaultQueryParametersTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksDefaultQueryParametersTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook1String, createdBook2String,
					createdBook3String, expectedArrayOfBooks);
			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("defaultQueryParameters");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with defaultParameters
		 */
		@Test
		public void getAllBooksCreatedOrUpdatedAscendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksCreatedOrUpdatedAscendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook3String, createdBook2String,
					createdBook1String, expectedArrayOfBooks);
			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("CreatedOrUpdatedAscending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with authorDescending
		 */
		@Test
		public void getAllBooksAuthorDescendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksAuthorDescendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook3String, createdBook1String,
					createdBook2String, expectedArrayOfBooks);
			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("authorDescending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with authorAscending
		 */
		@Test
		public void getAllBooksAuthorAscendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksAuthorAscendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook2String, createdBook1String,
					createdBook3String, expectedArrayOfBooks);
			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("authorAscending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with publicationDescending
		 */
		@Test
		public void getAllBooksPublicationDescendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksPublicationDescendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook1String, createdBook2String,
					createdBook3String, expectedArrayOfBooks);
			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("publicationDescending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with publicationAscending
		 */
		@Test
		public void getAllBooksPublicationAscendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksPublicationAscendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook3String, createdBook2String,
					createdBook1String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("publicationAscending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with titleDescending
		 */
		@Test
		public void getAllBooksTitleDescendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksTitleDescendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook1String, createdBook2String,
					createdBook3String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("titleDescending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with titleAscending
		 */
		@Test
		public void getAllBooksTitleAscendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksTitleAscendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook3String, createdBook2String,
					createdBook1String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("titleAscending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with pagesDescending
		 */
		@Test
		public void getAllBooksPagesDescendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksPagesDescendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook3String, createdBook2String,
					createdBook1String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("pagesDescending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with pagesAscending
		 */
		@Test
		public void getAllBooksPagesAscendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksPagesAscendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook1String, createdBook2String,
					createdBook3String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("pagesAscending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with releaseYearDescending
		 */
		@Test
		public void getAllBooksReleaseYearDescendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksReleaseYearDescendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook1String, createdBook3String,
					createdBook2String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("releaseYearDescending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with releaseYearAscending
		 */
		@Test
		public void getAllBooksReleaseYearAscendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksReleaseYearAscendingTest()");
			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook2String, createdBook3String,
					createdBook1String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("releaseYearAscending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting all books at a time with ratingDescending
		 */
		@Test
		public void getAllBooksRatingDescendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksRatingDescendingTest()");

			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();

			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook3String, createdBook2String,
					createdBook1String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("ratingDescending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}


		/**
		 * Getting all books at a time with ratingAscending
		 */
		@Test
		public void getAllBooksRatingAscendingTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooksRatingAscendingTest()");

			String createdBook1String = DoGetServiceTestCaseUtilities.addFirstBookInDoGetTestCase();
			String createdBook2String = DoGetServiceTestCaseUtilities.addSecondBookInDoGetTestCase();
			String createdBook3String = DoGetServiceTestCaseUtilities.addThirdBookInDoGetTestCase();
			
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			DoGetServiceTestCaseUtilities.addBooksToArrayInDoGetTestCase(createdBook1String, createdBook2String,
					createdBook3String, expectedArrayOfBooks);

			LinkedHashMap<String, String> queryParameters = DoGetTestData.queryParametersTestCases
					.get("ratingAscending");
			DoGetServiceTestCaseUtilities.assertInDoGetTestCase(expectedArrayOfBooks, queryParameters);
		}

		/**
		 * Getting one book at a time with Valid BookID
		 */
		@Test
		public void getOneBookPositiveTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getOneBookPositiveTest()");

			LinkedHashMap<String, Object> validBookMap = DoGetTestData.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook = DoGetServiceTestCaseUtilities.createBookInDoGetTestCase(validBookMap);
			LinkedHashMap<String, Object> strippedMap = DoGetServiceTestCaseUtilities.stripBookInDoGetTestCase(createdBook);

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
		public void getOneBookNegativeTest() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getOneBookNegativeTest()");
			// Calling getOneBook
			LinkedHashMap<String, Object> actualBookMap = BooksControllerUtilities
					.getOneBook(DoGetTestData.bookID.get("INVALID_BOOK_ID"));
			LinkedHashMap<String, Object> expectedResponseMap = DoGetTestData.testCases.get("noBookResponse");
			assertEquals(expectedResponseMap.toString(), actualBookMap.toString());
		}

}

