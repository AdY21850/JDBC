import java.util.Scanner;
import java.sql.*;
public class batch {
    private static String url="jdbc:mysql://localhost:3306/javaconnect";
    private static String username ="root";
    private static String password="Arigato@20";
    private static Connection con;
    public static void main(String args[])throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("drivers loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("unable to load drivers    "+e.getMessage());
        }

        try{
             con=DriverManager.getConnection(url,username,password);
            System.out.println("connection Stablishedm Successfully !!!!!    :)");
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("unable to Stablish connection  !!!! :)"+e.getMessage());
        }
        try{
            String query="INSERT INTO employee(id,name,job,salary) VALUES(?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            Scanner sc=new Scanner (System.in);
            while (true){
                System.out.println("enter the id");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("enter the name");
                String name=sc.nextLine();
                System.out.println("enter the job");
                String job=sc.nextLine();
                System.out.println("enter the salary");
                double salary=sc.nextDouble();
                ps.setInt(1,id);
                ps.setString(2,name);
                ps.setString(3,job);
                ps.setDouble(4,salary);
                ps.addBatch();
                System.out.println("add More values ? y/n");
                sc.nextLine();
                String des=sc.nextLine();
                if(des.toUpperCase().equals("N")){
                    break;
                }
            }
            int[] BR=ps.executeBatch();
            con.commit();
            System.out.println("batch executed succesfully!!!!!     :)))) ");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
