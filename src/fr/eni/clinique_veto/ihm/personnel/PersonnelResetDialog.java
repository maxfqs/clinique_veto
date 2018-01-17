package fr.eni.clinique_veto.ihm.personnel;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PersonnelResetDialog extends JDialog {
	private JTextField password;
	
	
	public PersonnelResetDialog() {
		setSize(500, 300);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		String title = "Réinitialiser le mot de passe de " + 
				PersonnelController.get().getSelectedPersonnel().getNom();
		
		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		container.add(new JLabel(title), gbc);
		
		gbc.gridwidth = 1;
		
		// Field
		password = new JTextField(10);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		container.add(new JLabel("Mot de passe"), gbc);
		gbc.gridx = 1;
		container.add(password, gbc);
		
		// Action buttons
		JButton cancel = new JButton("Annuler");
		JButton valid = new JButton("Valider");
		
		gbc.gridx = 0;
		gbc.gridy = 2;		
		container.add(cancel, gbc);
		
		gbc.gridx = 1;
		container.add(valid, gbc);
		
		cancel.addActionListener((e) -> PersonnelResetController.get().destroy());
		
		getContentPane().add(container);
	}
}
