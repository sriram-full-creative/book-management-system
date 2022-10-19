package com.fullcreative.bms.taskqueues;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

/**
 * Servlet implementation class PushQueueController. This will add the task to
 * delete all the books in datastore to the Task Queue.
 */
@WebServlet(name = "TaskEnqueue", urlPatterns = { "/taskqueues/enqueue" })
public class PushQueueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received the request in TaskQueue");
		// Add the task to the default queue.
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(TaskOptions.Builder.withUrl("/taskqueues/worker"));
		System.out.println("Task is Added to the Queue Successfully");

		response.getWriter().println("Task is Added and will be completed");
	}

}
