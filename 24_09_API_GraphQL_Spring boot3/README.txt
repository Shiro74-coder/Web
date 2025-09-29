graphql-demo/
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── vn
│   │   │       └── iostar
│   │   │           ├── Application.java                 // @SpringBootApplication
│   │   │           ├── entity
│   │   │           │   ├── User.java
│   │   │           │   ├── Category.java
│   │   │           │   └── Product.java
│   │   │           ├── repository
│   │   │           │   ├── UserRepository.java
│   │   │           │   ├── CategoryRepository.java
│   │   │           │   └── ProductRepository.java
│   │   │           ├── graphql
│   │   │           │   └── GraphQLApi.java              // Query/Mutation resolver
│   │   │           └── config
│   │   │               └── WebConfig.java               //ViewResolver cho JSP
│   │   ├── resources
│   │   │   ├── application.properties                   // cấu hình DB, GraphiQL
│   │   │   ├── graphql
│   │   │   │   └── schema.graphqls                      
│   │   │   ├── static
│   │   │   │   └── products.html                        // demo AJAX fetch GraphQL (HTML)
│   │   │   ├── templates                                
│   │   │   └── data.sql                                 
│   │   └── webapp                                       
│   │       └── WEB-INF
│   │           └── views
│   │               └── products.jsp                     // demo AJAX fetch GraphQL (JSP)
│   └── test
│       └── java
│           └── vn
│               └── iostar
│                   └── GraphqlDemoApplicationTests.java