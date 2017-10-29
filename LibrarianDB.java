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
public class LibrarianDB {

	
	public static int save(String name,String password){
		int status=0;
		try{
			   Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/login_credentials","root","12345");
PreparedStatement ps=con.prepareStatement("insert into profile(username,userpassword) values(?,?)");
			ps.setString(1,name);
			ps.setString(2,password);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int delete(String name){
		int status=0;
		try{
			   Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/login_credentials","root","12345");
PreparedStatement ps=con.prepareStatement("delete from profile where username=?");
			ps.setString(1,name);
			status=ps.executeUpdate();
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	public static boolean validate(String name,String password){
		//boolean status=false;
		try{   
                     Class.forName("com.mysql.jdbc.Driver");
  
    Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/login_credentials","root","12345");

            PreparedStatement pst;
            pst = conn.prepareStatement("SELECT * FROM profile where username=? and userpassword=?");
     pst.setString(1,name);
     pst.setString(2,password);
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

