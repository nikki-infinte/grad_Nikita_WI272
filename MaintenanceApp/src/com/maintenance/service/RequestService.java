package com.maintenance.service;

import com.maintenance.dao.RequestDAO;
import com.maintenance.dao.SiteDAO;
import com.maintenance.dao.impl.RequestDAOImpl;
import com.maintenance.dao.impl.SiteDAOImpl;
import com.maintenance.model.Site;
import com.maintenance.model.SiteRequest;
import java.util.List;

public class RequestService {

    private RequestDAO requestDAO;
    private SiteDAO siteDAO;

    public RequestService() {
        this.requestDAO = new RequestDAOImpl();
        this.siteDAO = new SiteDAOImpl();
    }

    public void requestSiteUpdate(int sid, int uid, int length, int width, String type) {
        SiteRequest request = new SiteRequest(sid, uid, length, width, type);
        requestDAO.addRequest(request);
        System.out.println("Update request sent (PENDING approval).");
    }

    public List<SiteRequest> getPendingRequests() {
        return requestDAO.getPendingRequests();
    }

    public List<SiteRequest> getRequestsByOwner(int uid) {
        return requestDAO.getRequestsByOwner(uid);
    }

    public void processRequest(int reqId, boolean approve) {
        if (approve) {
            SiteRequest req = requestDAO.getRequest(reqId);
            if (req != null) {
                // Apply update
                 /* 🔑 Occupancy derived from site type */
                boolean isOccupied = !req.getNewSiteType().equals("OPEN_SITE");
                
                Site site = new Site();
                site.setSid(req.getSid());
                site.setLength(req.getNewLength());
                site.setWidth(req.getNewWidth());
                site.setSiteType(req.getNewSiteType());
                site.setOccupied(isOccupied);
                
                siteDAO.updateSite(site);
                requestDAO.updateRequestStatus(reqId, "APPROVED");
                System.out.println("Request approved and site updated.");
            } else {
                System.out.println("Request not found.");
            }
        } else {
            requestDAO.updateRequestStatus(reqId, "REJECTED");
            System.out.println("Request rejected.");
        }
    }
}
