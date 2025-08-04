package BankingManagementSystem;
import java.sql.*;
import java.util.Scanner;
import static java.lang.Class.forName;
public class BankingApp {
    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String username = "root";
    private static final String password = "Arigato@20";
    private static Connection con;
    private static Scanner sc;
    public static void main(String args[])throws ClassNotFoundException,SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded succesfully");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try{
             con=DriverManager.getConnection(url,username,password);
            System.out.println("connection Stablished succesfully !!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
             sc=new Scanner (System.in);
            Users user=new Users(con,sc);
            Accounts accounts=new Accounts(con,sc);
            AccountManager accountmanager=new AccountManager(con,sc);
            String email;
            long account_number;
            while(true){
                System.out.println("*** welcome to Bank of Baroda***");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter your Choise");
                int choise=sc.nextInt();
                switch(choise){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email=user.login();
                        if (email!=null){
                            System.out.println();
                            System.out.println("User Logged in!!");
                            if (!accounts.account_exist(email)){
                                System.out.println();
                                System.out.println("1. Open a new bank account");
                                System.out.println("2. Exit");
                                if (sc.nextInt()==1){
                                    account_number=accounts.open_account(email);
                                    System.out.println("Account created succesfully");
                                    System.out.println("Your Account number is: "+account_number);
                                }else{
                                    break;
                                }
                            }
                            account_number=accounts.getAccount_number(email);
                            int choise2=0;
                            while(choise2!=5){
                                System.out.println();
                                System.out.println("1.Debit Money");
                                System.out.println("2.credit Money");
                                System.out.println("3.Transfer Money");
                                System.out.println("4.check balance");
                                System.out.println("5. Log Out");
                                choise2=sc.nextInt();
                                switch(choise2){
                                    case 1:
                                        accountmanager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountmanager.credit_money(account_number);
                                        break;
                                    case 4:
                                        accountmanager.getBalance(account_number);
                                        break;
                                    case 3:
                                        accountmanager.transfer_money(account_number);
                                        break;
                                    case 5:
                                        break;

                                    default:
                                        System.out.println("Enter valid choice");
                                        break;

                                }
                            }
                        }else{
                            System.out.println("INcorrect email or passwoed");
                        }
                    case 3:
                        System.out.println("Thanks for using Bank of Baroda!!");
                        System.out.println("Exiting the System");
                        return;
                        default:
                    System.out.println("enter the valid choise");
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
