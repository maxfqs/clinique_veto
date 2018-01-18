package fr.eni.clinique_veto.dal;

import java.sql.Date;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;

public interface RendezVousDAO {
	
	void insert(Animal a, Date date) throws DALException;
	
	List<Object[]> getRDVs(Personnel p, Date date) throws DALException;
}
