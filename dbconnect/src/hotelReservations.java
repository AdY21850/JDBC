import java.sql.*;
import java.util.Scanner;

public class hotelReservations {
    private static final String password = "Arigato@20";
    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            while (true) {
                System.out.println();
                System.out.println("Welcome to Sharma Residency");
                System.out.println("Enter 1 for new entry");
                System.out.println("Enter 2 for deletion entry");
                System.out.println("Enter 3 to update entry");
                System.out.println("Enter 4 to get room number");
                System.out.println("Enter 5 for list of reservations");
                System.out.println("Enter 0 for exit");
                System.out.print("Choose an option: ");
                int n = sc.nextInt();
                sc.nextLine(); // Clear buffer

                switch (n) {
                    case 1:
                        reserveroom(con, sc);
                        break;
                    case 2:
                        deletereservation(con, sc);
                        break;
                    case 3:
                        updatereservation(con, sc);
                        break;
                    case 4:
                        getroomnumber(con, sc);
                        break;
                    case 5:
                        viewerservation(con);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Input!");
                }
            }
        } catch (SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void reserveroom(Connection con, Scanner sc) {
        try {
            System.out.print("Enter the guest name: ");
            String guestname = sc.nextLine();
            System.out.print("Enter room number: ");
            int roomnumber = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter the contact number: ");
            String contactNumber = sc.nextLine();

            String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES ('" +
                    guestname + "', " + roomnumber + ", '" + contactNumber + "')";

            try (Statement statement = con.createStatement()) {
                int rowsAffected = statement.executeUpdate(sql);
                System.out.println(rowsAffected > 0 ? "Reservation Successful!" : "Reservation Failed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deletereservation(Connection con, Scanner sc) {
        try {
            System.out.print("Enter the reservation ID to delete: ");
            int reservationid = sc.nextInt();

            if (!reservationExists(con, reservationid)) {
                System.out.println("Reservation not found!");
                return;
            }

            String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationid;
            try (Statement statement = con.createStatement()) {
                int rowsAffected = statement.executeUpdate(sql);
                System.out.println(rowsAffected > 0 ? "Delete Successful!" : "Unable to delete!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatereservation(Connection con, Scanner sc) {
        try {
            System.out.print("Enter the reservation ID to update: ");
            int reservationid = sc.nextInt();
            sc.nextLine();

            if (!reservationExists(con, reservationid)) {
                System.out.println("Reservation not found!");
                return;
            }

            System.out.print("Enter new guest name: ");
            String newguestname = sc.nextLine();
            System.out.print("Enter new room number: ");
            int newroomnumber = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter new contact number: ");
            String newcontactnumber = sc.nextLine();

            String sql = "UPDATE reservations SET guest_name = '" + newguestname + "', room_number = " +
                    newroomnumber + ", contact_number = '" + newcontactnumber +
                    "' WHERE reservation_id = " + reservationid;

            try (Statement statement = con.createStatement()) {
                int rowsAffected = statement.executeUpdate(sql);
                System.out.println(rowsAffected > 0 ? "Update Successful!" : "Update Failed!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getroomnumber(Connection con, Scanner sc) {
        try {
            System.out.print("Enter reservation ID: ");
            int reservationid = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter guest name: ");
            String guestname = sc.nextLine();

            String sql = "SELECT room_number FROM reservations WHERE reservation_id = " + reservationid +
                    " AND guest_name = '" + guestname + "'";
            try (Statement statement = con.createStatement()) {
                ResultSet rs = statement.executeQuery(sql);
                if (rs.next()) {
                    int roomnumber = rs.getInt("room_number");
                    System.out.println("Room number for reservation ID " + reservationid +
                            " and guest " + guestname + " is: " + roomnumber);
                } else {
                    System.out.println("Room not found!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewerservation(Connection con) {
        String sql = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations";
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("Current Reservations:");
            System.out.printf("+----------------+----------------------+---------------+----------------------+---------------------+\n");
            System.out.printf("| Reservation ID | Guest Name           | Room Number   | Contact Number        | Reservation Date     |\n");
            System.out.printf("+----------------+----------------------+---------------+----------------------+---------------------+\n");

            while (rs.next()) {
                System.out.printf("| %-14d | %-20s | %-13d | %-20s | %-19s |\n",
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_number"),
                        rs.getString("contact_number"),
                        rs.getTimestamp("reservation_date").toString());
            }
            System.out.println("+----------------+----------------------+---------------+----------------------+---------------------+");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exit() throws InterruptedException {
        System.out.println("Exiting System");
        int i = 5;
        while (i-- > 0) {
            System.out.print(".");
            Thread.sleep(400);
        }
        System.out.println("\nThanks for your coordination and support!");
    }

    private static boolean reservationExists(Connection con, int reservationid) {
        String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationid;
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
