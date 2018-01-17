package fr.eni.clinique_veto.dal;

import fr.eni.clinique_veto.bo.Personnel;


public interface ConnexionDAO {
	public int getUserID(String nom, String mdp) throws DALException;
}
