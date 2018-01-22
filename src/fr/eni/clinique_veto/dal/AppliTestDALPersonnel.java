package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;

public class AppliTestDALPersonnel {
	public static void main(String[] args) throws DALException{
		
		PersonnelDAO personnelDAO = DAOFactory.getPersonnelDAO();
		
		Personnel p1 = new Personnel("pers1", "passe1", "VET", false);
		Personnel p2 = new Personnel("pers2", "passe2", "VET", false);
		Personnel p3 = new Personnel("pers3", "passe3", "SEC", false);
		Personnel p4 = new Personnel("pers4", "passe4", "ASS", false);
		Personnel p5 = new Personnel("pers5", "passe5", "SEC", false);
		
		System.out.println("Ajout des personnels... ");
		
		personnelDAO.insert(p1);
		System.out.println("Ajuot de personnel " + p1.getNom());
		personnelDAO.insert(p2);
		System.out.println("Ajuot de personnel " + p2.getNom());
		personnelDAO.insert(p3);
		System.out.println("Ajuot de personnel " + p3.getNom());
		personnelDAO.insert(p4);
		System.out.println("Ajuot de personnel " + p4.getNom());
		personnelDAO.insert(p5);
		System.out.println("Ajuot de personnel " + p5.getNom());
		
		System.out.println("\nAffichage de la base de donn�e\n----------------------");
		afficherPersonnels(personnelDAO);
		
		System.out.println("\nInitialiser un mot de passe\n---------------------");
		p1.setMdp("passe3");
		try {
			personnelDAO.update(p1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Le mot de passe de l'utilisateur " + p1.getNom() + " a été initialisé");
		System.out.println("\nSupprimer un personnel\n-----------------------");
		try {
			personnelDAO.delete(p5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("l'utilisateur " + p2.getNom() + " a été suprimé");
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
