package com.maintenance.service;

import com.maintenance.dao.MaintenanceDAO;
import com.maintenance.dao.SiteDAO;
import com.maintenance.dao.impl.MaintenanceDAOImpl;
import com.maintenance.dao.impl.SiteDAOImpl;
import com.maintenance.model.Maintenance;
import com.maintenance.model.Site;
import java.time.Year;
import java.util.List;

public class SiteService {

    private SiteDAO siteDAO;
    private MaintenanceDAO maintenanceDAO;

    public SiteService() {
        this.siteDAO = new SiteDAOImpl();
        this.maintenanceDAO = new MaintenanceDAOImpl();
    }

    public void addSite(int uid, int length, int width, String siteType) {
        /* 🔑 Occupancy derived from site type */
        boolean isOccupied = !siteType.equals("OPEN_SITE");

        Site site = new Site(uid, length, width, siteType, isOccupied);
        int sid = siteDAO.addSite(site);

        if (sid != -1) {
            // Generate Maintenance
            int area = length * width;
            double rate = isOccupied ? 9.0 : 6.0;
            double amount = area * rate;
            int year = Year.now().getValue();

            Maintenance m = new Maintenance();
            m.setSid(sid);
            m.setMaintenanceYear(year);
            m.setAmount(amount);
            m.setStatus("PENDING");
            
            maintenanceDAO.addMaintenance(m);
            System.out.println("Site added and Maintenance generated: Rs." + amount + " (PENDING)");
        } else {
            System.out.println("Failed to add site.");
        }
    }

    public List<Site> getAllSites() {
        return siteDAO.getAllSites();
    }

    public List<Site> getSitesByOwner(int uid) {
        return siteDAO.getSitesByOwner(uid);
    }

    public List<Maintenance> getPendingMaintenances() {
        return maintenanceDAO.getPendingMaintenances();
    }

    public void collectMaintenance(int sid) {
        boolean success = maintenanceDAO.payMaintenance(sid);
        if (success) {
            System.out.println("Maintenance collected successfully.");
        } else {
            System.out.println("No pending maintenance found or payment failed.");
        }
    }
}
