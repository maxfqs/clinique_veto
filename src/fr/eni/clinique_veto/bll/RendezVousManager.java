package fr.eni.clinique_veto.bll;

import java.util.Date;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;
import fr.eni.clinique_veto.dal.RendezVousDAO;

public class RendezVousManager {
	private static RendezVousDAO rdvDAO;
	
	static {
		rdvDAO = DAOFactory.getRendezVousDAO();
	}
	
	public static List<RendezVous> getVetoRdvForDate(Personnel p, Date d) throws BLLException {
		if(p == null || d == null) {
			throw new BLLException(BLLError.INVALID_REQUEST);
		}
		
		List<RendezVous> list = null;
		try {
			list = rdvDAO.getVetoRdvForDay(p, d);
		} catch (Exception e) {
			throw new BLLException(BLLError.DATABASE_ERROR);
		}
		
		return list;
	}
	
	public static void addRdv(Personnel p, Animal a, Date d) throws BLLException {
		if(p == null || a == null || d == null) {
			throw new BLLException(BLLError.INVALID_REQUEST);
		}
				
		try {
			RendezVous rdv = new RendezVous(p, d, a);
			rdvDAO.insert(rdv);
		} catch (DALException e) {
			throw new BLLException(BLLError.FAILED_RDV_ADD);
		}
	}
	
	public static void removeRdv(Personnel p, Animal a, Date d) throws BLLException {
		if(p == null || a == null || d == null) {
			throw new BLLException(BLLError.INVALID_REQUEST);
		}
		
		try {
			RendezVous rdv = new RendezVous(p, d, a);
			rdvDAO.delete(rdv);
		} catch (DALException e) {
			throw new BLLException(BLLError.FAILED_RDV_DELETE);
		}
	}	
}
