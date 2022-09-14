package com.fullcreative.servlet.doput;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.utilities.ServletUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoPutServiceTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}


	@Test
	public void updateBook_PositiveTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_PositiveTest()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases.get("validBookBeforeUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases.get("validBookAfterUpdate");
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities
				.updateBook(inputStringAfterUpdate, keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
		}
		assertEquals(200, code);
		assertEquals(testCaseMapAfterUpdate.toString(), actualBookValueAfterUpdate.toString());

		// Asserting if we updated the same book by checking the returned key
		assertEquals(keyBeforeUpdate, keyAfterUpdate);

	}

	@SuppressWarnings("unused")
	@Test
	public void updateBook_NegativeTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_NegativeTest()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases.get("validBookBeforeUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the book with valid and correct details
		LinkedHashMap<String, Object> validBookMapAfterUpdate = DoPutTestCases.testCases.get("validBookAfterUpdate");
		String inputStringStringForUpdate = ServletUtilities.mapToJsonString(validBookMapAfterUpdate);
		System.out.println(inputStringStringForUpdate);
		// Calling updatBook but with the wrong bookID
		LinkedHashMap<String, Object> actualBookMapAfterUpdate = ServletUtilities.updateBook(inputStringStringForUpdate,
				DoPutTestCases.bookID.get("INVALID_BOOK_ID").toString());
		LinkedHashMap<String, Object> expectedResponseMap = DoPutTestCases.testCases.get("noBookResponse");
		System.out.println(expectedResponseMap);
		String actualBookStringAfterUpdate = actualBookMapAfterUpdate.toString();
		assertEquals(expectedResponseMap.toString(), actualBookStringAfterUpdate);
	}


}
