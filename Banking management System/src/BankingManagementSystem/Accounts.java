package BankingManagementSystem;
import java.sql.*;
import java.util.Scanner;
public class Accounts {
    private Connection con;
    private Scanner sc;
    public Accounts(Connection con,Scanner sc){
        this.con=con;
        this.sc=sc;
    }
    public long open_account(String email){
        if (!account_exist(email)){
            String open_account_query="INSERT INTO Accounts(account_number,full_name,email,balance,security_pin) VALUES (?,?,?,?,?)";
            sc.nextLine();
            System.out.println("Enter full name:");
            String full_name=sc.nextLine();
            System.out.println("enter Security pin");
            String Security_pin=sc.nextLine();
            System.out.println("enter initial ammount");
            double balance =sc.nextDouble();
            try{
                long account_number=generateAccountNumber();
                PreparedStatement ps=con.prepareStatement(open_account_query);
               ps.setLong(1,account_number);
               ps.setString(2,full_name);
               ps.setString(3,email);
               ps.setDouble(4,balance);
               ps.setString(5,Security_pin);
               int rowsaffected = ps.executeUpdate();
               if (rowsaffected>0){
                   return account_number;
                }else{
                   throw new RuntimeException("Account Creation Failed!!");
            }
        } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("Account Already Exist");
    }
    public long getAccount_number(String email){
        String query ="SELECT account_number FROM Accounts WHERE email=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                return rs.getLong("account_number");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("Account Number Doesn't Exists");
    }
    private long generateAccountNumber(){
        try{
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT account_number FROM Accounts ORDER BY account_number DESC LIMIT 1");
            if (rs.next()){
                long last_account_number =rs.getLong("account_number");
                return last_account_number+1;
            }else{
                return 10000100;
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return 10000100;
    }
    public boolean account_exist(String email){
        String query="SELECT account_number FROM Accounts WHERE email=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
