package com.fullcreative.bms.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fullcreative.bms.utilities.BooksControllerUtilities;
import com.google.gson.Gson;

/**
 * @author Sriram
 *
 */
@WebServlet(name = "coverImage", urlPatterns = { "/images/*" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 2 /* 2 MB */, maxRequestSize = 1024 * 1024 * 3 /* 3 MB */)
public class CoverImageController extends HttpServlet {
	private static final long serialVersionUID = 3468208303755887709L;
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> responseMap = new LinkedHashMap<>();
			if (BooksControllerUtilities.hasBookID(request.getRequestURI()) == true
					&& BooksControllerUtilities.isValidEndPoint(request.getRequestURI())) {
				System.out.println(request.getContentType());
				String bookID = BooksControllerUtilities.getBookIDFromUri(request);
				Part filePart = request.getPart("coverImage");

				// Request is empty
				if (filePart == null) {
					throw new NullPointerException();
				}
				// Request has image to be updated
				else if (filePart != null) {
					System.out.println("Request Has Image");
					System.out.println("Detected Type of Image " + filePart.getContentType());
					// Get the file chosen by the user
					String imageFormat = filePart.getContentType().replace("image/", "").trim();
					InputStream fileInputStream = filePart.getInputStream();
					responseMap = BooksControllerUtilities.updateBook(fileInputStream, imageFormat, bookID);
				}
				int statusCode = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(statusCode);
			} else {
				responseMap = BooksControllerUtilities.invalidRequestEndpointResponse(responseMap);
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseAsJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.getWriter().print(responseAsJson);
				response.setStatus(code);
			}
		} catch (NullPointerException e) {
			// Thrown when a file is sent but with a different parameter name Apart From
			// "coverImage".
			e.printStackTrace();
			Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			requestErrorMap.put("EMPTY_REQUEST_ERROR", "Request should contain a image");
			String requestError = new Gson().toJson(requestErrorMap);
			response.getWriter().println(requestError);
			response.setStatus(400);
		} catch (ServletException e) {
			// Thrown when the request is an empty request
			e.printStackTrace();
			Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
			response.setContentType("application/json");
			requestErrorMap.put("EMPTY_REQUEST_ERROR", "Request should contain a image");
			String requestError = new Gson().toJson(requestErrorMap);
			response.getWriter().println(requestError);
			response.setStatus(400);
		} catch (Exception e) {
			// To handle file size exceeding Exception
			System.out.println("Caught in doPut servlet service method");
			if (e.getLocalizedMessage().contains("Multipart Mime part coverImage exceeds max filesize")) {
				e.printStackTrace();
				Map<String, String> requestErrorMap = new LinkedHashMap<String, String>();
				response.setContentType("application/json");
				requestErrorMap.put("FILE_SIZE_ERROR", "Image should be less than 2 MB");
				String requestError = new Gson().toJson(requestErrorMap);
				response.getWriter().println(requestError);
				response.setStatus(413);
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
}
