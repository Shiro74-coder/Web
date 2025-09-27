package vn.iostar.controllers.user;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iostar.models.UserModel;
import vn.iostar.services.IUserService;
import vn.iostar.services.impl.UserService;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = { "/member/profile" })
public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final IUserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession(false);
		UserModel currentUser = (UserModel) session.getAttribute("account");

		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
				currentUser.setFullname(fullname);
		currentUser.setPhone(phone);
		
		try {
			Part filePart = req.getPart("images"); 
			String fileName = filePart.getSubmittedFileName();
			
			if (fileName != null && !fileName.isEmpty()) {
				String uploadPath = Constant.UPLOAD_USER_IMG;
				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs(); 
				}
				filePart.write(uploadPath + File.separator + fileName);
				currentUser.setImages(fileName);
			}
			userService.updateUser(currentUser);
			session.setAttribute("account", currentUser);
			
			req.setAttribute("success", "Cập nhật thông tin thành công!");

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Đã xảy ra lỗi! Vui lòng thử lại.");
		}
		
		req.getRequestDispatcher("/views/user/profile.jsp").forward(req, resp);
	}
}