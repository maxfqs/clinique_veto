package fr.eni.clinique_veto.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.jdbc.ClientsDAOImplJDBC;

public class AppliTestDALClients {
	public static void main(String[] args) {

	ClientsDAOImplJDBC dao = new ClientsDAOImplJDBC();
	List<Client> listeClients = new ArrayList<Client>();
	
	Client c1 = new Client("MaxDef", "prenomClient1", "adresse1", "adresse2", "50505", "ville1", "numTel215", "assurance", "email@gmail.com", "remarque",0);
	Client c2 = new Client("nomClient2", "prenomClient2", "adresse2", "adresse3", "50505", "ville1", "numTel216", "assurance", "email2@gmail.com", "remarque2",0);
	Client c3 = new Client("nomClient3", "prenomClient3", "adresse1", "adresse2", "50505", "ville3", "numTel217", "assurance", "email3@gmail.com", "remarque3",0);
	
	try {
		dao.ajouterClient(c1);
		dao.ajouterClient(c2);
		dao.ajouterClient(c3);
	
		listeClients = dao.trouverParNom("Max");
		Client clientRecupere = null;
		if(listeClients.size() >0) {
			for(Client c: listeClients) {
				if(c.getNomClient().equals("MaxDef")) {
					clientRecupere = c;
					System.out.println("\n===>description avant modification");
					System.out.println(clientRecupere.toString());
					System.out.println("___________________________ _____________");
					
				}
			}
		}

		if(clientRecupere != null) {
			clientRecupere.setAdresse1("rue de la tour eiffel");
			clientRecupere.setCodePostal("44100");
			dao.modifierClient(clientRecupere);

			System.out.println("\n===>description apr�s modification");
			System.out.println(clientRecupere.toString());
			System.out.println("___________________________ _____________");
			
			System.out.println("archivage effectu� :");
			dao.archiver(clientRecupere.getCodeClient());
			
			System.out.println("Renvoie ClientDalException 'le client recherch� n'existe pas 'car archivage effectu�");
			Client clientRecupere2 = dao.trouverParId(clientRecupere.getCodeClient());
		
		}

		
	
		
	} catch (Exception e) {
		System.out.println(e);
	}




	
//	dao.archiver(c);
	
	}
}
