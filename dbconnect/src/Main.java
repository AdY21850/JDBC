import com.mysql.cj.protocol.Resultset;

import java.sql.*;



public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/javaconnect";
        String username = "root";
        String password = "Arigato@20";
        String querry = "select * from employee;";
        String querry2 = "INSERT INTO employee(id,name,job,salary) values(10,'Shubhya','front end dev',100000);";
        String querry1 = "DELETE from employee where id = 10;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("vroom vroom ");
            System.out.println("drivers loaded succesfullyv !!!");
        } catch (ClassNotFoundException e) {
            System.out.println(" class not found" + e.getMessage());
        }
        System.out.println("vroom vroom !!");
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection1 stablished succesfully !!");
            Statement stmt1 = con.createStatement();
            int rows_effected = stmt1.executeUpdate(querry1);
            if (rows_effected > 0) {
                System.out.println("data inserted !!! \nrows effeced =" + rows_effected);
            } else {
                System.out.println("unable to insert data");
            }
            stmt1.close();
            con.close();
            System.out.println("connection1 closed !!!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection stablished succesfully !!");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(querry);
            System.out.println("==========================================");
            System.out.println("id" + "   " + "|" + "  " + "name" + "  " + "|" + "  " + "job" + "  " + "|" + "  " + "salary");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String job = rs.getString("job");
                double salary = rs.getDouble("salary");


                System.out.println("-------------------------------------------");
                System.out.println(id + "   " + "|" + "  " + name + "  " + "|" + "  " + job + "  " + "|" + "  " + salary);
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("connection closed !!!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
                Connection con1 = DriverManager.getConnection(url, username, password);
                System.out.println("connection2 stablished succesfully !!");
                Statement stmt1 = con1.createStatement();
                int rows_effected = stmt1.executeUpdate(querry2);
                if (rows_effected > 0) {
                    System.out.println("data deleted !!! \nrows effeced =" + rows_effected);
                } else {
                    System.out.println("unable to delete data");
                }
                stmt1.close();
                con1.close();
                System.out.println("connection2 closed !!!!!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                Connection con2 = DriverManager.getConnection(url, username, password);
                System.out.println("connection stablished succesfully !!");
                Statement stmt2 = con2.createStatement();
                ResultSet rs2 = stmt2.executeQuery(querry);
                System.out.println("==========================================");
                System.out.println("id" + "   " + "|" + "  " + "name" + "  " + "|" + "  " + "job" + "  " + "|" + "  " + "salary");
                while (rs2.next()) {
                    int id = rs2.getInt("id");
                    String name = rs2.getString("name");
                    String job = rs2.getString("job");
                    double salary = rs2.getDouble("salary");


                    System.out.println("-------------------------------------------");
                    System.out.println(id + "   " + "|" + "  " + name + "  " + "|" + "  " + job + "  " + "|" + "  " + salary);
                }
                rs2.close();
                stmt2.close();
                con2.close();
                System.out.println("connection closed !!!!!");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

    }
}
