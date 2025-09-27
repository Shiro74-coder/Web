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

@WebServlet(urlPatterns = {"/reset"})
public class ResetPasswordController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private final IUserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String token = req.getParameter("token");
    if (token == null || token.isBlank()) {
      resp.sendRedirect(req.getContextPath() + "/forgot");
      return;
    }
    req.setAttribute("token", token);
    req.getRequestDispatcher(Constant.RESET).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String token = req.getParameter("token");
    String pass  = req.getParameter("password");
    String conf  = req.getParameter("confirm");

    if (token == null || token.isBlank()) {
      resp.sendRedirect(req.getContextPath() + "/forgot");
      return;
    }
    if (pass == null || pass.length() < 6 || !pass.equals(conf)) {
      req.setAttribute("alert", "Mật khẩu tối thiểu 6 ký tự và phải khớp xác nhận.");
      req.setAttribute("token", token);
      req.getRequestDispatcher(Constant.RESET).forward(req, resp);
      return;
    }

    boolean ok = userService.resetPasswordByEmail(token, pass);
    if (ok) resp.sendRedirect(req.getContextPath() + "/login");
    else {
      req.setAttribute("alert", "Token không hợp lệ hoặc đã hết hạn.");
      req.setAttribute("token", token);
      req.getRequestDispatcher(Constant.RESET).forward(req, resp);
    }
  }
}