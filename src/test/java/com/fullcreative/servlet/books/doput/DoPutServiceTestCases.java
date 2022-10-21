package com.fullcreative.servlet.books.doput;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoPutServiceTestCases {
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
	 * Tests for PUT Methods
	 * Updating everything at once
	 */
	@Test
	public void updateBookValidBookTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookValidBookTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases.get("validBookBeforeUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataAfterUpdate = "validBookAfterUpdate";
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringAfterUpdate);
	}


	/**
	 * Updating Single Entities - author
	 */
	@Test
	public void updateBookAuthorNameTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookAuthorNameTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeAuthorUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForAuthorUpdate";
		String testDataAfterUpdate = "validBookAfterAuthorUpdate";
		// Updating the Book
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}




	/**
	 * Updating Single Entities - publication
	 */
	@Test
	public void updateBookPublicationTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookPublicationTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforePublicationUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForPublicationUpdate";
		String testDataAfterUpdate = "validBookAfterPublicationUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}

	/**
	 * Updating Single Entities - title
	 */
	@Test
	public void updateBookTitleTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookTitleTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeTitleUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForTitleUpdate";
		String testDataAfterUpdate = "validBookAfterTitleUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}

	/**
	 * Updating Single Entities - language
	 */
	@Test
	public void updateBookLanguageTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookLanguageTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeLanguageUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForLanguageUpdate";
		String testDataAfterUpdate = "validBookAfterLanguageUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}


	/**
	 * Updating Single Entities - pages
	 */
	@Test
	public void updateBookPagesTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookPagesTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforePagesUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForPagesUpdate";
		String testDataAfterUpdate = "validBookAfterPagesUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}

	/**
	 * Updating Single Entities - releaseYear
	 */
	@Test
	public void updateBookReleaseYearTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookReleaseYearTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeReleaseYearUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForReleaseYearUpdate";
		String testDataAfterUpdate = "validBookAfterReleaseYearUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}


	/**
	 * Updating Single Entities - country
	 */
	@Test
	public void updateBookCountryTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookCountryTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeCountryUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForCountryUpdate";
		String testDataAfterUpdate = "validBookAfterCountryUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}

	/**
	 * Updating Single Entities - coverImage
	 */
	@Test
	public void updateBookCoverImageTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookCoverImageTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeCoverImageUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForCoverImageUpdate";
		String testDataAfterUpdate = "validBookAfterCoverImageUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);
		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}


	/**
	 * Updating Single Entities - rating
	 */
	@Test
	public void updateBookRatingTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookRatingTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeRatingUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		String testDataForUpdate = "validBookForRatingUpdate";
		String testDataAfterUpdate = "validBookAfterRatingUpdate";
		String inputStringForUpdate = DoPutServiceTestCaseUtilities.updateBookInDoPutTestCase(testDataForUpdate);
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get(testDataAfterUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		DoPutServiceTestCaseUtilities.assertInDoPutTestCase(keyBeforeUpdate, testCaseMapAfterUpdate,
				inputStringForUpdate);
	}

	/**
	 * Update with Invalid Key
	 **/
	@SuppressWarnings("unused")
	@Test
	public void updateBookInvalidKeyTest() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBookInvalidKeyTest()");
		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases.get("validBookBeforeUpdate");
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = DoPutServiceTestCaseUtilities
				.proccessInputStringBeforeUpdateInTestCase(
				testCaseMapBeforeUpdate);
		String keyBeforeUpdate = DoPutServiceTestCaseUtilities.assertCreatedBookInDoPutTestCase(testCaseMapBeforeUpdate,
				actualBookValueBeforeUpdate);
		// Updating the book with valid and correct details
		LinkedHashMap<String, Object> validBookMapAfterUpdate = DoPutTestData.testCases.get("validBookAfterUpdate");
		String inputStringStringForUpdate = BooksControllerUtilities.mapToJsonString(validBookMapAfterUpdate);
		System.out.println("inputStringForUpdate => " + inputStringStringForUpdate);
		// Calling updatBook but with the wrong bookID
		LinkedHashMap<String, Object> actualBookMapAfterUpdate = BooksControllerUtilities.updateBook(inputStringStringForUpdate,
				DoPutTestData.bookID.get("INVALID_BOOK_ID").toString());
		LinkedHashMap<String, Object> expectedResponseMap = DoPutTestData.testCases.get("noBookResponse");
		System.out.println("expectedResponseMap => " + expectedResponseMap);
		String actualBookStringAfterUpdate = actualBookMapAfterUpdate.toString();
		assertEquals(expectedResponseMap.toString(), actualBookStringAfterUpdate);
	}
}
