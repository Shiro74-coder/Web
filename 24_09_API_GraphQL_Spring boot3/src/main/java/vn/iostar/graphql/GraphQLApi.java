package vn.iostar.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import vn.iostar.entity.*;
import vn.iostar.repository.*;

import java.math.BigDecimal;
import java.util.List;

record UserInput(String fullname, String email, String password, String phone, java.util.List<Long> categoryIds) {}
record CategoryInput(String name, String images) {}
record ProductInput(String title, Integer quantity, String desc, Double price, Long userId, java.util.List<Long> categoryIds) {}

@Controller
@Transactional
public class GraphQLApi {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public GraphQLApi(UserRepository u, ProductRepository p, CategoryRepository c) {
        this.userRepo = u; this.productRepo = p; this.categoryRepo = c;
    }

    // ===== Queries =====
    @QueryMapping
    public List<Product> productsSortedByPriceAsc() {
        return productRepo.findAllByOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategoryId(@Argument Long categoryId) {
        return productRepo.findByCategories_Id(categoryId);
    }

    @QueryMapping public List<User> users() { return userRepo.findAll(); }
    @QueryMapping public List<Category> categories() { return categoryRepo.findAll(); }
    @QueryMapping public List<Product> products() { return productRepo.findAll(); }

    // ===== Mutations: USER =====
    @MutationMapping
    public User createUser(@Argument UserInput input) {
        User u = new User();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());
        attachUserCategories(u, input.categoryIds());
        return userRepo.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput input) {
        User u = userRepo.findById(id).orElseThrow();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());
        u.getCategories().clear();
        attachUserCategories(u, input.categoryIds());
        return userRepo.save(u);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }

    private void attachUserCategories(User u, java.util.List<Long> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty()) return;
        var cats = categoryRepo.findAllById(categoryIds);
        u.getCategories().addAll(cats);
    }

    // ===== Mutations: CATEGORY =====
    @MutationMapping
    public Category createCategory(@Argument CategoryInput input) {
        Category c = new Category();
        c.setName(input.name());
        c.setImages(input.images());
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument CategoryInput input) {
        Category c = categoryRepo.findById(id).orElseThrow();
        c.setName(input.name());
        c.setImages(input.images());
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (!categoryRepo.existsById(id)) return false;
        categoryRepo.deleteById(id);
        return true;
    }

    // ===== Mutations: PRODUCT =====
    @MutationMapping
    public Product createProduct(@Argument ProductInput input) {
        Product p = new Product();
        fillProduct(p, input);
        return productRepo.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument ProductInput input) {
        Product p = productRepo.findById(id).orElseThrow();
        p.getCategories().clear();
        fillProduct(p, input);
        return productRepo.save(p);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (!productRepo.existsById(id)) return false;
        productRepo.deleteById(id);
        return true;
    }

    private void fillProduct(Product p, ProductInput input) {
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDesc(input.desc());
        p.setPrice(BigDecimal.valueOf(input.price()));
        User owner = userRepo.findById(input.userId()).orElseThrow();
        p.setUser(owner);
        var cats = categoryRepo.findAllById(input.categoryIds());
        p.getCategories().addAll(cats);
    }
}
