package conn.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession ss = req.getSession(false);
        if (ss != null) ss.invalidate();

        Cookie ck = new Cookie("username", "");
        ck.setMaxAge(0);
        resp.addCookie(ck);

        HttpSession s2 = req.getSession(true);
        s2.setAttribute("flash_success", "Bạn đã đăng xuất.");
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
