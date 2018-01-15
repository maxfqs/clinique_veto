package fr.eni.clinique_veto.bll;

import fr.eni.clinique_veto.bo.Personnel;


public class PersonnelManager {
	private static PersonnelManager instance;
	
	private PersonnelManager() throws BLLException {
		
	}
	
	public static PersonnelManager get() throws BLLException {
		if(instance == null) instance = new PersonnelManager();
		return instance;
	}
	
		
	public void validerPersonnel(Personnel p) throws BLLException {
		if(p.getNom() == null || p.getNom().trim().equals("")) {
			throw new BLLException("Le nom du personnel est vide");
		}
		
		if(p.getMdp() == null || p.getMdp().trim().equals("")) {
			throw new BLLException("Le mdp du personnel est vide");
		}
		
		if(p.getRole() == null || p.getRole().trim().equals("")) {
			throw new BLLException("Le role du personnel est vide");
		}
	}
	
	public void update(Personnel p) throws BLLException {
		validerPersonnel(p);
	}
	
	public void add(Personnel p) throws BLLException {
		validerPersonnel(p);
	}
	
	public void delete(Personnel p) throws BLLException {
		
	}
}
