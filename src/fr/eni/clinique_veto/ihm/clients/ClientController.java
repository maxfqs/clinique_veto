package fr.eni.clinique_veto.ihm.clients;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.ihm.ErrorDialog;
import fr.eni.clinique_veto.ihm.MenuController;


public class ClientController implements MenuController {
	private static ClientController instance;
	private AnimalController animalController;
	private ClientsFrame clientFrame;
	
	private ClientController() {
		animalController = AnimalController.get();
		clientFrame = new ClientsFrame();
		hide();
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
	}

	public void ajouterClient() {
		AjoutClientFrame.get().resetFields();
		if (!AjoutClientFrame.get().isVisible()) {
			AjoutClientFrame.get().setVisible(true);
		}
	}

	public void updateClient() {
		try {
			ClientManager.get().modifierClient(clientFrame.getClientInfos());
		} catch (BLLException e) {
			ErrorDialog.showError(e.getMessage());
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
				clientFrame.resetFields();
				ClientManager.get().setDisplayedClient(null);
			} catch (BLLException e) {
				ErrorDialog.showError(e.getMessage());
			}
		}
	
	}
	
	public void annuler() {
		clientFrame.afficherClient(ClientManager.get().getDisplayedClient());
	}

	public void ajouterAnimal() {
		animalController.create();
	}

	public void supprimerAnimal() {
		Animal selected = ClientManager.get().getAnimalManager().getSelectedAnimal();

		if(selected == null) {
			ErrorDialog.showError("Vous devez sélectionner un animal");
			return;
		}
		
		int dialogResult = JOptionPane.showConfirmDialog(
			null,
			"Etes-vous sûr de vouloir supprimer " + selected.getNomAnimal(),
			"Confirmation",
			JOptionPane.WARNING_MESSAGE
		);
		
		if(dialogResult == 0) {
			try {
				ClientManager.get().getAnimalManager().archiver(selected.getCodeAnimal());
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
	}

	public void editerAnimal() {
		Animal selected = ClientManager.get().getAnimalManager().getSelectedAnimal();
		if(selected == null) {
			ErrorDialog.showError("Vous devez sélectionner un animal");
		} else {
			animalController.create(selected);
		}
	}

	@Override
	public void show() {
		clientFrame.setVisible(true);		
	}

	@Override
	public void hide() {
		clientFrame.setVisible(false);		
	}

	@Override
	public JPanel getPanel() {
		return clientFrame;
	}
	
	public ClientsFrame getClientsFrame() {
		return clientFrame;
	}
}
