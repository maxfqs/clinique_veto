package fr.eni.clinique_veto.ihm.animal;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AnimalDialog extends JDialog {
	private JTextField nom, couleur, tatoo;
	
	
	public AnimalDialog() {
		setSize(500, 300);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		AnimalController controller = AnimalController.get();
		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		
		nom = new JTextField(15);
		couleur = new JTextField(15);
		tatoo = new JTextField(15);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		int row = 0;
				
		// NOM
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(new JLabel("Nom: "), gbc);
		gbc.gridx = 1;
		container.add(nom, gbc);
		
		// COULEUR
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(new JLabel("Couleur: "), gbc);
		gbc.gridx = 1;
		container.add(couleur, gbc);
		
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
		
		gbc.gridx = 0;
		gbc.gridy = row++;
		container.add(cancel, gbc);
		gbc.gridx = 1;
		container.add(valid, gbc);
		
		getContentPane().add(container);
		
	}
	
	public JTextField getNom() { return nom; }
	public JTextField getCouleur() { return couleur; }
	public JTextField getTatoo() { return tatoo; }
}
