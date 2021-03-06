package fr.eni.clinique_veto.ihm.clients;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.ihm.agenda.RdzVousController;

public class ResultsSearchClientDialog extends JDialog {

	private static ResultsSearchClientDialog instance;
	
	public static final int FRAME_WIDTH = 550;
	public static final int FRAME_HEIGHT = 300;
	private static final int TEXTFIELD_WIDTH = 15;

	private JPanel containerSearch;
	private static JPanel containerTable;
	private static ClientTable clientTable;

	private JButton btnChercher;
	private JButton validerBtn;


	
	public ResultsSearchClientDialog() {
		this.setTitle("Recherche d'un client");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.initComponent();
		this.initListenersRdzVs();
	}
	
	public static ResultsSearchClientDialog get() {
		if(instance == null) {
			instance = new ResultsSearchClientDialog();
		}
		reset();
		return instance;
	}

	/*
	 * reset la jtable et field de recherche pour display
	 */
	private static void reset() {
		if (clientTable != null) {
			containerTable.remove(clientTable);
		}
	}


	
	public void initListenersRdzVs() {

		validerBtn.addActionListener((e)-> {
			try {
				RdzVousController.get().afficherClient();
			} catch (BLLException e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(this,
					    e1.getMessage(),
					    "erreur",
					    JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	

	private void initComponent() {
		this.setLayout(new BorderLayout());
		containerSearch = new JPanel();
		validerBtn = new JButton("valider");
		containerSearch.add(validerBtn);
		containerTable = new JPanel();

		this.add(containerSearch, BorderLayout.NORTH);
	}

	public void getTableOnSearch(List<Client> resultatRecherche) {

		if (clientTable != null)containerTable.remove(clientTable);
		
		clientTable = new ClientTable(resultatRecherche);
		containerTable.add(clientTable);
		this.add(containerTable, BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
		addListenerTable();
		//pour la recherche depuis le rdzvsController
		if(!this.isVisible())this.setVisible(true);
	}

	/*
	 * récupère le client dans la liste qu'a le manager, via le num de ligne du jtable
	 * puis range le client dans le manager => setdisplayedClient
	 */
	private void addListenerTable() {
		clientTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){ 
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Client clientToDisplay = ClientManager.get().getClients().get(clientTable.getSelectedRow());
				ClientManager.get().setDisplayedClient(clientToDisplay);
			}
	    });		
	}
	
}
