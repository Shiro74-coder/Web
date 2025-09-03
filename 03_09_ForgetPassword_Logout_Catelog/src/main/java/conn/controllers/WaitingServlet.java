package conn.controllers;

import conn.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/waiting")
public class WaitingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            Account u = (Account) session.getAttribute("account");
            String role = u.getRole();
            if ("ADMIN".equalsIgnoreCase(role)) {
                resp.sendRedirect(req.getContextPath() + "/admin/home.jsp");
            } else if ("MANAGER".equalsIgnoreCase(role)) {
                resp.sendRedirect(req.getContextPath() + "/manager/home.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
