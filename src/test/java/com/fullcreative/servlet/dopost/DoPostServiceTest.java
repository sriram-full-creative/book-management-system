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
	 * Tests for POST Methods Creating a entity - validBook
	 */
	@SuppressWarnings("unused")
	@Test
	public void createNewBook_validBook_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_validBook_Test()");
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

	/**
	 * Creating a entity - authorNameNull
	 */

	@Test
	public void createNewBook_authorNameNull_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("createNewBook_authorNameNull_Test()");
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("authorNameNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("authorNameNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("bookTitleNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("bookTitleNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("countryNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("countryNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("countryNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("countryNameWithNumerics");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("languageNull");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("languageNull");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("languageNameWithNumerics");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("languageNameWithNumerics");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("negativePages");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("negativePages");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("minPageCount");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("minPageCount");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("negativeYear");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("negativeYear");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
		LinkedHashMap<String, Object> testCaseMap = DoPostTestCases.testCases.get("futureYearValue");
		LinkedHashMap<String, Object> errorMessage = DoPostTestCases.errorMessages.get("futureYearValue");
		String inputString = ServletUtilities.mapToJsonString(testCaseMap);
		System.out.println(inputString);
		LinkedHashMap<String, Object> actualBookValue = ServletUtilities.createNewBook(inputString);
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
