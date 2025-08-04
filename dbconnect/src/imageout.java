
import java.io.IOException;
import java.sql.*;
import java.io.FileOutputStream;
public class imageout {
    private static Connection con;
    private static String username ="root";
    private static String password="Arigato@20";
    private static String url="jdbc:mysql://localhost:3306/image";
    private static String path="C:\\Users\\adity\\Videos\\image\\";
    public static void main(String args[]) throws IOException, SQLException{
        String query="SELECT image from image where id=(?)";
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
            String image_path=path+"extracted image.jpg";
            FileOutputStream fo=new FileOutputStream(image_path);

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,2);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                System.out.println("data retrived");
                byte [] imageb=rs.getBytes("image");
                fo.write(imageb);
            }
            else{
                System.out.println("uanble to find image");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
