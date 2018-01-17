package fr.eni.clinique_veto.ihm.clients;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.eni.clinique_veto.bo.AnimalTest;

public class ClientsFrame extends JFrame {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	private static final int TEXTFIELD_WIDTH = 12;
	
	// containers principaux
	private JPanel containerBtn;
	private JPanel containerInfosClient;
	private JPanel animauxPanel ;
	private JPanel containerBtnAnimaux;
	
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
	JButton btnRechercheClt;
	JButton btnAjouterClt;
	JButton btnSupprimerClt;
	JButton btnValiderClt;
	JButton btnAnnulerClt;

	JButton btnAjouterAnimal;
	JButton btnSupprAnimal;
	JButton btnEditerAnimal;
	
	
	public ClientsFrame() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.initComponentClient();
		this.initComponentAnimaux();
		this.initListeners();
	}


	private void initListeners() {
		btnRechercheClt.addActionListener((e)-> System.out.println("clic sur recherche client"));
		btnAjouterClt.addActionListener((e)-> System.out.println("clic sur ajout client"));
		btnValiderClt.addActionListener((e)-> System.out.println("clic sur valider client"));
		btnAnnulerClt.addActionListener((e)-> System.out.println("clic sur annuler client"));
		btnSupprimerClt.addActionListener((e)-> System.out.println("clic sur supprimer client"));
		
		btnAjouterAnimal.addActionListener((e)-> System.out.println("clic sur ajouter animal"));
		btnSupprAnimal.addActionListener((e)-> System.out.println("clic sur supprimer animal"));
		btnEditerAnimal.addActionListener((e)-> System.out.println("clic sur editer animal"));
		
	}


	private void initComponentAnimaux() {
		// partie pour tester à revoir
		AnimalTest a2 = new AnimalTest("numero2", "nom2", "sexe2", "couleu2", "race2", "espece22", "tatouage2");
		AnimalTest a3 = new AnimalTest("numero3", "nom3", "sexe3", "couleur3", "race3", "espece3", "tatouage3");
		AnimalTest a1 = new AnimalTest("numero", "nom", "sexe", "couleur", "race", "espece", "tatouage");
		List<AnimalTest> listeAnimaux = new ArrayList<>();
		listeAnimaux.add(a1);
		listeAnimaux.add(a2);
		listeAnimaux.add(a3);
		//==========================
		animauxPanel = new JPanel();
		containerBtnAnimaux = new JPanel();
		
		btnAjouterAnimal = new JButton("ajouter");
		btnSupprAnimal = new JButton("supprimer");
		btnEditerAnimal = new JButton("éditer");
		containerBtnAnimaux.add(btnAjouterAnimal);
		containerBtnAnimaux.add(btnSupprAnimal);
		containerBtnAnimaux.add(btnEditerAnimal);
	
		AnimauxTable table = new AnimauxTable(listeAnimaux);
		table.setPreferredScrollableViewportSize(new Dimension(500,150));
		JScrollPane scroll = new JScrollPane(table);
		animauxPanel.add(scroll);
		animauxPanel.add(containerBtnAnimaux);

	
		this.add(animauxPanel, BorderLayout.CENTER);
		
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
		
		btnRechercheClt = new JButton("Rechercher");
		btnAjouterClt = new JButton("ajouter");
		btnSupprimerClt = new JButton("supprimer");
		btnValiderClt = new JButton("valider");
		btnAnnulerClt = new JButton("Annuler");
		
		containerBtn.add(btnRechercheClt);
		containerBtn.add(btnAjouterClt);
		containerBtn.add(btnSupprimerClt);
		containerBtn.add(btnValiderClt);
		containerBtn.add(btnAnnulerClt);
		
		// ajout au frame principal
		this.add(containerBtn, BorderLayout.NORTH);
		this.add(containerInfosClient, BorderLayout.WEST);
		
	
	}
	
	
}
