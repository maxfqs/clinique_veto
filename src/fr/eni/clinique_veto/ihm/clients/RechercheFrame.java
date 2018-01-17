package fr.eni.clinique_veto.ihm.clients;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;
import fr.eni.clinique_veto.dal.jdbc.ClientsDAOImplJDBC;

public class RechercheFrame extends JFrame {

	private static final int FRAME_WIDTH = 550;
	private static final int FRAME_HEIGHT = 300;
	private static final int TEXTFIELD_WIDTH = 15;

	private JPanel containerSearch;
	private JPanel containerTable;

	private ClientTable clientTable;

	private JButton btnChercher;
	private JTextField searchField;

	public RechercheFrame() {
		this.setTitle("Recherche d'un client");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.initListeners();
	}

	private void initListeners() {
		btnChercher.addActionListener((e) -> RechercheClientController.get().chercherClient(searchField.getText()));
	}

	private void initComponent() {
		this.setLayout(new BorderLayout());
		containerSearch = new JPanel();
		btnChercher = new JButton("chercher");
		searchField = new JTextField(TEXTFIELD_WIDTH);
		containerSearch.add(searchField);
		containerSearch.add(btnChercher);
		containerTable = new JPanel();

		this.add(containerSearch, BorderLayout.NORTH);
	}

	public void getTableOnSearch() {
		// pour test
		ClientsDAOImplJDBC dao = new ClientsDAOImplJDBC();
		List<Client> listeClient = null;
		try {
			listeClient = dao.trouverParNom("MaxDef");
		} catch (ClientDALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ===

		if (clientTable != null) {
			containerTable.remove(clientTable);
		}
		clientTable = new ClientTable(listeClient);
		containerTable.add(clientTable);
		this.add(containerTable, BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
	}

}
