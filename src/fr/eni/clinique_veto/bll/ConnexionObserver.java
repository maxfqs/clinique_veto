package fr.eni.clinique_veto.bll;

import fr.eni.clinique_veto.bo.Personnel;

// Gestion de l'�tablissement d'une connexion valide
public interface ConnexionObserver {
	public void connexionNotification(Personnel p);
}
