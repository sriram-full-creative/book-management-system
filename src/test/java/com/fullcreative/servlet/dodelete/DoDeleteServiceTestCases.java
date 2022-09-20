package com.fullcreative.servlet.dodelete;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoDeleteServiceTestCases {
	public static Map<String, LinkedHashMap<String, Object>> testCases = new LinkedHashMap<>();
	public static Map<String, String> bookID = new LinkedHashMap<>();
	static {

		/**
		 * Deleting a book with Valid BookID
		 **/
		testCases.put("validBook", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = -1309288685350267130L;
			{
				put("author", new LinkedList<String>(Arrays.asList("Hans Christian Andersen")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Fairy tales");
				put("language", "Danish");
				put("pages", 784);
				put("releaseYear", 1836);
				put("country", "Denmark");
				put("coverImage", "");
				put("bookLink", "https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection.");
				put("rating", 5);
			}
		});

//		Response
		testCases.put("responseAfterSuccessfullDelete", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("SUCCESS", "Book was deleted");
			}
		});

		/**
		 * Deleting a book with Invalid BookID
		 **/

		bookID.put("INVALID_BOOK_ID", "08f38688-9de2-4682-b05b-38f40d42c4e8");

//		Response
		testCases.put("noBookResponse", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("ERROR", "Book not Found. Invalid Key");
			}
		});



	}
}

