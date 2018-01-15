package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.clinique_veto.dal.ConnexionDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.JDBCTools;
public class ConnexionDAOjdbcImpl implements ConnexionDAO{
	private static final String sqlVerifierNom = "select MotPass from Personnel where Nom = ?";
	
	public boolean verifierNom(String nom, String password){
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlVerifierNom);
			rqt.setString(1, nom);

			rs = rqt.executeQuery();
			if (rs.next()){

				
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + id , e);
		}
		boolean verifier = false;
		
		return verifier;
	}
	public boolean verifierPassword(String vpassword){
		
		return false;
	}
}
