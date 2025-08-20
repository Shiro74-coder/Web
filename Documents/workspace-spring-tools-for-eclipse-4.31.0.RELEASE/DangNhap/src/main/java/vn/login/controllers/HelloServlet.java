package vn.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/html");
	    PrintWriter printWriter = resp.getWriter();
	    String name = "";

	    Cookie[] cookies = req.getCookies();
	    if (cookies != null) {
	        for (Cookie c : cookies) {
	            if ("username".equals(c.getName())) {
	                name = c.getValue();
	                break;
	            }
	        }
	    }

	    if (name.equals("")) {
	        resp.sendRedirect(req.getContextPath() + "/index.html"); 
	        return; 
	    }

	    printWriter.println("Xin chào " + name);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
