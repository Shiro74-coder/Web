package vn.iostar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.iostar.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Page<Category> findByNameContaining(String name, Pageable pageable);
    <S extends Category> S save(S entity);
    Optional<Category> findById(Long aLong);
    void deleteById(Long aLong);
}