package com.fullcreative.servlet.dopost;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.utilities.ServletUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoPostServiceTest {
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
	 * Tests for POST Methods
	 */
	@SuppressWarnings("unused")
	@Test
	public void validBook_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("validBook_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("validBook");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		if (actualBookValue.containsKey("BOOK_ID")) {
			String key = actualBookValue.remove("BOOK_ID").toString();
		}
		assertEquals(200, code);
		assertEquals(testCaseMap.toString(), actualBookValue.toString());
	}

	@Test
	public void authorNameNull_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("authorNameNull_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("authorNameNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("authorNameNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void bookTitleNull_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("bookTitleNull_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("bookTitleNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("bookTitleNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void countryNull_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("countryNull_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("countryNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("countryNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void countryNameWithNumerics_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("countryNameWithNumerics_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("countryNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("countryNameWithNumerics");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void languageNull_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("languageNull_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("languageNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("languageNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void languageNameWithNumerics_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("languageNameWithNumerics_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("languageNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("languageNameWithNumerics");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void negativePages_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("negativePages_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("negativePages");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("negativePages");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void minPageCount_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("minPageCount_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("minPageCount");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("minPageCount");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void negativeYear_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("negativeYear_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("negativeYear");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("negativeYear");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void futureYearValue_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("futureYearValue_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("futureYearValue");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("futureYearValue");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}

	@Test
	public void ratingsOutOfRange_createNewBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("ratingsOutOfRange_createNewBookTest()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("ratingsOutOfRange");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("ratingsOutOfRange");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
		int code = Integer.parseInt(actualBookValue.remove("STATUS_CODE").toString());
		assertEquals(400, code);
		assertEquals(errorMessage.toString(), actualBookValue.toString());
	}
}
