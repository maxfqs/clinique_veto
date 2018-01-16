package fr.eni.clinique_veto.ihm;

public class HomeController {
	public static HomeController instance;
	private HomeFrame hframe;
	
	private HomeController() {
		
	}
	
	public static HomeController get() {
		if(instance == null) {
			instance = new HomeController();
		}
		
		return instance;
	}
	
	public void createFrame() {
		hframe = new HomeFrame();
		hframe.setVisible(true);
	}
	
	public void closeFrame() {
		hframe.setVisible(false);
		hframe.dispose();
	}
}
