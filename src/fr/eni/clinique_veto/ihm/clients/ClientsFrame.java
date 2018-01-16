package fr.eni.clinique_veto.ihm.clients;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientsFrame extends JFrame {

	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 400;
	
	// containers principaux
	private JPanel containerBtn;
	private JPanel containerInfosClient;
	private JPanel containerTableAnimal;
	
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
	JButton btnRecherche;
	JButton btnAjouter;
	JButton btnSupprimer;
	JButton btnValider;
	JButton btnAnnuler;

	
	public ClientsFrame() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		this.initComponent();
	}


	private void initComponent() {
		
		//=>partie client
		containerInfosClient = new JPanel();
		containerInfosClient.setLayout(new GridBagLayout());
		
		nomField = new JTextField(10);
		prenomField = new JTextField(10);
		adresse1Field = new JTextField(10);
		adresse2Field = new JTextField(10);
		codePostalField = new JTextField(10);
		villeField = new JTextField(10);
		numTelField = new JTextField(10);
		assuranceField = new JTextField(10);
		emailField = new JTextField(10);
		remarqueField = new JTextArea(20,10);
		JScrollPane scrollpane = new JScrollPane(remarqueField,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		GridBagConstraints gbd = new GridBagConstraints();
		gbd.insets =new Insets(5, 5, 5, 5);
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
		
//		gbd.gridx = 0;
		gbd.gridy = 3;
//		containerInfosClient.add(new JLabel("Adresse : "), gbd);
		gbd.gridx = 1;
		containerInfosClient.add(adresse1Field, gbd);
		
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
		
		btnRecherche = new JButton("Rechercher");
		btnAjouter = new JButton("ajouter");
		btnSupprimer = new JButton("supprimer");
		btnValider = new JButton("valider");
		btnAnnuler = new JButton("Annuler");
		
		containerBtn.add(btnRecherche);
		containerBtn.add(btnAjouter);
		containerBtn.add(btnSupprimer);
		containerBtn.add(btnValider);
		containerBtn.add(btnAnnuler);
		
		// ajout au frame principal
		gbd.gridy = 0;
		gbd.gridwidth = 0;
		this.add(containerBtn, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 1;
		gbd.gridwidth = 2;
		this.add(containerInfosClient, gbd);
	}
	
	
}
