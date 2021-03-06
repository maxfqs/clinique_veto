package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;

public class AppliTestDALAnimal {
	public static void main(String[] args) throws DALException{
		
		AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
		
		Animal a1 = new Animal("totiiii",'M',"noir","chien m�chant","Chien",1,null, null, false);
		Animal a2 = new Animal("titi",'M',"noir","cheval m�chant","Cheval",2,null, null, false);
		Animal a3 = new Animal("tata",'F',"blanc","vache m�chante","Vache",2,null, null, false);
		Animal a4 = new Animal("maxF",'H',"blanc","chien m�chant","Chien",3,null, null, false);
		Animal a5 = new Animal("tata",'F',"blanc","vache m�chante","Vache",3,null, null, false);
		
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
		
		System.out.println("\nTrouver un animal par son codeAnimal\n------------------------------");
		System.out.println("Je cherche le nom de l'animal dont le code = 4");
		String nomAnimal = animalDAO.getAnimalById(4).getNomAnimal();
		System.out.println("le nom de l'animal est: " + nomAnimal);
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
