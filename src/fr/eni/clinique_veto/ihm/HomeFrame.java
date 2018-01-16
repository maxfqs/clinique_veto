package fr.eni.clinique_veto.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class HomeFrame extends JFrame {
	private static final long serialVersionUID = -6107674517780760465L;
	
	private JMenuBar menu;
	private JPanel container;
	
	public HomeFrame() {
		setTitle(CVApp.APP_TITLE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize la frame
		
		menu = new JMenuBar();
		
		
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JToolBar toolBar = new JToolBar();
		container.add(toolBar);
		
		getContentPane().add(container);
	}

}
