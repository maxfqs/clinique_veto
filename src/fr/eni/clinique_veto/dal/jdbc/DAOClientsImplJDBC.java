package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.JDBCTools;

public class DAOClientsImplJDBC {

	private Connection conn;
	
	private static final String AJOUT_CLIENT = "INSERT INTO Clients ("
			+ "NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive  )"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	private static
	
	public DAOClientsImplJDBC() {
		try {
			this.conn = JDBCTools.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void ajouterClient(Client c)throws Exception {
		PreparedStatement pst = null;
		try {
			pst = this.conn.prepareStatement(AJOUT_CLIENT);
			pst.setString(1, c.getNomClient());
			pst.setString(2, c.getPrenomClient());
			pst.setString(3, c.getAdresse1());
			if(c.getAdresse2().equals(null) || c.getAdresse2().trim().length() == 0) {
				pst.setNull(4, Types.VARCHAR);
			}else {
				pst.setString(4, c.getAdresse2());
			}
			
			pst.setString(5, c.getCodePostal());
			pst.setString(6, c.getVille());
			pst.setString(7, c.getNumTel());
			pst.setString(8, c.getAssurance());
			pst.setString(9, c.getEmail());
			if(c.getRemarque().equals(null) || c.getRemarque().trim().length() == 0) {
				pst.setNull(10, Types.VARCHAR);
			}else {
				pst.setString(10, c.getRemarque());
			}
			pst.setInt(11, c.getArchive());
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
	}
	
	
}
