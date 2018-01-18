package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.dal.AnimalDAO;
import fr.eni.clinique_veto.dal.DALException;
import fr.eni.clinique_veto.dal.JDBCTools;

public class AnimalDAOJdbcImpl implements AnimalDAO{
	private Connection cnx = null;
	private static final String sqlSelectAll = "select CodeAnimal, NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents from Animaux where Archive = 0";
	private static final String sqlUpdate = "update Animaux set NomAnimal = ?, Sexe = ?, Couleur = ?, " 
			+ " Race = ?, Espece = ?, CodeClient = ?, Tatouage = ?, Antecedents = ?, Archive = ? where CodeAnimal = ?";
	private static final String sqlInsert = "insert into Animaux(NomAnimal, Sexe, Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) values(?,?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from Aimaux where CodeAnimal=?";
	private static final String sqlSelectRaces = "select * from Races";
	private static final String sqlSelectByClient = "select * from Animaux where CodeClient = ? and Archive = 0";

	public List<Animal> selectAll() throws DALException, SQLException {
		List<Animal> la = new ArrayList<Animal>();
		
			Statement rqt = null;
			ResultSet rs = null;
			try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Animal a = null;
			while(rs.next()){ 
				a = new Animal(
						rs.getInt("CodeAnimal"),
						rs.getString("NomAnimal"),
						rs.getString("Sexe").charAt(0),
						rs.getString("Couleur"),
						rs.getString("Race"),
						rs.getString("Espece"),
						rs.getInt("CodeClient"),
						rs.getString("Tatouage"),
						rs.getString("Antecedents")
						);     
				la.add(a);
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
		
		return la;
	}
	
	public void update(Animal a) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, a.getNomAnimal());
			rqt.setString(2, String.valueOf(a.getSexe()));
			rqt.setString(3, a.getCouleur());
			rqt.setString(4, a.getRace());
			rqt.setString(5, a.getEspece());
			rqt.setInt(6, (int)a.getCodeClient());
			rqt.setString(7, a.getTatouage());
			rqt.setString(8, a.getAntecedents());
			rqt.setBoolean(9, a.isArchive());
			rqt.setInt(10, a.getCodeAnimal());
			
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("update failed - Animal = " + a.getCodeAnimal() , e);
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
	
	public void insert(Animal a) throws DALException {
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, a.getNomAnimal());
			rqt.setString(2, String.valueOf(a.getSexe()));
			rqt.setString(3, a.getCouleur());
			rqt.setString(4, a.getRace());
			rqt.setString(5, a.getEspece());
			rqt.setInt(6, (int) a.getCodeClient());
			rqt.setString(7, a.getTatouage());
			rqt.setString(8, a.getAntecedents());
			rqt.setBoolean(9, false);
			
			rqt.executeUpdate();
		    rs = rqt.getGeneratedKeys();
			
			rs.next();
			int index = rs.getInt(1);			
			a.setCodeAnimal(index);
			
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

	public void delete(Animal a) throws DALException {

		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = JDBCTools.getConnection();
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, a.getCodeAnimal());
			rqt.executeUpdate();
		}  catch (SQLException e) {
			throw new DALException("delete failed - personnel = " + a.getCodeAnimal() , e);
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
	
	public List<String[]> selectRaces() throws SQLException, DALException{
		List<String[]> listeRaces = new ArrayList<>(); 
		
		Statement rqt = null;
		ResultSet rs = null;
		try{
		cnx = JDBCTools.getConnection();
		rqt = cnx.createStatement();
		rs = rqt.executeQuery(sqlSelectRaces);
		
		while(rs.next()){ 
			String[] ligne = {rs.getString("Race"), rs.getString("Espece")};
			listeRaces.add(ligne);
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
		return listeRaces;
	}
	
	public List<Animal> selectByClient(int codeClient) throws SQLException, DALException{
		List<Animal> la = new ArrayList<Animal>();
		
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
		cnx = JDBCTools.getConnection();
		rqt = cnx.prepareStatement(sqlSelectByClient);
		rqt.setInt(1, codeClient);
		rs = rqt.executeQuery();
		Animal a = null;
		while(rs.next()){ 
			a = new Animal(
					rs.getInt("CodeAnimal"),
					rs.getString("NomAnimal"),
					rs.getString("Sexe").charAt(0),
					rs.getString("Couleur"),
					rs.getString("Race"),
					rs.getString("Espece"),
					rs.getInt("CodeClient"),
					rs.getString("Tatouage"),
					rs.getString("Antecedents")
					);     
			la.add(a);
		}
	} catch (SQLException e) {
		throw new DALException("selectByClient failed" , e);
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
	
	return la;
}
	
	
	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}
		
}
