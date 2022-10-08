package com.fullcreative.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullcreative.pojo.Book;
import com.fullcreative.utilities.ServletUtilities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Sriram
 *
 */
@WebServlet(name = "bookServlet", urlPatterns = { "/books", "/books/*" })
public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = -8271652320356442502L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			Map<String, String> queryParameters = null;
			if (request.getParameterMap() != null) {
				queryParameters = ServletUtilities.processQueryParameters(request.getParameterMap());
			}
			if (ServletUtilities.isValidEndPoint(request.getRequestURI())) {
				if (ServletUtilities.hasBookID(request.getRequestURI())) {
					String bookID = ServletUtilities.getBookIDFromUri(request);
					responseMap = ServletUtilities.getOneBook(bookID);
					int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
					String responseAsJson = new GsonBuilder().setPrettyPrinting().create().toJson(responseMap);
					response.setContentType("application/json");
					response.getWriter().println(responseAsJson);
					response.setStatus(code);
				} else {
					LinkedList<String> arrayOfBooks = null;
					if (queryParameters != null) {
						arrayOfBooks = ServletUtilities.getAllBooks(queryParameters);
					} else {
						arrayOfBooks = ServletUtilities.getAllBooks();
					}
					Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
					ListIterator<String> iterator = arrayOfBooks.listIterator();
					while (iterator.hasNext()) {
						Book book = gson.fromJson(iterator.next(), Book.class);
						iterator.set(gson.toJson(book));
					}
					response.setContentType("application/json");
					response.getWriter().println(arrayOfBooks);
					response.setStatus(200);
				}
			} else {
				responseMap = ServletUtilities.invalidRequestEndpointResponse(responseMap);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			}
		} catch (Exception e) {
			System.out.println("Caught in doGet servlet service method");
			e.printStackTrace();
			Map<String, String> internalServerErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			internalServerErrorMap.put("500", "Something went wrong");
			String internalServerError = new Gson().toJson(internalServerErrorMap);
			response.getWriter().println(internalServerError);
			response.setStatus(500);
		}
	}

//	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			if (ServletUtilities.hasBookID(request.getRequestURI()) == false
					&& ServletUtilities.isValidEndPoint(request.getRequestURI())) {

				// Getting JSON request body and image Part
				String jsonRequestString = ServletUtilities.payloadFromRequest(request);

				// Request is empty
				if (jsonRequestString.length() == 0
						|| jsonRequestString.substring(1).replaceAll("}", "").length() == 0) {
					throw new NullPointerException();
				}
				// Request has only book details to be updated
				else if (jsonRequestString != null) {
					System.out.println("Request Has JSON Body");
					System.out.println("Request JSON Body: " + jsonRequestString);
					responseMap = ServletUtilities.createNewBook(jsonRequestString);
				}
				int statusCode = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(statusCode);
			} else {
				responseMap = ServletUtilities.invalidRequestEndpointResponse(responseMap);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			}
		} catch (Exception e) {
			// Thrown when a file is sent but with a different parameter name.
			e.printStackTrace();
			Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			requestErrorMap.put("EMPTY_REQUEST_ERROR", "Request should contain json body");
			String requestError = new Gson().toJson(requestErrorMap);
			response.getWriter().println(requestError);
			response.setStatus(400);
		}
	}

//	@SuppressWarnings("unused")
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			if (ServletUtilities.hasBookID(request.getRequestURI()) == true
					&& ServletUtilities.isValidEndPoint(request.getRequestURI())) {
				String bookID = ServletUtilities.getBookIDFromUri(request);

				// Getting JSON request body
				String jsonRequestString = ServletUtilities.payloadFromRequest(request);

				// Request is empty
				if (jsonRequestString.length() == 0
						|| jsonRequestString.substring(1).replaceAll("}", "").length() == 0) {
					throw new NullPointerException();
				}
				// Request has only book details to be updated
				else {
					System.out.println("Request Has JSON Body");
					System.out.println("Request JSON Body: " + jsonRequestString);
					responseMap = ServletUtilities.updateBook(jsonRequestString, bookID);
				}
				int statusCode = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(statusCode);
			} else {
				responseMap = ServletUtilities.invalidRequestEndpointResponse(responseMap);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			}
		} catch (NullPointerException e) {
			// Thrown when a file is sent but with a different parameter name.
			e.printStackTrace();
			Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			requestErrorMap.put("EMPTY_REQUEST_ERROR", "Request should contain json body");
			String requestError = new Gson().toJson(requestErrorMap);
			response.getWriter().println(requestError);
			response.setStatus(400);
		} catch (Exception e) {
			System.out.println("Caught in doPut servlet service method");
			e.printStackTrace();
			Map<String, String> internalServerErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			internalServerErrorMap.put("500", "Something went wrong");
			String internalServerError = new Gson().toJson(internalServerErrorMap);
			response.getWriter().println(internalServerError);
			response.setStatus(500);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			if (ServletUtilities.hasBookID(request.getRequestURI()) == true
					&& ServletUtilities.isValidEndPoint(request.getRequestURI())) {
				String bookID = ServletUtilities.getBookIDFromUri(request);
				responseMap = ServletUtilities.deleteBookWithImage(bookID);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			} else {
				responseMap = ServletUtilities.invalidRequestEndpointResponse(responseMap);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			}
		} catch (Exception e) {
			System.out.println("Caught in doDelete servlet service method");
			e.printStackTrace();
			Map<String, String> internalServerErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			internalServerErrorMap.put("500", "Something went wrong");
			String internalServerError = new Gson().toJson(internalServerErrorMap);
			response.getWriter().println(internalServerError);
			response.setStatus(500);
		}
	}
}