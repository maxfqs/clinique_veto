package fr.eni.clinique_veto.ihm.clients;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;

public class AjoutClientController {

	private static AjoutClientController instance;
	
	public static AjoutClientController get() {
		if(instance == null) {
			instance = new AjoutClientController();
		}	
		return instance;
	}

	public void ajouterClient(Client c) throws BLLException, ClientDALException {
		ClientManager.get().ajouterClient(c);
		ClientManager.get().setDisplayedClient(c);
		ClientsFrame.get().afficherClient(c);
		AjoutClientFrame.get().setVisible(false);
	}


	
}
