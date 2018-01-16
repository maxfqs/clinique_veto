package fr.eni.clinique_veto.ihm.connexion;

import javax.swing.SwingUtilities;


public class ConnexionApp {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				ConnexionController.get().startApp();
			}
			
		});
		
	}

}
