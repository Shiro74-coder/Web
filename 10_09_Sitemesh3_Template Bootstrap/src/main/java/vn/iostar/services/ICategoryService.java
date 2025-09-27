package vn.iostar.services;

import java.util.List;
import vn.iostar.models.CategoryModel;

public interface ICategoryService {
    void create(CategoryModel c);
    void update(CategoryModel c);
    boolean delete(int id, int ownerId);
    CategoryModel findById(int id, int ownerId);
    List<CategoryModel> listMine(int ownerId);
    List<CategoryModel> searchMine(int ownerId, String keyword);

    List<CategoryModel> listAll();
}
