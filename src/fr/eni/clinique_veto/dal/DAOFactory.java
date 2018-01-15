	package fr.eni.clinique_veto.dal;

import fr.eni.clinique_veto.dal.ConnexionDAO;

public class DAOFactory {
	public static ConnexionDAO getConnexionDAO()  {
		ConnexionDAO ConnexionDAO=null;
		try {
			ConnexionDAO=(ConnexionDAO ) Class.forName("fr.eni.clinique_veto.dal.jdbc.ConnexionDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ConnexionDAO; 
	}
}