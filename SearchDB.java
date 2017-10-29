/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SearchDB extends JFrame {
    private JPanel contentPane;
	private JTable table;
        private JButton button;
        int Roll_No;
	public static void main(String[] args) {
            System.out.println("in main");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchDB frame = new SearchDB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
        
    	public SearchDB() 
        {System.out.println("in const");
		//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBounds(300, 180, 785, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
                button=new JButton();
                button.setText("Back");
                button.setBounds(550,550,100,50);
		//String name=JOptionPane.showInputDialog(this,"Enter Roll Name");  
                
		String data[][]=null;
		String column[]=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
    
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","12345");

    Statement stmt= con.createStatement();
	
    ResultSet rs= stmt.executeQuery("select * from issue_book where Roll_No='"+Roll_No+"';"); 
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
       
   /* public void search(int student_id)
    {System.out.println("in search function");
        Roll_No=student_id;
        //SearchDB.main(new String[]{});
        
    }*/
}
    
    

