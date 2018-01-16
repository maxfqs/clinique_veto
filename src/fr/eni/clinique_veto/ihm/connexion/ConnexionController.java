package fr.eni.clinique_veto.ihm.connexion;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.LoginMger;
import fr.eni.clinique_veto.bo.Personnel;
public class ConnexionController {
	
	private EcranConnexion ecrConnexion;
	private LoginMger mger;
	private static ConnexionController instance;
	
	private ConnexionController(){
		try {
			mger = new LoginMger();
			
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
					Personnel pers = null;
					pers = mger.verifierUser(ecrConnexion.getTfNom().getText()
							,ecrConnexion.getTfPassword().getText());
					if(pers != null){
						ecrConnexion.setVisible(false);
						
					} else {
						System.out.println("Personnel non trouvé");
					}
					
				} catch (BLLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	
}
