package com.maintenance.main;

import com.maintenance.model.User;
import com.maintenance.service.UserService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("Login as: 1.Admin  2.Owner");
        int role = sc.nextInt();

        if (role == 1) {
            System.out.print("Enter Admin Username: ");
            String username = sc.next();
            System.out.print("Enter Admin Password: ");
            String password = sc.next();

            // Hardcoded admin check as per original code, or could be in DB
            if (username.equals("admin") && password.equals("adminpass")) {
                AdminOperations admin = new AdminOperations();
                while (true) {
                    System.out.println("\n--- ADMIN MENU ---");
                    System.out.println("1. Add Owner");
                    System.out.println("2. View Users");
                    System.out.println("3. Add Site");
                    System.out.println("4. View Sites");
                    System.out.println("5. Show Pending Maintenance");
                    System.out.println("6. Collect Maintenance");
                    System.out.println("7. View Site Update Requests");
                    System.out.println("8. Process Site Update Request");
                    System.out.println("9. Exit");

                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> admin.addOwner();
                        case 2 -> admin.viewUsers();
                        case 3 -> admin.addSite();
                        case 4 -> admin.viewSites();
                        case 5 -> admin.viewPendingMaintenance();
                        case 6 -> admin.collectMaintenance();
                        case 7 -> admin.viewPendingSiteRequests();
                        case 8 -> admin.processSiteRequest();
                        case 9 -> System.exit(0);
                        default -> System.out.println("Invalid choice");
                    }
                }
            } else {
                 System.out.println("Invalid Admin Credentials");
            }
        } else {
            System.out.print("Enter your UID: ");
            int ownerUid = sc.nextInt();
            
            // Validate owner exists
            User user = userService.getUser(ownerUid);
            if(user != null && "OWNER".equalsIgnoreCase(user.getRole())) {
                 OwnerOperations owner = new OwnerOperations();
                 while (true) {
                    System.out.println("\n--- OWNER MENU ---");
                    System.out.println("1. View My Sites");
                    System.out.println("2. Request Site Update");
                    System.out.println("3. View My Requests");
                    System.out.println("4. Exit");
    
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> owner.viewMySites(ownerUid);
                        case 2 -> owner.requestSiteUpdate(ownerUid);
                        case 3 -> owner.viewMyRequests(ownerUid);
                        case 4 -> System.exit(0);
                        default -> System.out.println("Invalid choice");
                    }
                }
            } else {
                System.out.println("Invalid Owner UID");
            }
        }
    }
}
