package fr.eni.clinique_veto.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;
import fr.eni.clinique_veto.dal.PersonnelDAO;


public class PersonnelManager {
	private static PersonnelManager instance;
	private PersonnelDAO personnelDAO;
	private List<Personnel> personnelList;
	private List<PersonnelObserver> observers;
	
	private PersonnelManager() throws BLLException {		
		observers = new ArrayList<PersonnelObserver>();
		
		try {
			personnelDAO = DAOFactory.getPersonnelDAO();
			personnelList = new ArrayList<Personnel>();
			
			List<Personnel> all = personnelDAO.selectAll();
			for(Personnel p : all) {
				if(!p.isArchive()) personnelList.add(p);
			}
		} catch (DALException | SQLException e) {
			throw new BLLException("Erreur lors de l'initialisation du PersonnelManager");
		}
	}
	
	public static PersonnelManager get() {
		try {
			if(instance == null) instance = new PersonnelManager();			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		return instance;
	}
	
	
	public List<Personnel> getPersonnels() {
		return Collections.unmodifiableList(personnelList);
	}
	
	public Personnel getById(int id) throws BLLException {
		Personnel retval = null;
		for(Personnel p : personnelList) {
			if(p.getId() == id) retval = p;
		}
		
		if(retval == null) {
			throw new BLLException("Impossible de trouver le personnel avec l'id " + id);
		}
		
		return retval;
	}
	
	public void addPersonnel(Personnel p) throws BLLException{
		try {
			validatePersonnel(p);
			personnelDAO.insert(p);
			personnelList.add(p);
		} catch (DALException e) {
			throw new BLLException("Erreur lors de l'ajout d'un personnel");
		}
		
		fireUpdate();
	}
	
	public void updatePassword(Personnel p) throws BLLException {
		update(p);
		fireUpdate();
	}
	
	public void archiver(Personnel p) throws BLLException {
		// On fait rien pour le moment, en attente de AGENDA
		if(p.getRole().equals("VET")) return;
		
		p.setArchive(true);
		update(p);
		
		personnelList.remove(p);
		System.out.println(personnelList);
		fireUpdate();
	}
	
	private void update(Personnel p) throws BLLException {
		if(!personnelList.contains(p)) {
			throw new BLLException("Impossible de maj ce personnel");
		}		
		
		try {
			personnelDAO.update(p);
		} catch (DALException | SQLException e) {
			throw new BLLException("Impossible de maj ce personnel");
		}
	}

	
	private void validatePersonnel(Personnel p) throws BLLException{
		// Règles métiers à coder.
	}	
	
	public void registerObserver(PersonnelObserver po) {
		observers.add(po);
	}
	
	private void fireUpdate() {
		for(PersonnelObserver po : observers) po.onListUpdated();
	}
}
