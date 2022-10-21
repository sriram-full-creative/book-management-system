package com.fullcreative.bms.utilities;

import java.util.LinkedHashMap;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class MemcacheUtilities {

	public static MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();

	public static void addBookDataInCache(String bookID, LinkedHashMap<String, Object> bookMap) {
		if (memcache != null && memcache.contains(bookID)) {
			memcache.delete(bookID);
		}
		memcache.put(bookID, bookMap);
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, Object> getBookFromCache(String bookID) {
		LinkedHashMap<String, Object> bookMap = new LinkedHashMap<>();
		if (memcache != null && memcache.contains(bookID)) {
			bookMap = (LinkedHashMap<String, Object>) memcache.get(bookID);
		}
		return bookMap;
	}

	public static boolean hasBookInCache(String bookID) {
		return (memcache != null && memcache.contains(bookID));
	}

	public static void deleteBookDataInCache(String bookID) {
		if (memcache != null && memcache.contains(bookID)) {
			memcache.delete(bookID);
		}
	}
}
