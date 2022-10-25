package com.fullcreative.bms.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullcreative.bms.services.AuthServices;
import com.fullcreative.bms.utilities.UserControllerUtilities;
import com.google.gson.Gson;

import java.util.*;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = -3277605647246155468L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String requestData = UserControllerUtilities.payloadAsStringFromRequestSignup(request);
			Map<String, Object> responseMap = AuthServices.signUp(requestData);
			int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
			String responseAsJson = new Gson().toJson(responseMap);
			response.setContentType("application/json");
			response.setStatus(code);

			if (code == 200) {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}
				HttpSession newSession = request.getSession(true);
				newSession.setAttribute("isLoggedIn", "yes");
				System.out.println(newSession.getAttribute("isLoggedIn"));
				newSession.setMaxInactiveInterval(60 * 60);
			}
			response.getWriter().print(responseAsJson);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			HashMap<String, String> serverError = new HashMap<String, String>();
			serverError.put("ERROR_MESSAGE", "Something went wrong");
			String serverErrorString = new Gson().toJson(serverError);
			response.getWriter().println(serverErrorString);
		}
	}
}