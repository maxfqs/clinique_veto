package fr.eni.clinique_veto.dal;

import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.clinique_veto.bo.Personnel;

public class AppliTestDAL {

	public static void main(String[] args) {
		try {
			Connection conn = JDBCTools.getConnection();
		} catch (SQLException e) {
			// Vérifier la bonne connexion avec la base
			e.printStackTrace();
		}
		
		Personnel p1 = new Personnel("Firas", "eni1", "ADM", false);
		Personnel p2 = new Personnel("MaxD", "eni2", "ADM", false);
		Personnel p3 = new Personnel("MaxF", "eni3", "ADM", false);

	}
}
