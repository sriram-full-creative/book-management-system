package com.fullcreative.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullcreative.utilities.ServletUtilities;
import com.google.gson.Gson;


@WebServlet(name = "GetBookServlet", urlPatterns = { "/books", "/books/*" })
public class GetBooksHandler extends HttpServlet {
	private static final long serialVersionUID = 1772395423854392481L;
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
			System.out.println("Caught in GetBooksHandler");
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
