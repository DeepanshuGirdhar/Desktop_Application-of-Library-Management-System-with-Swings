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

/**
 *
 * @author deepanshu girdhar
 */
public class FineDB 
{
    public static int save(Integer roll_no,Integer book_id,Integer fine){
		int status=0;
               
		try{
			   Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
PreparedStatement ps=con.prepareStatement("insert into fine_details(Roll_NO,book_id,fine) values(?,?,?)");
			ps.setInt(1,roll_no);
			ps.setInt(2,book_id);
                        ps.setInt(3,fine);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
   public static int delete(int book_id,int student_id){
		int status=0;
		try{
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

			
			PreparedStatement ps=con.prepareStatement("delete from fine_details where Roll_No=? and book_id=?");
			ps.setInt(1,student_id);
                        ps.setInt(2,book_id);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
    
   public static int check(int student_id){
		int status=0;
		try{
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

			
			PreparedStatement ps=con.prepareStatement("select book_id from fine_details where Roll_No=?");
			ps.setInt(1,student_id);
			ResultSet rs=ps.executeQuery();
                        if(rs.next()){
				status=rs.getInt("book_id");
				//issued=rs.getInt("issued");
			}
                        con.close();
		}catch(Exception e){System.out.println(e);}
                System.out.println(status);
		return status;
	}
    
}
