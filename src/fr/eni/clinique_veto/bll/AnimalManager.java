package fr.eni.clinique_veto.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.AnimalDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;
import fr.eni.clinique_veto.ihm.clients.ClientController;


public class AnimalManager {
	private static AnimalDAO animalDAO;
	
	private Client client;
	private List<Animal> animalList;
	private List<ManagerListObserver> observers;
	private Animal selectedAnimal;
	
	static {
		animalDAO = DAOFactory.getAnimalDAO();
	}

	
	public AnimalManager(Client client) throws BLLException {		
		if(client == null) {
			throw new BLLException("Param Client is null");
		}
		
		this.client = client;
		this.observers = new ArrayList<ManagerListObserver>();
		this.observers.add(ClientController.get().getClientsFrame());
		try {
			this.animalList = new ArrayList<Animal>();
			
			List<Animal> all = animalDAO.selectByClient(client.getCodeClient());
			
			for(Animal a : all) {
				a.setClient(client);
				if(!a.isArchive()) animalList.add(a);
			}
			
		} catch (DALException | SQLException e) {
			throw new BLLException(BLLError.DATABASE_ERROR);
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
	
	public void addAnimal(Animal a) throws BLLException{		
		try {
			validateAnimal(a);
			animalDAO.insert(a);
			animalList.add(a);
			setSelectedAnimal(a);
		} catch (DALException e) {
			throw new BLLException(BLLError.FAILED_ANIMAL_ADD);
		}
	
		fireUpdate();
	}
	
	public void archiver(int id) throws BLLException {
		Animal a = null;	
				
		try {
			a = getById(id);
			a.setArchive(true);
			animalDAO.update(a);
		} catch (DALException e) {
			throw new BLLException("Erreur lors de l'archivage de l'animal");
		}
		
		animalList.remove(a);
		selectedAnimal = null;
		fireUpdate();
	}
	
	public void update(Animal a) throws BLLException {
		try {
			validateAnimal(a);
			animalDAO.update(a);
			updateAnimalList(a);
		} catch (DALException e) {
			throw new BLLException(BLLError.FAILED_ANIMAL_UPDATE);
		}
				
		fireUpdate();
	}
	
	private void updateAnimalList(Animal o) throws BLLException {
		try {
			Animal a = getById(o.getCodeAnimal());
			int i = animalList.indexOf(a);
			animalList.set(i, o);
		} catch (Exception e) {
			throw new BLLException("Impossible de maj animalList avec animal: " + o.getCodeAnimal());
		}
	}
	
	public void setSelectedAnimal(Animal a) {
		if(animalList.contains(a)) {
			selectedAnimal = a;
		}
	}
	
	public Animal getSelectedAnimal() {
		return selectedAnimal;
	}
	
	private void validateAnimal(Animal a) throws BLLException{		
		if(client.getCodeClient() != a.getCodeClient()) {
			throw new BLLException(BLLError.INVALID_ANIMAL_CLIENT_ID);
		}
		
		if(!EspecesManager.isValidEspece(a.getEspece())) {
			throw new BLLException(BLLError.INVALID_ANIMAL_ESPECES);
		}
		
		if(!EspecesManager.isValidRace(a.getEspece(), a.getRace())) {
			throw new BLLException(BLLError.INVALID_ANIMAL_RACES);
		}
		
		for (int i = 0; i < Animal.SEXE.length; i++) {
			if(a.getSexe() == Animal.SEXE[i]) return;			
		}
		
		throw new BLLException(BLLError.INVALID_ANIMAL_SEXES);
	}
	
	public void registerObserver(ManagerListObserver o) {
		observers.add(o);
	}
	
	private void fireUpdate() {
		for(ManagerListObserver o : observers) o.onListUpdated();
	}
}
