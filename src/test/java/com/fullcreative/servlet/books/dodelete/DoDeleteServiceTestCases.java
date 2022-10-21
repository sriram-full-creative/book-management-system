package com.fullcreative.servlet.books.dodelete;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoDeleteServiceTestCases {
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
	 * Deleting a book with Valid BookID
	 **/
	@Test
	public void deleteBookValidBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("deleteBookValidBookTest()");

		// Creating a book with valid details
		LinkedHashMap<String, Object> validBookMapBeforeDelete = DoDeleteTestData.testCases.get("validBook");
		// Creating a book using test utility method
		LinkedHashMap<String, Object> createdBookBeforeDelete = DoDeleteServiceTestCaseUtilities
				.createBookInDoDeleteTestCase(validBookMapBeforeDelete);
		LinkedHashMap<String, Object> strippedMapBeforeDelete = DoDeleteServiceTestCaseUtilities
				.stripBookInDoDeleteTestCase(createdBookBeforeDelete);
		String bookIDBeforeDelete = strippedMapBeforeDelete.get("id").toString();
		int codeBeforeDelete = Integer.parseInt(strippedMapBeforeDelete.get("STATUS_CODE").toString());

		String createdBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(createdBookBeforeDelete);

		LinkedHashMap<String, Object> actualBookMapBeforeDelete = BooksControllerUtilities
				.getOneBook(bookIDBeforeDelete);
		actualBookMapBeforeDelete.remove("id");
		String actualBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(actualBookMapBeforeDelete);

		assertEquals(200, codeBeforeDelete);
		assertEquals(createdBookStringBeforeDelete, actualBookStringBeforeDelete);

		// Deleting the book with Correct Book ID
		LinkedHashMap<String, Object> expectedResponseMapAfterDelete = DoDeleteTestData.testCases
				.get("responseAfterSuccessfullDelete");
		// Calling deleteBook
		LinkedHashMap<String, Object> actualResponseMapAfterDelete = BooksControllerUtilities
				.deleteBookWithoutImage(strippedMapBeforeDelete.get("id").toString());
		System.out.println(actualResponseMapAfterDelete);
		System.out.println(expectedResponseMapAfterDelete);
		LinkedHashMap<String, Object> strippedMapAfterDelete = DoDeleteServiceTestCaseUtilities
				.stripBookInDoDeleteTestCase(actualResponseMapAfterDelete);
		int codeAfterDelete = Integer.parseInt(strippedMapAfterDelete.get("STATUS_CODE").toString());

		assertEquals(expectedResponseMapAfterDelete, actualResponseMapAfterDelete);
		assertEquals(200, codeAfterDelete);
	}

	/**
	 * Deleting a book with Invalid BookID
	 **/
	@Test
	public void deleteBookInvalidBookIDTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("deleteBookInvalidBookIDTest()");

		// Creating a book with valid details
		LinkedHashMap<String, Object> validBookMapBeforeDelete = DoDeleteTestData.testCases.get("validBook");
		// Creating a book using test utility method
		LinkedHashMap<String, Object> createdBookBeforeDelete = DoDeleteServiceTestCaseUtilities
				.createBookInDoDeleteTestCase(validBookMapBeforeDelete);
		LinkedHashMap<String, Object> strippedMapBeforeDelete = DoDeleteServiceTestCaseUtilities
				.stripBookInDoDeleteTestCase(createdBookBeforeDelete);
		String bookIDBeforeDelete = strippedMapBeforeDelete.get("id").toString();
		int codeBeforeDelete = Integer.parseInt(strippedMapBeforeDelete.get("STATUS_CODE").toString());

		String createdBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(createdBookBeforeDelete);

		LinkedHashMap<String, Object> actualBookMapBeforeDelete = BooksControllerUtilities
				.getOneBook(bookIDBeforeDelete);
		actualBookMapBeforeDelete.remove("id");
		String actualBookStringBeforeDelete = BooksControllerUtilities.mapToJsonString(actualBookMapBeforeDelete);

		assertEquals(200, codeBeforeDelete);
		assertEquals(createdBookStringBeforeDelete, actualBookStringBeforeDelete);

		// Deleting the book with Wrong Book ID
		LinkedHashMap<String, Object> expectedResponseMapAfterDelete = DoDeleteTestData.testCases
				.get("noBookResponse");
		String wrongBookId = DoDeleteTestData.bookID.get("INVALID_BOOK_ID");
		// Calling deleteBook
		LinkedHashMap<String, Object> actualResponseMapAfterDelete = BooksControllerUtilities
				.deleteBookWithoutImage(wrongBookId);
		System.out.println(actualResponseMapAfterDelete);
		System.out.println(expectedResponseMapAfterDelete);
		LinkedHashMap<String, Object> strippedMapAfterDelete = DoDeleteServiceTestCaseUtilities
				.stripBookInDoDeleteTestCase(actualResponseMapAfterDelete);
		int codeAfterDelete = Integer.parseInt(strippedMapAfterDelete.get("STATUS_CODE").toString());

		assertNotEquals(bookIDBeforeDelete, wrongBookId);
		assertEquals(expectedResponseMapAfterDelete, actualResponseMapAfterDelete);
		assertEquals(404, codeAfterDelete);
	}

}