package com.travel.dao;

import com.travel.model.Package;
import com.travel.util.DBConnection;
import java.sql.*;
import java.util.*;

public class PackageDAO {

    public List<Package> getAllPackages() {
        List<Package> list = new ArrayList<>();
        String sql = "SELECT * FROM packages ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Package getPackageById(int id) {
        String sql = "SELECT * FROM packages WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean addPackage(Package p) {
        String sql = "INSERT INTO packages (name, description, destination, duration_days, price, available_slots) VALUES (?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getDestination());
            ps.setInt(4, p.getDurationDays());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getAvailableSlots());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean updatePackage(Package p) {
        String sql = "UPDATE packages SET name=?, description=?, destination=?, duration_days=?, price=?, available_slots=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getDestination());
            ps.setInt(4, p.getDurationDays());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getAvailableSlots());
            ps.setInt(7, p.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean deletePackage(int id) {
        String sql = "DELETE FROM packages WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private Package mapRow(ResultSet rs) throws SQLException {
        Package p = new Package();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("description"));
        p.setDestination(rs.getString("destination"));
        p.setDurationDays(rs.getInt("duration_days"));
        p.setPrice(rs.getDouble("price"));
        p.setAvailableSlots(rs.getInt("available_slots"));
        return p;
    }
}