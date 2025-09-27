package vn.iostar.controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.models.CategoryModel;
import vn.iostar.models.UserModel;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = {"/member/categories"})
public class CategoryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ICategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        UserModel acc = (s != null) ? (UserModel) s.getAttribute("account") : null;
        if (acc == null) { resp.sendRedirect(req.getContextPath()+"/login"); return; }

        String q = req.getParameter("q");
        List<CategoryModel> list = (q == null || q.isBlank())
                ? service.listMine(acc.getId())
                : service.searchMine(acc.getId(), q.trim());
        req.setAttribute("list", list);
        req.getRequestDispatcher("/views/member/category/list.jsp").forward(req, resp);
    }
}
