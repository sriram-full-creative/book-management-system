package com.fullcreative.servlet.books.doput;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class DoPutServiceTestCaseUtilities {

	/**
	 * @param testCaseMapBeforeUpdate
	 * @return
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> proccessInputStringBeforeUpdateInTestCase(
			LinkedHashMap<String, Object> testCaseMapBeforeUpdate) throws EntityNotFoundException {
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		return actualBookValueBeforeUpdate;
	}

	/**
	 * @param testCaseMapBeforeUpdate
	 * @param actualBookValueBeforeUpdate
	 * @return
	 * @throws NumberFormatException
	 */
	public static String assertCreatedBookInDoPutTestCase(LinkedHashMap<String, Object> testCaseMapBeforeUpdate,
			LinkedHashMap<String, Object> actualBookValueBeforeUpdate) throws NumberFormatException {
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());
		return keyBeforeUpdate;
	}

	/**
	 * @param testDataForUpdate
	 * @return
	 */
	public static String updateBookInDoPutTestCase(String testDataForUpdate) {
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases.get(testDataForUpdate);
		String inputStringForUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		return inputStringForUpdate;
	}

	/**
	 * @param keyBeforeUpdate
	 * @param testCaseMapAfterUpdate
	 * @param inputStringForrUpdate
	 * @throws EntityNotFoundException
	 * @throws NumberFormatException
	 */
	public static void assertInDoPutTestCase(String keyBeforeUpdate,
			LinkedHashMap<String, Object> testCaseMapAfterUpdate, String inputStringForrUpdate)
			throws EntityNotFoundException, NumberFormatException {
		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities
				.updateBook(inputStringForrUpdate, keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("id")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("id").toString();
		}
		assertEquals(200, code);
		assertEquals(testCaseMapAfterUpdate.toString(), actualBookValueAfterUpdate.toString());

		// Asserting if we updated the same book by checking the returned key
		assertEquals(keyBeforeUpdate, keyAfterUpdate);
	}
}
