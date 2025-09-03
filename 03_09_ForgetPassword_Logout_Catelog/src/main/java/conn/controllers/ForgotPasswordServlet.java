package conn.controllers;

import conn.UserService;
import conn.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("forgot_password.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String email    = req.getParameter("email");

        if (username == null || username.isBlank() || email == null || email.isBlank()) {
            req.setAttribute("alert", "Vui lòng nhập đầy đủ Username và Email đã đăng ký!");
            req.getRequestDispatcher("forgot_password.jsp").forward(req, resp);
            return;
        }

        boolean ok = service.verifyUsernameEmail(username, email);
        if (ok) {
            HttpSession ss = req.getSession();
            ss.setAttribute("fp_username", username);
            resp.sendRedirect(req.getContextPath() + "/reset-password");
        } else {
            req.setAttribute("alert", "Không tìm thấy tài khoản khớp Username & Email!");
            req.getRequestDispatcher("forgot_password.jsp").forward(req, resp);
        }
    }
}
