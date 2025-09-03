package conn;

import java.util.List;

public interface CategoryService {
    List<Category> listByUser(int userId);
    Category getByIdForUser(int id, int userId);
    boolean create(int userId, String name, String note);
    boolean update(int id, int userId, String name, String note);
    boolean remove(int id, int userId);
}
