package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;
import java.sql.PreparedStatement;



public class BankApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         String url = "jdbc:mysql://localhost:3306/banksbi";
         String USER_NAME = "Bhanu";
         String PASSWORD = "Bhanu@25";
         Connection con = null;
         
         try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");
        	 con = DriverManager.getConnection(url,USER_NAME,PASSWORD);
        	 Scanner scan = new Scanner(System.in);
        	 
        	 
        	 //Login module 
        	 System.out.println("<<---- Welcome to SBI BANK ---->>>");
             System.out.println("Enter Account Number:");
             int Acc_num = scan.nextInt();
             System.out.println("Enter pin:");
             int pin = scan.nextInt();
             PreparedStatement  pstmt1 = con.prepareStatement("Select*from account where " + " acc_num = ? and pin = ? " );
             pstmt1.setInt(1,Acc_num);
             pstmt1.setInt(2, pin);
             ResultSet res1 = pstmt1.executeQuery();
             res1.next();
             String name = res1.getString(2);
             int bal = res1.getInt(4);
             System.out.println("welcome "+ name);
             System.out.println("Available balance is : "+ bal);
             
             
             
//             Transfer module
             System.out.println("<<---- Transfer Details --->>");
             System.out.println("enter the  benificiary account number:");
             int bacc_num = scan.nextInt();
             System.out.println("Enter the transfer amount:");
            int t_amount =scan.nextInt();
            
            con.setAutoCommit(false);
            
            Savepoint s =  con.setSavepoint();
            
            PreparedStatement  pstmt2 = con.prepareStatement(" UPDATE account SET balance = balance -? " + " where Acc_num =? ");
            pstmt2.setInt(1,t_amount);
            pstmt2.setInt(2, Acc_num);
            pstmt2.executeUpdate();
            
            System.out.println("<--- Incoming Credit Request -->");
            System.out.println(name + "account no"+ Acc_num + "wants to transfer "+ t_amount);
            System.out.println( "press Y to receieve:");
            System.out.println("Press N to  reject");
            String choice = scan.next();
            
            
            
            if(choice.equals("Y")) {
            	PreparedStatement  pstmt3 = con.prepareStatement(" UPDATE account SET balance = balance + ?" 
            			+ " where Acc_num =? ");
            	pstmt3.setInt(1, t_amount);
            	pstmt3.setInt(2, bacc_num);
            	pstmt3.executeUpdate();
            	PreparedStatement  pstmt4 = con.prepareStatement(" select *From account where Acc_num =? ");
            	pstmt4.setInt(1, bacc_num);
            	ResultSet res2 = pstmt4.executeQuery();
            	res2.next();
            	System.out.println("Update balance  is:"+res2.getInt(4));
            	
            }
            else {
            	con.rollback(s);
            	PreparedStatement  pstmt5 = con.prepareStatement(" select * from account "+ "where Acc_num =? ");
            	pstmt5.setInt(1,bacc_num);
            	ResultSet res2 = pstmt5.executeQuery();
            	res2.next();
            	System.out.println( "Existing balance is:" + res2.getInt(4));
            }
            con.commit();
            
             
         }
         catch( Exception e) {
        	 e.printStackTrace();
        	 
         }
         
	}

}
