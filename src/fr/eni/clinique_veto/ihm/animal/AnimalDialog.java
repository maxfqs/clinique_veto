package fr.eni.clinique_veto.ihm.animal;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.clinique_veto.bo.Animal;

@SuppressWarnings("serial")
public class AnimalDialog extends JDialog {
	private JComboBox<String> especes, races, sexes;
	private JTextField nom, couleur, tatoo;
	private JLabel client;
	
	public AnimalDialog() {
		setSize(500, 300);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		AnimalController controller = AnimalController.get();
		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		
		client = new JLabel();
		nom = new JTextField(15);
		couleur = new JTextField(15);
		tatoo = new JTextField(15);
		
		especes = new JComboBox<String>();
		races = new JComboBox<String>();
		sexes = new JComboBox<String>();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		int row = 0;
		
		// Client
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(new JLabel("Client: "), gbc);
		gbc.gridx = 1;
		container.add(client, gbc);
		
		// NOM SEXES
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(new JLabel("Nom: "), gbc);
		gbc.gridx = 1;
		container.add(nom, gbc);
		gbc.gridx = 2;
		container.add(new JLabel("Sexes: "), gbc);
		gbc.gridx = 3;
		container.add(sexes, gbc);
		
		// COULEUR
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(new JLabel("Couleur: "), gbc);
		gbc.gridx = 1;
		container.add(couleur, gbc);
		
		// ESPECES RACES
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(new JLabel("Espèces: "), gbc);
		gbc.gridx = 1;
		container.add(especes, gbc);
		gbc.gridx = 2;
		container.add(new JLabel("Races: "), gbc);
		gbc.gridx = 3;
		container.add(races, gbc);		
		
		// TATOO
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(new JLabel("Tatouage: "), gbc);
		gbc.gridx = 1;
		container.add(tatoo, gbc);
		
		
		// ACTION BUTTONS
		JButton cancel = new JButton("Annuler");
		JButton valid = new JButton("Valider");
		
		cancel.addActionListener((e) -> controller.destroy());
		valid.addActionListener((e) -> controller.valid());
		
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(cancel, gbc);
		gbc.gridx = 2;
		container.add(valid, gbc);
		
		getContentPane().add(container);
		
	}
	
	public JLabel getClient() { return client; }
	public JTextField getNom() { return nom; }
	public JTextField getCouleur() { return couleur; }
	public JTextField getTatoo() { return tatoo; }
	public JComboBox<String> getEspeces() {return especes; }
	public JComboBox<String> getRaces() {return races; }
	public JComboBox<String> getSexes() {return sexes; }
}
