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
	private static AnimalDAO animalDAO;
	
	static {
		animalDAO = DAOFactory.getAnimalDAO();
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
		return (String[]) especesMap.keySet().toArray();
	}
	
	public static String[] getRacesForEspece(String espece) {
		return (String[]) especesMap.get(espece).toArray();
	}
	
	public static boolean isValidEspece(String espece) {
		return especesMap.containsKey(espece);
	}
	
	public static boolean isValidRace(String espece, String race) {
		if(!isValidEspece(espece)) return false;
		
		return especesMap.get(espece).contains(race);
	}
}
