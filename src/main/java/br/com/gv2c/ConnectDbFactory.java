package br.com.gv2c;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectDbFactory {

	private static Connection con = null;

	public static Connection createConnection() {
		
		//return ConnectMySql.createConnection();
		return ConnectPostgres.createConnection();
	}
	

	public static Connection getConnection() {
		return con;
	}

	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}