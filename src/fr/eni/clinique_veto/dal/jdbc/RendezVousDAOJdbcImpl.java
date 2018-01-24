package fr.eni.clinique_veto.dal.jdbc;


import java.security.Timestamp;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.AnimalDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.DAOFactory;
import fr.eni.clinique_veto.dal.JDBCTools;
import fr.eni.clinique_veto.dal.RendezVousDAO;

public class RendezVousDAOJdbcImpl implements RendezVousDAO{
	private Connection cnx;
	private static final String sqlInsert = "insert into Agendas (CodeVeto, DateRdv, CodeAnimal) values (?,?,?)";
	private static final String sqlDelete = "delete from Agendas where CodeAnimal = ? and DateRdv = ? and CodeVeto = ?";
	private static final String sqlSelectByVeto = "select ag.DateRdv, ag.CodeAnimal ,c.* from Agendas ag "
			+ " inner join Animaux an on ag.CodeAnimal = an.CodeAnimal "
			+ " inner join Clients c on an.CodeClient = c.CodeClient"
			+ " where ag.CodeVeto = ? and Day(ag.DateRdv) = ?"
			+ " and Month(ag.DateRdv)= ? and Year(ag.DateRdv) = ? order by ag.DateRdv";
	
	public RendezVousDAOJdbcImpl(){
			
	}
	
	public RendezVousDAOJdbcImpl(Connection cnx){
		this.cnx = cnx;	
	}
	
	public void insert(RendezVous rdv) throws DALException{
		Animal a = rdv.getAnimal();
		Personnel pers = rdv.getPers();
		java.sql.Timestamp t = new java.sql.Timestamp(rdv.getDate().getTime());
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			rqt = cnx.prepareStatement(sqlInsert);
			
			rqt.setInt(1, pers.getId());
			rqt.setTimestamp(2, t);
			rqt.setInt(3,a.getCodeAnimal());
			
			rqt.executeUpdate();
			
		}  catch (SQLException e) {
			throw new DALException("insertRDV failed - RDV = " + rdv , e);
		}
	}
	
	public List<RendezVous> getVetoRdvForDay(Personnel p, Date date) throws DALException {
		List<RendezVous> listeRDV = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int day = cal.get(cal.DAY_OF_MONTH);
		int month = cal.get(cal.MONTH) + 1;
		int year = cal.get(cal.YEAR);
		PreparedStatement rqt = null;
		ResultSet rs = null;
		System.out.println(day + " " + month + " " + year);
		try{
			rqt = cnx.prepareStatement(sqlSelectByVeto);
			
			rqt.setInt(1, p.getId());
			rqt.setInt(2, day);
			rqt.setInt(3, month);
			rqt.setInt(4, year);
			
			rs = rqt.executeQuery();
			Animal a = null;
			AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
			while(rs.next()){
				a = animalDAO.getAnimalById(rs.getInt("CodeAnimal"));
				
				java.sql.Timestamp t = new java.sql.Timestamp(rs.getTimestamp("DateRdv").getTime());
				cal.setTimeInMillis( t.getTime());
				
				Client c = new Client(
						rs.getInt("codeClient"),
						rs.getString("nomClient"),
						rs.getString("prenomClient"),
						rs.getString("adresse1"),
						rs.getString("adresse2"),
						rs.getString("codePostal"),
						rs.getString("ville"),
						rs.getString("numTel"), 
						rs.getString("assurance"),
						rs.getString("email"),
						rs.getString("remarque"),
						rs.getByte("archive"));
				
				
				java.util.Date dateJ = new java.sql.Date(cal.getTime().getTime());
				
				RendezVous r = new RendezVous(p, dateJ, a, c);

				listeRDV.add(r);
			}
		}  catch (SQLException e) {
			throw new DALException("getRDV failed" , e);
		} 
		return listeRDV;
	} 
	

	public void delete(RendezVous rdv) throws DALException {
		java.sql.Timestamp t = new java.sql.Timestamp(rdv.getDate().getTime());
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			rqt = cnx.prepareStatement(sqlDelete);
			
			rqt.setInt(1, rdv.getAnimal().getCodeAnimal());
			rqt.setTimestamp(2, t);
			rqt.setInt(3, rdv.getPers().getId());
			
			rs = rqt.executeQuery();
		}  catch (SQLException e) {
			throw new DALException("Delete RDV failed" , e);
		} 
	}

	
}
