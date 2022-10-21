package com.fullcreative.bms.taskqueues;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullcreative.bms.utilities.TaskQueuesUtilities;

/**
 * Servlet implementation class DeleteAllBooksTaskHandler. This is a worker for
 * the Push Queue. It will complete the appropriate tasks added to the Task
 * Queues.
 */
@WebServlet(name = "DeleteAllBooksTaskHandler", urlPatterns = { "/taskqueues/deletebooks/all" })
public class DeleteAllBooksTaskHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("Received the request in DeleteAllBooksTaskHandler");
		try {
			System.err.println("Worker is processing the Request to Delete all the books");
			TaskQueuesUtilities.deleteAllBooksInBatches();
			System.err.println("Work is Done in DeleteAllBooksTaskHandler");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Caught in worker method");
		}

	}

}
