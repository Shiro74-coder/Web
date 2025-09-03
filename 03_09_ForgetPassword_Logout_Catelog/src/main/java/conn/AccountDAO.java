package conn;

import java.sql.*;

public class AccountDAO {
	public Account getByUsername(String username) {
		try (Connection con = new database().getConnection()) {
			String sql = "SELECT * FROM Account WHERE username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("id"));
				a.setUsername(rs.getString("username"));
				a.setPassword(rs.getString("password"));
				a.setFullname(rs.getString("fullname"));
				a.setRole(rs.getString("role"));
				try {
					a.setEmail(rs.getString("email"));
				} catch (Exception ignore) {
				}
				try {
					a.setPhone(rs.getString("phone"));
				} catch (Exception ignore) {
				}
				return a;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean existsUsername(String username) {
		try (Connection con = new database().getConnection()) {
			String sql = "SELECT 1 FROM Account WHERE username=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existsEmail(String email) {
		if (email == null || email.isBlank())
			return false;
		try (Connection con = new database().getConnection()) {
			String sql = "SELECT 1 FROM Account WHERE email=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existsByUsernameAndEmail(String username, String email) {
		String sql = "SELECT 1 FROM Account WHERE username=? AND email=?";
		try (Connection con = new database().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, email);
			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updatePassword(String username, String newPassword) {
		String sql = "UPDATE Account SET password=? WHERE username=?";
		try (Connection con = new database().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, newPassword);
			ps.setString(2, username);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void insert(Account a) {
		String sql = "INSERT INTO Account(username,password,fullname,role,email,phone) " + "VALUES(?,?,?,?,?,?)";
		try (Connection con = new database().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, a.getUsername());
			ps.setString(2, a.getPassword()); // (nâng cao: hash BCrypt)
			ps.setString(3, a.getFullname());
			ps.setString(4, a.getRole() == null ? "USER" : a.getRole());
			ps.setString(5, a.getEmail());
			ps.setString(6, a.getPhone());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
