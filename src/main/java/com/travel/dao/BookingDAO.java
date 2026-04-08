package com.travel.dao;

import com.travel.model.Booking;
import com.travel.util.DBConnection;
import java.sql.*;
import java.util.*;

public class BookingDAO {

    public boolean createBooking(Booking b) {
        String sql = "INSERT INTO bookings (user_id, package_id, total_price) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getUserId());
            ps.setInt(2, b.getPackageId());
            ps.setDouble(3, b.getTotalPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT b.*, p.name AS pkg_name FROM bookings b " +
                     "JOIN packages p ON b.package_id = p.id " +
                     "WHERE b.user_id = ? ORDER BY b.id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking bk = mapRow(rs);
                bk.setPackageName(rs.getString("pkg_name"));
                list.add(bk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();
        String sql = "SELECT b.*, u.name AS user_name, p.name AS pkg_name " +
                     "FROM bookings b JOIN users u ON b.user_id = u.id " +
                     "JOIN packages p ON b.package_id = p.id ORDER BY b.id DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Booking bk = mapRow(rs);
                bk.setUserName(rs.getString("user_name"));
                bk.setPackageName(rs.getString("pkg_name"));
                list.add(bk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private Booking mapRow(ResultSet rs) throws SQLException {
        Booking b = new Booking();
        b.setId(rs.getInt("id"));
        b.setUserId(rs.getInt("user_id"));
        b.setPackageId(rs.getInt("package_id"));
        b.setBookingDate(rs.getString("booking_date"));
        b.setStatus(rs.getString("status"));
        b.setTotalPrice(rs.getDouble("total_price"));
        return b;
    }
}