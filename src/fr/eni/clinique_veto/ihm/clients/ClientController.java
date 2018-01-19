package fr.eni.clinique_veto.ihm.clients;

import javax.swing.JOptionPane;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;


public class ClientController {
	private static ClientController instance;
	private AnimalController animalController;
	
	
	private ClientController() {
		animalController = AnimalController.get();
	}
	
	public static ClientController get() {
		if (instance == null) {
			instance = new ClientController();
		}

		return instance;
	}

	public void rechercheClient() {
		if (!RechercheFrame.get().isVisible()) {
			RechercheFrame.get().setVisible(true);
		}
		System.out.println("recherche client-> ");
	}

	public void ajouterClient() {
		AjoutClientFrame.get().resetFields();
		if (!AjoutClientFrame.get().isVisible()) {
			AjoutClientFrame.get().setVisible(true);
		}
		System.out.println("ajout client");
	}

	public void updateClient() {
		try {
			ClientManager.get().modifierClient(ClientsFrame.get().getClientInfos());
		} catch (BLLException e) {
			JOptionPane.showMessageDialog(null,
				    e.getMessage(),
				    "erreur",
				    JOptionPane.WARNING_MESSAGE);
		}
	}


	@SuppressWarnings("static-access")
	public void supprimerClient()  {
		
		JOptionPane jop = new JOptionPane();			
		int option = jop.showConfirmDialog(
				null,
				"Êtes vous sûr de vouloir supprimer le client?",
				"Suppression d'un client", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.YES_OPTION && ClientManager.get().getDisplayedClient()!= null) {
			try {
				ClientManager.get().supprimerClient();
				ClientsFrame.get().resetFields();
				ClientManager.get().setDisplayedClient(null);
			} catch (BLLException e) {
				JOptionPane.showMessageDialog(null,
					    e.getMessage(),
					    "erreur",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	
	}
	
	public void annuler() {
		ClientsFrame.get().afficherClient(ClientManager.get().getDisplayedClient());
	}

	public void ajouterAnimal() {
		animalController.create();
	}

	public void supprimerAnimal() {
		System.out.println("supprime animal");
	}

	public void editerAnimal() {
		animalController.create(
			ClientManager.get().getAnimalManager().getSelectedAnimal()
		);
	}


}
