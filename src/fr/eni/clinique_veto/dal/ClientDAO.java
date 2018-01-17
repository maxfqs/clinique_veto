package fr.eni.clinique_veto.dal;

import java.util.List;

import fr.eni.clinique_veto.bo.client.Client;

public interface ClientDAO {
	
	void ajouterClient(Client c)throws ClientDALException ;
	
	void modifierClient(Client c)throws ClientDALException;
	
	List<Client> trouverParNom(String str) throws ClientDALException;
	
	Client trouverParId(int id) throws ClientDALException;
	
	void archiver(int id)throws ClientDALException;
	
}

