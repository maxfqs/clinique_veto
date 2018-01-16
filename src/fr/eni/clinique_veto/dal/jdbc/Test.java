package fr.eni.clinique_veto.dal.jdbc;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.client.Client;

public class Test {
	public static void main(String[] args) {

	ClientsDAOImplJDBC dao = new ClientsDAOImplJDBC();
	List<Client> listeClients = new ArrayList<Client>();
	
	Client c3 = new Client("MaxDef", "prenomClient1", "adresse1", "adresse2", "50505", "ville1", "numTel215", "assurance", "email@gmail.com", "remarque",0);
//	Client c2 = new Client("nomClient2", "prenomClient2", "adresse2", "adresse3", "50505", "ville1", "numTel215", "assurance", "email2@gmail.com", "remarque2",0);

	try {
		dao.ajouterClient(c3);


	
//		listeClients = dao.trouverParNom("Max");
//		Client clientRecupere = null;
//		for(Client c: listeClients) {
//			if(c.getNomClient().equals("MaxDef")) {
//				clientRecupere = c;
//			}
//		}
//		
//		clientRecupere.setAdresse1("rue de la tour eiffel");
//		clientRecupere.setCodePostal("44100");
//		dao.modifierClient(clientRecupere);
//
//		System.out.println("===>description après modification");
//		System.out.println(clientRecupere.toString());
//		System.out.println("___________________________ _____________");
//	
//		dao.archiver(c3.getCodeClient());
//		System.out.println("===>description après archivage");
//		System.out.println(clientRecupere.toString());
//		
	} catch (Exception e) {
		System.out.println(e);
	}




	
//	dao.archiver(c);
	
	}
}
