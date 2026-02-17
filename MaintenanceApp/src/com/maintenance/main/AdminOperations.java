package com.maintenance.main;

import com.maintenance.model.Site;
import com.maintenance.model.SiteRequest;
import com.maintenance.model.User;
import com.maintenance.service.RequestService;
import com.maintenance.service.SiteService;
import com.maintenance.service.UserService;
import java.util.List;
import java.util.Scanner;

public class AdminOperations {

    private Scanner sc = new Scanner(System.in);
    private UserService userService;
    private SiteService siteService;
    private RequestService requestService;

    public AdminOperations() {
        this.userService = new UserService();
        this.siteService = new SiteService();
        this.requestService = new RequestService();
    }

    public void addOwner() {
        System.out.print("Enter Owner Name: ");
        String name = sc.next();
        System.out.print("Enter Phone: ");
        String phone = sc.next();

        userService.addOwner(name, phone);
        System.out.println("Owner added successfully.");
    }

    public void viewUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("UID | NAME | PHONE | ROLE");
        for (User u : users) {
             System.out.println(u.getUid() + " | " + u.getName() + " | " + u.getPhone() + " | " + u.getRole());
        }
    }

    public void addSite() {
        System.out.print("Enter Owner UID: ");
        int uid = sc.nextInt();
        System.out.print("Enter Length: ");
        int length = sc.nextInt();
        System.out.print("Enter Width: ");
        int width = sc.nextInt();
        System.out.print("Enter Site Type (VILLA / APARTMENT / INDEPENDENT_HOUSE / OPEN_SITE): ");
        String siteType = sc.next().toUpperCase();

        siteService.addSite(uid, length, width, siteType);
    }

    public void viewSites() {
        List<Site> sites = siteService.getAllSites();
        System.out.println("SID | UID | SIZE | TYPE | OCCUPIED");
        for (Site s : sites) {
            int size = s.getLength() * s.getWidth();
            System.out.println(s.getSid() + " | " + s.getUid() + " | " + size + " | " + s.getSiteType() + " | " + s.isOccupied());
        }
    }

    public void viewPendingMaintenance() {
         var maintenances = siteService.getPendingMaintenances();
         System.out.println("SID | YEAR | AMOUNT | STATUS");
         for (var m : maintenances) {
             System.out.println(m.getSid() + " | " + m.getMaintenanceYear() + " | " + m.getAmount() + " | " + m.getStatus());
         }
    }

    public void collectMaintenance() {
        System.out.print("Enter Site ID: ");
        int sid = sc.nextInt();
        siteService.collectMaintenance(sid);
    }

    public void viewPendingSiteRequests() {
        List<SiteRequest> requests = requestService.getPendingRequests();
        System.out.println("REQ_ID | SID | UID | SIZE | TYPE");
        for (SiteRequest r : requests) {
             int size = r.getNewLength() * r.getNewWidth();
             System.out.println(r.getRequestId() + " | " + r.getSid() + " | " + r.getUid() + " | " + size + " | " + r.getNewSiteType());
        }
    }

    public void processSiteRequest() {
        System.out.print("Enter Request ID: ");
        int reqId = sc.nextInt();
        System.out.print("Approve or Reject (A/R): ");
        String action = sc.next().toUpperCase();

        requestService.processRequest(reqId, action.equals("A"));
    }
}
