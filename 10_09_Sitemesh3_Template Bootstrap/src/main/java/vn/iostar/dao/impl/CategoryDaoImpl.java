package vn.iostar.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.configs.DBConnectSQL;
import vn.iostar.dao.ICategoryDao;
import vn.iostar.models.CategoryModel;

public class CategoryDaoImpl extends DBConnectSQL implements ICategoryDao {

    @Override
    public void insert(CategoryModel c) {
        String sql = "INSERT INTO categories(name, icon, user_id) VALUES(?,?,?)";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getIcon());
            ps.setInt(3, c.getUserId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void update(CategoryModel c) {
        String sql = "UPDATE categories SET name=?, icon=? WHERE id=? AND user_id=?";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getIcon());
            ps.setInt(3, c.getId());
            ps.setInt(4, c.getUserId());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public boolean delete(int id, int ownerId) {
        String sql = "DELETE FROM categories WHERE id=? AND user_id=?";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, ownerId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public CategoryModel findById(int id, int ownerId) {
        String sql = "SELECT id,name,icon,user_id,createdDate FROM categories WHERE id=? AND user_id=?";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, ownerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CategoryModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("icon"),
                        rs.getInt("user_id"),
                        rs.getTimestamp("createdDate")
                    );
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<CategoryModel> findAllByUser(int ownerId) {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT id,name,icon,user_id,createdDate FROM categories WHERE user_id=? ORDER BY id DESC";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new CategoryModel(
                        rs.getInt("id"), rs.getString("name"), rs.getString("icon"),
                        rs.getInt("user_id"), rs.getTimestamp("createdDate")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<CategoryModel> searchByUser(int ownerId, String keyword) {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT id,name,icon,user_id,createdDate FROM categories " +
                     "WHERE user_id=? AND name LIKE ? ORDER BY id DESC";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            ps.setString(2, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new CategoryModel(
                        rs.getInt("id"), rs.getString("name"), rs.getString("icon"),
                        rs.getInt("user_id"), rs.getTimestamp("createdDate")
                    ));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<CategoryModel> findAll() {
        List<CategoryModel> list = new ArrayList<>();
        String sql = "SELECT id,name,icon,user_id,createdDate FROM categories ORDER BY id DESC";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new CategoryModel(
                    rs.getInt("id"), rs.getString("name"), rs.getString("icon"),
                    rs.getInt("user_id"), rs.getTimestamp("createdDate")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
