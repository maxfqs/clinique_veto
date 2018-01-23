package fr.eni.clinique_veto.ihm.agenda;

import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;
import fr.eni.clinique_veto.ihm.MenuController;
import fr.eni.clinique_veto.ihm.clients.ClientController;
import fr.eni.clinique_veto.ihm.clients.ClientsFrame;
import fr.eni.clinique_veto.ihm.clients.RechercheFrame;
import fr.eni.clinique_veto.ihm.clients.ResultsSearchClientDialog;

public class RdzVousController implements MenuController {

	private static RdzVousController instance;
	private Animal animal;
	private Client client;
	private JDialog clientDial;
	private RdzVousDialog dial ;
	
	
	public RdzVousController() {
		this.dial = new RdzVousDialog();
		hide();
	}
	
	public static RdzVousController get() {
		if(instance == null) {
			instance = new RdzVousController();
		}
		return instance;
	}
	
	public void validerChoixAnimal() {
		animal = ClientManager.get().getAnimalManager().getSelectedAnimal();
		client = ClientManager.get().getDisplayedClient();
		clientDial.setVisible(false);
		dial.setNomRendezVous(client.getNomClient(), animal.getNomAnimal());
	}
	
	
	public void chercherClient(String nomClient) {

		RechercheFrame.get().initListenersRdzVs();
		List<Client> resultatRecherche;
		try {
			resultatRecherche = ClientManager.get().trouverClientParNom(nomClient);
			if(resultatRecherche.size()>0) {
				ResultsSearchClientDialog.get().getTableOnSearch(resultatRecherche);
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
			ResultsSearchClientDialog.get().setVisible(false);
			ClientController.get().getClientsFrame().afficherClient(ClientManager.get().getDisplayedClient());
			ClientController.get().getClientsFrame().afficherAnimaux(ClientManager.get().getAnimauxDisplayedClient());
			if(clientDial == null) {
				clientDial = new JDialog();
			}
			clientDial.setSize(ClientsFrame.FRAME_WIDTH, ClientsFrame.FRAME_HEIGHT);
			clientDial.setContentPane(ClientController.get().getClientsFrame());
			ClientController.get().show();
			clientDial.setVisible(true);
		}else {
			throw new BLLException("il n'y a pas d'élément sélectionné.");
		}
	}

	public void displayRdzVsDialog() {
		
		this.dial.setVisible(true);	
	}

	@Override
	public void show() {
		this.dial.setVisible(true);	
	}

	@Override
	public void hide() {
		this.dial.setVisible(false);	
		
	}

	@Override
	public JPanel getPanel() {
		return this.dial;
	}

}
