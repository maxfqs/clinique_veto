package fr.eni.clinique_veto.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.clinique_veto.dal.AnimalDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;

public class EspecesManager {
	private static Map<String,List<String>> especesMap;
	private static String[] especesArray;
	
	static {
		AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
		especesMap = new HashMap<String,List<String>>();
		List<String[]> result = null;
		
		try {
			result = animalDAO.selectRaces();			
		} catch (SQLException | DALException e) {
			e.printStackTrace();
		}
		
		for(String[] data : result){
			String race = data[0];
			String espece = data[1];
			
			if(especesMap.get(espece) == null){
				especesMap.put(espece, new ArrayList<String>());
			}
			
			especesMap.get(espece).add(race);
		}
	}
	
	public static String[] getEspeces() {
		if(especesArray != null) return especesArray;
		
		String[] sa = new String[especesMap.size()];
		especesArray = especesMap.keySet().toArray(sa);
		return especesArray;
	}
	
	public static String[] getRacesForEspece(String espece) {
		List<String> races = especesMap.get(espece);
		
		if(races == null) {
			return new String[0];
		}
		
		String[] sa = new String[races.size()];
		return races.toArray(sa);
	}
	
	public static boolean isValidEspece(String espece) {
		return especesMap.containsKey(espece);
	}
	
	public static boolean isValidRace(String espece, String race) {
		if(!isValidEspece(espece)) return false;
		
		return especesMap.get(espece).contains(race);
	}
}
