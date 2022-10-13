package com.fullcreative.servlet.doget;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoGetServiceTestData {
	public static Map<String, LinkedHashMap<String, Object>> testCases = new LinkedHashMap<>();
	public static Map<String, String> bookID = new LinkedHashMap<>();
	public static Map<String, LinkedHashMap<String, String>> queryParametersTestCases = new LinkedHashMap<>();

	static {

		/**
		 * Valid Book Data
		 */
		testCases.put("validBook1", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -1309288685350267130L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("DK Children")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "images/fairy-tales.jpg");
				put("rating", 5);
			}
		});

		testCases.put("validBook2", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 59050706136019371L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Jorge Luis Borges")));
				put("publication", new LinkedList<String>(Arrays.asList("Grove Press")));
				put("title", "Ficciones");
				put("language", "Spanish");
				put("pages", 224);
				put("releaseYear", 1965);
				put("country", "Argentina");
				put("coverImage", "images/ficciones.jpg");
				put("rating", 4);
			}
		});

		testCases.put("validBook3", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 8760762188995028866L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Chinua Achebe")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Things Fall Apart");
				put("language", "English");
				put("pages", 209);
				put("releaseYear", 1958);
				put("country", "Nigeria");
				put("coverImage", "images/things-fall-apart.jpg");
				put("rating", 3);
			}
		});

//		Response
		testCases.put("noBookResponse", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 9207485526400493380L;
			{
				put("ERROR", "Book not Found. Invalid Key");
				put("STATUS_CODE", 404);
			}
		});
		/**
		 * Invalid BookID
		 */
		bookID.put("INVALID_BOOK_ID", "08f38688-9de2-4682-b05b-38f40d42c4e8");

		/**
		 * QueryParameters TestCase - defaultQueryParameters
		 */
		queryParametersTestCases.put("defaultQueryParameters", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 6536984567841340822L;
			{
				put("sortOnProperty", "CreatedOrUpdated");
				put("sortDirection", "descending");

			}
		});

		/**
		 * QueryParameters TestCase - CreatedOrUpdatedAscending
		 */
		queryParametersTestCases.put("CreatedOrUpdatedAscending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -596059740387669375L;
			{
				put("sortOnProperty", "CreatedOrUpdated");
				put("sortDirection", "ascending");

			}
		});

		/**
		 * QueryParameters TestCase - authorDescending
		 */
		queryParametersTestCases.put("authorDescending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -8937481955260763658L;
			{
				put("sortOnProperty", "Author");
				put("sortDirection", "descending");

			}
		});

		/**
		 * QueryParameters TestCase - authorAscending
		 */
		queryParametersTestCases.put("authorAscending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 4242620605568066980L;
			{
				put("sortOnProperty", "Author");
				put("sortDirection", "ascending");

			}
		});

		/**
		 * QueryParameters TestCase - publicationDescending
		 */
		queryParametersTestCases.put("publicationDescending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -5704073798416141888L;
			{
				put("sortOnProperty", "Publication");
				put("sortDirection", "descending");

			}
		});

		/**
		 * QueryParameters TestCase - publicationAscending
		 */
		queryParametersTestCases.put("publicationAscending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -5786254560099755340L;
			{
				put("sortOnProperty", "Publication");
				put("sortDirection", "ascending");

			}
		});

		/**
		 * QueryParameters TestCase - titleDescending
		 */
		queryParametersTestCases.put("titleDescending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 3843577554745486501L;
			{
				put("sortOnProperty", "Title");
				put("sortDirection", "descending");

			}
		});

		/**
		 * QueryParameters TestCase - titleAscending
		 */
		queryParametersTestCases.put("titleAscending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -4552869471843418784L;
			{
				put("sortOnProperty", "Title");
				put("sortDirection", "ascending");

			}
		});

		/**
		 * QueryParameters TestCase - pagesDescending
		 */
		queryParametersTestCases.put("pagesDescending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 6146759644561296031L;
			{
				put("sortOnProperty", "Pages");
				put("sortDirection", "descending");

			}
		});

		/**
		 * QueryParameters TestCase - pagesAscending
		 */
		queryParametersTestCases.put("pagesAscending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 7748309373203802652L;
			{
				put("sortOnProperty", "Pages");
				put("sortDirection", "ascending");

			}
		});

		/**
		 * QueryParameters TestCase - releaseYearDescending
		 */
		queryParametersTestCases.put("releaseYearDescending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -2846489305466937896L;
			{
				put("sortOnProperty", "ReleaseYear");
				put("sortDirection", "descending");

			}
		});

		/**
		 * QueryParameters TestCase - releaseYearDescending
		 */
		queryParametersTestCases.put("releaseYearAscending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -5060768782038684221L;
			{
				put("sortOnProperty", "ReleaseYear");
				put("sortDirection", "ascending");

			}
		});

		/**
		 * QueryParameters TestCase - ratingDescending
		 */
		queryParametersTestCases.put("ratingDescending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 7927962917766366748L;
			{
				put("sortOnProperty", "Rating");
				put("sortDirection", "descending");

			}
		});

		/**
		 * QueryParameters TestCase - ratingAscending
		 */
		queryParametersTestCases.put("ratingAscending", new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = -5094567778028686391L;
			{
				put("sortOnProperty", "Rating");
				put("sortDirection", "ascending");

			}
		});

	}
}
