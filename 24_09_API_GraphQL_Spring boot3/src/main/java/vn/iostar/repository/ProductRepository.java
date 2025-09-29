package vn.iostar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.entity.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findByCategories_Id(Long categoryId);
}
