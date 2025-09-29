package vn.iostar.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iostar.entity.Product;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
List<Product> findByNameContaining(String name);
Page<Product> findByNameContaining(String name, Pageable pageable);
Optional<Product> findByName(String name);
Optional<Product> findByCreateDate(Instant createDate);
}