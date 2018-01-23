package fr.eni.clinique_veto.ihm.clients;

import java.util.List;

import javax.swing.JOptionPane;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;

public class RechercheClientController {

	private static RechercheClientController instance;

	
	public static RechercheClientController get() {
		if(instance == null) {
			instance = new RechercheClientController();
		}
		
		return instance;
	}

	public void chercherClient(String nomClient) {

		List<Client> resultatRecherche;
		try {
			resultatRecherche = ClientManager.get().trouverClientParNom(nomClient);
			if(resultatRecherche.size()>0) {
				System.out.println("trouvé");
				RechercheFrame.get().getTableOnSearch(resultatRecherche);
			}else {
				JOptionPane.showMessageDialog(null,
					    "il n'y a pas de client sous ce nom");
			}
		} catch (BLLException e) {
			JOptionPane.showMessageDialog(null,
				    e.getMessage());	
		}catch (ClientDALException e) {
			JOptionPane.showMessageDialog(null,
				    e.getMessage());	
		}
	}


	public void afficherClient()throws BLLException {
		if( ClientManager.get().getDisplayedClient() != null) {
			RechercheFrame.get().setVisible(false);
			ClientController.get().getClientsFrame().afficherClient(ClientManager.get().getDisplayedClient());
			ClientController.get().getClientsFrame().afficherAnimaux(ClientManager.get().getAnimauxDisplayedClient());
		}else {
			throw new BLLException("il n'y a pas d'élément sélectionné.");
		}
	}
	
}
