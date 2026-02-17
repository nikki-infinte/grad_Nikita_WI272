package com.maintenance.dao.impl;

import com.maintenance.dao.MaintenanceDAO;
import com.maintenance.model.Maintenance;
import com.maintenance.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAOImpl implements MaintenanceDAO {

    @Override
    public void addMaintenance(Maintenance maintenance) {
        String sql = "INSERT INTO maintenance1(sid, maintenance_year, amount, status, payment_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maintenance.getSid());
            ps.setInt(2, maintenance.getMaintenanceYear());
            ps.setDouble(3, maintenance.getAmount());
            ps.setString(4, maintenance.getStatus());
            ps.setDate(5, maintenance.getPaymentDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Maintenance> getPendingMaintenances() {
        List<Maintenance> list = new ArrayList<>();
        String sql = "SELECT * FROM maintenance1 WHERE status = 'PENDING'";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Maintenance m = new Maintenance();
                m.setSid(rs.getInt("sid"));
                m.setMaintenanceYear(rs.getInt("maintenance_year"));
                m.setAmount(rs.getDouble("amount"));
                m.setStatus(rs.getString("status"));
                m.setPaymentDate(rs.getDate("payment_date"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean payMaintenance(int sid) {
        String sql = "UPDATE maintenance1 SET status = 'PAID', payment_date = CURRENT_DATE WHERE sid = ? AND status = 'PENDING'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, sid);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
