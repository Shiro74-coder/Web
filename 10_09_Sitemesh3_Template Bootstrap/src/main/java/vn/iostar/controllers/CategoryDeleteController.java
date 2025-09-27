package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/member/categories/delete"})
public class CategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final CategoryServiceImpl service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var acc = (vn.iostar.models.UserModel) req.getSession().getAttribute("account");
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }
        int id = Integer.parseInt(req.getParameter("id"));
        service.delete(id, acc.getId());
        resp.sendRedirect(req.getContextPath()+"/member/categories");
    }
}
