package fr.eni.clinique_veto.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.AnimalDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;


public class AnimalManager {
	private static AnimalDAO animalDAO;
	
	private Client client;
	private List<Animal> animalList;
	private List<AnimalObserver> observers;
	
	
	static {
		animalDAO = DAOFactory.getAnimalDAO();
	}

	
	public AnimalManager(Client client) throws BLLException {		
		this.client = client;
		this.observers = new ArrayList<AnimalObserver>();
		
		try {
			this.animalList = new ArrayList<Animal>();
			
			List<Animal> all = animalDAO.selectAll();
			for(Animal p : all) {
				if(!p.isArchive()) animalList.add(p);
			}
		} catch (DALException | SQLException e) {
			throw new BLLException("Erreur lors de l'initialisation du AnimalManager");
		}
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
	
	
	public List<Animal> getAnimalsByClient(int data) {
		List<Animal> la = new ArrayList<>();
		try {
			la.addAll(animalDAO.selectByClient(data));
		} catch (SQLException | DALException e) {
			e.printStackTrace();
		}	
		return la;
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
