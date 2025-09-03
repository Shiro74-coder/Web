package conn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public List<Category> findAllByUser(int userId) {
        String sql = "SELECT id, user_id, name, note FROM Category WHERE user_id=? ORDER BY id DESC";
        List<Category> list = new ArrayList<>();
        try (Connection con = new database().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getInt("id"));
                    c.setUserId(rs.getInt("user_id"));
                    c.setName(rs.getString("name"));
                    c.setNote(rs.getString("note"));
                    list.add(c);
                }
            }
        } catch (Exception e){ e.printStackTrace(); }
        return list;
    }

    public Category findByIdAndUser(int id, int userId) {
        String sql = "SELECT id, user_id, name, note FROM Category WHERE id=? AND user_id=?";
        try (Connection con = new database().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setId(rs.getInt("id"));
                    c.setUserId(rs.getInt("user_id"));
                    c.setName(rs.getString("name"));
                    c.setNote(rs.getString("note"));
                    return c;
                }
            }
        } catch (Exception e){ e.printStackTrace(); }
        return null;
    }

    public boolean insert(Category c) {
        String sql = "INSERT INTO Category(user_id, name, note, createdAt) VALUES(?,?,?,GETDATE())";
        try (Connection con = new database().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getUserId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getNote());
            return ps.executeUpdate() > 0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean update(Category c) {
        String sql = "UPDATE Category SET name=?, note=?, updatedAt=GETDATE() WHERE id=? AND user_id=?";
        try (Connection con = new database().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getNote());
            ps.setInt(3, c.getId());
            ps.setInt(4, c.getUserId());
            return ps.executeUpdate() > 0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id, int userId) {
        String sql = "DELETE FROM Category WHERE id=? AND user_id=?";
        try (Connection con = new database().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception e){ e.printStackTrace(); }
        return false;
    }
}
