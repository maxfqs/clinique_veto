package fr.eni.clinique_veto.ihm.animal;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.ihm.IHMException;

public class Test {
	public static void main(String[] args) {
		try {
			AnimalController ac = AnimalController.get();
			Animal anm = new Animal(0, "Firas", 'F', "Bleu", "Chien", "Caniche", 8, null, null, false);
			Client c = null;
			ac.create(c, anm);
		} catch (IHMException e) {
			e.printStackTrace();
		}
	}
}
