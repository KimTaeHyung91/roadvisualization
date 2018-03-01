package kr.ac.skuniv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class MysqlResource {

	protected String className = "com.mysql.jdbc.Driver";
	protected Connection conn = null;
	protected ResultSet rs = null;
	protected PreparedStatement ps = null;
	protected String id="seoul";
	protected String pwd="seoul";
	protected String url="jdbc:mysql://localhost:3306/nodelinkinfo?verifyServerCertificate=false&useSSL=true";
	
	protected void getMysqlConnection(String query){
		
		try {
			
			Class.forName(className);
			conn = DriverManager.getConnection(url,id,pwd);
			ps = conn.prepareStatement(query);
		} catch (ClassNotFoundException cnf) {
			// TODO Auto-generated catch block
			cnf.printStackTrace();
		}catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}
	}
}
