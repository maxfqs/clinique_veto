package fr.eni.clinique_veto.ihm.personnel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique_veto.bo.Personnel;

public class PersonnelTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -5819375907592880238L;

	private List<Personnel> pers;
	private String[] colNames = {
		"Nom", "Rôle", "Mot de passe"	
	};
	
	public PersonnelTableModel(List<Personnel> p) {
		pers = p;
	}
	
	@Override
	public String getColumnName(int col) {
        return colNames[col];
    }
	
	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return pers.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object val = null;
		
		switch (col) {
		case 0:
			val = pers.get(row).getNom();
			break;
		case 1:
			val = pers.get(row).getRole();
			break;
		case 2:
			val = pers.get(row).getMdp();
			break;			
		default:
			break;
		}
		
		return val;
	}

}
