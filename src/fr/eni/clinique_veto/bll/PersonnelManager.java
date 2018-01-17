package fr.eni.clinique_veto.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;
import fr.eni.clinique_veto.dal.PersonnelDAO;


public class PersonnelManager {
	
	private PersonnelDAO personnelDAO;
	private static PersonnelManager instance;
	
	private PersonnelManager() throws BLLException {
		personnelDAO = DAOFactory.getPersonnelDAO();
	}
	
	public static PersonnelManager get() throws BLLException {
		if(instance == null) instance = new PersonnelManager();
		return instance;
	}
	
	public List<Personnel> getPersonnels() throws SQLException, DALException{
		List<Personnel> lp = new ArrayList<>();
		lp.addAll(personnelDAO.selectAll());
		return lp;
	}
		
	public void addPersonnel(Personnel p) throws DALException{
		personnelDAO.insert(p);
	}
	
	public void updatePersonnel(Personnel p) throws SQLException, DALException{
		final String VET = "VET";
		if(p.getRole().equals(VET)){
			//todo
		} else {
			personnelDAO.update(p);
		}
		
	}
	
	
}
