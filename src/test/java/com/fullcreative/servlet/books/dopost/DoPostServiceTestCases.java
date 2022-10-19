package com.fullcreative.servlet.books.dopost;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoPostServiceTestCases {
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
	 * Tests for POST Methods Creating a entity - validBook
	 */
	@SuppressWarnings("unused")
	@Test
	public void createNewBook_validBook_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_validBook_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("validBook");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		if (actualBookValue.containsKey("id")) {
			String key = actualBookValue.remove("id").toString();
		}
		assertEquals(200, code);
		assertEquals(testCaseMap.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - authorNameNull
	 */

	@Test
	public void createNewBook_authorNameNull_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_authorNameNull_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("authorNameNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("authorNameNull");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - bookTitleNull
	 */

	@Test
	public void createNewBook_bookTitleNull_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_bookTitleNull_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("bookTitleNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("bookTitleNull");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - countryNull
	 */

	@Test
	public void createNewBook_countryNull_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_countryNull_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("countryNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("countryNull");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - countryNameWithNumerics
	 */

	@Test
	public void createNewBook_countryNameWithNumerics_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_countryNameWithNumerics_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("countryNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("countryNameWithNumerics");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - languageNull
	 */

	@Test
	public void createNewBook_languageNull_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_languageNull_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("languageNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("languageNull");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - languageNameWithNumerics
	 */

	@Test
	public void createNewBook_languageNameWithNumerics_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_languageNameWithNumerics_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("languageNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("languageNameWithNumerics");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - negativePages
	 */

	@Test
	public void createNewBook_negativePages_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_negativePages_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("negativePages");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("negativePages");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - minPageCount
	 */

	@Test
	public void createNewBook_minPageCount_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_minPageCount_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("minPageCount");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("minPageCount");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - negativeYear
	 */

	@Test
	public void createNewBook_negativeYear_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_negativeYear_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("negativeYear");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("negativeYear");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - futureYearValue
	 */

	@Test
	public void createNewBook_futureYearValue_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_futureYearValue_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("futureYearValue");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("futureYearValue");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	/**
	 * Creating a entity - ratingsOutOfRange
	 */

	@Test
	public void createNewBook_ratingsOutOfRange_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_ratingsOutOfRange_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("ratingsOutOfRange");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("ratingsOutOfRange");
		String inputString = BooksControllerUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = BooksControllerUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}
}
