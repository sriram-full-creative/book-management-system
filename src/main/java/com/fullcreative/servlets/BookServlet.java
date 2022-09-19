package com.fullcreative.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.fullcreative.utilities.ServletUtilities;
import com.google.gson.Gson;

@WebServlet(name = "bookServlet", urlPatterns = { "/books", "/books/*" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 2 /* 2 MB */,
		maxRequestSize = 1024 * 1024 * 5 * 5 /* 5 MB */ )
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
				if (ServletUtilities.hasBookKey(request.getRequestURI())) {
					String bookID = ServletUtilities.getBookKeyFromUri(request);
					responseMap = ServletUtilities.getOneBook(bookID);
					int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
					String responseAsJson = new Gson().toJson(responseMap);
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
					response.setContentType("application/json");
					response.getWriter().println(arrayOfBooks);
					response.setStatus(200);
				}
			} else {
				responseMap = ServletUtilities.invalidRequestEndpoint(responseMap);
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

	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			if (ServletUtilities.hasBookKey(request.getRequestURI()) == false
					&& ServletUtilities.isValidEndPoint(request.getRequestURI())) {
				// Getting json request body
				String jsonRequestString = request.getParameter("jsonBody");
				if (jsonRequestString == null && request.getPart("coverImage") == null) {
					throw new NullPointerException();
				} else if (request.getPart("coverImage") != null) {
					// Get the file chosen by the user
					Part filePart = request.getPart("coverImage");
					String imageFormat = filePart.getContentType().replace("image/", "").trim();
					InputStream fileInputStream = filePart.getInputStream();
					responseMap = ServletUtilities.createNewBook(jsonRequestString, fileInputStream, imageFormat);
				} else {
					responseMap = ServletUtilities.createNewBook(jsonRequestString);
				}
				int statusCode = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				if (responseMap.containsKey("BOOK_ID")) {
					String key = responseMap.remove("BOOK_ID").toString();
				}
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(statusCode);
			} else {
				responseMap = ServletUtilities.invalidRequestEndpoint(responseMap);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			requestErrorMap.put("REQUEST_EMPTY_ERROR", "Request should contain a image or json body");
			String requestError = new Gson().toJson(requestErrorMap);
			response.getWriter().println(requestError);
			response.setStatus(400);
		} catch (ServletException e) {
			e.printStackTrace();
			Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			requestErrorMap.put("EMPTY_REQUEST_ERROR", "Request should contain a image or json body");
			String requestError = new Gson().toJson(requestErrorMap);
			response.getWriter().println(requestError);
			response.setStatus(400);
		}
		catch (Exception e) {
			System.out.println("Caught in doPost servlet service method");
			if (e.getCause().toString().contains("Multipart Mime part coverImage exceeds max filesize")) {
				e.printStackTrace();
				Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
				response.setContentType("application/json");
				requestErrorMap.put("FILE_SIZE_ERROR", "Image should be less than 2 MB");
				String requestError = new Gson().toJson(requestErrorMap);
				response.getWriter().println(requestError);
				response.setStatus(400);
			} else {
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

	@SuppressWarnings("unused")
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			if (ServletUtilities.hasBookKey(request.getRequestURI()) == true
					&& ServletUtilities.isValidEndPoint(request.getRequestURI())) {
				String bookID = ServletUtilities.getBookKeyFromUri(request);
				String jsonRequestString = IOUtils.toString(request.getInputStream());
				responseMap = ServletUtilities.updateBook(jsonRequestString, bookID);
				int statusCode = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				if (responseMap.containsKey("BOOK_ID")) {
					String key = responseMap.remove("BOOK_ID").toString();
				}
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(statusCode);
			} else {
				responseMap = ServletUtilities.invalidRequestEndpoint(responseMap);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			}
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
			if (ServletUtilities.hasBookKey(request.getRequestURI()) == true
					&& ServletUtilities.isValidEndPoint(request.getRequestURI())) {
				String bookID = ServletUtilities.getBookKeyFromUri(request);
				responseMap = ServletUtilities.deleteBook(bookID);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			} else {
				responseMap = ServletUtilities.invalidRequestEndpoint(responseMap);
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