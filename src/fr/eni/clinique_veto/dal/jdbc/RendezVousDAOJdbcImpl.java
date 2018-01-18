package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.dal.RendezVousDAO;

public class RendezVousDAOJdbcImpl implements RendezVousDAO{
	private Connection cnx = null;
	private static final String sqlInsert = "insert into Agendas (CodeVeto, DateRdv, CodeAnimal) values (?,?,?)";
	private static final String sqlDelete = "delete from Agendas where CodeAnimal = ?";
	private static final String sqlSelectByVeto = "select DateRdv, CodeAnimal from Agendas where CodeVeto = ? and DateRdv = ?";
	private static final String sqlUpdate = "update Agendas set CodeVeto = ?, DateRdv = ?, CodeAnimal = ? where CodeAnimal = ?";
	
	public void insert(Animal a){
		
	}
}
