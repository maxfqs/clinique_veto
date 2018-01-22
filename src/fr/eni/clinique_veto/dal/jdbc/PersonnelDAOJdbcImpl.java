package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.JDBCTools;
import fr.eni.clinique_veto.dal.PersonnelDAO;

public class PersonnelDAOJdbcImpl implements PersonnelDAO{
	
	private Connection cnx = null;
	
	private static final String sqlSelectAll = "select CodePers,Nom,MotPasse,Role from Personnels where Archive = 0";
	private static final String sqlInsert = "insert into Personnels (Nom, MotPasse, Role, Archive) values(?,?,?,?)";
	private static final String sqlUpdate = "update Personnels set Nom = ?, MotPasse = ?, Role = ?, Archive = ?"
			+ " where CodePers = ?";
	private static final String sqlDelete = "delete from Personnels where CodePers = ?";
	private static final String sqSelectNomById = "select Nom from Personnel where CodePers = ?";
	
	public void insert(Personnel p) throws DALException {	
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, p.getNom());
			rqt.setString(2, p.getMdp());
			rqt.setString(3, p.getRole());
			rqt.setBoolean(4, p.isArchive());
			
			rqt.executeUpdate();
		    rs = rqt.getGeneratedKeys();
			
			rs.next();
			int index = rs.getInt(1);			
			p.setId(index);
			
		}  catch (SQLException e) {
			throw new DALException("insert failed - personnel = " + p.getNom() , e);
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
	
	
	public void update(Personnel p) throws SQLException, DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, p.getNom());
			rqt.setString(2, p.getMdp());
			rqt.setString(3, p.getRole());
			rqt.setBoolean(4, p.isArchive());
			rqt.setInt(5, p.getId());
			
			rqt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DALException("update failed - personnel = " + p.getId() , e);
			
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
	
	public List<Personnel> selectAll() throws SQLException, DALException {
		List<Personnel> ps = new ArrayList<Personnel>();
		Statement rqt = null;
		ResultSet rs = null;
		cnx = JDBCTools.getConnection();
		try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Personnel p = null;
			while(rs.next()){
				p = new Personnel(
						rs.getInt("CodePers"),
						rs.getString("Nom"),
						rs.getString("MotPasse"),
						rs.getString("Role")
						);
				ps.add(p);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed" , e);
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
		
		return ps;
	}
	
	public void delete(Personnel p) throws SQLException, DALException {
		
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, p.getId());
			rqt.executeUpdate();
		}  catch (SQLException e) {
			throw new DALException("delete failed - personnel = " + p.getId() , e);
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



}
