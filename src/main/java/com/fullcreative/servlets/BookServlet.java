package com.fullcreative.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.fullcreative.utilities.ServletUtilities;
import com.google.gson.Gson;

@WebServlet(name = "bookServlet", urlPatterns = { "/books", "/books/*" })
public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = -8271652320356442502L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("appliation/json");
			String requestString = IOUtils.toString(request.getInputStream());
			response.getWriter().println(requestString);
		} catch (Exception e) {
			response.getWriter().print("No Response");
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
				String jsonRequestString = IOUtils.toString(request.getInputStream());
				responseMap = ServletUtilities.createNewBook(jsonRequestString);
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
		}catch(Exception e) {
			System.out.println("Caught in doPost servlet service method");
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
	}

}
