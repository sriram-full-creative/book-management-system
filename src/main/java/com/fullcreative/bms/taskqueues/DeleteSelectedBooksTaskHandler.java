package com.fullcreative.bms.taskqueues;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.fullcreative.bms.utilities.TaskQueuesUtilities;
import com.google.gson.Gson;

/**
 * Servlet implementation class DeleteSelectedBooksTaskHandler. This is a worker
 * for the Push Queue. It will complete the appropriate tasks added to the Task
 * Queues.
 */
@WebServlet(name = "DeleteSelectedBooksTaskHandler", urlPatterns = { "/taskqueues/deletebooks/selected" })
public class DeleteSelectedBooksTaskHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("Received the request in DeleteSelectedBooksTaskHandler");
		try {
			String jsonString = BooksControllerUtilities.payloadFromRequest(request);
			Gson gson = new Gson();
			LinkedHashMap<String, ArrayList<String>> map = gson.fromJson(jsonString, LinkedHashMap.class);
			ArrayList<String> booksToBeDeleted = map.get("books");
			System.err.println("Worker is processing the Request to Delete selected books");
			TaskQueuesUtilities.deleteSelectedBooks(booksToBeDeleted);
			System.err.println("Work is Done DeleteSelectedBooksTaskHandler");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Caught in worker method");
		}

	}

}
