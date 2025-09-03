package conn.controllers;

import conn.UserService;
import conn.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession ss = req.getSession(false);
        if (ss == null || ss.getAttribute("fp_username") == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }
        req.getRequestDispatcher("reset_password.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession ss = req.getSession(false);
        if (ss == null || ss.getAttribute("fp_username") == null) {
            resp.sendRedirect(req.getContextPath() + "/forgot-password");
            return;
        }
        String username = (String) ss.getAttribute("fp_username");

        String pass1 = req.getParameter("password");
        String pass2 = req.getParameter("retype");

        if (pass1 == null || pass1.isBlank() || pass2 == null || pass2.isBlank()) {
            req.setAttribute("alert", "Vui lòng nhập đầy đủ mật khẩu mới!");
            req.getRequestDispatcher("reset_password.jsp").forward(req, resp);
            return;
        }
        if (!pass1.equals(pass2)) {
            req.setAttribute("alert", "Mật khẩu nhập lại không khớp!");
            req.getRequestDispatcher("reset_password.jsp").forward(req, resp);
            return;
        }

        boolean ok = service.changePassword(username, pass1);
        if (ok) {
            ss.removeAttribute("fp_username");
            ss.setAttribute("flash_success", "Đổi mật khẩu thành công. Mời đăng nhập!");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.setAttribute("alert", "Không thể đổi mật khẩu lúc này. Vui lòng thử lại!");
            req.getRequestDispatcher("reset_password.jsp").forward(req, resp);
        }
    }
}
