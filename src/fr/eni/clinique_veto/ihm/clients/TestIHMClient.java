package fr.eni.clinique_veto.ihm.clients;



public class TestIHMClient {

	public static void main(String[] args) {

		
		ClientController.get().getClientsFrame().setVisible(true);
	

		AjoutClientFrame aframe  =new AjoutClientFrame();
		aframe.setVisible(true);
		
//		AnimalFrame aniframe  =new AnimalFrame();
//		aniframe.setVisible(true);
//		
//		RechercheFrame rframe = new RechercheFrame();
//		rframe.setVisible(true);
		
//		DossierMedicalDialog dmDial = new DossierMedicalDialog();
//		dmDial.setVisible(true);
	}

}
