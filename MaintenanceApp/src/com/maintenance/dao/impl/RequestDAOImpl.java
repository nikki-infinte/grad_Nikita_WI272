package com.maintenance.dao.impl;

import com.maintenance.dao.RequestDAO;
import com.maintenance.model.SiteRequest;
import com.maintenance.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImpl implements RequestDAO {

    @Override
    public void addRequest(SiteRequest request) {
        String sql = "INSERT INTO site_update_request(sid, uid, new_length, new_width, new_site_type, status, request_date) VALUES (?, ?, ?, ?, ?, 'PENDING', CURRENT_DATE)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, request.getSid());
            ps.setInt(2, request.getUid());
            ps.setInt(3, request.getNewLength());
            ps.setInt(4, request.getNewWidth());
            ps.setString(5, request.getNewSiteType());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SiteRequest> getPendingRequests() {
        return getRequestsByStatus("PENDING");
    }

    @Override
    public List<SiteRequest> getRequestsByOwner(int uid) {
        List<SiteRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM site_update_request WHERE uid = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                requests.add(extractRequestFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    @Override
    public SiteRequest getRequest(int requestId) {
         String sql = "SELECT * FROM site_update_request WHERE request_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return extractRequestFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void updateRequestStatus(int requestId, String status) {
        String sql = "UPDATE site_update_request SET status = ? WHERE request_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<SiteRequest> getRequestsByStatus(String status) {
        List<SiteRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM site_update_request WHERE status = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                requests.add(extractRequestFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    private SiteRequest extractRequestFromResultSet(ResultSet rs) throws SQLException {
        SiteRequest req = new SiteRequest();
        req.setRequestId(rs.getInt("request_id"));
        req.setSid(rs.getInt("sid"));
        req.setUid(rs.getInt("uid"));
        req.setNewLength(rs.getInt("new_length"));
        req.setNewWidth(rs.getInt("new_width"));
        req.setNewSiteType(rs.getString("new_site_type"));
        req.setStatus(rs.getString("status"));
        req.setRequestDate(rs.getDate("request_date"));
        return req;
    }
}
