package fr.eni.clinique_veto.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;
import fr.eni.clinique_veto.dal.RendezVousDAO;

public class RendezVousManager {
	private static RendezVousDAO rdvDAO;
	private List<ManagerListObserver> observers;
	private List<RendezVous> rdvList;
	
	static {
		rdvDAO = DAOFactory.getRendezVousDAO();
	}
	
	public RendezVousManager(Personnel p, Date date) throws BLLException {		
		if (p.getRole() != "VET") throw new BLLException("Le personnel n'est pas un veto");
		
		this.observers = new ArrayList<ManagerListObserver>();

		try {
			this.rdvList = new ArrayList<RendezVous>();
			
			rdvList.addAll(rdvDAO.getVetoRdvForDay(p, date));
			
			
		} catch (DALException e) {
			throw new BLLException(BLLError.DATABASE_ERROR);
		}
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
				
			}
			
		return lis;
	}
	
	// à coder
	
	
	
	
	
	
	
	
	
	
	public void registerObserver(ManagerListObserver o) {
		observers.add(o);
	}
	
	private void fireUpdate() {
		for(ManagerListObserver o : observers) o.onListUpdated();
	}
	
}
