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
	public void updateBook_validBook_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_validBook_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases.get("validBookBeforeUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases.get("validBookAfterUpdate");
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities
				.updateBook(inputStringAfterUpdate, keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - author
	 */

	@Test
	public void updateBook_authorName_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_authorName_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeAuthorUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases.get("validBookForAuthorUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterAuthorUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - publication
	 */

	@Test
	public void updateBook_publication_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_publication_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforePublicationUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases
				.get("validBookForPublicationUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterPublicationUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - title
	 */

	@Test
	public void updateBook_title_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_title_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeTitleUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases.get("validBookForTitleUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterTitleUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - language
	 */

	@Test
	public void updateBook_language_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_language_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeLanguageUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases.get("validBookForLanguageUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterLanguageUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - pages
	 */

	@Test
	public void updateBook_pages_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_pages_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforePagesUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases.get("validBookForPagesUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterPagesUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - releaseYear
	 */

	@Test
	public void updateBook_releaseYear_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_releaseYear_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeReleaseYearUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases
				.get("validBookForReleaseYearUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterReleaseYearUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - country
	 */

	@Test
	public void updateBook_country_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_country_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeCountryUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases.get("validBookForCountryUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterCountryUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Updating Single Entities - coverImage
	 */

	@Test
	public void updateBook_coverImage_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_coverImage_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeCoverImageUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases
				.get("validBookForCoverImageUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterCoverImageUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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


	/**
	 * Updating Single Entities - rating
	 */

	@Test
	public void updateBook_rating_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_rating_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases
				.get("validBookBeforeRatingUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestData.testCases.get("validBookForRatingUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestData.testCases
				.get("validBookAfterRatingUpdate");
		String inputStringForrUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println("inputStringAfterUpdate => " + inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = BooksControllerUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
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

	/**
	 * Update with Invalid Key
	 **/

	@SuppressWarnings("unused")
	@Test
	public void updateBook_invalidKey_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_invalidKey_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestData.testCases.get("validBookBeforeUpdate");
		String inputStringBeforeUpdate = BooksControllerUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println("inputStringBeforeUpdate => " + inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = BooksControllerUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("id")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("id").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

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
