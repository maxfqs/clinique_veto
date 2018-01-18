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
	private JTextField nom, couleur, tatoo;
	private JComboBox<String> sexe, espece, race;
	
	
	public AnimalDialog(Animal a) {
		setTitle("Animal: " + a.getNomAnimal()); 
		setSize(500, 300);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		AnimalController controller = AnimalController.get();
				
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		int row = 0;
		
		gbc.gridy = row++;
		gbc.gridx = 0;
		container.add(new JLabel("Client: " + a.getCodeClient()), gbc);
		
		gbc.gridy = row++;
		gbc.gridx = 0;
		container.add(new JLabel("Code: " + a.getCodeAnimal()), gbc);
		
		nom = new JTextField(10);
		nom.setText(a.getNomAnimal());
		String[] sexes = {"Male", "Femele"};
		sexe = new JComboBox<String>(sexes);
		
		gbc.gridy = row++;
		gbc.gridx = 0;
		container.add(new JLabel("Nom: "), gbc);
		gbc.gridx = 1;
		container.add(nom, gbc);
		gbc.gridx = 2;
		container.add(sexe, gbc);
		
		couleur = new JTextField(10);
		couleur.setText(a.getCouleur());
		gbc.gridy = row++;
		gbc.gridx = 0;
		container.add(new JLabel("Couleur: "), gbc);
		gbc.gridx = 1;
		container.add(couleur, gbc);
		
		
		tatoo = new JTextField(10);
		tatoo.setText(a.getTatouage());
		gbc.gridy = row++;
		gbc.gridx = 0;
		container.add(new JLabel("Tatouage: "), gbc);
		gbc.gridx = 1;
		container.add(tatoo, gbc);

		JButton cancel = new JButton("Annuler");
		JButton valid = new JButton("Valider");
		
		cancel.addActionListener((e) -> controller.destroy());
		valid.addActionListener((e) -> controller.valid());
		
		gbc.gridy = row++;		
		gbc.gridx = 0;
		container.add(cancel, gbc);
		gbc.gridx = 1;
		container.add(valid, gbc);
		
		getContentPane().add(container);		
	}
}
