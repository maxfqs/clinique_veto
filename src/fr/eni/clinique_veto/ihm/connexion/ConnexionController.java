package fr.eni.clinique_veto.ihm.connexion;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ConnexionManager;
import fr.eni.clinique_veto.bo.Personnel;
public class ConnexionController {
	
	private EcranConnexion ecrConnexion;
	private ConnexionManager mger;
	private static ConnexionController instance;
	
	private ConnexionController(){
		try {
			mger = new ConnexionManager();
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static synchronized ConnexionController get(){
		if(instance == null){
			instance = new ConnexionController();
		}
		return instance;
	}
	
	public void startApp(){	
		ecrConnexion = new EcranConnexion();
		ecrConnexion.setVisible(true);
		getEventValider();
	}
	public void getEventValider(){
		ecrConnexion.getValider().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(mger.verifierUser(ecrConnexion.getTfNom().getText(),ecrConnexion.getTfPassword().getText())){
						ecrConnexion.setVisible(false);
						
					} else {
						
					}
					
				} catch (BLLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	
}
