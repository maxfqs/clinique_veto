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
	public static PersonnelDAO getPersonnelDAO(){
		PersonnelDAO PersonnelDAO=null;
		try {
			PersonnelDAO=(PersonnelDAO ) Class.forName("fr.eni.clinique_veto.dal.jdbc.PersonnelDAOJdbcImpl").newInstance();
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
		return PersonnelDAO; 
	}
	
	public static ClientDAO getClientDAO(){
		ClientDAO ClientDAO=null;
		try {
			ClientDAO=(ClientDAO ) Class.forName("fr.eni.clinique_veto.dal.jdbc.ClientsDAOJdbcImpl").newInstance();
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
		return ClientDAO; 
	}
	public static AnimalDAO getAnimalDAO()  {
		AnimalDAO AnimalDAO=null;
		try {
			AnimalDAO=(AnimalDAO ) Class.forName("fr.eni.clinique_veto.dal.jdbc.AnimalDAOJdbcImpl").newInstance();
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
		return AnimalDAO; 
	}
}
