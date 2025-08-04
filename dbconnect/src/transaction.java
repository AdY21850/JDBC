import java.util.Scanner;
import java.sql.*;
public class transaction {
    private static String url = "jdbc:mysql://localhost:3306/javaconnect";
    private static String username = "root";
    private static String password = "Arigato@20";
    private static Connection con;

    public static void main(String args[]) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("drivers loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("unable to load drivers    " + e.getMessage());
        }

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("connection Stablishedm Successfully !!!!!    :)");
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("unable to Stablish connection  !!!! :)" + e.getMessage());
        }
        try {
            try {
                String querry1 = "UPDATE employee SET salary =salary-? WHERE id=?";
                String querry2 = "UPDATE employee SET salary =salary+? WHERE id=?";
                PreparedStatement wdrw = con.prepareStatement(querry1);
                PreparedStatement dpst = con.prepareStatement(querry2);
                wdrw.setDouble(1, 300);
                wdrw.setInt(2, 1);
                dpst.setDouble(1, 300);
                dpst.setInt(2, 9);
                wdrw.executeUpdate();
                dpst.executeUpdate();
                con.commit();
                System.out.println("transaction Succesfulll !!!!!");
            } catch (SQLException e) {
                con.rollback();
                System.out.println("rolled back the transaction succesfully");
            }
        } catch (Exception e) {
            System.out.println("transaction failed" + e.getMessage());
        }
    }
}