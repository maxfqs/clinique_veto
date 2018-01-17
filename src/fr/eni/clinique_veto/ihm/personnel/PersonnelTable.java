package fr.eni.clinique_veto.ihm.personnel;

import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bo.Personnel;

@SuppressWarnings("serial")
public class PersonnelTable extends JTable {
	private PersonnelTableModel ptm;
	
	public PersonnelTable() {
		List<Personnel> pers = PersonnelManager.get().getPersonnels();
		ptm = new PersonnelTableModel(pers);
		
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
