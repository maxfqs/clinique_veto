package fr.eni.clinique_veto.bo;

import java.util.Calendar;
import java.util.Date;

import fr.eni.clinique_veto.bo.client.Client;

public class RendezVous {
	private Client client;
	private Personnel pers;
	private Date date;
	private Animal animal;
	private String hour;
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public RendezVous(){
		
	}
	
	public RendezVous(Personnel pers, Date date, Animal animal, Client client) {
		this.pers = pers;
		this.date = date;
		this.animal = animal;
		this.client = client;
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.date);
		int hour =  cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		this.hour = hour+"h"+minutes;
	}
		public RendezVous(Personnel pers, Date date, Animal animal) {
			this.pers = pers;
			this.date = date;
			this.animal = animal;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.date);
		int hour =  cal.get(Calendar.HOUR_OF_DAY);
		int minutes = cal.get(Calendar.MINUTE);
		this.hour = hour+"h"+minutes;
	}
	
	public String getHour(){
		return this.hour;	
	}

	
	@Override
	public String toString() {
		return "RendezVous [pers=" + pers + ", date=" + date + ", animal=" + animal +"]";
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
