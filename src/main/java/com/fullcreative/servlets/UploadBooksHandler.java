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


@WebServlet(name = "UploadBookServlet", urlPatterns = { "/books", "/books/*" })
public class UploadBooksHandler extends HttpServlet {
	private static final long serialVersionUID = -6226793176699038455L;
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
			System.out.println("Caught in UploadBooksHandler");
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
