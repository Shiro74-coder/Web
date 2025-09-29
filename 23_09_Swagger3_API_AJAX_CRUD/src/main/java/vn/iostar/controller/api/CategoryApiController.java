package vn.iostar.controller.api;

import org.springdoc.core.converters.models.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import vn.iostar.model.ApiResponse;
import vn.iostar.service.CategoryService;
import vn.iostar.service.IStorageService;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {
@Autowired private CategoryService categoryService;
@Autowired private IStorageService storageService;


@GetMapping
public ResponseEntity<?> list(@RequestParam(value = "name", required = false) String name,
@RequestParam(value = "page", defaultValue = "1") int page,
@RequestParam(value = "size", defaultValue = "10") int size) {
Pageable pageable = PageRequest.of(page - 1, size, Sort.by("name").ascending());
Page<Category> data = (StringUtils.hasText(name))
? categoryService.findByNameContaining(name, pageable)
: categoryService.findAll(pageable);
return ResponseEntity.ok(new ApiResponse(true, "OK", data));
}


@GetMapping("/{id}")
public ResponseEntity<?> get(@PathVariable("id") Long id) {
return categoryService.findById(id)
.<ResponseEntity<?>>map(c -> ResponseEntity.ok(new ApiResponse(true, "OK", c)))
.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
.body(new ApiResponse(false, "Not found", null)));
}


@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> createJson(@RequestBody Category req) {
if (StringUtils.hasText(req.getName()) && categoryService.findByName(req.getName()).isPresent()) {
return ResponseEntity.status(HttpStatus.BAD_REQUEST)
.body(new ApiResponse(false, "Category name existed", null));
}
Category saved = categoryService.save(req);
return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Created", saved));
}


// Multipart: name + icon (tùy chọn)
@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> createMultipart(@RequestParam("name") String name,
@RequestParam(value = "icon", required = false) MultipartFile icon) {
Category c = new Category();
c.setName(name);
if (icon != null && !icon.isEmpty()) {
String id = UUID.randomUUID().toString();
String storeName = storageService.getStorageFilename(icon, id);
storageService.store(icon, storeName);
c.setIcon(storeName);
}
return ResponseEntity.status(HttpStatus.CREATED)
.body(new ApiResponse(true, "Created", categoryService.save(c)));
}


@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> updateJson(@PathVariable("id") Long id, @RequestBody Category req) {
Optional<Category> opt = categoryService.findById(id);
if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body(new ApiResponse(false, "Not found", null));
Category c = opt.get();
if (StringUtils.hasText(req.getName())) c.setName(req.getName());
if (StringUtils.hasText(req.getIcon())) c.setIcon(req.getIcon());
return ResponseEntity.ok(new ApiResponse(true, "Updated", categoryService.save(c)));
}


@PutMapping(path = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> updateMultipart(@PathVariable("id") Long id,
@RequestParam("name") String name,
@RequestParam(value = "icon", required = false) MultipartFile icon) {
Optional<Category> opt = categoryService.findById(id);
if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body(new ApiResponse(false, "Not found", null));
Category c = opt.get();
c.setName(name);
if (icon != null && !icon.isEmpty()) {
String storeName = storageService.getStorageFilename(icon, id.toString());
storageService.store(icon, storeName);
c.setIcon(storeName);
}
return ResponseEntity.ok(new ApiResponse(true, "Updated", categoryService.save(c)));
}


@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable("id") Long id) {
Optional<Category> opt = categoryService.findById(id);
if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body(new ApiResponse(false, "Not found", null));
categoryService.deleteById(id);
return ResponseEntity.ok(new ApiResponse(true, "Deleted", opt.get()));
}
}