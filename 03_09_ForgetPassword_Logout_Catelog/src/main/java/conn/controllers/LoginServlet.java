package conn.controllers;

import conn.Account;
import conn.UserService;
import conn.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final UserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath() + "/waiting");
			return;
		}
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if ("username".equals(c.getName())) {
					Account acc = service.get(c.getValue());
					if (acc != null) {
						session = req.getSession(true);
						session.setAttribute("account", acc);
						resp.sendRedirect(req.getContextPath() + "/waiting");
						return;
					}
				}
			}
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
	    String username = req.getParameter("username");
	    String password = req.getParameter("password");
	    String remember = req.getParameter("remember");

	    if (username == null || username.isBlank() || password == null || password.isBlank()) {
	        req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin");
	        req.getRequestDispatcher("login.jsp").forward(req, resp);
	        return;
	    }

	    Account acc = service.login(username, password);
	    if (acc != null) {
	        HttpSession session = req.getSession();
	        session.setAttribute("account", acc);

	        if (remember != null) {
	            Cookie ck = new Cookie("username", username);
	            ck.setMaxAge(30*60);
	            resp.addCookie(ck);
	        }
	        session.setAttribute("flash_success", "Đăng nhập thành công!");
	        resp.sendRedirect(req.getContextPath() + "/waiting");
	        
	    } else {
	        req.setAttribute("alert", "Sai tài khoản hoặc mật khẩu!");
	        req.getRequestDispatcher("login.jsp").forward(req, resp);
	    }
	}
}
