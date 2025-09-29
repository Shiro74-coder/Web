package vn.iostar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/products-page")
    public String productsPage() {
        return "products"; // maps to /WEB-INF/views/products.jsp
    }
}
