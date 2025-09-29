package vn.iostar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.iostar.entity.Product;
import vn.iostar.repository.ProductRepository;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
@Autowired private ProductRepository repo;


@Override public List<Product> findAll() { return repo.findAll(); }
@Override public Page<Product> findAll(Pageable pageable) { return repo.findAll(pageable); }


@Override public Page<Product> findByNameContaining(String name, Pageable pageable) {
return repo.findByNameContaining(name, pageable);
}
@Override public List<Product> findByNameContaining(String name) { return repo.findByNameContaining(name); }
@Override public Optional<Product> findById(Long id) { return repo.findById(id); }
@Override public Optional<Product> findByName(String name) { return repo.findByName(name); }
@Override public Optional<Product> findByCreateDate(Instant createDate) { return repo.findByCreateDate(createDate); }
@Override public <S extends Product> S save(S entity) { return repo.save(entity); }
@Override public void deleteById(Long id) { repo.deleteById(id); }
}
