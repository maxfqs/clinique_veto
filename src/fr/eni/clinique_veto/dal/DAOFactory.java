	package fr.eni.clinique_veto.dal;

import java.sql.Connection;
import java.sql.SQLException;

import fr.eni.clinique_veto.dal.jdbc.ClientsDAOImplJDBC;
import fr.eni.clinique_veto.dal.jdbc.AnimalDAOJdbcImpl;
import fr.eni.clinique_veto.dal.jdbc.ConnexionDAOJdbcImpl;
import fr.eni.clinique_veto.dal.jdbc.PersonnelDAOJdbcImpl;
import fr.eni.clinique_veto.dal.jdbc.RendezVousDAOJdbcImpl;

public class DAOFactory {
	private static Connection cnx;
	private static ConnexionDAO connexionDAO;
	private static ClientDAO clientDAO;
	private static AnimalDAO animalDAO;
	private static RendezVousDAO rendezVousDAO;
	private static PersonnelDAO personnelDAO;
	
		static {
			 try {
				cnx = JDBCTools.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 connexionDAO = new ConnexionDAOJdbcImpl(cnx);
			 clientDAO = new ClientsDAOImplJDBC(cnx);
			 animalDAO = new AnimalDAOJdbcImpl(cnx);
			 rendezVousDAO = new RendezVousDAOJdbcImpl(cnx);
			 personnelDAO = new PersonnelDAOJdbcImpl(cnx);
		}
		
	

	
	
	public static ConnexionDAO getConnexionDAO()  {
		
		return connexionDAO; 
	}
	public static PersonnelDAO getPersonnelDAO(){
		
		return personnelDAO; 
	}
	
	public static ClientDAO getClientDAO(){
		
		return clientDAO; 
	}
	public static AnimalDAO getAnimalDAO()  {
		
		return animalDAO; 
	}
	
	public static RendezVousDAO getRendezVousDAO()  {
		
		return rendezVousDAO; 
	}
}
