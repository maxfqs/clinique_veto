package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;

public class AppliTestDALAnimal {
	public static void main(String[] args) throws DALException{
		
		AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
		
		Animal a1 = new Animal("totiiii",'M',"noir","chien méchant","Chien",7	,null, null, false);
		Animal a2 = new Animal("titi",'M',"noir","cheval méchant","Cheval",8,null, null, false);
		Animal a3 = new Animal("tata",'F',"blanc","vache méchante","Vache",8,null, null, false);
		
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
		} 
		
		System.out.println("\nArchiver un animal:\n-------------------------");
		a1.setArchive(true);
		animalDAO.update(a1);
		System.out.println("L'animal " + a1.getNomAnimal() + " a été archiveé.");
		
		List<Animal> li = null;
		try {
			 li = animalDAO.selectByClient(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("\nLa liste des animaux du client a2 :\n------------------------");
		afficherListAnimaux(li);
	}
	
	public static void afficherListAnimaux(List<Animal> li){
		for(Animal a : li){
			System.out.println(a);
		}
	}
	
	public static void afficherAnimaux(AnimalDAO aDAO) throws SQLException{
		List<Animal> la = new ArrayList<>();
		try {
			la.addAll(aDAO.selectAll());
			for(Animal a : la){
				System.out.println(a);
			}
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
