package com.fullcreative.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fullcreative.services.AuthServices;
import com.fullcreative.utilities.UserControllerUtilities;
import com.google.gson.Gson;


@WebServlet("/users/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = -8779402624862072307L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String requestData = UserControllerUtilities.payloadAsStringFromRequestLogin(request);
			Map<String, Object> responseMap = AuthServices.Login(requestData);
			int code = Integer.parseInt(responseMap.remove("STATUS_CODE").toString());
			response.setContentType("application/json");
			String respopnseAsJson = new Gson().toJson(responseMap);
			response.setStatus(code);
			if (code == 200) {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) {
					oldSession.invalidate();
				}
				HttpSession newSession = request.getSession(true);
				newSession.setMaxInactiveInterval(5 * 60);
			}
			response.setContentType("application/json");
			response.getWriter().print(respopnseAsJson);
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