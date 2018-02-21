package br.com.gv2c;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleQuery {

	public static void main(String[] args) {

		Connection con = ConnectDbFactory.createConnection();

		try {
			Statement stmt = con.createStatement();
			String sql = "select * from painel";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
				System.out.println(rs.getString(5));
				System.out.println(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
