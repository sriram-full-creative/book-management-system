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
	public void createNewBookValidBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookValidBookTest()");
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
	public void createNewBookAuthorNameNullTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookAuthorNameNullTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("authorNameNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("authorNameNull");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - bookTitleNull
	 */
	@Test
	public void createNewBookBookTitleNullTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookBookTitleNullTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("bookTitleNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("bookTitleNull");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - countryNull
	 */
	@Test
	public void createNewBookCountryNullTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookCountryNullTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("countryNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("countryNull");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - countryNameWithNumerics
	 */
	@Test
	public void createNewBookCountryNameWithNumericsTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookCountryNameWithNumericsTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("countryNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("countryNameWithNumerics");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - languageNull
	 */
	@Test
	public void createNewBookLanguageNullTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookLanguageNullTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("languageNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("languageNull");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - languageNameWithNumerics
	 */
	@Test
	public void createNewBookLanguageNameWithNumericsTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookLanguageNameWithNumericsTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("languageNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("languageNameWithNumerics");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - negativePages
	 */
	@Test
	public void createNewBookNegativePagesTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookNegativePagesTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("negativePages");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("negativePages");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - minPageCount
	 */
	@Test
	public void createNewBookMinPageCountTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookMinPageCountTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("minPageCount");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("minPageCount");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - negativeYear
	 */
	@Test
	public void createNewBookNegativeYearTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookNegativeYearTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("negativeYear");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("negativeYear");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - futureYearValue
	 */
	@Test
	public void createNewBookFutureYearValueTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookFutureYearValueTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("futureYearValue");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("futureYearValue");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

	/**
	 * Creating a entity - ratingsOutOfRange
	 */
	@Test
	public void createNewBookRatingsOutOfRangeTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBookRatingsOutOfRangeTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestData.testCases.get("ratingsOutOfRange");
		LinkedHashMap<String, Object> errorMessage = DoPostTestData.errorMessages.get("ratingsOutOfRange");
		DoPostServiceTestCaseUtilities.assertInDoPostTestCase(testCaseMap, errorMessage);
	}

}
