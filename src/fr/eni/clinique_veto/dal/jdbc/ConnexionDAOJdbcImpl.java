package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.dal.ConnexionDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.JDBCTools;

public class ConnexionDAOJdbcImpl implements ConnexionDAO{
	private static final String sqlVerifierPersonnel = "select * from Personnels where Nom = ? and MotPasse = ?";
	
	public int getUserID(String nom, String mdp) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		int userID = 0;
		
		try {
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlVerifierPersonnel);
			rqt.setString(1, nom);
			rqt.setString(2, mdp);

			rs = rqt.executeQuery();
			if (rs.next()){
				userID = rs.getInt("CodePers");
			}

		} catch (Exception e) {
			throw new DALException("Nom ou Mot de Passe incorrect " , e);
		}
		
		return userID;
	}

	

	
	
}

