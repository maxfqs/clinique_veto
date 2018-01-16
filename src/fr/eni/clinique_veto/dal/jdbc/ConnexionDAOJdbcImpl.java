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
	
	public Personnel verifierPersonnel(String nom, String mdp) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try {
			Personnel pers = null;
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlVerifierPersonnel);
			rqt.setString(1, nom);
			rqt.setString(2, mdp);

			rs = rqt.executeQuery();
			if (rs.next()){
				pers = new Personnel(rs.getInt("CodePers"),
						rs.getString("Nom"),
						rs.getString("MotPasse"),
						rs.getString("Role")
						);
						
				System.out.println("connexion établi");
				return pers;
			}

		} catch (Exception e) {
			throw new DALException("Nom ou Mot de Passe incorrect " , e);
		}
		
		return null;
	}

	

	
	
}

