package vn.iostar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.iostar.entity.Product;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


public interface ProductService {
List<Product> findAll();
Page<Product> findAll(Pageable pageable);
Page<Product> findByNameContaining(String name, Pageable pageable);
List<Product> findByNameContaining(String name);
Optional<Product> findById(Long id);
Optional<Product> findByName(String name);
Optional<Product> findByCreateDate(Instant createDate);
<S extends Product> S save(S entity);
void deleteById(Long id);
}