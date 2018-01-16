package fr.eni.clinique_veto.dal.jdbc;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.dal.PersonnelDAO;

public class PersonnelDAOJdbcImpl implements PersonnelDAO{
	private int idPersonnel;
	private static final String sqlAddPersonnel = "";
	private static final String sqlSelectPersonel = "";
	
	public void addPersonnel(String nom, String mdp, String role, boolean archive){
		
	}
	
	public Personnel getPersonnel(int idPersonnel){
		
		return null;
	}
		
}
