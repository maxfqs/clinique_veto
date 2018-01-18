package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;

public class AppliTestDALAnimal {
	public static void main(String[] args) throws DALException{
		
		AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
		
		Animal a1 = new Animal("toto",'M',"noir","chien méchant","Chien",1,null, null, false);
		Animal a2 = new Animal("titi",'M',"noir","cheval méchant","Cheval",2,null, null, false);
		Animal a3 = new Animal("tata",'F',"blanc","vache méchante","Vache",2,null, null, false);
		
		System.out.println("Ajout des animaux:\n-----------------------------");
		
		animalDAO.insert(a1);
		animalDAO.insert(a2);
		animalDAO.insert(a3);
		
		System.out.println("\nAffichage des animaux:\n---------------------------");
		try {
			afficherAnimaux(animalDAO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public static void afficherAnimaux(AnimalDAO aDAO) throws SQLException{
		List<Animal> la = new ArrayList<>();
		try {
			la.addAll(aDAO.selectAll());
			for(Animal a : la){
				System.out.println("Animal " + a.getCodeAnimal());
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
