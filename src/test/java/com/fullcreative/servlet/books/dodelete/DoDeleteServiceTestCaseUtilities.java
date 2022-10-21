package com.fullcreative.servlet.books.dodelete;

import java.util.LinkedHashMap;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;

public class DoDeleteServiceTestCaseUtilities {
	/**
	 * @throws EntityNotFoundException
	 */

	public static LinkedHashMap<String, Object> createBookInDoDeleteTestCase(LinkedHashMap<String, Object> ValidTestCaseMap)
			throws EntityNotFoundException {
		String inputString = BooksControllerUtilities.mapToJsonString(ValidTestCaseMap);
		LinkedHashMap<String, Object> createdBook = BooksControllerUtilities.createNewBook(inputString);
		return createdBook;
	}

	/**
	 * @param createdBook
	 * @return
	 */
	public static LinkedHashMap<String, Object> stripBookInDoDeleteTestCase(LinkedHashMap<String, Object> createdBook) {
		LinkedHashMap<String, Object> strippedMap = new LinkedHashMap<>();
		int code = Integer.parseInt(createdBook.remove("STATUS_CODE").toString());
		strippedMap.put("STATUS_CODE", code);
		if (createdBook.containsKey("id")) {
			String key = createdBook.remove("id").toString();
			strippedMap.put("id", key);
		}
		return strippedMap;
	}

}
