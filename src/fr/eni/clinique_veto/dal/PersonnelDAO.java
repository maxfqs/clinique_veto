package fr.eni.clinique_veto.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;

public interface PersonnelDAO {

	void insert(Personnel p) throws DALException;

	List<Personnel> selectAll() throws SQLException, DALException;

}
