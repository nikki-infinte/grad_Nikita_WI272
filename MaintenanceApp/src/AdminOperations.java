
import java.sql.*;
import java.time.Year;
import java.util.Scanner;

public class AdminOperations {

    private Scanner sc = new Scanner(System.in);

    /* ---------------- ADD OWNER ---------------- */
    public void addOwner() {

        System.out.print("Enter Owner Name: ");
        String name = sc.next();

        System.out.print("Enter Phone: ");
        String phone = sc.next();

        String sql
                = "INSERT INTO userDetails(name, phone, role) VALUES (?, ?, 'OWNER')";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();

            System.out.println("Owner added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding owner: " + e.getMessage());
        }
    }

    /* ---------------- VIEW USERS ---------------- */
    public void viewUsers() {

        String sql = "SELECT * FROM userDetails";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("UID | NAME | PHONE | ROLE");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("uid") + " | "
                        + rs.getString("name") + " | "
                        + rs.getString("phone") + " | "
                        + rs.getString("role")
                );
            }

        } catch (Exception e) {
            System.out.println("Error showing users: " + e.getMessage());
        }
    }

    /* ---------------- ADD SITE + MAINTENANCE ---------------- */
    public void addSite() {

        try {
            System.out.print("Enter Owner UID: ");
            int uid = sc.nextInt();

            System.out.print("Enter Length: ");
            int length = sc.nextInt();

            System.out.print("Enter Width: ");
            int width = sc.nextInt();

            System.out.print("Enter Site Type (VILLA / APARTMENT / INDEPENDENT_HOUSE / OPEN_SITE): ");
            String siteType = sc.next().toUpperCase();

            /* 🔑 Occupancy derived from site type */
            boolean isOccupied = !siteType.equals("OPEN_SITE");

            Connection con = DBConnection.getConnection();

            /* Insert site */
            String siteSql
                    = "INSERT INTO siteDetail(uid, length, width, site_type, is_occupied) "
                    + "VALUES (?, ?, ?, ?, ?) RETURNING sid";

            PreparedStatement sitePs = con.prepareStatement(siteSql);
            sitePs.setInt(1, uid);
            sitePs.setInt(2, length);
            sitePs.setInt(3, width);
            sitePs.setString(4, siteType);
            sitePs.setBoolean(5, isOccupied);

            ResultSet rs = sitePs.executeQuery();
            rs.next();
            int sid = rs.getInt("sid");

            System.out.println("Site added successfully.");

            /* Maintenance calculation */
            int area = length * width;
            double rate = isOccupied ? 9.0 : 6.0;
            double amount = area * rate;
            int year = Year.now().getValue();

            String maintSql
                    = "INSERT INTO maintenance1 "
                    + "(sid, maintenance_year, amount, status, payment_date) "
                    + "VALUES (?, ?, ?, 'PENDING', NULL)";

            PreparedStatement maintPs = con.prepareStatement(maintSql);
            maintPs.setInt(1, sid);
            maintPs.setInt(2, year);
            maintPs.setDouble(3, amount);
            maintPs.executeUpdate();

            System.out.println("Maintenance generated: Rs." + amount + " (PENDING)");

        } catch (Exception e) {
            System.out.println("Error adding site: " + e.getMessage());
        }
    }

    /* ---------------- VIEW SITES ---------------- */
    public void viewSites() {

        String sql = "SELECT * FROM siteDetail";

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("SID | UID | SIZE | TYPE | OCCUPIED");
            while (rs.next()) {

                int size = rs.getInt("length") * rs.getInt("width");

                System.out.println(
                        rs.getInt("sid") + " | "
                        + rs.getInt("uid") + " | "
                        + size + " | "
                        + rs.getString("site_type") + " | "
                        + rs.getBoolean("is_occupied")
                );
            }

        } catch (Exception e) {
            System.out.println("Error showing sites: " + e.getMessage());
        }
    }

    /* ---------------- VIEW PENDING MAINTENANCE ---------------- */
    public void viewPendingMaintenance() {

        String sql
                = "SELECT sid, maintenance_year, amount "
                + "FROM maintenance1 WHERE status = 'PENDING'";

        try (Connection con = DBConnection.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("SID | YEAR | AMOUNT | status");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("sid") + " | "
                        + rs.getInt("maintenance_year") + " | "
                        + rs.getDouble("amount") + " |  PENDING"
                );
            }

        } catch (Exception e) {
            System.out.println("Error showing pending maintenance: " + e.getMessage());
        }
    }

    /* ---------------- COLLECT MAINTENANCE ---------------- */
    public void collectMaintenance() {

        System.out.print("Enter Site ID: ");
        int sid = sc.nextInt();

        String sql
                = "UPDATE maintenance1 SET status = 'PAID', payment_date = CURRENT_DATE "
                + "WHERE sid = ? AND status = 'PENDING'";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, sid);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Maintenance collected successfully.");
            } else {
                System.out.println("No pending maintenance found.");
            }

        } catch (Exception e) {
            System.out.println("Error collecting maintenance: " + e.getMessage());
        }
    }

    public void viewPendingSiteRequests() {

        String sql
                = "SELECT request_id, sid, uid, new_length, new_width, new_site_type "
                + "FROM site_update_request WHERE status = 'PENDING'";

        try {
            Connection con = DBConnection.getConnection(); 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("REQ_ID | SID | UID | SIZE | TYPE");

            while (rs.next()) {
                int size
                        = rs.getInt("new_length") * rs.getInt("new_width");

                System.out.println(
                        rs.getInt("request_id") + " | "
                        + rs.getInt("sid") + " | "
                        + rs.getInt("uid") + " | "
                        + size + " | "
                        + rs.getString("new_site_type")
                );
            }

        } catch (Exception e) {
            System.out.println("Error viewing requests: " + e.getMessage());
        }
    }
    public void processSiteRequest() {

    System.out.print("Enter Request ID: ");
    int reqId = sc.nextInt();

    System.out.print("Approve or Reject (A/R): ");
    String action = sc.next().toUpperCase();

    try {
        Connection con = DBConnection.getConnection();
        

        if (action.equals("A")) {

            /* Apply update */
            String updateSiteSql =
                "UPDATE siteDetail SET " +
                "length = r.new_length, " +
                "width = r.new_width, " +
                "site_type = r.new_site_type, " +
                "is_occupied = CASE WHEN r.new_site_type = 'OPEN_SITE' THEN false ELSE true END " +
                "FROM site_update_request r " +
                "WHERE siteDetail.sid = r.sid AND r.request_id = ?";

            PreparedStatement ps1 = con.prepareStatement(updateSiteSql);
            ps1.setInt(1, reqId);
            ps1.executeUpdate();

            /* Mark request approved */
            String approveSql =
                "UPDATE site_update_request SET status = 'APPROVED' WHERE request_id = ?";

            PreparedStatement ps2 = con.prepareStatement(approveSql);
            ps2.setInt(1, reqId);
            ps2.executeUpdate();

            System.out.println("Request approved and site updated.");

        } else {

            String rejectSql =
                "UPDATE site_update_request SET status = 'REJECTED' WHERE request_id = ?";

            PreparedStatement ps = con.prepareStatement(rejectSql);
            ps.setInt(1, reqId);
            ps.executeUpdate();

            System.out.println("Request rejected.");
        }

    } catch (Exception e) {
        System.out.println("Error processing request: " + e.getMessage());
    }
}


}
