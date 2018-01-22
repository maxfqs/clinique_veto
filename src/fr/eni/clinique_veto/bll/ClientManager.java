package fr.eni.clinique_veto.bll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;
import fr.eni.clinique_veto.dal.ClientDAO;
import fr.eni.clinique_veto.dal.DAOFactory;

public class ClientManager {

	
	private static ClientManager instance;
	private static ClientDAO dao;
	private AnimalManager animalManager;



	private  List<Client> listeRechercheClient = new ArrayList<Client>();
	private Client displayedClient;
	


	private ClientManager() {
		dao = DAOFactory.getClientDAO();

	}
	
	public static ClientManager get() {
		if(instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}


	public List<Client> trouverClientParNom(String nomClient) throws ClientDALException, BLLException {
		if(nomClient== null ||nomClient.trim().length() == 0) {
			throw new BLLException("Merci de saisir un nom valide");
		}
		List<Client> retListe = new ArrayList<>();
		try {
			retListe = 	dao.trouverParNom(nomClient);
		} catch (ClientDALException e) {	
			throw new BLLException(BLLError.FAILED_CLIENT_GET);
		}
		listeRechercheClient = retListe;
		return retListe;
	}
	
	public List<Animal> getAnimauxDisplayedClient(){
		return animalManager.getAnimals();
	}
	
	public List<Client> getClients() {
		return Collections.unmodifiableList(listeRechercheClient);
	}
	
	


	public void ajouterClient(Client c)throws BLLException  {	
			try {
				verifierClient(c);
				dao.ajouterClient(c);
			} catch (ClientDALException | BLLException e) {
				throw new BLLException(BLLError.FAILED_CLIENT_ADD, e.getMessage());
			}
	}
	

	public void modifierClient(Client c) throws BLLException {
		try {
			verifierClient(c);
			dao.modifierClient(c);
		}  catch (ClientDALException | BLLException e) {
			throw new BLLException(BLLError.FAILED_CLIENT_UPDATE, e.getMessage());
		}
	}
	
	public void supprimerClient() throws BLLException  {
			try {
				dao.archiver(displayedClient.getCodeClient());
			} catch (ClientDALException e) {
				throw new BLLException(BLLError.FAILED_CLIENT_UPDATE);
			}	
	}
	
	public void verifierClient(Client c)throws BLLException {
		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		boolean okClient = true;
		String error = "";
		
		if(c.getNomClient().equals(null) || c.getNomClient().trim().length() == 0 ) {
			error += "\n la valeur renseignée pour le nom du client est incorrecte";
			okClient = false;
		}
		if(c.getPrenomClient().equals(null) || c.getPrenomClient().trim().length() == 0 ) {
			error += "\n la valeur renseignée pour le prénom du client est incorrecte";
			okClient = false;
		}
		if(c.getAdresse1().equals(null) || c.getAdresse1().trim().length() == 0 ) {
			error += "\n la valeur renseignée pour l'adresse du client est incorrecte";
			okClient = false;
		}
		if(!c.getCodePostal().equals(null)) {
			c.setCodePostal(c.getCodePostal().trim());
			if(!c.getCodePostal().matches("\\d{5}")) {
				error += "\n la valeur renseignée pour le code postal est incorrecte";
				okClient = false;
			}
		}
		if((Integer)c.getArchive()== null || c.getArchive() != 0 && c.getArchive() != 1) {
			error += "\n la valeur de l'arhive renseignée est incorrecte";
			okClient = false;
		}
		if(!c.getEmail().equals(null)) {
			if(!VALID_EMAIL_ADDRESS_REGEX .matcher(c.getEmail()).find() ) {
				error += "\n l'email renseigné est incorrect";
				okClient = false;
			}
		}
		if(!okClient) {
			throw new BLLException(error);
		}
			
	}
	
	public Client getDisplayedClient() {
		return displayedClient;
	}

	public void setDisplayedClient(Client displayedClient) {
		this.displayedClient = displayedClient;
		try {
			animalManager = new AnimalManager(displayedClient);
		} catch (BLLException e) {
			JOptionPane.showMessageDialog(null,
				    "Erreur lors de l'initialisation du programme, veuillez contacter l'administrateur",
				    "fatal error",
				    JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public AnimalManager getAnimalManager() {
		return animalManager;
	}


	
	
}
