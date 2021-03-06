package fr.eni.clinique_veto.ihm.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.eni.clinique_veto.bll.ClientManager;
import fr.eni.clinique_veto.bll.ManagerListObserver;
import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.ihm.agenda.RdzVousController;

public class ClientsFrame_Impl_RdsVs  extends JPanel implements ManagerListObserver {

	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 600;
	private static final int TEXTFIELD_WIDTH = 12;
	
	// containers principaux
	private JPanel containerBtn;
	private JPanel containerInfosClient;
	private JPanel animauxPanel ;
	private JPanel containerBtnAnimaux;
	private AnimauxTable table;
	
	// => partie client
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField adresse1Field;
	private JTextField adresse2Field;
	private JTextField codePostalField;
	private JTextField villeField;
	private JTextField numTelField;
	private JTextField assuranceField;
	private JTextField emailField;
	private JTextArea remarqueField;
	
	
	//partie btn


	JButton btnValiderChoix;
	
	
	public ClientsFrame_Impl_RdsVs()  {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLayout(new BorderLayout());
		this.initComponentClient();
		this.initComponentAnimaux();
		this.initListeners();
	}
	

	private void initListeners() {
		btnValiderChoix.addActionListener((e)-> RdzVousController.get().validerChoixAnimal());
	}


	private void initComponentAnimaux() {

		animauxPanel = new JPanel();
		animauxPanel.setLayout(new BoxLayout(animauxPanel, BoxLayout.Y_AXIS));
		containerBtnAnimaux = new JPanel();
		btnValiderChoix = new JButton("valider");
		containerBtnAnimaux.add(btnValiderChoix);
	
	}
	

	private void initComponentClient() {
		
		//=>partie client
		containerInfosClient = new JPanel();
		containerInfosClient.setLayout(new GridBagLayout());
		
		nomField = new JTextField(TEXTFIELD_WIDTH);
		prenomField = new JTextField(TEXTFIELD_WIDTH);
		adresse1Field = new JTextField(TEXTFIELD_WIDTH);
		adresse2Field = new JTextField(TEXTFIELD_WIDTH);
		codePostalField = new JTextField(TEXTFIELD_WIDTH);
		villeField = new JTextField(TEXTFIELD_WIDTH);
		numTelField = new JTextField(TEXTFIELD_WIDTH);
		assuranceField = new JTextField(TEXTFIELD_WIDTH);
		emailField = new JTextField(TEXTFIELD_WIDTH);
		remarqueField = new JTextArea(12,TEXTFIELD_WIDTH-1);
		JScrollPane scrollpane = new JScrollPane(remarqueField,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		GridBagConstraints gbd = new GridBagConstraints();
		gbd.insets =new Insets(5, 5, 5, 5);
		gbd.anchor = GridBagConstraints.WEST;
		
		gbd.gridx = 0;
		gbd.gridy = 0;
		containerInfosClient.add(new JLabel("Nom : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(nomField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 1;
		containerInfosClient.add(new JLabel("Prénom : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(prenomField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 2;
		containerInfosClient.add(new JLabel("Adresse : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(adresse1Field, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 3;
		containerInfosClient.add(new JLabel("Adresse 2 : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(adresse2Field, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 4;
		containerInfosClient.add(new JLabel("Code postal : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(codePostalField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 5;
		containerInfosClient.add(new JLabel("Ville : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(villeField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 6;
		containerInfosClient.add(new JLabel("Téléphone : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(numTelField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 7;
		containerInfosClient.add(new JLabel("Email : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(emailField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 8;
		containerInfosClient.add(new JLabel("Assurance : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(assuranceField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 9;
		containerInfosClient.add(new JLabel("Remarques : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(scrollpane, gbd);
		
		//partie btn
		containerBtn = new JPanel();
		

		
		// ajout au frame principal
		this.add(containerBtn, BorderLayout.NORTH);
		this.add(containerInfosClient, BorderLayout.WEST);
		
	
	}

	public void afficherClient(Client c) {

		nomField.setText(c.getNomClient());;
		prenomField.setText(c.getPrenomClient());;
		adresse1Field.setText(c.getAdresse1());;
		adresse2Field.setText(c.getAdresse2());;
		codePostalField.setText(c.getCodePostal());;
		villeField.setText(c.getVille());;
		numTelField.setText(c.getNumTel());;
		assuranceField.setText(c.getAssurance());;
		emailField.setText(c.getEmail());;
		remarqueField.setText(c.getRemarque());;
		this.revalidate();
	}
	
	
	
	public void resetFields() {
		nomField.setText("");
		prenomField.setText("");
		adresse1Field.setText("");
		adresse2Field.setText("");
		codePostalField.setText("");
		villeField.setText("");
		numTelField.setText("");
		assuranceField.setText("");
		emailField.setText("");
		remarqueField.setText("");
		this.revalidate();
	}
	
	public Client getClientInfos() {
		return new Client(
				ClientManager.get().getDisplayedClient().getCodeClient(),
				nomField.getText(),
				prenomField.getText(),
				adresse1Field.getText(),
				adresse2Field.getText(),
				codePostalField.getText(),
				villeField.getText(), 
				numTelField.getText(),
				assuranceField.getText(), 
				emailField.getText(),
				remarqueField.getText(),
				0);	
	}

	public void afficherAnimaux(List<Animal> list) {
	
		animauxPanel.removeAll();
		table = new AnimauxTable(list);
		table.setPreferredScrollableViewportSize(new Dimension(500,150));
		JScrollPane scroll = new JScrollPane(table);
		animauxPanel.add(scroll);
		animauxPanel.add(containerBtnAnimaux);
	
		this.add(animauxPanel, BorderLayout.CENTER);
		this.revalidate();
	}

	@Override
	public void onListUpdated() {
		table.getModelTable().fireTableDataChanged();
		table.requestFocus();
		table.changeSelection(ClientManager.get().getAnimauxDisplayedClient().size()-1,0,false, false);
	}



	
}
