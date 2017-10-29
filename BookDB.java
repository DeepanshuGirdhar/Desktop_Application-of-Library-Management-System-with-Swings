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
import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BookDB {
public static int save(String Book_name,String Author_name,String course,String Branch,int book_id,int quantity){
	int status=0;
	try{
		Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
   PreparedStatement ps=con.prepareStatement("insert into add_book(book_name,book_author,course,branch,book_id,quantity) values(?,?,?,?,?,?)");
		ps.setString(1, Book_name);
		ps.setString(2, Author_name);
		ps.setString(3, course);
		ps.setString(4, Branch);
		ps.setInt(5, book_id);
               ps.setInt(6, quantity); 
		status=ps.executeUpdate();
		con.close();    
  }catch(Exception e){System.out.println(e);}
        return status;
}
/*public static int search(int book_id)
{
    int issue_no=0;
    int quantity=-1;
    
    try{
		Class.forName("com.mysql.jdbc.Driver");
  
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");
PreparedStatement ps=con.prepareStatement("select issued and quantity from add_book where book_id=?");
		ps.setInt(1,book_id);
	    ResultSet rs=ps.executeQuery();
		while(rs.next())
                {
                   issue_no=rs.getInt("issued");
                 quantity=rs.getInt("quantity");
                 System.out.println("in book_id search");
                
                }
		con.close();
	}catch(Exception e){System.out.println(e);}
    
    if(quantity==0)
    {return quantity;
    
       // JOptionPane.showMessageDialog(BookDB.this,issue_no+" Books all issued","Book Not Available!", JOptionPane.ERROR_MESSAGE);
    }
    else if(quantity>0)
    {
    return quantity;
        //JOptionPane.showMessageDialog(,quantity+"Books Available!");
    }
   
        return quantity;
    
}



*/
}
