package com.fullcreative.servlet.doput;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoPutTestData {
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
			private static final long serialVersionUID = 5679090312364365804L;
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
			private static final long serialVersionUID = -776743216754059067L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
			}
		});

		testCases.put("validBookAfterAuthorUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 3029890783866742519L;
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
			private static final long serialVersionUID = 206185531369326483L;
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
			private static final long serialVersionUID = 4960445749418421066L;
			{
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
			}
		});

		testCases.put("validBookAfterPublicationUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -6108902716754375858L;
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
			private static final long serialVersionUID = 752942164317377056L;
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
			private static final long serialVersionUID = -5306184250117786443L;
			{
				put("title", "Fairy tales");
			}
		});

		testCases.put("validBookAfterTitleUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -8584403810188073259L;
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
			private static final long serialVersionUID = 6561811474056803783L;
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
			private static final long serialVersionUID = -4171488075200440381L;
			{
				put("language", "Danish");
			}
		});

		testCases.put("validBookAfterLanguageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -2253581048800913663L;
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
			private static final long serialVersionUID = 2526886113702960249L;
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
			private static final long serialVersionUID = -3180031410294629032L;
			{
				put("pages", 784);
			}
		});

		testCases.put("validBookAfterPagesUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6632736591348846528L;
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
			private static final long serialVersionUID = -5864070888590989915L;
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
			private static final long serialVersionUID = 4339692922802913346L;
			{
				put("releaseYear", 1836);
			}
		});

		testCases.put("validBookAfterReleaseYearUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 6565542561543312121L;
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
			private static final long serialVersionUID = 4769374069576048892L;
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
			private static final long serialVersionUID = 4873370667043669416L;
			{
				put("country", "Denmark");
			}
		});

		testCases.put("validBookAfterCountryUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -5041324650786907062L;
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
			private static final long serialVersionUID = -1560592701209776431L;
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
			private static final long serialVersionUID = 7066637835267940026L;
			{
				put("coverImage", "images/fairy-tales.jpg");
			}
		});

		testCases.put("validBookAfterCoverImageUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -4677504331527716721L;
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

		/**
		 * Single Entity Update - bookLink
		 **/

		testCases.put("validBookBeforeBookLinkUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -4640211074354211863L;
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
			private static final long serialVersionUID = 4805357052056985110L;
			{
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
			}
		});

		testCases.put("validBookAfterBookLinkUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -1303561933035673150L;
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
			private static final long serialVersionUID = 6859105862230692189L;
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
			private static final long serialVersionUID = -421005329263326640L;
			{
				put("rating", 5);
			}
		});

		testCases.put("validBookAfterRatingUpdate", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 4126253379598052727L;
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
			private static final long serialVersionUID = -2146734025347030111L;
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
