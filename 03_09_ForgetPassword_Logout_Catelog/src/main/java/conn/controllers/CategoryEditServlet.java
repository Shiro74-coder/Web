package conn.controllers;

import conn.Account;
import conn.Category;
import conn.CategoryService;
import conn.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/category/edit")
public class CategoryEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("account");
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login.jsp"); return; }

        int id = parseInt(req.getParameter("id"));
        Category c = service.getByIdForUser(id, acc.getId());
        if (c == null) {
            req.getSession().setAttribute("flash_success", "Không tìm thấy danh mục (hoặc không thuộc về bạn).");
            resp.sendRedirect(req.getContextPath()+"/category");
            return;
        }
        req.setAttribute("category", c);
        req.setAttribute("mode", "edit");
        req.getRequestDispatcher("/category_form.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("account");
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login.jsp"); return; }

        int id = parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String note = req.getParameter("note");

        if (service.update(id, acc.getId(), name, note)) {
            req.getSession().setAttribute("flash_success", "Cập nhật danh mục thành công!");
            resp.sendRedirect(req.getContextPath()+"/category");
        } else {
            req.setAttribute("alert", "Không thể cập nhật. Vui lòng kiểm tra lại!");
            req.setAttribute("mode", "edit");
            req.setAttribute("category", service.getByIdForUser(id, acc.getId()));
            req.getRequestDispatcher("/category_form.jsp").forward(req, resp);
        }
    }

    private int parseInt(String s){
        try { return Integer.parseInt(s); } catch(Exception e){ return 0; }
    }
}
