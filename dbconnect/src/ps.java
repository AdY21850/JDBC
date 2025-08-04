import java.sql.*;
import java.util.Scanner;
public class ps {
    private static String url="jdbc:mysql://localhost:3306/javaconnect";
    private static String username="root";
    private static String password="Arigato@20";
    private static Connection con;
    public static void main(String args[]){
        String query="INSERT INTO employee(id,name,job,salary) VALUES(?,?,?,?);";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded successfully!!!!!     :)");
        } catch (ClassNotFoundException e) {
            System.out.println("unable to Load drivers!!!!!!!!!!!!!!!!!!          :(");
        }
        try{
             con=DriverManager.getConnection(url,username,password);
            System.out.println("connetion stablished succesfully");

        } catch (SQLException e) {
            System.out.print("Unable to stablish connection");
        }
        try{
            Scanner sc=new Scanner(System.in);
            System.out.println("enter the id ");
            int id=sc.nextInt();
            sc.nextLine();
            System.out.println("enter the name ");
            String name =sc.nextLine();
            System.out.println("enter the job ");
            String job=sc.nextLine();
            System.out.println("enter the salary");
            double salary=sc.nextDouble();

            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,job);
            ps.setDouble(4,salary);
            int affectedrows =ps.executeUpdate();
            if (affectedrows>0){
                System.out.println("data entered succesfully !!!!!!!!!!!! :)");
            }
            else{
                System.out.println("unable to insert data");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
