package fr.eni.clinique_veto.bo;

import fr.eni.clinique_veto.bo.personnel.Personnel;

public class AppliTestBO {

	public static void main(String[] args) {
		System.out.println("Création nouveau personnel");
		Personnel p = new Personnel("Bob Dylan", "1234", "ADM", false);		
		System.out.println(p);
	}

}
