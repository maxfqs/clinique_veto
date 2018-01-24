package fr.eni.clinique_veto.bll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
	private static Integer[] availableMin = {0, 15, 30, 45};
	
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
	
	public static void addRdv(Personnel p, Animal a, Date d, int heure, int minutes) throws BLLException {
		if(p == null || a == null || d == null) {
			throw new BLLException(BLLError.INVALID_REQUEST);
		}
		
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int day = cal.get(cal.DAY_OF_MONTH);
			int month = cal.get(cal.MONTH) + 1;
			int year = cal.get(cal.YEAR);
			cal.set(cal.YEAR, year);
		    cal.set(cal.MONTH, month);
		    cal.set(cal.SECOND, 0);
		    //set a date for a given hour
			cal.set(cal.DATE, day);
			cal.set(cal.HOUR_OF_DAY, heure);
			cal.set(cal.MINUTE, minutes);
			
			
			
			
			
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
	
	public List<Integer> disponibleHour(List<RendezVous> rdvs, int h){
		List<Integer> minutes = new ArrayList<Integer>();
		
		for(RendezVous Rdv : rdvs){
			// Aucun rdv, toutes les plages dispo
			if(rdvs.size() == 0){
				minutes.addAll(Arrays.asList(availableMin));
			} else{
				// Trouve les plages dispo
				Calendar cal = Calendar.getInstance();
				cal.setTime(Rdv.getDate());
				
				int hour =  cal.get(Calendar.HOUR_OF_DAY);
				int m = cal.get(Calendar.MINUTE);
				int c = 0;
				while(h == hour && m <= 45){
					if(m != c) minutes.add(m);
					c += 15;
				}
			}				
		}
		
		return minutes;
	}
}
