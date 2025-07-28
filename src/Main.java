import com.mysql.cj.protocol.Resultset;

import java.sql.*;



public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/javaconnect";
        String username = "root";
        String password = "Arigato@20";
        String querry = "select * from employee;";
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
            System.out.println("connection stablished succesfully !!");
            Statement stmt=con.createStatement();
            ResultSet rs= stmt.executeQuery(querry);
            System.out.println("==========================================");
            System.out.println("id"+"   "+"|"+"  "+"name"+"  "+"|"+"  "+"job"+"  "+"|"+"  "+"salary");
            while (rs.next())
            {
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String job=rs.getString("job");
                double salary=rs.getDouble("salary");


                System.out.println("-------------------------------------------");
                System.out.println(id+"   "+"|"+"  "+name+"  "+"|"+"  "+job+"  "+"|"+"  "+salary);
            }
            rs.close();
            stmt.close();
            con.close();
            System.out.println("connection closed !!!!!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
