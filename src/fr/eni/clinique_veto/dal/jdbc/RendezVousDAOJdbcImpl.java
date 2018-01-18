package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.JDBCTools;
import fr.eni.clinique_veto.dal.RendezVousDAO;

public class RendezVousDAOJdbcImpl implements RendezVousDAO{
	private Connection cnx = null;
	private static final String sqlInsert = "insert into Agendas (CodeVeto, DateRdv, CodeAnimal) values (?,?,?)";
	private static final String sqlDelete = "delete from Agendas where CodeAnimal = ?";
	private static final String sqlSelectByVeto = "select DateRdv, CodeAnimal from Agendas where CodeVeto = ? and Day(DateRdv) = ?";
	
	public void insert(Animal a, Date date) throws DALException{
		
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			String nom = a.getClient().getNomClient();
			
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			
			rqt.setString(1, nom);
			rqt.setDate(2, date);
			rqt.setInt(3,a.getCodeAnimal());
			
			rqt.executeUpdate();
			
		}  catch (SQLException e) {
			throw new DALException("insert failed - animal = " + a.getNomAnimal() , e);
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
	
	public List<Object[]> getRDVs(Personnel p, Date date) throws DALException {
		List<Object[]> listeObj = null;
		
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByVeto);
			
			rqt.setInt(1, p.getId());
			rqt.setInt(2, day);
			
			rs = rqt.executeQuery();
			while(rs.next()) {
				Object[] listeRdv = new Object[2];
				listeRdv[0] = rs.getDate("DateRdv");
				listeRdv[1] = rs.getInt("CodeClient");
				listeObj.add(listeRdv);
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
		return listeObj;
	}
	
	public void delete(Personnel p, Animal a) {
		//ToDo
	}
}
