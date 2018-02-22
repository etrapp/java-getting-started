package br.com.gv2c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectPostgres {
	
	private static Connection con = null;
	
//	public static Connection createConnection() {
//		
//		String dbUrl = System.getenv("JDBC_DATABASE_URL");
//		
//		System.out.println("DB:::::::::::::::" + dbUrl);
//		
//		dbUrl = "postgres://ffmabncveoxuow:e889257a35e423a552cdeb10c8dc75c11b0936dc51775de44794c49bc7da68ee@ec2-54-227-252-237.compute-1.amazonaws.com:5432/d6m6n1dg2c3phr";
//		
//		return createConnection(dbUrl);
//
//	}
	
	
	public static Connection createConnection()  {

		if(con != null) {
			try {
				if (con.isValid(0)) {
					return con;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try {
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			return DriverManager.getConnection(dbUrl);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static Connection createConnection(String url) {
		if(con != null) {
			try {
				if (con.isValid(0)) {
					return con;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Postgres JDBC Driver?");
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (con != null) {
//			System.out.println("You made it, take control your database now!");

		} else {
//			System.out.println("Failed to make connection!");
		}
		return con;
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
   
    
    public static void teste(Connection con) throws SQLException {
    	
    	ResultSet rs = con.createStatement().executeQuery("select * from painel.processo");
    	while(rs.next()) {
    		System.out.println(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3));
    	}
    	
    }
}