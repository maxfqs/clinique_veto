package fr.eni.clinique_veto.ihm.agenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bll.RendezVousManager;
import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;
import fr.eni.clinique_veto.ihm.ErrorDialog;
import fr.eni.clinique_veto.ihm.MenuController;
import fr.eni.clinique_veto.ihm.clients.ClientController;
import fr.eni.clinique_veto.ihm.clients.ClientsFrame;
import fr.eni.clinique_veto.ihm.clients.RechercheFrame;
import fr.eni.clinique_veto.ihm.clients.ResultsSearchClientDialog;

public class RdzVousController implements MenuController {

	private static RdzVousController instance;
	private List<Personnel> listeVetos ;
	private List<RendezVous> listeRdzVs;
	private Animal animal;
	private Client client;
	private Personnel veto;
	private Date date;
	private String heure;
	private String minutes;
	private JDialog clientDial;
	private RdzVousDialog dial ;

	
	
	public RdzVousController() {
		this.listeRdzVs =new ArrayList<RendezVous>();
		listeVetos = PersonnelManager.get().getVeto();
		this.dial = new RdzVousDialog(listeVetos);
		this.veto = listeVetos.get(0);
		this.date =  new Date();
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

	public void addRdzVous() {
		if( client == null) {
			 ErrorDialog.showError("Merci de renseigner un client");
		}else {
			if(veto == null) {
				veto = this.listeVetos.get(0);
			}
			if(minutes == null) {
				this.minutes = "00";
			}
			if(heure == null){
				this.heure = "07";
			}
			try {
				RendezVousManager.addRdv(veto, animal, date, Integer.parseInt(heure), Integer.parseInt(minutes));
				listeRdzVs.clear();
				listeRdzVs.addAll(RendezVousManager.getVetoRdvForDate(veto, date));
				dial.onListUpdated();
			} catch (BLLException e) {
				ErrorDialog.showError("Cet horaire n'est pas libre, merci d'en choisir un autre");
				e.printStackTrace();
			}
		}
	
	}

	public void setVeto(int index) {
		this.veto = listeVetos.get(index);
	}

	public void setDate(Date pDate) {
		this.date = pDate;
	}

	public void setHeure(String h) {
		this.heure = h;
	}

	public void setMinutes(String m) {
		this.minutes = m;
	}

	public void getRendezVous() {
			try {
				listeRdzVs.clear();
				listeRdzVs.addAll(RendezVousManager.getVetoRdvForDate(veto, date));
			} catch (BLLException e) {
				e.printStackTrace();
			}	
		dial.addTable(listeRdzVs);
	}
	
	public List<RendezVous> getListeRdzVous(){
		return this.listeRdzVs;
	}




}
