package vn.iostar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.iostar.entity.*;
import vn.iostar.repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(UserRepository userRepo, CategoryRepository catRepo, ProductRepository prodRepo) {
        return args -> {
            if (userRepo.count() > 0) return;
            Category c1 = new Category(); c1.setName("Phone"); c1.setImages("phone.png");
            Category c2 = new Category(); c2.setName("Accessory"); c2.setImages("acc.png");
            catRepo.saveAll(List.of(c1,c2));

            User u = new User();
            u.setFullname("Admin");
            u.setEmail("admin@example.com");
            u.setPassword("123456");
            u.setPhone("0900000000");
            u.getCategories().add(c1);
            userRepo.save(u);

            Product p = new Product();
            p.setTitle("iPhone 15");
            p.setQuantity(10);
            p.setDesc("New model");
            p.setPrice(new BigDecimal("1899.00"));
            p.setUser(u);
            p.getCategories().add(c1);
            prodRepo.save(p);
        };
    }
}
