/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

/**
 *
 * @author deepanshu girdhar
 */
import java.sql.*;
public class IssueBookDB {
	
public static boolean checkBook(int book_id){
	boolean status=false;
	try{
		Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
PreparedStatement ps=con.prepareStatement("select * from add_book where book_id=?");
		ps.setInt(1,book_id);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}

public static int save(int book_id,int student_id,String studentname,String course){
	int status=0;
	try{
            Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

		status=updatebook(book_id);//updating quantity and issue
		
		if(status>0){
                    java.util.Date b = new java.util.Date();
				String var = b.toString();			 
		 
		PreparedStatement ps=con.prepareStatement("insert into issue_book(book_id,Roll_No,Book_Name,course,Date_of_issue) values(?,?,?,?,?)");
		ps.setInt(1,book_id);
		ps.setInt(2,student_id);
		ps.setString(3,studentname);
		ps.setString(4,course);
                ps.setString(5,var);
		status=ps.executeUpdate();
		}
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int updatebook(int book_id){
	int status=0;
	int quantity=0,issued=0;
	try{
		Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

		PreparedStatement ps=con.prepareStatement("select quantity,issued from add_book where book_id=?");
		ps.setInt(1,book_id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("quantity");
			issued=rs.getInt("issued");
		}
		
		if(quantity>0){
		PreparedStatement ps2=con.prepareStatement("update add_book set quantity=?,issued=? where book_id=?");
		ps2.setInt(1,quantity-1);
		ps2.setInt(2,issued+1);
		ps2.setInt(3,book_id);
		
		status=ps2.executeUpdate();
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static boolean student_validate(int book_id,int student_id){
		//boolean status=false;
		try{   
                     Class.forName("com.mysql.jdbc.Driver");
  
    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

            PreparedStatement pst;
            pst = conn.prepareStatement("SELECT * FROM issue_book where book_id=? and Roll_No=?");
     pst.setInt(1,book_id);
     pst.setInt(2,student_id);
         ResultSet rs = pst.executeQuery();
         if(rs.next())
         { 
             return true;//
                 }
         else
             return false;//
              

			//con.close();
		}catch(Exception e)
                {System.out.println(e);
                return false;}
		
	}
}

