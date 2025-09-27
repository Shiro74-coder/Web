Do file assets quá lớn nên em không bỏ vào project được, mong thầy thông cảm ạ


https://github.com/Shiro74-coder/Web/blob/main/10_09.gif

├── src
│   └── main
│       └── java
│           └── vn
│               └── iostar
│                   ├── configs
│                   │   └── DBConnectSQL.java
│                   ├── controllers
│                   │   ├── admin
│                   │   │   └── HomeController.java
│                   │   ├── manager
│                   │   │   └── HomeController.java
│                   │   ├── user
│                   │   │   ├── CategoryAddController.java
│                   │   │   ├── CategoryDeleteController.java
│                   │   │   ├── CategoryEditController.java
│                   │   │   ├── CategoryListController.java
│                   │   │   ├── HomeController.java
│                   │   │   ├── LoginController.java
│                   │   │   ├── LogoutController.java
│                   │   │   ├── ProfileController.java  
│                   │   │   ├── RegisterController.java
│                   │   │   └── WaitingController.java
│                   │   └── ImageController.java        
│                   ├── dao
│                   │   ├── impl
│                   │   │   ├── CategoryDaoImpl.java
│                   │   │   └── UserDaoImpl.java
│                   │   ├── ICategoryDao.java
│                   │   └── IUserDao.java
│                   ├── models
│                   │   ├── CategoryModel.java
│                   │   └── UserModel.java
│                   ├── services
│                   │   ├── impl
│                   │   │   ├── CategoryServiceImpl.java
│                   │   │   └── UserServiceImpl.java
│                   │   ├── ICategoryService.java
│                   │   └── IUserService.java
│                   └── utils
│                       └── Constant.java
└── webapp
    ├── assets
    │   └── ... (Các file CSS, JS, ảnh của template)
    ├── commons
    │   ├── admin, manager, web
    │   │   └── ... (header.jsp, footer.jsp, ...)
    │   └── taglib.jsp
    ├── views
    │   ├── admin
    │   │   └── home.jsp
    │   ├── manager
    │   │   └── home.jsp
    │   ├── member
    │   │   └── category
    │   │       ├── form.jsp
    │   │       └── list.jsp
    │   └── user
    │       ├── home.jsp
    │       ├── login.jsp
    │       ├── profile.jsp             
    │       └── register.jsp
    │       └── ... (Các file user khác)
    └── WEB-INF
        ├── decorators
        │   ├── admin.jsp
        │   ├── manager.jsp
        │   └── web.jsp
        ├── sitemesh3.xml

        └── web.xml
