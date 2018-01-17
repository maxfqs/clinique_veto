package fr.eni.clinique_veto.ihm.clients;

import java.awt.BorderLayout;
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

public class AjoutClientFrame extends JFrame {

	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 600;
	private static final int TEXTFIELD_WIDTH = 12;

	private JPanel containerBtn;
	private JPanel containerForm;
	
	
	private JButton btnAjouter;
	private JButton btnAnnuler;
	
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

	
	public AjoutClientFrame() {
		this.setTitle("Ajouter un client");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.initComponent();
		this.initListeners();
	}


	private void initListeners() {
		btnAjouter.addActionListener((e)-> AjoutClientController.get().ajouterClient());
		btnAnnuler.addActionListener((e)-> this.setVisible(false));
	}


	private void initComponent() {
		containerBtn = new JPanel();
		containerForm = new JPanel();
		containerForm.setLayout(new GridBagLayout());
		
		btnAjouter = new JButton("ajouter");
		btnAnnuler = new JButton("annuler");
		
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
		containerForm.add(new JLabel("Nom : "), gbd);
		gbd.gridx = 1;
		containerForm.add(nomField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 1;
		containerForm.add(new JLabel("Prénom : "), gbd);
		gbd.gridx = 1;
		containerForm.add(prenomField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 2;
		containerForm.add(new JLabel("Adresse : "), gbd);
		gbd.gridx = 1;
		containerForm.add(adresse1Field, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 3;
		containerForm.add(new JLabel("Adresse 2 : "), gbd);
		gbd.gridx = 1;
		containerForm.add(adresse2Field, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 4;
		containerForm.add(new JLabel("Code postal : "), gbd);
		gbd.gridx = 1;
		containerForm.add(codePostalField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 5;
		containerForm.add(new JLabel("Ville : "), gbd);
		gbd.gridx = 1;
		containerForm.add(villeField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 6;
		containerForm.add(new JLabel("Téléphone : "), gbd);
		gbd.gridx = 1;
		containerForm.add(numTelField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 7;
		containerForm.add(new JLabel("Email : "), gbd);
		gbd.gridx = 1;
		containerForm.add(emailField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 8;
		containerForm.add(new JLabel("Assurance : "), gbd);
		gbd.gridx = 1;
		containerForm.add(assuranceField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 9;
		containerForm.add(new JLabel("Remarques : "), gbd);
		gbd.gridx = 1;
		containerForm.add(scrollpane, gbd);
		
		containerBtn.add(btnAjouter);
		containerBtn.add(btnAnnuler);
		
		this.add(containerBtn, BorderLayout.NORTH);
		this.add(containerForm, BorderLayout.CENTER);
		
	}
}
