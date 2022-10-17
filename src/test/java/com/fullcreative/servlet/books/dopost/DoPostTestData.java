package com.fullcreative.servlet.books.dopost;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author sriram
 *
 */
public class DoPostTestData {
	public static Map<String, LinkedHashMap<String, Object>> errorMessages = new LinkedHashMap<>();
	public static Map<String, LinkedHashMap<String, Object>> testCases = new LinkedHashMap<>();
	static {

		/**
		 * Creating a entity - validBook
		 */

		testCases.put("validBook", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -5252322234633096329L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});

		/**
		 * Creating a entity - authorNameNull
		 */

		testCases.put("authorNameNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 2015954993899946652L;
			{
				put("author", new LinkedList<String>(Arrays.asList("")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});

//	    Response
		errorMessages.put("authorNameNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 827522402094573728L;

			{
				put("AUTHOR_NAME_EMPTY_ERROR", "Author Name should contain atleast 1 character");
			}
		});

		/**
		 * Creating a entity - authorNameWithNumerics
		 */

		testCases.put("authorNameWithNumerics", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 2015954993899946652L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Han5 Chris7ian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("authorNameWithNumerics", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 827522402094573728L;

			{
				put("AUTHOR_NAME_FORMAT_ERROR", "Author Name should contain only alphabets");
			}
		});

		/**
		 * Creating a entity - publicationNameNull
		 */

		testCases.put("publicationNameNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 2015954993899946652L;
			{
				put("author", new LinkedList<String>(Arrays.asList("")));
				put("publication", new LinkedList<String>(Arrays.asList("")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("publicationNameNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 827522402094573728L;

			{
				put("PUBLICATION_NAME_EMPTY_ERROR", "Publication Name should contain atleast 1 character");
			}
		});

		/**
		 * Creating a entity - bookTitleNull
		 */

		testCases.put("bookTitleNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -708603894994379974L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("bookTitleNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -9083862031904971689L;
			{
				put("TITLE_NAME_ERROR", "Title Name should contain atleast 1 character");
			}
		});
		
		
		/**
		 * Creating a entity - languageNull
		 */

		testCases.put("languageNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 5565937547046482270L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("languageNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -1444642404690769773L;
			{
				put("LANGUAGE_EMPTY_ERROR", "Language field can't be empty");
			}
		});

		/**
		 * Creating a entity - languageNameWithNumerics
		 */

		testCases.put("languageNameWithNumerics", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 4037851097674763232L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish322");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("languageNameWithNumerics", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -1444642404690769773L;
			{
				put("LANGUAGE_FORMAT_ERROR", "Language field can contain only alphabets");
			}
		});

		
		/**
		 * Creating a entity - negativePages
		 */

		testCases.put("negativePages", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -6579816598766219199L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", -784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("negativePages", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 2010750117218732102L;
			{
				put("PAGES_ERROR", "Page Count should be Positive");
			}
		});

		/**
		 * Creating a entity - minPageCount
		 */

		testCases.put("minPageCount", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -4069751294272476521L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 19);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("minPageCount", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 3442616792115603121L;
			{
				put("PAGES_ERROR", "Book should have atleast 20 pages");
			}
		});
		
		/**
		 * Creating a entity - negativeYear
		 */

		testCases.put("negativeYear", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -7798148528131996642L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", -1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("negativeYear", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 5977467271497379180L;
			{
				put("YEAR_NEGATIVE_VALUE_ERROR", "Year should be positive");
			}
		});

		/**
		 * Creating a entity - futureYearValue
		 */

		testCases.put("futureYearValue", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 4037851097674763232L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 2023);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("futureYearValue", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -2044372333818686741L;
			{
				put("YEAR_FUTURE_VALUE_ERROR", "Year should be less than or equal to the current year -> '2022'");
			}
		});

		/**
		 * Creating a entity - countryNull
		 */

		testCases.put("countryNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -6403459380201848196L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("countryNull", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 4817806869724154658L;

			{
				put("COUNTRY_FIELD_EMPTY_ERROR", "Country should atleast have 3 characters");
			}
		});

		/**
		 * Creating a entity - countryNameWithNumerics
		 */

		testCases.put("countryNameWithNumerics", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 5565937547046482270L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark453");
				put("coverImage", "");
				put("rating", 5);
			}
		});
//		Response
		errorMessages.put("countryNameWithNumerics", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 3442616792115603121L;
			{
				put("COUNTRY_FIELD_FORMAT_ERROR", "Country field can contain only alphabets");
			}
		});
		
		/**
		 * Creating a entity - ratingsOutOfRange
		 */

		testCases.put("ratingsOutOfRange", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 7718676402864922676L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("rating", 6);
			}
		});
//		Response
		errorMessages.put("ratingsOutOfRange", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -4851103486239685402L;
			{
				put("RATING_RANGE_ERROR", "Rating should be in the range of 0 to 5");
			}
		});
	}
}
