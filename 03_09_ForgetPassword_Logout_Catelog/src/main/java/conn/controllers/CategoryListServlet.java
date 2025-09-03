package conn.controllers;

import conn.Account;
import conn.CategoryService;
import conn.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/category")
public class CategoryListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("account");
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login.jsp"); return; }
        req.setAttribute("categories", service.listByUser(acc.getId()));
        req.getRequestDispatcher("/category_list.jsp").forward(req, resp);
    }
}
