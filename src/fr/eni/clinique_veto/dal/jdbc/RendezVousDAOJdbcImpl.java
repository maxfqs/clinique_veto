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
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.JDBCTools;
import fr.eni.clinique_veto.dal.RendezVousDAO;

public class RendezVousDAOJdbcImpl implements RendezVousDAO{
	private Connection cnx = null;
	private static final String sqlInsert = "insert into Agendas (CodeVeto, DateRdv, CodeAnimal) values (?,?,?)";
	private static final String sqlDelete = "delete from Agendas where CodeAnimal = ? and DateRdv = ? and CodeVeto = ?";
	private static final String sqlSelectByVeto = "select DateRdv, CodeAnimal from Agendas where CodeVeto = ? and Day(DateRdv) = ?";
	
	public void insert(RendezVous rdv) throws DALException{
		Animal a = rdv.getAnimal();
		Personnel pers = rdv.getPers();
		java.sql.Timestamp t = new java.sql.Timestamp(rdv.getDate().getTime());
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			
			rqt.setInt(1, pers.getId());
			rqt.setTimestamp(2, t);
			rqt.setInt(3,a.getCodeAnimal());
			
			rqt.executeUpdate();
			
		}  catch (SQLException e) {
			throw new DALException("insertRDV failed - RDV = " + rdv , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public List<RendezVous> getRDVs(Personnel p, java.util.Date date) throws DALException {
		// la date reçu doit être de format java.sql.Date
		List<RendezVous> listeRdvs = new ArrayList<RendezVous>();
		RendezVous rdv;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = JDBCTools.getConnection();
//			rqt = cnx.prepareStatement(sqlSelectByVeto);
			
//			rqt.setInt(1, p.getId());
//			rqt.setDate(2, new java.sql.Date(date.getTime()));
			
			Statement rq = cnx.createStatement();
			rs = rq.executeQuery("select * from Agendas");

			while(rs.next()) {
				rdv = new RendezVous();
				rdv.setPers(new Personnel());
				rdv.setDate(rs.getDate("DateRdv"));
				listeRdvs.add(rdv);
			}
		}  catch (SQLException e) {
			throw new DALException("insert failed - RDV = " , e);
		} finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (rqt != null){
					rqt.close();
				}
				if(cnx!=null){
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return listeRdvs;

	}

	public void delete(Personnel p, java.util.Date date, Animal a) throws DALException {
//		PreparedStatement rqt = null;
//		ResultSet rs = null;
//		try{
//			
//			cnx = JDBCTools.getConnection();
//			rqt = cnx.prepareStatement(sqlDelete);
//			
//			rqt.setInt(1, a.getCodeAnimal());
//			rqt.setDate(2, (Date) date);
//			rqt.setInt(3,p.getId());
//			
//			rqt.executeUpdate();
//			
//		}  catch (SQLException e) {
//			throw new DALException("deleteRDV failed - animal = " + a.getNomAnimal() , e);
//		} finally {
//			try {
//				if (rs != null){
//					rs.close();
//				}
//				if (rqt != null){
//					rqt.close();
//				}
//				if(cnx!=null){
//					cnx.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
