package vn.iostar.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserService;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final IUserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String username = trim(req.getParameter("username"));
        String password = trim(req.getParameter("password"));
        String email    = trim(req.getParameter("email"));
        String fullname = trim(req.getParameter("fullname"));
        String phone    = trim(req.getParameter("phone"));

        String alert = null;
        if (isEmpty(username) || isEmpty(password) || isEmpty(email)) {
            alert = "Vui lòng nhập đầy đủ thông tin bắt buộc.";
        } else if (password.length() < 6) {
            alert = "Mật khẩu tối thiểu 6 ký tự.";
        } else if (userService.checkExistUsername(username)) {
            alert = "Tài khoản đã tồn tại!";
        } else if (userService.checkExistEmail(email)) {
            alert = "Email đã tồn tại!";
        } else if (!isEmpty(phone) && userService.checkExistPhone(phone)) {
            alert = "Số điện thoại đã tồn tại!";
        }

        if (alert != null) {
            req.setAttribute("alert", alert);
            req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
            return;
        }

        boolean ok = userService.register(username, password, email, fullname, phone);
        if (ok) resp.sendRedirect(req.getContextPath() + "/login");
        else {
            req.setAttribute("alert", "Đăng ký thất bại. Vui lòng thử lại.");
            req.getRequestDispatcher(Constant.REGISTER).forward(req, resp);
        }
    }

    private static boolean isEmpty(String s) { return s == null || s.trim().isEmpty(); }
    private static String trim(String s) { return s == null ? null : s.trim(); }
}
