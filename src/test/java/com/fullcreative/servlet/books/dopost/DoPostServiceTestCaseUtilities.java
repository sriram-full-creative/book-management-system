package com.fullcreative.servlet.books.dopost;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class DoPostServiceTestCaseUtilities {
	/**
	 * @param testCaseMap
	 * @param errorMessage
	 * @throws EntityNotFoundException
	 * @throws NumberFormatException
	 */
	public static void assertInDoPostTestCase(LinkedHashMap<String, Object> testCaseMap, LinkedHashMap<String, Object> errorMessage)
			throws EntityNotFoundException, NumberFormatException {
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}
}
