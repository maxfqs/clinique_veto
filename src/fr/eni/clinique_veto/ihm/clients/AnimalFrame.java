package fr.eni.clinique_veto.ihm.clients;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AnimalFrame extends JDialog {

	private static AnimalFrame instance;
	
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 600;
	private static final int TEXTFIELD_WIDTH = 12;

	private JPanel containerBtn;
	private JPanel containerForm;
	
	
	private JButton btnAjouter;
	private JButton btnAnnuler;
	
	private JTextField nomField;
	private JTextField couleurField;
	private JTextField raceField;
	private JTextField especeField;
	private JTextField tatouageField;
	private JTextArea antecedentsField;
	
	private JPanel btnPanel;
	private ButtonGroup groupBtn;
	private JRadioButton male;
	private JRadioButton female;
	

	
	public AnimalFrame() {
		this.setTitle("Editer/Ajouter un animal");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setLayout(new BorderLayout());
		this.initComponent();
		this.initListeners();
	}
	
	public static AnimalFrame get() {
		if(instance == null) {
			instance = new AnimalFrame();
		}
		return instance;
	}


	private void initListeners() {
		btnAjouter.addActionListener((e)-> AnimalController.get().addAnimal());
		btnAnnuler.addActionListener((e)-> this.setVisible(false));
	}


	private void initComponent() {
		containerBtn = new JPanel();
		containerForm = new JPanel();
		containerForm.setLayout(new GridBagLayout());
		
		btnAjouter = new JButton("ajouter");
		btnAnnuler = new JButton("annuler");
		
		nomField = new JTextField(TEXTFIELD_WIDTH);
		couleurField = new JTextField(TEXTFIELD_WIDTH);
		raceField = new JTextField(TEXTFIELD_WIDTH);
		especeField = new JTextField(TEXTFIELD_WIDTH);
		tatouageField = new JTextField(TEXTFIELD_WIDTH);
		antecedentsField = new JTextArea(12,TEXTFIELD_WIDTH-1);
		JScrollPane scrollpane = new JScrollPane(antecedentsField,
	            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		btnPanel = new JPanel();
		female = new JRadioButton("F");
		male = new JRadioButton("M");
		btnPanel.add(male);
		btnPanel.add(female);
		
		groupBtn = new ButtonGroup();
		groupBtn.add(male);
		groupBtn.add(female);
		
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
		containerForm.add(new JLabel("Sexe : "), gbd);
		gbd.gridx = 1;
		containerForm.add(btnPanel, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 2;
		containerForm.add(new JLabel("Couleur : "), gbd);
		gbd.gridx = 1;
		containerForm.add(couleurField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 3;
		containerForm.add(new JLabel("Espece : "), gbd);
		gbd.gridx = 1;
		containerForm.add(especeField, gbd);
		
		gbd.gridx = 0;
		gbd.gridy = 4;
		containerForm.add(new JLabel("Race : "), gbd);
		gbd.gridx = 1;
		containerForm.add(raceField, gbd);
	
		
		gbd.gridx = 0;
		gbd.gridy = 5;
		containerForm.add(new JLabel("Tatouage : "), gbd);
		gbd.gridx = 1;
		containerForm.add(tatouageField, gbd);
		
		
		gbd.gridx = 0;
		gbd.gridy = 6;
		containerForm.add(new JLabel("Antécédents : "), gbd);
		gbd.gridx = 1;
		containerForm.add(scrollpane, gbd);
		
		containerBtn.add(btnAjouter);
		containerBtn.add(btnAnnuler);
		
		this.add(containerBtn, BorderLayout.NORTH);
		this.add(containerForm, BorderLayout.CENTER);
		
	}
	
}
