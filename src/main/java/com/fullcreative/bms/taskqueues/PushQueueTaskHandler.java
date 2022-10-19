package com.fullcreative.bms.taskqueues;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullcreative.bms.utilities.BooksControllerUtilities;

/**
 * Servlet implementation class PushQueueHandler. This is the worker for the
 * Push Queue. It will complete the tasks added to the Task Queues.
 */
@WebServlet(name = "PushQueueHandler", urlPatterns = { "/taskqueues/worker" })
public class PushQueueTaskHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received the request in TaskHandler");
		try {
			System.out.println("Work will be started in 10 seconds");
			Thread.sleep(10000);
			System.out.println("Worker is processing the Request to Delete all the books");
			BooksControllerUtilities.deleteAllBooks();
			System.out.println("Work is Done");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Caught in worker method");
		}

	}

}
