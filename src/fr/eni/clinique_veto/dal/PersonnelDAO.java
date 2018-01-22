package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.client.Client;

public interface PersonnelDAO {

	void insert(Personnel p) throws DALException;
	
	void update(Personnel p) throws SQLException, DALException;
	
	List<Personnel> selectAll() throws SQLException, DALException;
	
	void delete(Personnel p) throws DALException, SQLException;
	
}
