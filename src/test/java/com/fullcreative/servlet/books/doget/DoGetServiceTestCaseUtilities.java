package com.fullcreative.servlet.books.doget;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class DoGetServiceTestCaseUtilities {
	/**
	 * Utility Methods for using in the test cases
	 */

	/**
	 * @param ValidTestCaseMap
	 * @return
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> createBookInDoGetTestCase(LinkedHashMap<String, Object> ValidTestCaseMap)
			throws EntityNotFoundException {
		String inputString = BooksControllerUtilities.mapToJsonString(ValidTestCaseMap);
		LinkedHashMap<String, Object> createdBook = BooksControllerUtilities.createNewBook(inputString);
		return createdBook;
	}

	/**
	 * @param createdBook
	 * @return
	 */
	public static LinkedHashMap<String, Object> stripBookInDoGetTestCase(LinkedHashMap<String, Object> createdBook) {
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
	 * @param ValidTestCaseMap
	 * @return
	 * @throws EntityNotFoundException
	 */
	public static LinkedHashMap<String, Object> createAndStripInDoGetTestCase(LinkedHashMap<String, Object> ValidTestCaseMap)
			throws EntityNotFoundException {
		LinkedHashMap<String, Object> createdBook = createBookInDoGetTestCase(ValidTestCaseMap);
		return createdBook;
	}

	/**
	 * @return
	 * @throws EntityNotFoundException
	 */
	public static String addThirdBookInDoGetTestCase() throws EntityNotFoundException {
		LinkedHashMap<String, Object> validBook3Map = DoGetTestData.testCases.get("validBook3");
		LinkedHashMap<String, Object> createdBook3 = createAndStripInDoGetTestCase(validBook3Map);
		String createdBook3String = BooksControllerUtilities.mapToJsonString(createdBook3);
		return createdBook3String;
	}

	/**
	 * @return
	 * @throws EntityNotFoundException
	 */
	public static String addSecondBookInDoGetTestCase() throws EntityNotFoundException {
		LinkedHashMap<String, Object> validBook2Map = DoGetTestData.testCases.get("validBook2");
		LinkedHashMap<String, Object> createdBook2 = createAndStripInDoGetTestCase(validBook2Map);
		String createdBook2String = BooksControllerUtilities.mapToJsonString(createdBook2);
		return createdBook2String;
	}

	/**
	 * @return
	 * @throws EntityNotFoundException
	 */
	public static String addFirstBookInDoGetTestCase() throws EntityNotFoundException {
		LinkedHashMap<String, Object> validBook1Map = DoGetTestData.testCases.get("validBook1");
		LinkedHashMap<String, Object> createdBook1 = createAndStripInDoGetTestCase(validBook1Map);
		String createdBook1String = BooksControllerUtilities.mapToJsonString(createdBook1);
		return createdBook1String;
	}

	/**
	 * @param createdBook1String
	 * @param createdBook2String
	 * @param createdBook3String
	 * @param expectedArrayOfBooks
	 */
	public static void addBooksToArrayInDoGetTestCase(String createdBook1String, String createdBook2String,
			String createdBook3String, LinkedList<String> expectedArrayOfBooks) {
		expectedArrayOfBooks.add(createdBook3String);
		expectedArrayOfBooks.add(createdBook2String);
		expectedArrayOfBooks.add(createdBook1String);
	}

	/**
	 * @param expectedArrayOfBooks
	 * @param queryParameters
	 */
	@SuppressWarnings("deprecation")
	public static void assertInDoGetTestCase(LinkedList<String> expectedArrayOfBooks,
			LinkedHashMap<String, String> queryParameters) {
		System.out.println(queryParameters);
		LinkedList<String> actualArrayOfBooks = BooksControllerUtilities.getAllBooks(queryParameters);
		assertEquals(expectedArrayOfBooks, actualArrayOfBooks);
	}

}
