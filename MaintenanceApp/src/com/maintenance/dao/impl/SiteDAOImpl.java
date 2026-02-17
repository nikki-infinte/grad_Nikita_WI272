package com.maintenance.dao.impl;

import com.maintenance.dao.SiteDAO;
import com.maintenance.model.Site;
import com.maintenance.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiteDAOImpl implements SiteDAO {

    @Override
    public int addSite(Site site) {
        String sql = "INSERT INTO siteDetail(uid, length, width, site_type, is_occupied) VALUES (?, ?, ?, ?, ?) RETURNING sid";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, site.getUid());
            ps.setInt(2, site.getLength());
            ps.setInt(3, site.getWidth());
            ps.setString(4, site.getSiteType());
            ps.setBoolean(5, site.isOccupied());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("sid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Site> getAllSites() {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT * FROM siteDetail";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                sites.add(extractSiteFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sites;
    }

    @Override
    public List<Site> getSitesByOwner(int uid) {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT * FROM siteDetail WHERE uid = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sites.add(extractSiteFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sites;
    }
    
    @Override
    public void updateSite(Site site) {
        String sql = "UPDATE siteDetail SET length = ?, width = ?, site_type = ?, is_occupied = ? WHERE sid = ?";
         try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, site.getLength());
            ps.setInt(2, site.getWidth());
            ps.setString(3, site.getSiteType());
            ps.setBoolean(4, site.isOccupied());
            ps.setInt(5, site.getSid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Site extractSiteFromResultSet(ResultSet rs) throws SQLException {
        Site site = new Site();
        site.setSid(rs.getInt("sid"));
        site.setUid(rs.getInt("uid"));
        site.setLength(rs.getInt("length"));
        site.setWidth(rs.getInt("width"));
        site.setSiteType(rs.getString("site_type"));
        site.setOccupied(rs.getBoolean("is_occupied"));
        return site;
    }
}
