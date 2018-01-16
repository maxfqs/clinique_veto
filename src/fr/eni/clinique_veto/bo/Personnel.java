package fr.eni.clinique_veto.bo;

public class Personnel {
	private static final String[] ROLES = {"ADM", "VET", "SEC", "ASS"};
	
	private int id;
	private String nom, mdp, role;
	private boolean archive;
	
	public Personnel() {
		
	}
	
	public Personnel(int id, String nom, String mdp, String role, boolean archive) {
		setId(id);
		setNom(nom);
		setMdp(mdp);
		setRole(role);
		setArchive(archive);
	}
	
	public Personnel(int id, String nom, String mdp, String role) {
		setId(id);
		setNom(nom);
		setMdp(mdp);
		setRole(role);
	}
	
	public Personnel(String nom, String mdp, String role, boolean archive) {
		setNom(nom);
		setMdp(mdp);
		setRole(role);
		setArchive(archive);
	}
	
	
	@Override
	public String toString() {
		return "Personnel [id=" + id + ", nom=" + nom + ", mdp=" +
				mdp + ", role=" + role + ", archive=" + archive	+ "]";
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}	
}
