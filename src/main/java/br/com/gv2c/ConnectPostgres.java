package br.com.gv2c;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.postgresql.*;

public class ConnectPostgres {
	
	private static Connection con = null;
	
	public static Connection createConnection() {
		
		   private static final String DB = "/d6m6n1dg2c3phr";
		   private static final String VARS = "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";   
		
		try {
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
//			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + DB + VARS;

			return createConnection(dbUrl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static Connection createConnection(String url, String user, String passwd) {
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
			con = DriverManager.getConnection(url, user, passwd);

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


    public static void main(String[] args)
    {
        String driver = "org.postgresql.Driver";
        String user   = "postgres";
        String senha = "admin";
        String url      = "jdbc:postgresql://localhost:5432/postgres";
        try
        {
            Class.forName(driver);
            Connection con = null;
            con = (Connection) DriverManager.getConnection(url, user, senha);
            System.out.println("Conexao realizada com sucesso.");
//            teste(con);
        }
        catch (ClassNotFoundException ex)
        {
            System.err.print(ex.getMessage());
        } 
        catch (SQLException e)
        {
            System.err.print(e.getMessage());
        }
    }
    
    
    public static void teste(Connection con) throws SQLException {
    	
    	ResultSet rs = con.createStatement().executeQuery("select * from painel.processo");
    	while(rs.next()) {
    		System.out.println(rs.getString(1) + "-" + rs.getString(2) + "-" + rs.getString(3));
    	}
    	
    }
}