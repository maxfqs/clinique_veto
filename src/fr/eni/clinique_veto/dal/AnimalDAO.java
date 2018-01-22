package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.client.Client;

public interface AnimalDAO {
	
	public List<Animal> selectAll() throws DALException, SQLException;
	
	public void update(Animal data) throws DALException;
	
	public void insert(Animal data) throws DALException;
	
	public List<String[]> selectRaces() throws SQLException, DALException;
	
	public List<Animal> selectByClient(int data) throws SQLException, DALException;
	
	public Animal getAnimalById(int data)throws DALException;
}
