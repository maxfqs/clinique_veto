package fr.eni.clinique_veto.ihm.personnel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.ihm.CVApp;
import fr.eni.clinique_veto.ihm.HomeController;

public class PersonnelFrame extends JFrame {
	private static final long serialVersionUID = 1444564582371527529L;

	private JButton add, del, reset;
	
	public PersonnelFrame(List<Personnel> prs) {
		this.setTitle(CVApp.APP_TITLE + " - Gestion du personnel");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
				
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel btnc = new JPanel();
		
		// Buttons
		add = new JButton("Ajouter");
		del = new JButton("Supprimer");
		reset = new JButton("Réinitialiser");
		
		del.setEnabled(false);
		reset.setEnabled(false);
		
		btnc.add(add);
		btnc.add(del);
		btnc.add(reset);
		
		container.add(btnc);
		
		add.addActionListener((e) -> PersonnelController.get().openAddPersonnel());
		reset.addActionListener((e) -> PersonnelController.get().openResetPersonnel());
		
		
		// Table
		PersonnelTable pt = new PersonnelTable(prs);
		JScrollPane scroll = new JScrollPane(pt);
		container.add(scroll);
		
		getContentPane().add(container);
	}
	
	public void enableActionButtons() {
		del.setEnabled(true);
		reset.setEnabled(true);
	}

}
