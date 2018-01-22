package fr.eni.clinique_veto.ihm.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class DossierMedicalDialog extends JDialog {
	
	private static int WIDTH = 600;
	private static int HEIGHT = 500;
	
	private JPanel northPanel;
	private JPanel btnPanel;
	private JPanel antecedentsPanel;
	
	private JPanel westPanel;
	private JPanel infosNomClient;
	private JLabel nomClientLabel;
	private JPanel animalPanel;
	
	private JLabel infosRace;
	private JLabel infosEspece;
	private JLabel infosCodeAnimal;
	private JLabel infosSexe;
	private JLabel infosCouleur;
	private JLabel infosNomAnimal;
	private JLabel infosTatouage;
	private JLabel labelRace;
	private JLabel labelEspece;
	private JLabel labelCodeAnimal;
	private JLabel labelSexe;
	private JLabel labelCouleur;
	private JLabel labelNomAnimal;
	private JLabel labelTatouage;
	private JTextArea infosAntecedents;
	private JLabel antecedents;

	private JButton btnValider;
	private JButton btnAnnuler;

	public DossierMedicalDialog() {
		this.setTitle("Dossier médical");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();
		this.initListeners();
	}

	private void initListeners() {
		btnValider.addActionListener((e)-> System.out.println("validé"));
		btnAnnuler.addActionListener((e)-> System.out.println("annulé"));
	}

	private void initComponent() {
		TitledBorder title;
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		northPanel = new JPanel();
		btnPanel = new JPanel();
		btnValider = new JButton("Valider");
		btnAnnuler = new JButton("Annuler");
		
		btnPanel.add(btnValider);
		btnPanel.add(btnAnnuler);

		
		northPanel.setLayout(new BorderLayout());
		northPanel.add(btnPanel, BorderLayout.EAST);
		
		
		northPanel.setBorder(blackline);
				
		westPanel = new JPanel();
		westPanel.setMinimumSize(new Dimension(250,200));
		westPanel.setPreferredSize(new Dimension(250,200));
		
		westPanel.setLayout(new GridLayout(2,1));
		animalPanel = new JPanel();
		animalPanel.setLayout(new GridLayout(7,1));
		
		infosNomAnimal = new JLabel();
		infosRace = new JLabel();
		infosEspece = new JLabel();
		infosCodeAnimal = new JLabel();
		infosSexe = new JLabel();
		infosCouleur = new JLabel();
		infosTatouage = new JLabel();
	

		title = BorderFactory.createTitledBorder("Animal");	
		animalPanel.setBorder(title);
		
		labelNomAnimal = new JLabel("nom : ");
		labelEspece = new JLabel("espèce : ");
		labelCodeAnimal = new JLabel("numéro : ");
		labelSexe = new JLabel("sexe : ");
		labelCouleur = new JLabel("couleur : ");
		labelRace = new JLabel("race : ");
		labelTatouage = new JLabel("num tatouage : ");
		animalPanel.add(labelNomAnimal);
		animalPanel.add(infosNomAnimal);
		animalPanel.add(labelCodeAnimal);
		animalPanel.add(infosCodeAnimal);
		animalPanel.add(labelEspece);
		animalPanel.add(infosEspece);
		animalPanel.add(labelRace);
		animalPanel.add(infosRace);
		animalPanel.add(labelSexe);
		animalPanel.add(infosSexe);
		animalPanel.add(labelCouleur);
		animalPanel.add(infosCouleur);
		animalPanel.add(labelTatouage);
		animalPanel.add(infosTatouage);
		
		infosNomClient = new JPanel();
	
		title = BorderFactory.createTitledBorder("Nom du client");	
		infosNomClient.setBorder(title);
		//label  à changer dynamiquement
		nomClientLabel = new JLabel();
		infosNomClient.add(nomClientLabel);
		
		westPanel.add(infosNomClient);
		westPanel.add(animalPanel);
		
		
		antecedentsPanel = new JPanel();
		antecedentsPanel.setLayout(new BoxLayout(	antecedentsPanel, BoxLayout.Y_AXIS));
		antecedents = new JLabel("Antécédents");
		infosAntecedents = new JTextArea();
		antecedentsPanel.add(antecedents);
		antecedentsPanel.add(infosAntecedents);
		
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(westPanel, BorderLayout.WEST);
		this.add(antecedentsPanel, BorderLayout.CENTER);
	}
	
	

}
