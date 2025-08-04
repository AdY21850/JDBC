package BankingManagementSystem;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;
public class AccountManager {
    private Connection con;
    private Scanner sc;
    AccountManager(Connection con, Scanner sc){
         this.con=con;
         this.sc=sc;
    }
    public void credit_money(long account_number)throws SQLException{
        sc.nextLine();
        System.out.println("enter the ammount : ");
        double ammount=sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter the Security pin");
        String security_pin=sc.nextLine();

            try{
                con.setAutoCommit(false);
                if (account_number !=0){
                    PreparedStatement ps=con.prepareStatement("SELECT * FROM accounts WHERE account_numer =? and security_pin=?");
                    ps.setLong(1,account_number);
                    ps.setString(2,security_pin);
                    ResultSet resultset=ps.executeQuery();
                    if (resultset.next()){
                        String credit_query="UPDATE Accounts SET balance=balance+? WHERE account_number=?";
                        PreparedStatement ps2=con.prepareStatement(credit_query);
                        ps2.setDouble(1,ammount);
                        ps2.setLong(2,account_number);
                        int rowsaffected=ps.executeUpdate();
                        if (rowsaffected>0){
                            System.out.println("Rs."+ammount+"creditted succesfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        }else{
                            System.out.println("Invalid Security pin");
                        }
                    }
            }
    } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public void debit_money(long account_number)throws SQLException{
        sc.nextLine();
        System.out.println("enter the ammount : ");
        double ammount=sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter the Security pin");
        String security_pin=sc.nextLine();

        try{
            con.setAutoCommit(false);
            if (account_number !=0){
                PreparedStatement ps=con.prepareStatement("SELECT * FROM accounts WHERE account_numer =? and security_pin=?");
                ps.setLong(1,account_number);
                ps.setString(2,security_pin);
                ResultSet resultset=ps.executeQuery();
                if (resultset.next()){
                    double current_balance=resultset.getDouble("balance");
                    if (ammount<current_balance) {
                        String debit_query = "UPDATE Accounts SET balance=balance-? WHERE account_number=?";
                        PreparedStatement ps2 = con.prepareStatement(debit_query);
                        ps2.setDouble(1, ammount);
                        ps2.setLong(2, account_number);
                        int rowsaffected = ps.executeUpdate();
                        if (rowsaffected > 0) {
                            System.out.println("Rs." + ammount + "debitted succesfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Transaction Failed!");
                            con.rollback();
                            con.setAutoCommit(true);
                        }

                    }else{
                        System.out.println("insufficient Balance");
                    }
                }else{
                    System.out.println("Invalid pin!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        con.setAutoCommit(true);
    }
    public void transfer_money(long sender_account_number)throws SQLException{
        sc.nextLine();
        System.out.println("Enter the recievers Accoount number: ");
        long recivers_account_number=sc.nextLong();
        System.out.println("Enter Amount: ");
        double amount=sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter the security pin");
        String pin=sc.nextLine();
        try{
            con.setAutoCommit(false);
            if (sender_account_number!=0 && recivers_account_number!=0){
                PreparedStatement ps=con.prepareStatement("SELECT * FROM Account WHERE account_number = ? AND security_pin=?");
                ps.setLong(1,sender_account_number);
                ps.setString(2,pin);
                ResultSet resultset=ps.executeQuery();
                if (resultset.next()){
                    double current_balance=resultset.getDouble("balance");
                    if (amount<=current_balance){
                        String debit_query = "UPDATE Accounts SET balance=balance-? WHERE account_number=?";
                        String credit_query="UPDATE Accounts SET balance=balance+? WHERE account_number=?";
                        PreparedStatement creditps=con.prepareStatement(credit_query);
                        PreparedStatement debitps=con.prepareStatement(debit_query);



                        creditps.setDouble(1,amount);
                        creditps.setLong(2,recivers_account_number);
                        debitps.setDouble(1,amount);
                        debitps.setLong(2,sender_account_number);
                        int rowsaffected=debitps.executeUpdate();
                        int rowsaffected2=creditps.executeUpdate();
                        if (rowsaffected>0 && rowsaffected2>0){
                            System.out.println("Transaction Succesfull!!");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        }else{
                            System.out.println("Transaction Failed!!");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    }else{
                        System.out.println("Insufficient Balance");
                    }
                }else{
                    System.out.println("Invalid Security pin");
                }
            }else{
                System.out.println("Invalid account number");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        con.setAutoCommit(true);
    }
    public void getBalance(long account_number){
        sc.nextLine();
        System.out.print("Enter the Security pin");
        String pin=sc.nextLine();
        try{
            PreparedStatement ps=con.prepareStatement("SELECT balance FROM Accouts WHERE account_number=? AND security_pin=?");
            ps.setLong(1,account_number);
            ps.setString(2,pin);
            ResultSet resultset=ps.executeQuery();
            if(resultset.next()){
                double balance=resultset.getDouble("balance");
                System.out.println("Available Balance = "+balance);
            }else{
                System.out.println("INvalid pin");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}