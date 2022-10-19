package com.fullcreative.bms.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fullcreative.bms.utilities.UserControllerUtilities;
import com.google.gson.Gson;

@WebServlet(urlPatterns = { "/users", "/users/*" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			if (UserControllerUtilities.isOneUser(request.getRequestURI())) {
				String userID = UserControllerUtilities.getUserKeyFromUri(request);
				Map<String, Object> responseMap = new HashMap<>();
				responseMap = UserControllerUtilities.getOneUser(userID);
				responseMap.remove("password");
				int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
				String responseJson = new Gson().toJson(responseMap);
				response.setContentType("application/json");
				response.setStatus(code);
				response.getWriter().println(responseJson);
			} else {
				List<Map<String, Object>> users = UserControllerUtilities.getAllUsers();
				String responseAsJson = new Gson().toJson(users);
				response.setContentType("application/json");
				response.setStatus(200);
				response.getWriter().print(responseAsJson);
			}
		} catch (Exception e) {
			response.setStatus(500);
			HashMap<String, String> serverError = new HashMap<>();
			serverError.put("errorMessage", "Something went wrong");
			String serverErrorString = new Gson().toJson(serverError);
			response.getWriter().println(serverErrorString);
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String userID = UserControllerUtilities.getUserKeyFromUri(request);
			Map<String, Object> responseMap = new HashMap<>();
			responseMap = UserControllerUtilities.deleteUser(userID);
			int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
			String responseAsJson = new Gson().toJson(responseMap);
			response.setContentType("application/json");
			response.getWriter().print(responseAsJson);
			response.setStatus(code);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			Map<String, String> serverError = new HashMap<String, String>();
			serverError.put("500", "Something went wrong");
			String serverErrorString = new Gson().toJson(serverError);
			response.getWriter().println(serverErrorString);
		}
	}

}