package BankingManagementSystem;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Users {
    private Connection con;
    private Scanner sc;
    public Users(Connection con,Scanner sc){
    this.con=con;
    this.sc=sc;
    }
    public void register(){
        sc.nextLine();
        System.out.println("Full Name: ");
        String full_name=sc.nextLine();
        System.out.println
                ("Enter the email : ");
        String email=sc.nextLine();
        System.out .println("Enter password: ");
    }
}
