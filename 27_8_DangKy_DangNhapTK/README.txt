src/
 └─ main/
     ├─ java/
     │   ├─ conn/
     │   │   ├─ database.java
     │   │   ├─ Account.java
     │   │   ├─ AccountDAO.java
     │   │   ├─ UserService.java
     │   │   └─ UserServiceImpl.java
     │   │
     │   └─ controllers/
     │       ├─ LoginServlet.java
     │       ├─ WaitingServlet.java
     │       ├─ LogoutServlet.java
     │       └─ RegisterServlet.java     <-- thêm cho chức năng đăng ký
     │
     └─ webapp/
         ├─ META-INF/
         ├─ WEB-INF/
         ├─ home.jsp
         ├─ login.jsp          (trang đăng nhập chính)
         ├─ register.jsp       (form đăng ký mới)
         └─ topbar.jsp         (menu, login/logout/register link)
