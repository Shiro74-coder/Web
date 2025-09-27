package vn.iostar.controllers.user;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import net.coobird.thumbnailator.Thumbnails;
import vn.iostar.models.CategoryModel;
import vn.iostar.models.UserModel;
import vn.iostar.services.ICategoryService;
import vn.iostar.services.impl.CategoryServiceImpl;
import vn.iostar.utils.Constant; 

@WebServlet(urlPatterns = {"/member/categories/add"})
@MultipartConfig 
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final ICategoryService service = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/member/category/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession s = req.getSession(false);
        var acc = (s != null) ? (UserModel) s.getAttribute("account") : null;
        if (acc == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String name = req.getParameter("name");
        var c = new CategoryModel();
        c.setName(name.trim());
        c.setUserId(acc.getId());

        try {
            Part filePart = req.getPart("icon"); 
            String fileName = filePart.getSubmittedFileName();
            
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = Constant.UPLOAD_CATEGORY_IMG;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String filePath = uploadPath + File.separator + fileName;
                
                InputStream fileContent = filePart.getInputStream();
                Thumbnails.of(fileContent)
                        .size(300, 300) 
                        .toFile(filePath);
                
                c.setIcon(fileName);
            }
            
            service.create(c);
            resp.sendRedirect(req.getContextPath() + "/member/categories");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("alert", "Tạo danh mục thất bại, đã có lỗi xảy ra.");
            req.getRequestDispatcher("/views/member/category/form.jsp").forward(req, resp);
        }
    }
}
