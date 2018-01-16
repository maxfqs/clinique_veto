package fr.eni.clinique_veto.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.dal.ConnexionDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;


public class LoginMger {
	private static ConnexionDAO daoConnexion;
	private List<ConnexionObserver> listeObservateurs;
	
	public LoginMger() throws BLLException{
			//Instancier le Data Access Object
		 	daoConnexion = DAOFactory.getConnexionDAO();
		 	listeObservateurs = new ArrayList<>();
	}
	
	public Personnel verifierUser(String nom, String mdp) throws BLLException{
		Personnel pers = null;
		try{
			pers = daoConnexion.verifierPersonnel(nom, mdp);
		} catch(DALException e){
			e.printStackTrace();
			throw new BLLException("Erreur récupération nom ou mot de passe");
		}
		if(pers != null){
			envoiNotification(pers);
		}
		return pers;
	}
	
	public void envoiNotification(Personnel pers){
		for(ConnexionObserver co : listeObservateurs){
			co.connexionNotification(pers);
		}
	}
	
	public void ajouterObservateur(ConnexionObserver cObs){
		listeObservateurs.add(cObs);
	}
	
}