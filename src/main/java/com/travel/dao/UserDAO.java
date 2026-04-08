package com.travel.dao;

import com.travel.model.User;
import com.travel.util.DBConnection;
import java.sql.*;

public class UserDAO {

    public User login(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));
                return u;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean register(User user) {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, 'USER')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT id FROM users WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeQuery().next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
}