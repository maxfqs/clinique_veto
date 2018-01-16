package fr.eni.clinique_veto.ihm.personnel;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.ihm.CVApp;

public class PersonnelFrame extends JFrame {
	private static final long serialVersionUID = 1444564582371527529L;
	
	public PersonnelFrame(List<Personnel> prs) {
		this.setTitle(CVApp.APP_TITLE + " - Gestion du personnel");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		JPanel btnc = new JPanel();
		
		// Buttons
		JButton add = new JButton("Ajouter");
		JButton del = new JButton("Supprimer");
		JButton reset = new JButton("Réinitialiser");
		
		btnc.add(add);
		btnc.add(del);
		btnc.add(reset);
		
		container.add(btnc);
		
		// Table
		PersonnelTable pt = new PersonnelTable(prs);
		JScrollPane scroll = new JScrollPane(pt);
		container.add(scroll);
		
		getContentPane().add(container);
	}

}
