package com.fullcreative.servlet.doget;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class DoGetServiceTestCases {
	public static Map<String, LinkedHashMap<String, Object>> testCases = new LinkedHashMap<>();
	public static Map<String, String> bookID = new LinkedHashMap<>();
	static {

		testCases.put("validBook1", new LinkedHashMap<String, Object>() {

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

		testCases.put("validBook2", new LinkedHashMap<String, Object>() {

			{
				put("author", new LinkedList<String>(Arrays.asList("Jorge Luis Borges")));
				put("publication", new LinkedList<String>(Arrays.asList("Grove Press")));
				put("title", "Ficciones");
				put("language", "Spanish");
				put("pages", 224);
				put("releaseYear", 1965);
				put("country", "Argentina");
				put("coverImage", "images/ficciones.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Ficciones");
				put("rating", 4);
			}
		});

		testCases.put("validBook3", new LinkedHashMap<String, Object>() {

			{
				put("author", new LinkedList<String>(Arrays.asList("Chinua Achebe")));
				put("publication", new LinkedList<String>(Arrays.asList("Penguin Publishing Group")));
				put("title", "Things Fall Apart");
				put("language", "English");
				put("pages", 209);
				put("releaseYear", 1958);
				put("country", "Nigeria");
				put("coverImage", "images/things-fall-apart.jpg");
				put("bookLink", "https://en.wikipedia.org/wiki/Things_Fall_Apart");
				put("rating", 3);
			}
		});

		testCases.put("noBookResponse", new LinkedHashMap<String, Object>() {
			{
				put("ERROR", "Book not Found. Invalid Key");
				put("STATUS_CODE", 404);
			}
		});

		bookID.put("INVALID_BOOK_ID", "08f38688-9de2-4682-b05b-38f40d42c4e8");

	}
}
