package fr.eni.clinique_veto.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import fr.eni.clinique_veto.bo.client.Client;
import fr.eni.clinique_veto.dal.ClientDALException;
import fr.eni.clinique_veto.dal.JDBCTools;

public class ClientsDAOImplJDBC {

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
			+ " WHERE upper(NomClient) like ?";
	
	private static final String ARCHIVER = ""
			+ "BEGIN TRANSACTION;"
			+ " UPDATE "+ TABLE_CLIENT
			+ " SET  Archive=1" 
			+ " WHERE CodeClient=?; "
			+ " UPDATE " + TABLE_ANIMAUX
			+ " SET  Archive=1" 
			+ " WHERE CodeClient=?;"
			+ " COMMIT"; 
	
	public ClientsDAOImplJDBC() {
		try {
			this.conn = JDBCTools.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ==> a ajouter exception DAL
	public void ajouterClient(Client c)throws ClientDALException {
		PreparedStatement pst = null;
		try {
			verifierClient(c);
			pst = this.conn.prepareStatement(AJOUT_CLIENT);
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new ClientDALException("erreur lors de l'insertion des nouveaux clients");
		}finally {
			try {
				if(pst!= null) {pst.close();};
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// ==> a ajouter exception DAL
	public void modifierClient(Client c)throws ClientDALException {
		PreparedStatement pst = null;
		try {
			verifierClient(c);
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
		}finally {		
				try {
					if(pst!= null) {pst.close();};
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		}finally {
			try {
				if(pst!= null) {pst.close();};
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			if(!result) {throw new ClientDALException("Le client recherché n'existe pas");};
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pst!= null) {pst.close();};
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		}finally {
			try {
				if(pst!= null) {pst.close();};
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void verifierClient(Client c)throws ClientDALException {
		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		boolean okClient = true;
		String error = "";
		
		if(c.getNomClient().equals(null) || c.getNomClient().trim().length() == 0 ) {
			error += " | la valeur renseignée pour le nom du client est incorrecte";
			okClient = false;
		}
		if(c.getPrenomClient().equals(null) || c.getPrenomClient().trim().length() == 0 ) {
			error += " | la valeur renseignée pour le prénom du client est incorrecte";
			okClient = false;
		}
		if(!c.getCodePostal().equals(null)) {
			if(!c.getCodePostal().matches("\\d{5}")) {
				error += " | la valeur renseignée pour le code postal est incorrecte";
				okClient = false;
			}
		}
		if((Integer)c.getArchive()== null || c.getArchive() != 0 && c.getArchive() != 1) {
			error += " | la valeur de l'arhive renseignée est incorrecte";
			okClient = false;
		}
		if(!c.getEmail().equals(null)) {
			if(!VALID_EMAIL_ADDRESS_REGEX .matcher(c.getEmail()).find() ) {
				error += " | l'email renseigné est incorrect";
				okClient = false;
			}
		}
		if(!okClient) {
			throw new ClientDALException(error);
		}
		
		
	}
	
	
	
	
}
