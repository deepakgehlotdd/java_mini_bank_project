package bank_details;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class Bank {
	int accNumber;
	String pan;
	String name;
	int balance;
	
	Scanner sc=new Scanner(System.in);
	Bank()
	{
		balance=-1;
		accNumber=-1;
	}
	int createAccount() {
		System.out.print("Enter PAN number: ");  
		pan = sc.next();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			Statement stmt = con.createStatement();
			
	        String sql="select pan from banktable where pan=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, pan);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				return -1;
			}
			
			String sql3="select max(accnumber) from banktable";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ResultSet rs3=ps3.executeQuery();
			
			if(rs3.next()) {
				String ap=rs3.getString(1);
				accNumber=Integer.parseInt(ap)+1;
			}
			
			System.out.print("Enter Name: ");  
	        name = sc.next();  
	        System.out.print("Enter Balance: ");  
	        balance = sc.nextInt();  
	        
	        
	        String sql4="insert into banktable(accnumber,pan,name,balance) values(?,?,?,?)";
	        PreparedStatement ps4 = con.prepareStatement(sql4);
	        ps4.setInt(1, accNumber);
	        ps4.setString(2, pan);
	        ps4.setString(3, name);
	        ps4.setInt(4, balance);
	        int res=ps4.executeUpdate();
	        	
	       con.close();
	       
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
        return accNumber;
	}
	
	int checkBalance(){
		System.out.print("Enter Account No. : ");  
		accNumber = sc.nextInt();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			String sql="select balance from banktable where accnumber=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accNumber);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				balance=rs.getInt("balance");
			}
			con.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}
	
	int withdrawal()
	{
		System.out.print("Enter Account No. : ");  
		accNumber = sc.nextInt();
	    System.out.println("Enter the amount you want to withdraw: ");  
	    int amount = sc.nextInt();  
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			
			/*
			String sql="select balance from banktable where accnumber=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accNumber);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				return -1;
			}
			int avb=rs.getInt(1);
			
			if(avb<amount)
			{
				System.out.println("Available balance is less then your withdrawal amount");
				return -1;
			}
			*/
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("Update Banktable set balance = balance - "+amount+" where "+ "accnumber= "+accNumber);
			
			ResultSet rs2=stmt.executeQuery("select balance from banktable where accnumber = "+accNumber);
			if(rs2.next())
			balance=rs2.getInt("balance");
			con.close();
			
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	
		return balance;
	}
	
	int deposite(){
		
		System.out.print("Enter Account No. : ");  
		accNumber = sc.nextInt();
	    System.out.println("Enter the amount you want to deposit: ");  
	    int amount = sc.nextInt();  
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			
			Statement stmt = con.createStatement();
			stmt.executeUpdate("Update Banktable set balance = balance + "+amount+" where "+ "accnumber= "+accNumber);
			
			ResultSet rs=stmt.executeQuery("select balance from banktable where accnumber = "+accNumber);
			if(rs.next())
			balance=rs.getInt("balance");
			con.close();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}
	void showData()
	{
		System.out.print("Enter Account No. : ");  
		accNumber = sc.nextInt();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			
			String sql="select * from banktable where accnumber=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, accNumber);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("------------------------------------------");
				System.out.println("Account number : "+rs.getString(1) );
				System.out.println("PAN number : "+rs.getString(2) );
				System.out.println("Account holder Name : "+rs.getString(3) );
				System.out.println("Available balance : "+rs.getString(4) );
				System.out.println("------------------------------------------");
			}
			else
			{
				System.out.println("No account exists of this account number");
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*void showAll()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String userName = "scott";
			String password = "tiger";
			
			Connection con = DriverManager.getConnection(url, userName, password);
			
			String sql="select * from banktable";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			int flag=0;
			if(rs.next()) {
				flag=1;
				System.out.println("------------------------------------------");
				System.out.println("Account number : "+rs.getString(1) );
				System.out.println("PAN number : "+rs.getString(2) );
				System.out.println("Account holder Name : "+rs.getString(3) );
				System.out.println("Available balance : "+rs.getString(4) );
				System.out.println("------------------------------------------");
			}
			if(flag==0)
			{
				System.out.println("No account exists in bank");
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}
