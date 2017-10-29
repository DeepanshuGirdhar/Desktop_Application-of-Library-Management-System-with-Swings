/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

import static java.lang.String.format;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author deepanshu girdhar
 */
public class FineCalculatorDB {
    public static int calculate(int book_id,int student_id){
		int fine=0;
                String d_o_issued="";
		try{
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
PreparedStatement ps=con.prepareStatement("select Date_of_issue from issue_book where book_id=? and Roll_No=?");
			ps.setInt(1,book_id);
                        ps.setInt(2,student_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				
			 d_o_issued=rs.getString("Date_of_issue");
			}
                  System.out.println(d_o_issued);
			java.util.Date b = new java.util.Date();
				String current_date = b.toString();	
          	 SimpleDateFormat format = new SimpleDateFormat("E MMM dd hh:mm:ss Z yyyy");

    Date d1 = null;
    Date d2 = null;
    d1 = format.parse(d_o_issued);
    d2 = format.parse(current_date);
      /* long diff = d2.getTime() - d1.getTime();   
    long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
         System.out.println(diff);*/
    int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
     System.out.println(diffInDays);
     System.out.println("hello");
    if (diffInDays >= 30) {
        diffInDays=diffInDays+1;
        fine=diffInDays-30;    
        System.out.println("Difference in number of days (2) : " + diffInDays);
    }  
   /* else if (diffHours > 1) {
               
            System.out.println(">1");
            fine=diffHours+1;
         //   return false;
        } */
    }catch(Exception e)
                {System.out.println(e);
                //return false;
                }
    return fine;}
}
