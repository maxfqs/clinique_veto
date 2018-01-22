package fr.eni.clinique_veto.ihm.personnel;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PersonnelFrame extends JPanel {
	private static final long serialVersionUID = 1444564582371527529L;

	private JButton add, del, reset;
	private PersonnelTable personnelTable;
	
	public PersonnelFrame() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel btnc = new JPanel();
		
		// Buttons
		add = new JButton("Ajouter");
		del = new JButton("Supprimer");
		reset = new JButton("R�initialiser");
		
		del.setEnabled(false);
		reset.setEnabled(false);
		
		btnc.add(add);
		btnc.add(del);
		btnc.add(reset);
		
		container.add(btnc);
		
		add.addActionListener((e) -> PersonnelController.get().openAddPersonnel());
		reset.addActionListener((e) -> PersonnelController.get().openResetPersonnel());
		del.addActionListener((e) -> PersonnelController.get().openDeletePersonnel());
		
		// Table
		personnelTable = new PersonnelTable();
		JScrollPane scroll = new JScrollPane(personnelTable);
		container.add(scroll);
		
		add(container);
	}
	
	public void enableActionButtons(boolean enabled) {
		del.setEnabled(enabled);
		reset.setEnabled(enabled);
	}
	
	public PersonnelTable getPersonnelTable() {
		return personnelTable;
	}
}
