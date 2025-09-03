package conn.controllers;

import conn.Account;
import conn.CategoryService;
import conn.CategoryServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/category/delete")
public class CategoryDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Account acc = (Account) req.getSession().getAttribute("account");
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login.jsp"); return; }

        int id = parseInt(req.getParameter("id"));
        if (service.remove(id, acc.getId())) {
            req.getSession().setAttribute("flash_success", "Xóa danh mục thành công!");
        } else {
            req.getSession().setAttribute("flash_success", "Không thể xóa danh mục (không tồn tại hoặc không thuộc về bạn).");
        }
        resp.sendRedirect(req.getContextPath()+"/category");
    }

    private int parseInt(String s){
        try { return Integer.parseInt(s); } catch(Exception e){ return 0; }
    }
}
