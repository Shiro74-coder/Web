package vn.iostar.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "categories")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "category_id")
private Long categoryId;


@Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(100)")
private String name;


@Column(name = "icon")
private String icon; // tên file ảnh, có thể null


// getters/setters
public Long getCategoryId() { return categoryId; }
public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getIcon() { return icon; }
public void setIcon(String icon) { this.icon = icon; }
}