package com.fullcreative.servlet.books.dodelete;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoDeleteServiceTest {
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
	 * Tests for DELETE Methods
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

	/**
	 * Deleting a book with Valid BookID
	 **/
	@Test
	public void deleteBook_validBook_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("deleteBook_validBook_Test()");

		// Creating a book with valid details
		LinkedHashMap<String, Object> validBookMapBeforeDelete = DoDeleteServiceTestCases.testCases.get("validBook");
		// Creating a book using test utility method
		LinkedHashMap<String, Object> createdBookBeforeDelete = createBookInTestEnv(validBookMapBeforeDelete);
		LinkedHashMap<String, Object> strippedMapBeforeDelete = stripBookInTestEnv(createdBookBeforeDelete);
		String bookIDBeforeDelete = strippedMapBeforeDelete.get("id").toString();
		int codeBeforeDelete = Integer.parseInt(strippedMapBeforeDelete.get("STATUS_CODE").toString());

		String createdBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(createdBookBeforeDelete);

		LinkedHashMap<String, Object> actualBookMapBeforeDelete = BooksControllerUtilities.getOneBook(bookIDBeforeDelete);
		actualBookMapBeforeDelete.remove("id");
		String actualBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(actualBookMapBeforeDelete);

		assertEquals(200, codeBeforeDelete);
		assertEquals(createdBookStringBeforeDelete, actualBookStringBeforeDelete);

		// Deleting the book with Correct Book ID
		LinkedHashMap<String, Object> expectedResponseMapAfterDelete = DoDeleteServiceTestCases.testCases
				.get("responseAfterSuccessfullDelete");
		// Calling deleteBook
		LinkedHashMap<String, Object> actualResponseMapAfterDelete = BooksControllerUtilities
				.deleteBookWithoutImage(strippedMapBeforeDelete.get("id").toString());
		System.out.println(actualResponseMapAfterDelete);
		System.out.println(expectedResponseMapAfterDelete);
		LinkedHashMap<String, Object> strippedMapAfterDelete = stripBookInTestEnv(actualResponseMapAfterDelete);
		int codeAfterDelete = Integer.parseInt(strippedMapAfterDelete.get("STATUS_CODE").toString());

		assertEquals(expectedResponseMapAfterDelete, actualResponseMapAfterDelete);
		assertEquals(200, codeAfterDelete);
	}

	/**
	 * Deleting a book with Invalid BookID
	 **/
	@Test
	public void deleteBook_invalidBookID_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("deleteBook_invalidBookID_Test()");

		// Creating a book with valid details
		LinkedHashMap<String, Object> validBookMapBeforeDelete = DoDeleteServiceTestCases.testCases.get("validBook");
		// Creating a book using test utility method
		LinkedHashMap<String, Object> createdBookBeforeDelete = createBookInTestEnv(validBookMapBeforeDelete);
		LinkedHashMap<String, Object> strippedMapBeforeDelete = stripBookInTestEnv(createdBookBeforeDelete);
		String bookIDBeforeDelete = strippedMapBeforeDelete.get("id").toString();
		int codeBeforeDelete = Integer.parseInt(strippedMapBeforeDelete.get("STATUS_CODE").toString());

		String createdBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(createdBookBeforeDelete);

		LinkedHashMap<String, Object> actualBookMapBeforeDelete = BooksControllerUtilities.getOneBook(bookIDBeforeDelete);
		actualBookMapBeforeDelete.remove("id");
		String actualBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(actualBookMapBeforeDelete);

		assertEquals(200, codeBeforeDelete);
		assertEquals(createdBookStringBeforeDelete, actualBookStringBeforeDelete);

		// Deleting the book with Wrong Book ID
		LinkedHashMap<String, Object> expectedResponseMapAfterDelete = DoDeleteServiceTestCases.testCases
				.get("noBookResponse");
		String wrongBookId = DoDeleteServiceTestCases.bookID.get("INVALID_BOOK_ID");
		// Calling deleteBook
		LinkedHashMap<String, Object> actualResponseMapAfterDelete = BooksControllerUtilities
				.deleteBookWithoutImage(wrongBookId);
		System.out.println(actualResponseMapAfterDelete);
		System.out.println(expectedResponseMapAfterDelete);
		LinkedHashMap<String, Object> strippedMapAfterDelete = stripBookInTestEnv(actualResponseMapAfterDelete);
		int codeAfterDelete = Integer.parseInt(strippedMapAfterDelete.get("STATUS_CODE").toString());

		assertNotEquals(bookIDBeforeDelete, wrongBookId);
		assertEquals(expectedResponseMapAfterDelete, actualResponseMapAfterDelete);
		assertEquals(404, codeAfterDelete);
	}

}
