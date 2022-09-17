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


@WebServlet(name = "UpdateBooksServlet", urlPatterns = { "/books", "/books/*" })
public class UpdateBooksHandler extends HttpServlet {
	private static final long serialVersionUID = 1817773210012912874L;
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
			System.out.println("Caught in UpdateBooksHandler");
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
