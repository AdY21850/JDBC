import java.sql.*;

public class update {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/javaconnect";
        String username = "root";
        String password = "Arigato@20";
        String querry="select * from employee ;";
        String querry1 = "UPDATE employee\n" +
                "SET Name = 'Ayush' , job = 'Web Developer', salary= 100000\n" +
                "WHERE id = 7;";

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
                System.out.println("data updated !!! /n rows effeced =" + rows_effected);
            } else {
                System.out.println("unable to update data");
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
    }
}