package conn.controllers;

import conn.UserService;
import conn.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String retype   = req.getParameter("retype");
        String fullname = req.getParameter("fullname");
        String email    = req.getParameter("email");
        String phone    = req.getParameter("phone");

        if (isBlank(username) || isBlank(password) || isBlank(retype)) {
            req.setAttribute("alert", "Vui lòng nhập các trường bắt buộc (*)");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        if (!password.equals(retype)) {
            req.setAttribute("alert", "Mật khẩu nhập lại không khớp");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistUsername(username)) {
            req.setAttribute("alert", "Tài khoản đã tồn tại");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        if (email != null && !email.isBlank() && service.checkExistEmail(email)) {
            req.setAttribute("alert", "Email đã tồn tại");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        boolean ok = service.register(username, password, fullname, email, phone);
        if (ok) {
            req.getSession().setAttribute("flash_success", "Đăng ký thành công. Mời đăng nhập!");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.setAttribute("alert", "Không thể đăng ký. Vui lòng thử lại!");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }

    private boolean isBlank(String s){ return s == null || s.trim().isEmpty(); }
}
