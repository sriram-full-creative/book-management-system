package com.fullcreative.servlet.doput;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoPutTestCases {
	public static Map<String, LinkedHashMap<String, Object>> errorMessages = new LinkedHashMap<>();
	public static Map<String, LinkedHashMap<String, Object>> testCases = new LinkedHashMap<>();
	public static Map<String, String> bookID = new LinkedHashMap<>();
	static {
		/**
		 * Updating everything at once
		 **/
		testCases.put("validBookBeforeUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairytales");
				put("language", "Danish");
				put("pages", 84);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookAfterUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});
		
		/**
		 * Single Entity Update - author
		 **/

		testCases.put("validBookBeforeAuthorUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForAuthorUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
			}
		});

		testCases.put("validBookAfterAuthorUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - publication
		 **/

		testCases.put("validBookBeforePublicationUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForPublicationUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
			}
		});

		testCases.put("validBookAfterPublicationUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - title
		 **/


		testCases.put("validBookBeforeTitleUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForTitleUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("title", "Fairy tales");
			}
		});

		testCases.put("validBookAfterTitleUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - language
		 **/

		testCases.put("validBookBeforeLanguageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Dan");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForLanguageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("language", "Danish");
			}
		});

		testCases.put("validBookAfterLanguageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - pages
		 **/

		testCases.put("validBookBeforePagesUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 78);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForPagesUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("pages", 784);
			}
		});

		testCases.put("validBookAfterPagesUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});


		/**
		 * Single Entity Update - releaseYear
		 **/

		testCases.put("validBookBeforeReleaseYearUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1839);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForReleaseYearUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("releaseYear", 1836);
			}
		});

		testCases.put("validBookAfterReleaseYearUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - country
		 **/

		testCases.put("validBookBeforeCountryUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Den");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForCountryUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("country", "Denmark");
			}
		});

		testCases.put("validBookAfterCountryUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - coverImage
		 **/

		testCases.put("validBookBeforeCoverImageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		testCases.put("validBookForCoverImageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("coverImage", "images/fairy-tales.jpg");
			}
		});

		testCases.put("validBookAfterCoverImageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - bookLink
		 **/

		testCases.put("validBookBeforeBookLinkUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/");
				put("rating", 5);
			}
		});

		testCases.put("validBookForBookLinkUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
			}
		});

		testCases.put("validBookAfterBookLinkUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

		/**
		 * Single Entity Update - rating
		 **/

		testCases.put("validBookBeforeRatingUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6944496079699775748L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 0);
			}
		});

		testCases.put("validBookForRatingUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("rating", 5);
			}
		});

		testCases.put("validBookAfterRatingUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -398718960311225742L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});


		/**
		 * Update with Invalid Key
		 **/

		testCases.put("noBookResponse", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 5679090312364365804L;
			{
				put("ERROR", "Book not Found. Invalid Key");
				put("STATUS_CODE", 404);
			}
		});

		/**
		 * Invalid Key
		 **/

		bookID.put("INVALID_BOOK_ID", "08f38688-9de2-4682-b05b-38f40d42c4e8");
	}
}
