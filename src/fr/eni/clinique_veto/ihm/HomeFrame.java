package fr.eni.clinique_veto.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

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
		
		
		JMenu gp = new JMenu("Gestion du personnel");
		JMenuItem gpi = new JMenuItem("Ouvrir");
		gpi.addActionListener((e) -> {
			HomeController.get().selectMenu(PersonnelController.get());
		});
		
		gp.add(gpi);
		menu.add(gp);

		
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JToolBar toolBar = new JToolBar();
		container.add(toolBar);
		
		getContentPane().add(container);
		setJMenuBar(menu);
	}

}
