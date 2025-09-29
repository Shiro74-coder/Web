package vn.iostar.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.iostar.entity.Category;
import vn.iostar.entity.Product;
import vn.iostar.model.ApiResponse;
import vn.iostar.service.CategoryService;
import vn.iostar.service.IStorageService;
import vn.iostar.service.ProductService;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/products")
public class ProductApiController {
@Autowired private ProductService productService;
@Autowired private CategoryService categoryService;
@Autowired private IStorageService storageService;


@GetMapping
public ResponseEntity<?> list(@RequestParam(value = "name", required = false) String name,
@RequestParam(value = "page", defaultValue = "1") int page,
@RequestParam(value = "size", defaultValue = "10") int size) {
Pageable pageable = PageRequest.of(page - 1, size, Sort.by("name").ascending());
Page<Product> data = (StringUtils.hasText(name))
? productService.findByNameContaining(name, pageable)
: productService.findAll(pageable);
return ResponseEntity.ok(new ApiResponse(true, "OK", data));
}


@GetMapping("/{id}")
public ResponseEntity<?> get(@PathVariable("id") Long id) {
return productService.findById(id)
.<ResponseEntity<?>>map(p -> ResponseEntity.ok(new ApiResponse(true, "OK", p)))
.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
.body(new ApiResponse(false, "Not found", null)));
}


@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> create(@RequestParam("name") String name,
@RequestParam("quantity") Integer quantity,
@RequestParam("unitPrice") Double unitPrice,
@RequestParam(value = "discount", defaultValue = "0") Double discount,
@RequestParam(value = "description", required = false) String description,
@RequestParam("categoryId") Long categoryId,
@RequestParam(value = "image", required = false) MultipartFile image) {
if (productService.findByName(name).isPresent()) {
return ResponseEntity.status(HttpStatus.BAD_REQUEST)
.body(new ApiResponse(false, "Product existed", null));
}
Product p = new Product();
p.setName(name); p.setQuantity(quantity); p.setUnitPrice(unitPrice);
p.setDiscount(discount); p.setDescription(description); p.setCreateDate(Instant.now());
Category c = categoryService.findById(categoryId).orElse(null);
p.setCategory(c);
if (image != null && !image.isEmpty()) {
String id = UUID.randomUUID().toString();
String store = storageService.getStorageFilename(image, id);
storageService.store(image, store);
p.setImages(store);
}
return ResponseEntity.status(HttpStatus.CREATED)
.body(new ApiResponse(true, "Created", productService.save(p)));
}


@PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> update(@PathVariable("id") Long id,
@RequestParam("name") String name,
@RequestParam("quantity") Integer quantity,
@RequestParam("unitPrice") Double unitPrice,
@RequestParam(value = "discount", defaultValue = "0") Double discount,
@RequestParam(value = "description", required = false) String description,
@RequestParam("categoryId") Long categoryId,
@RequestParam(value = "image", required = false) MultipartFile image) {
}