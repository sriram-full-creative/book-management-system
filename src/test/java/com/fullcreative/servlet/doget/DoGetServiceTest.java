package com.fullcreative.servlet.doget;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.utilities.ServletUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoGetServiceTest {
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
			String inputString = ServletUtilities.mapToJsonString(ValidTestCaseMap);
			LinkedHashMap<String, Object> createdBook = ServletUtilities.createNewBook(inputString);
			return createdBook;
		}

		private LinkedHashMap<String, Object> stripBookInTestEnv(LinkedHashMap<String, Object> createdBook) {
			LinkedHashMap<String, Object> strippedMap = new LinkedHashMap<>();
			int code = Integer.parseInt(createdBook.remove("STATUS_CODE").toString());
			strippedMap.put("STATUS_CODE", code);
			if (createdBook.containsKey("BOOK_ID")) {
				String key = createdBook.remove("BOOK_ID").toString();
				strippedMap.put("BOOK_ID", key);
			}
			return strippedMap;
		}

		private LinkedHashMap<String, Object> createAndStrip(LinkedHashMap<String, Object> ValidTestCaseMap)
				throws EntityNotFoundException {
			LinkedHashMap<String, Object> createdBook = createBookInTestEnv(ValidTestCaseMap);
			return createdBook;
		}

		@Test
		public void getAllBooks_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getAllBooks_Test()");
			LinkedHashMap<String, Object> validBook1Map = DoGetServiceTestCases.testCases.get("validBook1");
			LinkedHashMap<String, Object> createdBook1 = createAndStrip(validBook1Map);
			String createdBook1String = ServletUtilities.mapToJsonString(createdBook1);
			LinkedHashMap<String, Object> validBook2Map = DoGetServiceTestCases.testCases.get("validBook2");
			LinkedHashMap<String, Object> createdBook2 = createAndStrip(validBook2Map);
			String createdBook2String = ServletUtilities.mapToJsonString(createdBook2);
			LinkedHashMap<String, Object> validBook3Map = DoGetServiceTestCases.testCases.get("validBook3");
			LinkedHashMap<String, Object> createdBook3 = createAndStrip(validBook3Map);
			String createdBook3String = ServletUtilities.mapToJsonString(createdBook3);
			// Construction the expected array
			LinkedList<String> expectedArrayOfBooks = new LinkedList<>();
			expectedArrayOfBooks.add(createdBook3String);
			expectedArrayOfBooks.add(createdBook2String);
			expectedArrayOfBooks.add(createdBook1String);
			// Calling getAllBooks
			LinkedList<String> actualArrayOfBooks = ServletUtilities.getAllBooks();
			assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
		}

		@Test
		public void getOneBook_positive_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getOneBook_positive_Test()");

			LinkedHashMap<String, Object> validBookMap = DoGetServiceTestCases.testCases.get("validBook1");
			// Creating a book using test utility method
			LinkedHashMap<String, Object> createdBook = createBookInTestEnv(validBookMap);
			LinkedHashMap<String, Object> strippedMap = stripBookInTestEnv(createdBook);
			int StatusCode = Integer.parseInt(strippedMap.get("STATUS_CODE").toString());
			String createdBookString = ServletUtilities.mapToJsonString(createdBook);
			// Calling getOneBook
			LinkedHashMap<String, Object> actualBookMap = ServletUtilities
					.getOneBook(strippedMap.get("BOOK_ID").toString());
			String actualBookString = ServletUtilities.mapToJsonString(actualBookMap);

			assertEquals(200, StatusCode);
			assertEquals(createdBookString, actualBookString);
		}

		@Test
		public void getOneBook_negative_Test() throws EntityNotFoundException {
			System.out.println();
			System.out.println("getOneBook_negative_Test()");
			// Calling getOneBook
			LinkedHashMap<String, Object> actualBookMap = ServletUtilities
					.getOneBook(DoGetServiceTestCases.bookID.get("INVALID_BOOK_ID"));
			LinkedHashMap<String, Object> expectedResponseMap = DoGetServiceTestCases.testCases.get("noBookResponse");
			assertEquals(expectedResponseMap.toString(), actualBookMap.toString());
		}

}

