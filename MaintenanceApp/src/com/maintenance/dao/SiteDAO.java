package com.maintenance.dao;

import com.maintenance.model.Site;
import java.util.List;

public interface SiteDAO {
    int addSite(Site site); // returns generated SID
    List<Site> getAllSites();
    List<Site> getSitesByOwner(int uid);
    void updateSite(Site site);
}
