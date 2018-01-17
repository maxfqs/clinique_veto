package fr.eni.clinique_veto.dal;

import java.util.List;

import fr.eni.clinique_veto.bo.Animal;

public interface AnimalDAO {
	
	public List<Animal> selectAll() throws DALException;
	
	public void update(Animal data) throws DALException;
	
	public void insert(Animal data) throws DALException;
	
	public void delete(int id) throws DALException;
	

}
