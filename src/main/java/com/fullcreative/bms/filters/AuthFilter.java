package com.fullcreative.bms.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/Authfilter")
public class AuthFilter implements Filter {
		private ServletContext context;

		public AuthFilter() {
		}

		public void destroy() {
		}

		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			HttpSession session = req.getSession(false);
			System.out.println(req.getContentType());
			if (session == null) {
				this.context.log("Unauthorized access request");
				res.sendRedirect("/");
			} else {
				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				res.setDateHeader("Expires", 0);
				chain.doFilter(request, response);
			}

		}

		public void init(FilterConfig fConfig) throws ServletException {
			this.context = fConfig.getServletContext();
			this.context.log("AuthenticationFilter initialized");
		}

	}