package br.com.gv2c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectPostgres {
	
	private static Connection con = null;

	public static Connection createConnection() {
		
//        String driver = "org.postgresql.Driver";
//        String user   = "postgres";
//        String senha = "admin";
//        String url      = "jdbc:postgresql://localhost:5432/postgres";
		
//		return createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
		return createConnection("jdbc:postgresql://ec2-23-21-195-249.compute-1.amazonaws.com:5432/df22e01vn35is3", "ubgbnkvnqylpqe", "7ac9f5261ee1636e80661bc2982251c6cb51ccf7b36f3c6d399bf1a11230aa54");

	}
	
	public static Connection createConnection(String url, String user, String passwd) {
//		System.out.println("-------- MySQL JDBC Connection ------------");
		//Connection con =ConnectDbFactory.createConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
		
		if(con != null) {
			try {
				if (con.isValid(0)) {
//				if (!con.isClosed()) {
//					System.out.println("conex√£o existente e valida....");
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
            System.out.println("Conex„o realizada com sucesso.");
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