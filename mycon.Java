package JDBC;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.sql.*;
public class mycon extends JPanel{
	public static String un = "SYSTEM";
    public static String pw = "kingsley678";
    public static String url = "jdbc:oracle:thin:@localhost:1521:mydb";
    public static ResultSet rs1,rs2,rs3; 
    static int rol;
    JTable t; 
    
    static String[][] data = new String[8][30];
    public mycon()
    {
    	String [] colom  = {"CID", "LastName", "FirstName", "City", "State", "Country", "date", "Credit"};
    	t = new JTable(data,colom);
    	t.setPreferredScrollableViewportSize(new Dimension(500,60));
    	t.setFillsViewportHeight(true);
    	
    	JScrollPane jps = new JScrollPane(t);
    	add(jps);
    	
    }
	public static void main(String[] args) {
		try{
			
			//load class driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Oracle class Loaded");
			//establish connection with data base
			Connection con = DriverManager.getConnection(url,un,pw);
			System.out.println("Connection extablished with data base 'mydb'");
			Statement myst = con.createStatement();
			rs1 = myst.executeQuery("Select * from customers");
			
			System.out.println("CID \t LastName \t FirstName \t City \t State \t Country \t date \t     Credit");
		
			rol = 0;
			// populate Array with data from database
			while(rs1.next())
			{
				data[rol][0] = rs1.getString(1);
				data[rol][1] = rs1.getString(2);data[rol][2] = rs1.getString(3);data[rol][3] = rs1.getString(4); data[rol][4] = rs1.getString(5); data[rol][5] = rs1.getString(6); 
				data[rol][6] = rs1.getString(7); data[rol][7] = rs1.getString(8); 
				rol++;
				System.out.println(rs1.getString(1)+"\t  "+rs1.getString(2)+"\t   "+ rs1.getString(3)+"\t    "+rs1.getString(4)+"\t   "+rs1.getString(5)+"\t"+rs1.getString(6)+"\t"+ rs1.getString(7)+ " "+rs1.getString(8));
			}
			JFrame jf = new FrameClass();
			mycon jp = new mycon();
			jf.add(jp);
			rs2 = myst.executeQuery("Select distinct city from customers");
			while(rs2.next())
			{
				System.out.println(rs2.getString(1));
			}
			
		// close connections....
			rs1.close();
			myst.close();
			con.close();
			System.out.println("connections closed!!");
		}catch(ClassNotFoundException ex){
			System.out.println("# - Class not found!!");
			
		}catch(SQLException ex){
			System.out.println("# - Problem connecting with SQL Database");
			ex.printStackTrace();
		}
		

	}

}
