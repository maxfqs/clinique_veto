package fr.eni.clinique_veto.ihm.personnel;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PersonnelDeleteDialog extends JDialog {
	
	public PersonnelDeleteDialog() {
		setSize(350, 100);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

		String info = "Etes-vous sûr de vouloir supprimer " +
				PersonnelController.get().getSelectedPersonnel().getNom();
		
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		container.add(new JLabel(info), gbc);
		
		gbc.gridwidth = 1;
		
		// Action buttons
		JButton cancel = new JButton("Annuler");
		JButton valid = new JButton("Valider");
		
		gbc.gridx = 0;
		gbc.gridy = 1;		
		container.add(cancel, gbc);
		
		gbc.gridx = 1;
		container.add(valid, gbc);
		
		cancel.addActionListener((e) -> PersonnelDeleteController.get().destroy());
		valid.addActionListener((e) -> PersonnelDeleteController.get().valider());
	
		getContentPane().add(container);
	}
}
