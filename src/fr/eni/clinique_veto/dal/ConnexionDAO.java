package fr.eni.clinique_veto.dal;

import fr.eni.clinique_veto.bo.Personnel;


public interface ConnexionDAO {
	public Personnel verifierPersonnel(String nom, String mdp) throws DALException;
}
