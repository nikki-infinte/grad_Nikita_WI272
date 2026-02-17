package com.maintenance.main;

import com.maintenance.model.Site;
import com.maintenance.model.SiteRequest;
import com.maintenance.service.RequestService;
import com.maintenance.service.SiteService;
import java.util.List;
import java.util.Scanner;

public class OwnerOperations {

    private Scanner sc = new Scanner(System.in);
    private SiteService siteService;
    private RequestService requestService;

    public OwnerOperations() {
        this.siteService = new SiteService();
        this.requestService = new RequestService();
    }

    public void viewMySites(int ownerUid) {
        List<Site> sites = siteService.getSitesByOwner(ownerUid);
        System.out.println("SID | SIZE | TYPE | OCCUPIED");
        for (Site s : sites) {
            int size = s.getLength() * s.getWidth();
            System.out.println(s.getSid() + " | " + size + " | " + s.getSiteType() + " | " + s.isOccupied());
        }
    }

    public void requestSiteUpdate(int ownerUid) {
        System.out.print("Enter Site ID: ");
        int sid = sc.nextInt();
        System.out.print("New Length: ");
        int length = sc.nextInt();
        System.out.print("New Width: ");
        int width = sc.nextInt();
        System.out.print("New Type (VILLA / APARTMENT / INDEPENDENT_HOUSE / OPEN_SITE): ");
        String type = sc.next().toUpperCase();

        requestService.requestSiteUpdate(sid, ownerUid, length, width, type);
    }

    public void viewMyRequests(int ownerUid) {
        List<SiteRequest> requests = requestService.getRequestsByOwner(ownerUid);
        System.out.println("REQ_ID | SID | STATUS | DATE");
        for (SiteRequest r : requests) {
            System.out.println(r.getRequestId() + " | " + r.getSid() + " | " + r.getStatus() + " | " + r.getRequestDate());
        }
    }
}
