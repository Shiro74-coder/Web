package vn.iostar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.iostar.configs.DBConnectSQL;
import vn.iostar.dao.IUserDao;
import vn.iostar.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao {

	@Override
    public void insert(UserModel u) {
        String sql = "INSERT INTO users(username, password, email, fullname, images, phone, roleid, createDate) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = super.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());  // password trước
            ps.setString(3, u.getEmail());     // email sau
            ps.setString(4, u.getFullname());
            ps.setString(5, u.getImages());
            ps.setString(6, u.getPhone());
            ps.setInt(7, u.getRoleid());
            ps.setDate(8, u.getCreateDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try (Connection c = super.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (Connection c = super.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String sql = "SELECT 1 FROM users WHERE phone = ?";
        try (Connection c = super.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public UserModel findOne(int id) {
        String sql = "SELECT TOP 1 id, username, password, email, fullname, images, phone, roleid, createDate "
                   + "FROM users WHERE id = ?";
        try (Connection c = super.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    return new UserModel(
                        r.getInt("id"),
                        r.getString("username"),
                        r.getString("password"), // password trước
                        r.getString("email"),    // email sau
                        r.getString("fullname"),
                        r.getString("images"),
                        r.getString("phone"),
                        r.getInt("roleid"),
                        r.getDate("createDate")
                    );
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT TOP 1 id, username, password, email, fullname, images, phone, roleid, createDate "
                   + "FROM users WHERE username = ?";
        try (Connection c = super.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet r = ps.executeQuery()) {
                if (r.next()) {
                    return new UserModel(
                        r.getInt("id"),
                        r.getString("username"),
                        r.getString("password"),
                        r.getString("email"),
                        r.getString("fullname"),
                        r.getString("images"),
                        r.getString("phone"),
                        r.getInt("roleid"),
                        r.getDate("createDate")
                    );
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
    @Override
    public boolean updatePasswordByEmail(String email, String newPassword){
        String sql="UPDATE users SET password=? WHERE email=?";
        try(Connection c=super.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        }catch(Exception e){ e.printStackTrace(); }
        return false;
    }
    
    @Override
	public void updateUser(UserModel user) {
		StringBuilder sql = new StringBuilder("UPDATE users SET fullname = ?, phone = ?");
		if (user.getImages() != null && !user.getImages().isEmpty()) {
			sql.append(", images = ?");
		}
		sql.append(" WHERE id = ?");

		try (Connection c = super.getConnection(); 
			 PreparedStatement ps = c.prepareStatement(sql.toString())) {
			
			ps.setString(1, user.getFullname());
			ps.setString(2, user.getPhone());

			if (user.getImages() != null && !user.getImages().isEmpty()) {
				ps.setString(3, user.getImages());
				ps.setInt(4, user.getId());
			} else {
				ps.setInt(3, user.getId());
			}

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
