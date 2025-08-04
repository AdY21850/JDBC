
import java.io.IOException;
import java.sql.*;
import java.io.FileInputStream;
public class image {
    private static Connection con;
    private static String username ="root";
    private static String password="Arigato@20";
    private static String url="jdbc:mysql://localhost:3306/image";
    private static String path="C:\\Users\\adity\\Pictures\\mehndi pics niharika didi\\IMG-20250219-WA0016.jpg";
    public static void main(String args[]) throws IOException, SQLException{
        String query="INSERT into image (image) values (?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("drivers loaded succesfully !!!!!!!!!!!!!!!!!!!!!! :) ");
        } catch (ClassNotFoundException e) {
            System.out.println("unable to load drivers");
        }
        try{
             con=DriverManager.getConnection(url,username,password);
            System.out.println("connection stablished !!!!!!!!!!!! :)");

        } catch (SQLException e) {
            System.out.println("unable to stablish connection ");
        }
        try{
            FileInputStream fi=new FileInputStream(path);
            byte [] imageb=new byte[fi.available()];
            fi.read(imageb);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setBytes(1,imageb);
            int affectedrows=ps.executeUpdate();
            if (affectedrows>0){
                System.out.println("data inserted\nrows affected ="+affectedrows);
            }
            else{
                System.out.println("uanble to insert data");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
