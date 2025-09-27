package vn.iostar.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class CategoryModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
    private String icon;      
    private int userId;       
    private Timestamp createdDate;

    public CategoryModel() {}

    public CategoryModel(int id, String name, String icon, int userId, Timestamp createdDate) {
        this.id = id; this.name = name; this.icon = icon; this.userId = userId; this.createdDate = createdDate;
    }

    // getters/setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getIcon() {return icon;}
    public void setIcon(String icon) {this.icon = icon;}
    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}
    public Timestamp getCreatedDate() {return createdDate;}
    public void setCreatedDate(Timestamp createdDate) {this.createdDate = createdDate;}
}
