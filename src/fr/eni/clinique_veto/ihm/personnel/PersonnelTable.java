package fr.eni.clinique_veto.ihm.personnel;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.eni.clinique_veto.bo.Personnel;

public class PersonnelTable extends JTable {
	private static final long serialVersionUID = -6654460128942235000L;
	
	private List<Personnel> pers;
	private PersonnelTableModel ptm;
	
	public PersonnelTable(List<Personnel> p) {
		pers = p;
		ptm = new PersonnelTableModel(p);
		
		super.setModel(ptm);
		
		getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					PersonnelController.get().setSelectedPersonnel(pers.get(getSelectedRow()));
				}
			}
		});
	}
}
