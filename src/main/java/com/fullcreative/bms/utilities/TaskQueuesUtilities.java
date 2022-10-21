package com.fullcreative.bms.utilities;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Query.SortDirection;

public class TaskQueuesUtilities {
	/**
	 * <p>
	 * Used in TaskQueue to Delete All Books at once.
	 * </p>
	 */
	@Deprecated
	public static void deleteAllBooksInOneOperation() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Books").addSort("CreatedOrUpdated", SortDirection.DESCENDING);
		List<Entity> bookEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for (Entity entity : bookEntities) {
			String bookID = entity.getKey().getName();
			try {
				System.out.println("Deleting the book with the bookID -> " + bookID);
				BooksControllerUtilities.deleteBookWithImage(bookID);
			} catch (EntityNotFoundException e) {
				System.out.println("Caught in the deleteAllBooks Method");
				System.out.println("Caught when Deleting the book -> " + bookID);
				e.printStackTrace();

			}
		}
	}

	/**
	 * <p>
	 * Used in TaskQueue to Delete All Books in batches.
	 * </p>
	 */
	public static void deleteAllBooksInBatches() {
		String startCursor = null;
		boolean deletedAll = false;
		int bookCount = 0;
		int PAGE_SIZE = 50;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		FetchOptions fetchOptions = FetchOptions.Builder.withLimit(PAGE_SIZE);
		Query query = new Query("Books").setKeysOnly();
		do {
			if (startCursor != null) {
				fetchOptions.startCursor(Cursor.fromWebSafeString(startCursor));
			}
			QueryResultList<Entity> bookEntities = datastore.prepare(query).asQueryResultList(fetchOptions);
			startCursor = bookEntities.getCursor().toWebSafeString();
			System.out.println("Size -> " + bookEntities.size());
			for (Entity book : bookEntities) {
				@Nullable
				String bookID = book.getKey().getName();
				System.out.println("Book Count = " + (++bookCount) + " -> " + bookID);
				datastore.delete(book.getKey());
				GoogleCloudStorageUtilities.deleteImageInCloudStorage(bookID);
			}
			if (bookEntities.size() == 0) {
				deletedAll = true;
				System.err.println("Deleted all the " + bookCount + " books.");
			}
		} while (!deletedAll);
	}

	/**
	 * <p>
	 * Used in TaskQueue to Delete Selected Books at once.
	 * </p>
	 * 
	 * @param bookIDs
	 */
	public static void deleteSelectedBooks(ArrayList<String> bookIDs) {
		for (String bookID : bookIDs) {
			System.out.println("Deleting the book with the bookID -> " + bookID);
			try {
				BooksControllerUtilities.deleteBookWithImage(bookID);
			} catch (EntityNotFoundException e) {
				System.out.println("Caught in the deleteSelectedBooks Method");
				System.out.println("Caught when Deleting the book -> " + bookID);
				e.printStackTrace();
			}
		}
	}

}
