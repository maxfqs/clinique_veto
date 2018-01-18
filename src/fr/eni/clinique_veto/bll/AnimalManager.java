package fr.eni.clinique_veto.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.dal.AnimalDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;


public class AnimalManager {
	
	private static Map<String,List<String>> especesMap;
	
	private AnimalDAO animalDAO;
	private List<Animal> animalList;
	private List<AnimalObserver> observers;
	
	
	public AnimalManager() throws BLLException {		
		observers = new ArrayList<AnimalObserver>();
		
		try {
			animalDAO = DAOFactory.getAnimalDAO(); // trouver une liste d'animaux par un numéro de client
			animalList = new ArrayList<Animal>();
			
			List<Animal> all = animalDAO.selectAll();
			for(Animal p : all) {
				if(!p.isArchive()) animalList.add(p);
			}
		} catch (DALException | SQLException e) {
			throw new BLLException("Erreur lors de l'initialisation du AnimalManager");
		}
	}
	public static Map<String,List<String>> getEspecesMap(){
		if(especesMap != null) return especesMap;
		
		especesMap = new HashMap<String,List<String>>();
		List<String[]> result = null;
		try {
			result = DAOFactory.getAnimalDAO().selectRaces();
			
		} catch (SQLException | DALException e) {
			e.printStackTrace();
		}
		for(String[] data : result){
			String race = data[0];
			String espece = data[1];
			
			if(especesMap.get(espece) == null){
				especesMap.put(espece, new ArrayList<String>());
			}
			
			especesMap.get(espece).add(race);
		}
		return especesMap;
	}
	
	
	
	public List<Animal> getAnimals() {
		return Collections.unmodifiableList(animalList);
	}
	
	public Animal getById(int id) throws BLLException {
		Animal retval = null;
		for(Animal a : animalList) {
			if(a.getCodeAnimal() == id) retval = a;
		}
		
		if(retval == null) {
			throw new BLLException("Impossible de trouver l'Animal avec l'id " + id);
		}
		
		return retval;
	}
	
	public void addAnimal(Animal a) throws BLLException{
		try {
			validateAnimal(a);
			animalDAO.insert(a);
			animalList.add(a);
		} catch (DALException e) {
			throw new BLLException("Erreur lors de l'ajout d'un Animal");
		}
		
		fireUpdate();
	}
	
	public void updatePassword(Animal a) throws BLLException {
		update(a);
		fireUpdate();
	}
	
	public void archiver(Animal a) throws BLLException {
		
		a.setArchive(true);
		update(a);
		
		animalList.remove(a);
		//System.out.println(AnimalList);
		fireUpdate();
	}
	
	private void update(Animal a) throws BLLException {
		if(!animalList.contains(a)) {
			throw new BLLException("Impossible de maj cet Animal");
		}		
		
		try {
			animalDAO.update(a);
		} catch (DALException e) {
			throw new BLLException("Impossible de maj cet Animal");
		}
	}

	
	private void validateAnimal(Animal a) throws BLLException{
		// R�gles m�tiers � coder.
	}	
	
	public void registerObserver(AnimalObserver ao) {
		observers.add(ao);
	}
	
	private void fireUpdate() {
		for(AnimalObserver ao : observers) ao.onListUpdated();
	}
}
