package fr.eni.clinique_veto.ihm;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.eni.clinique_veto.ihm.clients.ClientController;
import fr.eni.clinique_veto.ihm.personnel.PersonnelController;

public class HomeFrame extends JFrame {
	private static final long serialVersionUID = -6107674517780760465L;
	
	private JMenuBar menu;
	private JPanel container;
	
	public HomeFrame() {
		setTitle(CVApp.APP_TITLE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize la frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		HomeController homeController = HomeController.get();
		
		
		menu = new JMenuBar();
		
		
		// Fichier
		JMenu menuFichier = new JMenu("Fichier");
		JMenuItem itemLogOut = new JMenuItem("Déconnexion");
		JMenuItem itemClose = new JMenuItem("Fermer");
		
		itemLogOut.addActionListener((e) -> homeController.logOut());
		itemClose.addActionListener((e) -> homeController.closeApp());
		
		menuFichier.add(itemLogOut);
		menuFichier.add(itemClose);
		
		menu.add(menuFichier);
		
		
		// RDV - CLIENTS
		JMenu menuRDV = new JMenu("Gestion des rendez-vous");
		JMenuItem itemRDV = new JMenuItem("Prise des rendez-vous");
		JMenuItem itemClient = new JMenuItem("Gestion des clients");

		itemClient.addActionListener((e) -> HomeController.get().selectMenu(ClientController.get()));
		
		menuRDV.add(itemRDV);
		menuRDV.add(itemClient);
		
		menu.add(menuRDV);
		
		
		// Personnel
		JMenu gp = new JMenu("Gestion du personnel");
		JMenuItem gpi = new JMenuItem("Ouvrir");
		gpi.addActionListener((e) -> {
			HomeController.get().selectMenu(PersonnelController.get());
		});
		
		gp.add(gpi);
		menu.add(gp);

		setJMenuBar(menu);
		
		getContentPane().add(ClientController.get().getPanel());
		getContentPane().add(PersonnelController.get().getPanel());
	}

}
