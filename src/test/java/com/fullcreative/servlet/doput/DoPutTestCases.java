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
		
		testCases.put("noBookResponse", new LinkedHashMap<String, Object>() {
			private static final long serialVersionUID = 5679090312364365804L;
			{
				put("ERROR", "Book not Found. Invalid Key");
				put("STATUS_CODE", 404);
			}
		});

		bookID.put("INVALID_BOOK_ID", "08f38688-9de2-4682-b05b-38f40d42c4e8");
	
	}
}
