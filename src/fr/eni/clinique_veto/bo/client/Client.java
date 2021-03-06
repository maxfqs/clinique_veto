package fr.eni.clinique_veto.bo.client;

public class Client {


	private int codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private int archive;
	//private List<Animal>listeAnimaux = new ArrayList<Animal>();
	
	
	public Client() {
		
	}
	
	/*
	 * contructeur complet
	 */
	public Client(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			int archive) {
		super();
		this.setCodeClient( codeClient);
		this.setNomClient(nomClient);
		this.setPrenomClient(prenomClient);
		this.setAdresse1(adresse1);
		this.setAdresse2(adresse2);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setNumTel(numTel);
		this.setAssurance(assurance);
		this.setEmail(email);
		this.setRemarque(remarque);
		this.setArchive(archive);
	}
	
	/*
	 * contructeur sans codeClient
	 */
	public Client(String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque,
			int archive) {
		super();
		this.setNomClient(nomClient);
		this.setPrenomClient(prenomClient);
		this.setAdresse1(adresse1);
		this.setAdresse2(adresse2);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setNumTel(numTel);
		this.setAssurance(assurance);
		this.setEmail(email);
		this.setRemarque(remarque);
		this.setArchive(archive);
	}

//	public void addAnimal(Animal a) {
//		this.listeAnimaux.add(a);
//	}
//	
//	public void removeAnimal(Animal a) {
//		this.listeAnimaux.remove(a.getCodeAnimal());
//	}

	public int getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getAdresse1() {
		return adresse1;
	}
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getAssurance() {
		return assurance;
	}
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public int getArchive() {
		return archive;
	}
	public void setArchive(int archive) {
		this.archive = archive;
	}

	
	@Override
	public String toString() {
		return "Client [codeClient=" + codeClient + ", nomClient=" + nomClient + ", prenomClient=" + prenomClient
				+ ", adresse1=" + adresse1 + ", adresse2=" + adresse2 + ", codePostal=" + codePostal + ", ville="
				+ ville + ", numTel=" + numTel + ", assurance=" + assurance + ", email=" + email + ", remarque="
				+ remarque + ", archive=" + archive + "]";
	}
	
	
}
