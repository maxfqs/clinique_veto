package fr.eni.clinique_veto.ihm.personnel;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.clinique_veto.bo.PersonnelRole;

@SuppressWarnings("serial")
public class PersonnelAddDialog extends JDialog {
	
	JComboBox<String> role;
	JTextField name, password;
	
	public PersonnelAddDialog() {
		setSize(500, 300);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		container.add(new JLabel("Création d'un personnel"), gbc);
		
		gbc.gridwidth = 1;
		
		// Field
		name = new JTextField(10);
		password = new JTextField(10);
		role = new JComboBox<String>();
		DefaultComboBoxModel<String> rModel = new DefaultComboBoxModel<String>(
				PersonnelRole.getDescriptions()
		);
		role.setModel(rModel);
		
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		container.add(new JLabel("Nom"), gbc);
		gbc.gridx = 1;
		container.add(name, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		container.add(new JLabel("Role"), gbc);
		gbc.gridx = 1;
		container.add(role, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		container.add(new JLabel("Mot de passe"), gbc);
		gbc.gridx = 1;
		container.add(password, gbc);
		
		// Action buttons
		JButton cancel = new JButton("Annuler");
		JButton valid = new JButton("Valider");
		
		gbc.gridx = 0;
		gbc.gridy = 4;		
		container.add(cancel, gbc);
		
		gbc.gridx = 1;
		container.add(valid, gbc);
		
		cancel.addActionListener((e) -> PersonnelAddController.get().destroy());
		valid.addActionListener((e) -> PersonnelAddController.get().valider());
	
		getContentPane().add(container);
	}

	public JTextField getNameField() {
		return name;
	}

	public JTextField getPasswordField() {
		return password;
	}

	public JComboBox<String> getRoleCBox() {
		return role;
	}
	
	public PersonnelRole getSelectedRole() {
		String desc = (String) role.getSelectedItem();
		return PersonnelRole.selectByDescription(desc);		
	}
}
