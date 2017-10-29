/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author deepanshu girdhar
 */
public class ViewFine extends JFrame {
    private JPanel contentPane;
	private JTable table;
        private JButton button;
        
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewFine frame = new ViewFine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
        
    	public ViewFine() 
        {
		//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBounds(300, 180, 785, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
                button=new JButton();
                button.setText("Back");
                button.setBounds(550,550,100,50);
		String name=JOptionPane.showInputDialog(this,"Enter Roll Name");  
                int student_id=Integer.parseInt(name);
		String data[][]=null;
		String column[]=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
    
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

    Statement stmt= con.createStatement();
	
    ResultSet rs= stmt.executeQuery("select * from fine_details where Roll_No='"+student_id+"';"); 
			ResultSetMetaData rsmd=rs.getMetaData();
			int cols=rsmd.getColumnCount(); //it gives column count for JTable
			column=new String[cols];
                       for(int i=1;i<=cols;i++){
				column[i-1]=rsmd.getColumnName(i);
			}
			
			rs.last(); //its returns last row of resultset either true/false
			int rows=rs.getRow();
			rs.beforeFirst();

			data=new String[rows][cols];
			int count=0;
			while(rs.next()){
				for(int i=1;i<=cols;i++){
					data[count][i-1]=rs.getString(i);
				}
				count++;
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		
		table = new JTable(data,column);
		JScrollPane sp=new JScrollPane(table);
		
		contentPane.add(sp, BorderLayout.CENTER);
	}
       
    
}
