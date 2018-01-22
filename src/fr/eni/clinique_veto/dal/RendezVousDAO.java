package fr.eni.clinique_veto.dal;

import java.util.Date;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;

public interface RendezVousDAO {
	
	void insert(RendezVous rdv) throws DALException;
	
	List<RendezVous> getRDVs(Personnel p, Date date) throws DALException;
	
	void delete(RendezVous data) throws DALException;
}
