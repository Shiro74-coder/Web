package conn.controllers;

import conn.Account;
import conn.CategoryService;
import conn.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/category/create")
public class CategoryCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("account");
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login.jsp"); return; }
        req.setAttribute("mode", "create");
        req.getRequestDispatcher("/category_form.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("account");
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login.jsp"); return; }

        String name = req.getParameter("name");
        String note = req.getParameter("note");
        if (service.create(acc.getId(), name, note)) {
            req.getSession().setAttribute("flash_success", "Tạo danh mục thành công!");
            resp.sendRedirect(req.getContextPath()+"/category");
        } else {
            req.setAttribute("alert", "Không thể tạo danh mục. Vui lòng kiểm tra lại!");
            req.setAttribute("mode", "create");
            req.getRequestDispatcher("/category_form.jsp").forward(req, resp);
        }
    }
}
