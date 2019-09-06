package br.com.example.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
	
	private final String DRIVE = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/dbexample";
	private final String USER = "root";
	private final String PASS = " ";
	
	public Connection getConnection() {
		try {
			Class.forName(DRIVE);
			
			return DriverManager.getConnection(URL, USER, PASS);
			
		} catch (ClassNotFoundException | SQLException ex) {
			
			throw new RuntimeException("Error to connect", ex);
		} 
	}
	
	public static void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		}
		} catch (SQLException ex) {		
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	

	public static void closeConnection(Connection conn, PreparedStatement pstm) {
		
		closeConnection(conn);
		
		try {
			if(pstm != null) {
				pstm.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	public static void closeConnection(Connection conn, PreparedStatement pstm, ResultSet rs) {
			
		closeConnection(conn, pstm);
			
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException ex) {
			Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
