package com.fullcreative.servlet.doput;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fullcreative.utilities.ServletUtilities;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class DoPutServiceTest {
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases.get("validBookBeforeUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases.get("validBookAfterUpdate");
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities
				.updateBook(inputStringAfterUpdate, keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeAuthorUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases.get("validBookForAuthorUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterAuthorUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforePublicationUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases
				.get("validBookForPublicationUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterPublicationUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeTitleUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases.get("validBookForTitleUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterTitleUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeLanguageUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases.get("validBookForLanguageUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterLanguageUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforePagesUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases.get("validBookForPagesUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterPagesUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeReleaseYearUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases
				.get("validBookForReleaseYearUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterReleaseYearUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeCountryUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases.get("validBookForCountryUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterCountryUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeCoverImageUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases
				.get("validBookForCoverImageUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterCoverImageUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
		}
		assertEquals(200, code);
		assertEquals(testCaseMapAfterUpdate.toString(), actualBookValueAfterUpdate.toString());

		// Asserting if we updated the same book by checking the returned key
		assertEquals(keyBeforeUpdate, keyAfterUpdate);

	}

	/**
	 * Updating Single Entities - bookLink
	 */

	@Test
	public void updateBook_bookLink_Test() throws EntityNotFoundException {
		System.out.println();
		System.out.println("updateBook_bookLink_Test()");

		// Creating a book with wrong details which are to be updated
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeBookLinkUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases.get("validBookForBookLinkUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterBookLinkUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases
				.get("validBookBeforeRatingUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the Book
		LinkedHashMap<String, Object> testCaseMapForUpdate = DoPutTestCases.testCases.get("validBookForRatingUpdate");
		LinkedHashMap<String, Object> testCaseMapAfterUpdate = DoPutTestCases.testCases
				.get("validBookAfterRatingUpdate");
		String inputStringForrUpdate = ServletUtilities.mapToJsonString(testCaseMapForUpdate);
		String inputStringAfterUpdate = ServletUtilities.mapToJsonString(testCaseMapAfterUpdate);
		System.out.println(inputStringAfterUpdate);

		// Updating the book by passing the bookID received from creating the book.
		LinkedHashMap<String, Object> actualBookValueAfterUpdate = ServletUtilities.updateBook(inputStringForrUpdate,
				keyBeforeUpdate);
		int code = Integer.parseInt(actualBookValueAfterUpdate.remove("STATUS_CODE").toString());
		String keyAfterUpdate = null;
		if (actualBookValueAfterUpdate.containsKey("BOOK_ID")) {
			keyAfterUpdate = actualBookValueAfterUpdate.remove("BOOK_ID").toString();
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
		LinkedHashMap<String, Object> testCaseMapBeforeUpdate = DoPutTestCases.testCases.get("validBookBeforeUpdate");
		String inputStringBeforeUpdate = ServletUtilities.mapToJsonString(testCaseMapBeforeUpdate);
		System.out.println(inputStringBeforeUpdate);
		LinkedHashMap<String, Object> actualBookValueBeforeUpdate = ServletUtilities
				.createNewBook(inputStringBeforeUpdate);
		int codeBeforeUpdate = Integer.parseInt(actualBookValueBeforeUpdate.remove("STATUS_CODE").toString());
		String keyBeforeUpdate = null;
		if (actualBookValueBeforeUpdate.containsKey("BOOK_ID")) {
			keyBeforeUpdate = actualBookValueBeforeUpdate.remove("BOOK_ID").toString();
		}
		// Checking if the book is created with the sent data
		assertEquals(200, codeBeforeUpdate);
		assertEquals(testCaseMapBeforeUpdate.toString(), actualBookValueBeforeUpdate.toString());

		// Updating the book with valid and correct details
		LinkedHashMap<String, Object> validBookMapAfterUpdate = DoPutTestCases.testCases.get("validBookAfterUpdate");
		String inputStringStringForUpdate = ServletUtilities.mapToJsonString(validBookMapAfterUpdate);
		System.out.println(inputStringStringForUpdate);
		// Calling updatBook but with the wrong bookID
		LinkedHashMap<String, Object> actualBookMapAfterUpdate = ServletUtilities.updateBook(inputStringStringForUpdate,
				DoPutTestCases.bookID.get("INVALID_BOOK_ID").toString());
		LinkedHashMap<String, Object> expectedResponseMap = DoPutTestCases.testCases.get("noBookResponse");
		System.out.println(expectedResponseMap);
		String actualBookStringAfterUpdate = actualBookMapAfterUpdate.toString();
		assertEquals(expectedResponseMap.toString(), actualBookStringAfterUpdate);
	}
}
