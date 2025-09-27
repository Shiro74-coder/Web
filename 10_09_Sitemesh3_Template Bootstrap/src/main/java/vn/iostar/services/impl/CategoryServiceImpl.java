package vn.iostar.services.impl;

import java.util.List;

import vn.iostar.dao.ICategoryDao;
import vn.iostar.dao.impl.CategoryDaoImpl;
import vn.iostar.models.CategoryModel;
import vn.iostar.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryDao dao = new CategoryDaoImpl();

    @Override public void create(CategoryModel c) { dao.insert(c); }
    @Override public void update(CategoryModel c) { dao.update(c); }
    @Override public boolean delete(int id, int ownerId) { return dao.delete(id, ownerId); }
    @Override public CategoryModel findById(int id, int ownerId) { return dao.findById(id, ownerId); }
    @Override public List<CategoryModel> listMine(int ownerId) { return dao.findAllByUser(ownerId); }
    @Override public List<CategoryModel> searchMine(int ownerId, String k) { return dao.searchByUser(ownerId, k); }

    @Override public List<CategoryModel> listAll() { return dao.findAll(); }
}
