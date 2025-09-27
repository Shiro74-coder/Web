package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserService;

@WebServlet(urlPatterns = {"/forgot"})
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = safe(req.getParameter("email"));
        String pass  = safe(req.getParameter("password"));
        String conf  = safe(req.getParameter("confirm"));

        String alert = null;
        if (email.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
            alert = "Vui lòng nhập đủ Email, Mật khẩu mới và Xác nhận.";
        } else if (pass.length() < 6) {
            alert = "Mật khẩu tối thiểu 6 ký tự.";
        } else if (!pass.equals(conf)) {
            alert = "Mật khẩu xác nhận không khớp.";
        }

        if (alert != null) {
            req.setAttribute("alert", alert);
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
            return;
        }

        boolean ok = userService.resetPasswordByEmail(email, pass);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("alert", "Email không tồn tại.");
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
        }
    }

    private static String safe(String s){ return s==null? "": s.trim(); }
}
