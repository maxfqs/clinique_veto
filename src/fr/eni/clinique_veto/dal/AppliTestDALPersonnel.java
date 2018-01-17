package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;

public class AppliTestDALPersonnel {
	public static void main(String[] args) throws DALException{
		
		PersonnelDAO personnelDAO = DAOFactory.getPersonnelDAO();
		
		Personnel p1 = new Personnel("pers1", "passe1", "SEC", false);
		Personnel p2 = new Personnel("pers2", "passe2", "VET", false);
		Personnel p3 = new Personnel("pers3", "passe3", "ASS", false);
		
		System.out.println("Ajout des personnels... ");
		
		personnelDAO.insert(p1);
		System.out.println("Ajuot de personnel " + p1.getNom());
		personnelDAO.insert(p2);
		System.out.println("Ajuot de personnel " + p1.getNom());
		personnelDAO.insert(p3);
		System.out.println("Ajuot de personnel " + p1.getNom());
		
		System.out.println("Affichage de la base de donn�e\n----------------------");
		afficherPersonnels(personnelDAO);
		
		System.out.println("Initialiser un mot de passe\n---------------------");
		try {
			personnelDAO.update(p1, "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Le mot de passe de l'utilisateur " + p1.getNom() + " a été initialisé");
		
		System.out.println("Archiver un utilisateur si il n'a plus de rendez-vous\n-----------------------");
		//personnelDAO.delete(p2);
		System.out.println("l'utilisateur " + p2.getNom() + " a été archivé");
	}
	public static void afficherPersonnels(PersonnelDAO pDAO){
		List<Personnel> lp= new ArrayList<>();
		try {
			lp.addAll(pDAO.selectAll());
			for(Personnel p : lp){
				System.out.println(p);
			}
		} catch (SQLException | DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
