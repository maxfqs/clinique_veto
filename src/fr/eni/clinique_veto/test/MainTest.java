package fr.eni.clinique_veto.test;

import fr.eni.clinique_veto.bll.BLLException;
import fr.eni.clinique_veto.bll.ConnexionManager;
import fr.eni.clinique_veto.ihm.CVApp;
import fr.eni.clinique_veto.ihm.LoginController;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test");
		CVApp.main(null);
		try {
			ConnexionManager.get().logUser("Firas", "firas");
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
