package com.ssj.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class ConnecteSql {
	@Test
	public void ConnectionTesst() 
	{
		Connection con=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		List<String> list=new ArrayList<String>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc\\:mysql\\://localhost\\:3306/ggmis","root","root");
			String sql="SELECT b.userid,MAX(b.mounts)\n" +
			"from \n" +
			"(SELECT a.userid,COUNT(a.userid) as mounts\n" +
			"FROM qy_rb a\n" +
			"group by a.userid) as b";
			st=con.prepareStatement(sql);
			st.setString(1,"");
			rs=st.executeQuery();
			if(rs!=null)
			{
				String userName;
				while(rs.next())
				{
				    userName=rs.getString("userName");
					list.add(userName);
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(rs!=null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(st!=null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(con!=null)
				try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		
	}

}
