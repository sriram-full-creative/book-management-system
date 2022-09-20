package com.fullcreative.servlet.dodelete;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.utilities.ServletUtilities;
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
		String bookIDBeforeDelete = strippedMapBeforeDelete.get("BOOK_ID").toString();
		int codeBeforeDelete = Integer.parseInt(strippedMapBeforeDelete.get("STATUS_CODE").toString());

		String createdBookStringBeforeDelete = ServletUtilities.mapToJsonString(createdBookBeforeDelete);

		LinkedHashMap<String, Object> actualBookMapBeforeDelete = ServletUtilities.getOneBook(bookIDBeforeDelete);
		String actualBookStringBeforeDelete = ServletUtilities.mapToJsonString(actualBookMapBeforeDelete);

		assertEquals(200, codeBeforeDelete);
		assertEquals(createdBookStringBeforeDelete, actualBookStringBeforeDelete);

		// Deleting the book with Correct Book ID
		LinkedHashMap<String, Object> expectedResponseMapAfterDelete = DoDeleteServiceTestCases.testCases
				.get("responseAfterSuccessfullDelete");
		// Calling deleteBook
		LinkedHashMap<String, Object> actualResponseMapAfterDelete = ServletUtilities
				.deleteBookWithoutImage(strippedMapBeforeDelete.get("BOOK_ID").toString());
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
		String bookIDBeforeDelete = strippedMapBeforeDelete.get("BOOK_ID").toString();
		int codeBeforeDelete = Integer.parseInt(strippedMapBeforeDelete.get("STATUS_CODE").toString());

		String createdBookStringBeforeDelete = ServletUtilities.mapToJsonString(createdBookBeforeDelete);

		LinkedHashMap<String, Object> actualBookMapBeforeDelete = ServletUtilities.getOneBook(bookIDBeforeDelete);
		String actualBookStringBeforeDelete = ServletUtilities.mapToJsonString(actualBookMapBeforeDelete);

		assertEquals(200, codeBeforeDelete);
		assertEquals(createdBookStringBeforeDelete, actualBookStringBeforeDelete);

		// Deleting the book with Wrong Book ID
		LinkedHashMap<String, Object> expectedResponseMapAfterDelete = DoDeleteServiceTestCases.testCases
				.get("noBookResponse");
		String wrongBookId = DoDeleteServiceTestCases.bookID.get("INVALID_BOOK_ID");
		// Calling deleteBook
		LinkedHashMap<String, Object> actualResponseMapAfterDelete = ServletUtilities
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
