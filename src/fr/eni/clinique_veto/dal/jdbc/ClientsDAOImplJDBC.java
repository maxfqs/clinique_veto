package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;
import fr.eni.clinique_veto.dal.ClientDAO;
import fr.eni.clinique_veto.dal.JDBCTools;

public class ClientsDAOImplJDBC implements ClientDAO {

	private Connection conn;
	private static final String TABLE_CLIENT = "Clients";
	private static final String TABLE_ANIMAUX = "Animaux";
	
	private static final String AJOUT_CLIENT = ""
			+ "INSERT INTO "
			+ TABLE_CLIENT
			+ " (NomClient, PrenomClient, Adresse1, Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive  )"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_CLIENT =""
			+ "UPDATE "+ TABLE_CLIENT
			+ " SET NomClient= ?,"
			+ "PrenomClient=?,"
			+ "Adresse1=?,"
			+ "Adresse2=?,"
			+ "CodePostal=?,"
			+ "Ville=?,"
			+ "NumTel=?,"
			+ "Assurance=?,"
			+ "Email=?,"
			+ "Remarque=?,"
			+ "Archive=?"
			+ " WHERE CodeClient=?"
			+ " AND Archive = 0";
	
	private static final String TROUVER_CLIENT_PAR_ID  = ""
			+ "SELECT * "
			+ " FROM "+ TABLE_CLIENT
			+ " WHERE CodeClient= ?"
			+ " AND Archive = 0";
	
	private static final String TROUVER_CLIENT  = ""
			+ "SELECT * "
			+ " FROM "+ TABLE_CLIENT
			+ " WHERE upper(NomClient) like ?"
			+ " AND Archive = 0";
	
	private static final String ARCHIVER = ""
			+ "BEGIN TRANSACTION;"
			+ " UPDATE "+ TABLE_CLIENT
			+ " SET  Archive=1" 
			+ " WHERE CodeClient=?; "
			+ " UPDATE " + TABLE_ANIMAUX
			+ " SET  Archive=1" 
			+ " WHERE CodeClient=?;"
			+ " COMMIT"; 
	
	public ClientsDAOImplJDBC(Connection conn) {
		this.conn = conn;
	}
	
	// ==> a ajouter exception DAL
	public void ajouterClient(Client c)throws ClientDALException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = this.conn.prepareStatement(AJOUT_CLIENT, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, c.getNomClient());
			pst.setString(2, c.getPrenomClient());
			pst.setString(3, c.getAdresse1());
			if(c.getAdresse2().equals(null) || c.getAdresse2().trim().length() == 0) {
				pst.setNull(4, Types.VARCHAR);
			}else {
				pst.setString(4, c.getAdresse2());
			}
			
			pst.setString(5, c.getCodePostal());
			pst.setString(6, c.getVille());
			pst.setString(7, c.getNumTel());
			pst.setString(8, c.getAssurance());
			pst.setString(9, c.getEmail());
			if(c.getRemarque().equals(null) || c.getRemarque().trim().length() == 0) {
				pst.setNull(10, Types.VARCHAR);
			}else {
				pst.setString(10, c.getRemarque());
			}
			pst.setInt(11, c.getArchive());

			pst.executeUpdate();
			
			   rs = pst.getGeneratedKeys();	
			   rs.next();
			   int index = rs.getInt(1);			
			   c.setCodeClient(index);
			   
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new ClientDALException("erreur lors de l'insertion des nouveaux clients");
		}
	}
	
	// ==> a ajouter exception DAL
	public void modifierClient(Client c)throws ClientDALException {
		PreparedStatement pst = null;
		try {
			pst = this.conn.prepareStatement(UPDATE_CLIENT);
			pst.setString(1, c.getNomClient());
			pst.setString(2, c.getPrenomClient());
			pst.setString(3, c.getAdresse1());
			if(c.getAdresse2()== null || c.getAdresse2().trim().length() == 0) {
				pst.setNull(4, Types.VARCHAR);
			}else {
				pst.setString(4, c.getAdresse2());
			}
			
			pst.setString(5, c.getCodePostal());
			pst.setString(6, c.getVille());
			pst.setString(7, c.getNumTel());
			pst.setString(8, c.getAssurance());
			pst.setString(9, c.getEmail());
			if(c.getRemarque()== null || c.getRemarque().trim().length() == 0) {
				pst.setNull(10, Types.VARCHAR);
			}else {
				pst.setString(10, c.getRemarque());
			}
			pst.setInt(11, c.getArchive());
			pst.setInt(12, c.getCodeClient());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new ClientDALException("erreur lors de la modification du client");
		}
	}
	
	/*
	 * ==> fait une recherche en 'like nom%' ;
	 */
	public List<Client> trouverParNom(String str) throws ClientDALException{
		List<Client> retListe = new ArrayList<>();
		PreparedStatement pst = null;
		
		try {
			pst = this.conn.prepareStatement(TROUVER_CLIENT);
			pst.setString(1, str.toUpperCase()+ "%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				retListe.add(new Client(
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
						rs.getByte("archive"))
						);		
			}
			rs.close();
		} catch (SQLException e) {
			throw new ClientDALException("erreur lors de la recherche du client");
		}
		return retListe;
	}
	
	// => a remplacer par dalException
	public Client trouverParId(int id) throws ClientDALException {
	
		PreparedStatement pst = null;
		Client retClient = null;
		boolean result = false;
		
		try {
			pst = this.conn.prepareStatement(TROUVER_CLIENT_PAR_ID);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				result = true;
				retClient = new Client(
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
						rs.getByte("archive")
						);		
			};
			if(!result) {throw new ClientDALException("Le client recherch� n'existe pas");};
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retClient;
	}
	
	
	public void archiver(int id)throws ClientDALException {
		PreparedStatement pst = null;
	
		try {
			pst = this.conn.prepareStatement(ARCHIVER);
			pst.setInt(1, id);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new ClientDALException("erreur lors de l'archivage du client");
		}
	}
		
}
