package fr.eni.clinique_veto.dal;

import java.sql.Connection;
import java.sql.SQLException;

public class AppliTestDAL {

	public static void main(String[] args) {
		try {
			Connection conn = JDBCTools.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
