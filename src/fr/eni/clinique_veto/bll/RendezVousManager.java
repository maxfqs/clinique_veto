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
	
	public List<RendezVous> getRdvList() {
		return Collections.unmodifiableList(rdvList);
	}
	
	public List<Integer> disponibleHour(int h){
		List<Integer> lis = new ArrayList<>();
		
			for(RendezVous Rdv : rdvList){
				if(rdvList.size() == 0){
					lis.add(1); lis.add(15); lis.add(30); lis.add(45);
				} else{
					Calendar cal = Calendar.getInstance();
					cal.setTime(Rdv.getDate());
					int hour =  cal.get(cal.HOUR_OF_DAY);
					int minutes = cal.get(cal.MINUTE);
					int c = 0;
					while(h == hour && minutes <= 45){
						if(minutes != c) lis.add(minutes);
						c += 15;
					}
				}
	public static void addRdv(Personnel p, Animal a, Date d) throws BLLException {
		if(p == null || a == null || d == null) {
			throw new BLLException(BLLError.INVALID_REQUEST);
		}
				
			}
			
		return lis;
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
