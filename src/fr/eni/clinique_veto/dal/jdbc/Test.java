package fr.eni.clinique_veto.dal.jdbc;

import fr.eni.clinique_veto.bo.client.Client;

public class Test {
	public static void main(String[] args) {
		System.out.println("test");
Client c  = new Client("nomClient"," prenomClient", "adresse1", "", "codePo", "ville", "numTel", "assuranc", "email", "", 0);
	DAOClientsImplJDBC dao = new DAOClientsImplJDBC();
	try {
		dao.ajouterClient(c);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
