package fr.eni.clinique_veto.bo;

import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique_veto.bo.client.Client;

public class Animal {
	private final char[] SEXE = {'M', 'F', 'H'}; 
	private final String[] ESPECES = {"Chat", "Chien", "Vache", "Cheval", "Crorodile"};
	private final String[][] RACES = {
			{"chat gentil", "chat méchant"},
			{"chien gentil", "chien méchant"},
			{"vache gentile", "vache méchante"},
			{"cheval gentil", "cheval méchant"},
			{"crocodile gentil", "crocodile méchant"}
	};
	
	private String race;
	private String espece;
	private Client client;
	private int codeAnimal;
	private String nomAnimal;
	private char sexe;
	private boolean archive;
	private String couleur;
	private long codeClient;
	private String tatouage;
	private String antecedents;
	
	public Animal(int codeAnimal, String nomAnimal, char sexe, String couleur, String race, String espece, int codeClient, String tatouage, 
			String antecedents, boolean archive) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	public Animal(String nomAnimal, char sexe, String couleur, String race, String espece, int codeClient, String tatouage, 
			String antecedents, boolean archive) {
		super();
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	public Animal(int codeAnimal, String nomAnimal, char sexe, String couleur, String race, String espece, int codeClient, String tatouage, 
			String antecedents) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
	}
	
	
	@Override
	public String toString() {
		return "Animal [race=" + race + ", espece=" + espece + ", client=" + client + ", codeAnimal=" + codeAnimal
				+ ", nomAnimal=" + nomAnimal + ", sexe=" + sexe + ", archive=" + archive + ", couleur=" + couleur
				+ ", codeClient=" + codeClient + ", tatouage=" + tatouage + ", antecedents=" + antecedents + "]";
	}


	public char[] getSEXE() {
		return SEXE;
	}


	public String[] getESPECES() {
		return ESPECES;
	}


	public String[][] getRACES() {
		return RACES;
	}

	
	
	
	public String getRace() {
		return race;
	}


	public void setRace(String race) {
		this.race = race;
	}


	public String getEspece() {
		return espece;
	}


	public void setEspece(String espece) {
		this.espece = espece;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public int getCodeAnimal() {
		return codeAnimal;
	}


	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}


	public String getNomAnimal() {
		return nomAnimal;
	}


	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}


	public char getSexe() {
		return sexe;
	}


	public void setSexe(char sexe) {
		this.sexe = sexe;
	}


	public boolean isArchive() {
		return archive;
	}


	public void setArchive(boolean archive) {
		this.archive = archive;
	}


	public String getCouleur() {
		return couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


	public long getCodeClient() {
		return codeClient;
	}


	public void setCodeClient(long codeClient) {
		this.codeClient = codeClient;
	}


	public String getTatouage() {
		return tatouage;
	}


	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}


	public String getAntecedents() {
		return antecedents;
	}


	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}


	
	
}
