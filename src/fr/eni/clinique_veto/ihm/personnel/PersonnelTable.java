package fr.eni.clinique_veto.ihm.personnel;

import java.util.List;

import javax.swing.JTable;

import fr.eni.clinique_veto.bo.Personnel;

public class PersonnelTable extends JTable {
	private static final long serialVersionUID = -6654460128942235000L;
	
	private List<Personnel> pers;
	private PersonnelTableModel ptm;
	
	public PersonnelTable(List<Personnel> p) {
		pers = p;
		ptm = new PersonnelTableModel(p);
		
		super.setModel(ptm);
	}
}
