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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBookDB {
	public static int delete(int book_id,int student_id){
		int status=0;
                System.out.println("in delete");
		try{
			  Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

			
			System.out.println("back in del");
			
			PreparedStatement ps=con.prepareStatement("delete from issue_book where book_id=? and Roll_No=?");
			ps.setInt(1,book_id);
			ps.setInt(2,student_id);
			status=ps.executeUpdate();
                        System.out.println("in delete");
                        if(status>0){
                            status=updatebook(book_id);//updating quantity and issue
			}
			
			con.close();
		}catch(Exception e){System.out.println(e);}
                System.out.println(status);
		return status;
	}
	public static int updatebook(int book_id){
		int status=0;
		int quantity=0,issued=0;
                System.out.println("in update");
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
			
			if(issued>0){
			PreparedStatement ps2=con.prepareStatement("update add_book set quantity=?,issued=? where book_id=?");
			ps2.setInt(1,quantity+1);
			ps2.setInt(2,issued-1);
			ps2.setInt(3,book_id);
			
			status=ps2.executeUpdate();
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
                System.out.println(status);
		return status;
	}
}

