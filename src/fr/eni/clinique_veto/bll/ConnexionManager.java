package fr.eni.clinique_veto.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.dal.ConnexionDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;


public class ConnexionManager {
	private static ConnexionManager instance;
	
	private ConnexionDAO daoConnexion;
	private List<ConnexionObserver> observers;
	private Personnel user;
	
	private ConnexionManager() throws BLLException {
	 	daoConnexion = DAOFactory.getConnexionDAO();
	 	observers = new ArrayList<ConnexionObserver>();
	}
	
	public static ConnexionManager get() {
		try {
			if(instance == null) instance = new ConnexionManager();			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		return instance;
	}
	
	public void logUser(String nom, String mdp) throws BLLException {
		try{
			int userID = daoConnexion.getUserID(nom, mdp);
			user = PersonnelManager.get().getById(userID);
		} catch(DALException e){
			e.printStackTrace();
			throw new BLLException("Erreur récupération nom ou mot de passe");
		}
		
		for(ConnexionObserver co : observers) co.onUserLogIn(user);
	}
	
	public void logOutUser() {
		user = null;
		for(ConnexionObserver co : observers) co.onUserLogOut();
	}
	
	public Personnel getUser() throws BLLException {
		if(user == null) {
			throw new BLLException("Aucun utilisateur de logger");
		}
		
		return user;
	}
	
	public void ajouterObservateur(ConnexionObserver cObs){
		observers.add(cObs);
	}
	

}