package javaEEtest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentCRUD {
	public static void add() throws ClassNotFoundException,SQLException{
		String url="jdbc:mysql://localhost:3306/testdb";
		String dbUser="root";
		String dbPassword="root";
		String driverName="com.mysql.jdbc.Driver";
		Class.forName(driverName);
		Connection conn=DriverManager.getConnection(url, dbUser,dbPassword);
		String sql="insert into student(stuid,sname,sex,age,birthday,classname)values(2,'lisi','man',20,'1996-10-10','ruanjian142')";
		Statement stmt=conn.createStatement();
		int lines=stmt.executeUpdate(sql);
		System.out.print("update lines="+lines);//4遍历结果集
		stmt.close();
		conn.close();
		
	}
	public static void update() throws ClassNotFoundException,SQLException{
		//定义连接数据库的参数
		String url="jdbc:mysql://localhost:3306/testdb";
		String dbUser="root";
		String dbPassword="root";
		String driverName="com.mysql.jdbc.Driver";
		Class.forName(driverName);//1加载驱动
		Connection conn=DriverManager.getConnection(url,dbUser,dbPassword);//2创建连接对象
		String sql="update student set sname='zhangsan' where id=2";//3执行sql语句
		Statement  stmt=conn.createStatement();
		int lines=stmt.executeUpdate(sql);
		System.out.print("update lines="+lines);//4遍历结果集
		stmt.close();//5关闭资源对象
		conn.close();
								
		
	}
	public static void delete() throws ClassNotFoundException,SQLException{
		String url="jdbc:mysql://localhost:3306/testdb";
		String dbUser="root";
		String dbPassword="root";
		String driverName="com.mysql.jdbc.Driver";
		Class.forName(driverName);
		Connection conn=DriverManager.getConnection(url,dbUser,dbPassword);//2创建连接对象
		String sql="delete from student  where id=2";//3执行sql语句
		Statement  stmt=conn.createStatement();
		int lines=stmt.executeUpdate(sql);
		System.out.print("update lines="+lines);//4遍历结果集
		stmt.close();//5关闭资源对象
		conn.close();
						
	}
	public static void get() throws ClassNotFoundException,SQLException{
		String url="jdbc:mysql://localhost:3306/testdb";
		String dbUser="root";
		String dbPassword="root";
		String diverName="com.mysql.jdbc.Driver";
		Class.forName(diverName);
		Connection conn=DriverManager.getConnection(url, dbUser, dbPassword);
		String sql="select * from student";
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()){
			int id=rs.getInt("id");
			String name=rs.getString("name");
			String password=rs.getString("password");
			String sex=rs.getString("sex");
			int age=rs.getInt("age");
			Date birthday=rs.getDate("birthday");
			System.out.println("id=" + id + ";name=" + name + ";password="
					+ password + ";sex=" + sex + ";age=" + age + ";birthday="
					+ birthday);
		}
		stmt.close();
		conn.close();
		
		
	}
	public static void addwithtrans()throws SQLException{
		Connection conn=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs=null;
		try{

			conn = new DBConnection().getConnection();
		
			conn.setAutoCommit(false);//这儿问一下老师，先后顺序
			String sql1="insert into student(stuid,sname,sex,age,birthday) values(1,'liming','man',20,'1996-10-10')";
			String sql2="insert into user(id,name,password,sex,age,birthday)values(1,'liming','111111','man',20,'1998-12-12')";
			
			ps1=conn.prepareStatement(sql1);
			ps1.executeUpdate();
			
			ps2=conn.prepareStatement(sql2);
			ps2.executeUpdate();
			
			conn.commit();
			
		}catch(SQLException e){
			conn.rollback();
			
		}finally{
			conn.setAutoCommit(true);
			DBConnection.close(null, ps1, null);
			DBConnection.close(rs,ps2,conn);
		}
		
		
	}
	
	
	public static void main(String args[]) throws ClassNotFoundException,SQLException{
		add();
		update();
		delete();
		get();
		addwithtrans();
		
	}
	

}
