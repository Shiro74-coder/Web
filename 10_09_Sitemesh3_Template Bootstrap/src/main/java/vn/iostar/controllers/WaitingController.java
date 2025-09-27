package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.UserModel;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	        throws ServletException, IOException {
		HttpSession session= req.getSession();
		if(session != null && session.getAttribute("account") != null) {
			UserModel u=(UserModel) session.getAttribute("account");
			req.setAttribute("username", u.getUsername());
			if(u.getRoleid()==1) {
				resp.sendRedirect(req.getContextPath()+"/");
				return;
			}
			else if(u.getRoleid()==2) {
				resp.sendRedirect(req.getContextPath()+"/admin/home");
				return;
			}
			else {
				resp.sendRedirect(req.getContextPath()+"/manager/home");
				return;
			}
		}
		else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}
	}
}
