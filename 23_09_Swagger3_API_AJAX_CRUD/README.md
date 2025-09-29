Dưới đây là cấu trúc thư mục sau khi tích hợp Swagger 3 + REST API + AJAX CRUD (Category & Product).
Mình ký hiệu: [N] = mới, [M] = đã chỉnh sửa, [=] = giữ nguyên.

23_09_Thymeleaf/
├── pom.xml                                       [M]
├── src
│   ├── main
│   │   ├── java
│   │   │   └── vn
│   │   │       └── iostar
│   │   │           ├── Application.java          [M]  (enable @EnableConfigurationProperties + init storage)
│   │   │           ├── config
│   │   │           │   ├── StorageProperties.java              [N]
│   │   │           │   └── WebStaticConfig.java                [N]  (/uploads/** → file:uploads/)
│   │   │           ├── controller
│   │   │           │   ├── CategoryController.java             [M]  (+ mapping /ajax)
│   │   │           │   ├── ProductController.java              [N]  (trang AJAX)
│   │   │           │   └── api
│   │   │           │       ├── CategoryApiController.java      [N]  (/api/categories ...)
│   │   │           │       └── ProductApiController.java       [N]  (/api/products ...)
│   │   │           ├── entity
│   │   │           │   ├── Category.java                       [M]  (+ field icon, tùy chọn)
│   │   │           │   └── Product.java                        [N]
│   │   │           ├── exception
│   │   │           │   ├── StorageException.java               [N]
│   │   │           │   └── StorageFileNotFoundException.java   [N]
│   │   │           ├── model
│   │   │           │   └── ApiResponse.java                    [N]
│   │   │           ├── repository
│   │   │           │   ├── CategoryRepository.java             [M]  (+ findByName)
│   │   │           │   └── ProductRepository.java              [N]
│   │   │           └── service
│   │   │               ├── CategoryService.java                [M]  (+ findByName)
│   │   │               ├── CategoryServiceImpl.java            [M]
│   │   │               ├── IStorageService.java                [N]
│   │   │               ├── FileSystemStorageServiceImpl.java   [N]
│   │   │               ├── ProductService.java                 [N]
│   │   │               └── ProductServiceImpl.java             [N]
│   │   └── resources
│   │       ├── application.properties              [M]  (datasource, springdoc, multipart, storage.location=uploads)
│   │       ├── static/                             [=]
│   │       └── templates
│   │           └── admin
│   │               ├── layout.html                 [M]  (+ jQuery cho AJAX)
│   │               ├── fragments
│   │               │   ├── header.html            [=]
│   │               │   └── footer.html            [=]
│   │               ├── categories
│   │               │   ├── list.html              [=]
│   │               │   ├── addOrEdit.html         [=]
│   │               │   └── ajax.html              [N]  (AJAX CRUD Category)
│   │               └── products
│   │                   └── ajax.html              [N]  (AJAX CRUD Product)
│   └── test
│       └── java
│           └── vn
│               └── iostar
│                   └── ApplicationTests.java      [=]