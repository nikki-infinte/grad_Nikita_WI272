import java.sql.*;
import java.util.Scanner;

public class OwnerOperations {

    private Scanner sc = new Scanner(System.in);

    /* ---------------- VIEW MY SITES ---------------- */
    public void viewMySites(int ownerUid) {

        String sql = "SELECT * FROM siteDetail WHERE uid = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ownerUid);

            ResultSet rs = ps.executeQuery();

            System.out.println("SID | SIZE | TYPE | OCCUPIED");
            while (rs.next()) {

                int size =
                    rs.getInt("length") * rs.getInt("width");

                System.out.println(
                    rs.getInt("sid") + " | " +
                    size + " | " +
                    rs.getString("site_type") + " | " +
                    rs.getBoolean("is_occupied")
                );
            }

        } catch (Exception e) {
            System.out.println("Error viewing sites: " + e.getMessage());
        }
    }

    /* ---------------- REQUEST SITE UPDATE ---------------- */
    public void requestSiteUpdate(int ownerUid) {

        try {
            System.out.print("Enter Site ID: ");
            int sid = sc.nextInt();

            System.out.print("New Length: ");
            int length = sc.nextInt();

            System.out.print("New Width: ");
            int width = sc.nextInt();

            System.out.print(
                "New Type (VILLA / APARTMENT / INDEPENDENT_HOUSE / OPEN_SITE): "
            );
            String type = sc.next().toUpperCase();

            String sql =
                "INSERT INTO site_update_request " +
                "(sid, uid, new_length, new_width, new_site_type) " +
                "VALUES (?, ?, ?, ?, ?)";

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, sid);
            ps.setInt(2, ownerUid);
            ps.setInt(3, length);
            ps.setInt(4, width);
            ps.setString(5, type);

            ps.executeUpdate();

            System.out.println("Update request sent (PENDING approval).");

        } catch (Exception e) {
            System.out.println("Error requesting update: " + e.getMessage());
        }
    }

    /* ---------------- VIEW MY REQUEST STATUS ---------------- */
    public void viewMyRequests(int ownerUid) {

        String sql =
            "SELECT request_id, sid, status, request_date " +
            "FROM site_update_request WHERE uid = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ownerUid);

            ResultSet rs = ps.executeQuery();

            System.out.println("REQ_ID | SID | STATUS | DATE");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("request_id") + " | " +
                    rs.getInt("sid") + " | " +
                    rs.getString("status") + " | " +
                    rs.getDate("request_date")
                );
            }

        } catch (Exception e) {
            System.out.println("Error viewing requests: " + e.getMessage());
        }
    }
}