package vn.iostar.entity;

import jakarta.persistence.*;
import java.time.Instant;

import groovy.lang.Category;


@Entity
@Table(name = "products")
public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "product_id")
private Long productId;


@Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(200)")
private String name;


@Column(name = "quantity", nullable = false)
private Integer quantity = 0;


@Column(name = "unit_price", nullable = false)
private Double unitPrice = 0.0;


@Column(name = "images")
private String images; // tên file ảnh (tuỳ chọn)


@Column(name = "description", columnDefinition = "NVARCHAR(500)")
private String description;


@Column(name = "discount", nullable = false)
private Double discount = 0.0;


@Column(name = "create_date", nullable = false)
private Instant createDate = Instant.now();


@Column(name = "status", nullable = false)
private Short status = 1; // 1=active, 0=inactive


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "category_id")
private Category category;


// getters/setters
public Long getProductId() { return productId; }
public void setProductId(Long productId) { this.productId = productId; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public Integer getQuantity() { return quantity; }
public void setQuantity(Integer quantity) { this.quantity = quantity; }
public Double getUnitPrice() { return unitPrice; }
public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
public String getImages() { return images; }
public void setImages(String images) { this.images = images; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public Double getDiscount() { return discount; }
public void setDiscount(Double discount) { this.discount = discount; }
public Instant getCreateDate() { return createDate; }
public void setCreateDate(Instant createDate) { this.createDate = createDate; }
public Short getStatus() { return status; }
public void setStatus(Short status) { this.status = status; }
public Category getCategory() { return category; }
public void setCategory(Category category) { this.category = category; }
}
