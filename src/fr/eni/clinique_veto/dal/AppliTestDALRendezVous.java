package fr.eni.clinique_veto.dal;

import java.util.Date;
import java.util.List;
import java.util.Calendar;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.jdbc.RendezVousDAOJdbcImpl;

public class AppliTestDALRendezVous {
	public static void main(String[] args){
		
	RendezVousDAO rendezVousDAO = new RendezVousDAOJdbcImpl();
		
				// set the date of a given month
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, 2018);
			    calendar.set(Calendar.MONTH, Calendar.JANUARY);
			    calendar.set(Calendar.SECOND, 0);
			    //set a date for a given hour
				calendar.set(Calendar.DATE, 23);
				calendar.set(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 0);
				Date date1 = new Date(calendar.getTime().getTime());
				
				//set a date for a given hour
				calendar.set(Calendar.DATE, 23);
				calendar.set(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 15);
				Date date2 = new Date(calendar.getTime().getTime());		
				//set a date for a given hour
				calendar.set(Calendar.DATE, 23);
				calendar.set(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 45);
				Date date3 = new Date(calendar.getTime().getTime());
				//set a date for a given hour
				calendar.set(Calendar.DATE, 23);
				calendar.set(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 0);
				Date date4 = new Date(calendar.getTime().getTime());
				//set a date for a given hour
				calendar.set(Calendar.DATE, 23);
				calendar.set(Calendar.HOUR_OF_DAY, 10);
				calendar.set(Calendar.MINUTE, 0);
				Date date5 = new Date(calendar.getTime().getTime());
				//set a date for a given hour
				calendar.set(Calendar.DATE, 24);
				calendar.set(Calendar.HOUR_OF_DAY, 9);
				calendar.set(Calendar.MINUTE, 30);
				Date date6 = new Date(calendar.getTime().getTime());
		
		Personnel p1 = new Personnel("pers1", "passe1", "VET", false);
		Personnel p2 = new Personnel("pers2", "passe2", "VET", false);
		Personnel p3 = new Personnel("pers3", "passe3", "SEC", false);
		Personnel p4 = new Personnel("pers4", "passe4", "ASS", false);
		Personnel p5 = new Personnel("pers5", "passe5", "SEC", false);
		
		Client c1 = new Client("MaxDef", "prenomClient1", "adresse1", "adresse2", "50505", "ville1", "numTel215", "assurance", "email@gmail.com", "remarque",0);
		Client c2 = new Client("nomClient2", "prenomClient2", "adresse2", "adresse3", "50505", "ville1", "numTel216", "assurance", "email2@gmail.com", "remarque2",0);
		Client c3 = new Client("nomClient3", "prenomClient3", "adresse1", "adresse2", "50505", "ville3", "numTel217", "assurance", "email3@gmail.com", "remarque3",0);

		Animal a1 = new Animal("totiiii",'M',"noir","chien méchant","Chien",1,null, null, false);
		Animal a2 = new Animal("titi",'M',"noir","cheval méchant","Cheval",2,null, null, false);
		Animal a3 = new Animal("tata",'F',"blanc","vache méchante","Vache",2,null, null, false);
		Animal a4 = new Animal("maxF",'H',"blanc","chien méchant","Chien",3,null, null, false);
		Animal a5 = new Animal("maxD",'H',"blanc","cheval gentil","Cheval",3,null, null, false);
		
		p1.setId(1);
		a1.setCodeAnimal(3);
		a4.setCodeAnimal(6);
		a5.setCodeAnimal(7);
		System.out.println(p1.getId());
		System.out.println(date1);
		System.out.println(a1.getCodeAnimal());
		
		
		RendezVous rdv1 = new RendezVous(p1, date1, a1);
		RendezVous rdv2 = new RendezVous(p1, date2, a4);
		RendezVous rdv3 = new RendezVous(p1, date3, a5);
		//RendezVous rdv4 = new RendezVous(p1, date1, a4);
		
		System.out.println("Ajout des rdvs:\n--------------------");
		try {
			rendezVousDAO.insert(rdv1);
			rendezVousDAO.insert(rdv2);
			rendezVousDAO.insert(rdv3);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Afficher la liste de RDV pour la date : " + date1);
		try {
			List<RendezVous> listeRDV = rendezVousDAO.getVetoRdvForDay(p1, date1);
			afficherListeRendezVous(listeRDV);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*System.out.println("RDV1 est inseré");
		rendezVousDAO.insert(rdv2);
		System.out.println("RDV1 est inseré");
		rendezVousDAO.insert(rdv3);
		System.out.println("RDV1 est inseré");
		rendezVousDAO.insert(rdv4);
		*/
		
	}
	
	public static void afficherListeRendezVous(List<RendezVous> lr){
		for(RendezVous rdv : lr){
			System.out.println(rdv);
		}
	}
}
