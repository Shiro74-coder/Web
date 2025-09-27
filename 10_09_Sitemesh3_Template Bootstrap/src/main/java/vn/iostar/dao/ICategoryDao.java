package vn.iostar.dao;

import java.util.List;
import vn.iostar.models.CategoryModel;

public interface ICategoryDao {
    void insert(CategoryModel c);
    void update(CategoryModel c);
    boolean delete(int id, int ownerId);                 
    CategoryModel findById(int id, int ownerId);         
    List<CategoryModel> findAllByUser(int ownerId);
    List<CategoryModel> searchByUser(int ownerId, String keyword);

    List<CategoryModel> findAll();
}
