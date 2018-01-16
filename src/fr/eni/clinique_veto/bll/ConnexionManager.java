package fr.eni.clinique_veto.bll;

import fr.eni.clinique_veto.dal.ConnexionDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;
import fr.eni.clinique_veto.bll.BLLException;


public class ConnexionManager {
	private static ConnexionDAO daoConnexion;

	public ConnexionManager() throws BLLException{
			//Instancier le Data Access Object
		 	daoConnexion = DAOFactory.getConnexionDAO();
	}
	
	public boolean verifierUser(String nom, String mdp) throws BLLException{
		boolean verifier;
		try{
			verifier = daoConnexion.verifierPersonnel(nom, mdp);
		} catch(DALException e){
			e.printStackTrace();
			throw new BLLException("Erreur récupération nom ou mot de passe");
		}
		return verifier;
	}
}