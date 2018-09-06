package javaEEtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DBConnection {
	
	private static final String driverName="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/testdb";
	private static final String user="root";
	private static final String password="root";
		static{
			try{
				Class.forName(driverName);
			}catch(ClassNotFoundException e){
				e.printStackTrace();
				
			}
		}
		Connection getConnection() throws SQLException{
			return DriverManager.getConnection(url, user, password);
			
		}
		public static void close(ResultSet rs,Statement st,Connection conn){
		try{
			if(rs!=null){
				rs.close();
			
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally{
			try{
				if(st!=null){
					st.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
				
			}finally{
				if(conn!=null){
					try{
						conn.close();
					}catch(SQLException e){
						e.printStackTrace();
						
					}
				}
				
			}
		}
			
		}
		
		
}
