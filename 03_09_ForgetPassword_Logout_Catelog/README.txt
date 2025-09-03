src/
 └─ main/
     ├─ java/
     │   ├─ conn/
     │   │   ├─ database.java
     │   │   ├─ Account.java
     │   │   ├─ AccountDAO.java
     │   │   ├─ UserService.java
     │   │   ├─ UserServiceImpl.java
     │   │   ├─ Category.java             <-- model cho Category
     │   │   ├─ CategoryDAO.java
     │   │   ├─ CategoryService.java
     │   │   └─ CategoryServiceImpl.java
     │   │
     │   └─ controllers/
     │       ├─ LoginServlet.java
     │       ├─ WaitingServlet.java
     │       ├─ LogoutServlet.java
     │       ├─ RegisterServlet.java
     │       ├─ ForgotPasswordServlet.java    <-- quên mật khẩu (GET: form)
     │       ├─ ResetPasswordServlet.java     <-- đặt lại mật khẩu (POST)
     │       ├─ CategoryListServlet.java
     │       ├─ CategoryCreateServlet.java
     │       ├─ CategoryEditServlet.java
     │       └─ CategoryDeleteServlet.java
     │
     └─ webapp/
         ├─ META-INF/
         ├─ WEB-INF/
         ├─ home.jsp                (trang chính sau đăng nhập)
         ├─ login.jsp               (trang đăng nhập)
         ├─ register.jsp            (form đăng ký)
         ├─ topbar.jsp              (menu login/logout/register/link tới Category)
         ├─ forgot_password.jsp      (form nhập username + email)
         ├─ reset_password.jsp       (form nhập mật khẩu mới)
         ├─ category_list.jsp        (danh sách category)
         └─ category_form.jsp        (form thêm/sửa category)

