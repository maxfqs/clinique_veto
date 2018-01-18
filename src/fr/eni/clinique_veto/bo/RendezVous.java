package fr.eni.clinique_veto.bo;

import java.sql.Date;

public class RendezVous {
	private Personnel pers;
	private Date date;
	private Animal animal;
	
	public RendezVous(Personnel pers, Date date, Animal animal) {
		super();
		this.pers = pers;
		this.date = date;
		this.animal = animal;
	}
	
	@Override
	public String toString() {
		return "RendezVous [pers=" + pers + ", date=" + date + ", animal=" + animal + "]";
	}
	
	public Personnel getPers() {
		return pers;
	}
	public void setPers(Personnel pers) {
		this.pers = pers;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	
	
}
