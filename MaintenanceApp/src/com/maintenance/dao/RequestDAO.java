package com.maintenance.dao;

import com.maintenance.model.SiteRequest;
import java.util.List;

public interface RequestDAO {
    void addRequest(SiteRequest request);
    List<SiteRequest> getPendingRequests();
    List<SiteRequest> getRequestsByOwner(int uid);
    SiteRequest getRequest(int requestId);
    void updateRequestStatus(int requestId, String status);
}
