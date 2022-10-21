package com.fullcreative.bms.taskqueues;

import java.io.IOException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.gson.Gson;

/**
 * Servlet implementation class PushQueueController. This will add the task to
 * delete all the books in datastore to the Task Queue.
 */
@WebServlet(name = "PushQueueController", urlPatterns = { "/taskqueues/enqueue" })
public class PushQueueController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Received the request in TaskQueue");
		String requestParameter = request.getParameter("delete");
		LinkedHashMap<String, String> responseMap = new LinkedHashMap<>();

		// Add the task to the default queue.
		Queue queue = QueueFactory.getDefaultQueue();
		if (requestParameter.equals("all")) {
			queue.add(TaskOptions.Builder.withUrl("/taskqueues/deletebooks/all"));
		} else if (requestParameter.equals("selected")) {
			String contentType = request.getContentType();
			String jsonString = BooksControllerUtilities.payloadFromRequest(request);
			queue.add(TaskOptions.Builder.withUrl("/taskqueues/deletebooks/selected").header("Content-Type",
					contentType).payload(jsonString));
		}
		System.out.println("Task is Added to the Queue Successfully");
		responseMap.put("Message", "Task is Added and will be completed");
		String responseJson = new Gson().newBuilder().setPrettyPrinting().create().toJson(responseMap,
				LinkedHashMap.class);
		BooksControllerUtilities.sendJsonResponse(response, responseJson);
	}

}
