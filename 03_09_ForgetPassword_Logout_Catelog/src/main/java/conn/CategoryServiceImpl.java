package conn;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO dao = new CategoryDAO();

    @Override public List<Category> listByUser(int userId) {
        return dao.findAllByUser(userId);
    }

    @Override public Category getByIdForUser(int id, int userId) {
        return dao.findByIdAndUser(id, userId);
    }

    @Override public boolean create(int userId, String name, String note) {
        if (name == null || name.isBlank()) return false;
        Category c = new Category();
        c.setUserId(userId);
        c.setName(name.trim());
        c.setNote(note);
        return dao.insert(c);
    }

    @Override public boolean update(int id, int userId, String name, String note) {
        if (name == null || name.isBlank()) return false;
        Category c = new Category();
        c.setId(id);
        c.setUserId(userId);
        c.setName(name.trim());
        c.setNote(note);
        return dao.update(c);
    }

    @Override public boolean remove(int id, int userId) {
        return dao.delete(id, userId);
    }
}
