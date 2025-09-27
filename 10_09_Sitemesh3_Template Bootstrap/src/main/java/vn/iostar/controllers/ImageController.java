package vn.iostar.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.utils.Constant;

@WebServlet(urlPatterns = "/uploads/*")
public class ImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); 
            return;
        }

        String filePath = Constant.UPLOAD_BASE_PATH + pathInfo;
        File file = new File(filePath);

        if (file.exists() && !file.isDirectory()) {
            String contentType = getServletContext().getMimeType(file.getName());
            if (contentType == null) {
                contentType = "application/octet-stream"; 
            }

            resp.setContentType(contentType);
            resp.setContentLength((int) file.length());
            Files.copy(file.toPath(), resp.getOutputStream());
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}