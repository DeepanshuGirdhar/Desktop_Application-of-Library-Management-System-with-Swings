/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author deepanshu girdhar
 */
public class Notification {
    
    static int roll_no;
    static String doi,email;
    public static void main(String arg[])
    {
       
    try{
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

	PreparedStatement ps=con.prepareStatement("select * from issue_book");
			ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                 System.out.println("in issue book table");
               roll_no=rs.getInt("Roll_No");
               doi=rs.getString("Date_of_issue");
               CalculateDays(doi);
            }
}
catch(Exception e)
{System.out.println(e);}
			
    }
    public static void CalculateDays(String d_o_issued)
    {
       try{
            
        java.util.Date b = new java.util.Date();
				String current_date = b.toString();	
          	 SimpleDateFormat format = new SimpleDateFormat("E MMM dd hh:mm:ss Z yyyy");

    Date d1 = null;
    Date d2 = null;
    d1 = format.parse(d_o_issued);
    d2 = format.parse(current_date);
    int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
     System.out.println("in calculate function     "+diffInDays);
    // diffInDays=diffInDays+1;
     System.out.println("number of days (2) : " + diffInDays);
    if (diffInDays == 28) {
        
        try{
             String warn_message="Hi "+roll_no+" !"+" Return Your Book within next two days otherwise you have to pay fine on your book";
       
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
PreparedStatement ps=con.prepareStatement("select Email_address from notification_details where Roll_No=?");
			ps.setInt(1,roll_no);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				 System.out.println("in notification table");
			 email=rs.getString("Email_address");
                         EmailSend.Sendmail(roll_no,email,warn_message);
			}
                        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    } 
    else
    {
        System.out.println("number of days : " + diffInDays);
    }
    }
        catch(Exception e)
        {
            System.out.println(e);
        }
    
}
    public static void issue_notification(int roll_no,String message)
    {
          try{
             String issue_message=message;
       
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
PreparedStatement ps=con.prepareStatement("select Email_address from notification_details where Roll_No=?");
			ps.setInt(1,roll_no);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				 System.out.println("in issue_notification table");
			 email=rs.getString("Email_address");
                         EmailSend.Sendmail(roll_no,email,issue_message);
			}
                        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    
    }
    
     public static void return_notification(int roll_no,String message)
    {
          try{
             String return_message=message;
       
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
PreparedStatement ps=con.prepareStatement("select Email_address from notification_details where Roll_No=?");
			ps.setInt(1,roll_no);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				 System.out.println("in return_notification table");
			 email=rs.getString("Email_address");
                         EmailSend.Sendmail(roll_no,email,return_message);
			}
                        
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    
    }
    
}