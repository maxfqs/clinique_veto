package fr.eni.clinique_veto.dal;

import fr.eni.clinique_veto.bo.Personnel;


public interface ConnexionDAO {
	public boolean verifierPersonnel(String nom, String mdp) throws DALException;
}
