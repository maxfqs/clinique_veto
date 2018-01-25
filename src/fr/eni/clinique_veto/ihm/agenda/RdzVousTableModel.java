package fr.eni.clinique_veto.ihm.agenda;

import java.util.EventObject;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import fr.eni.clinique_veto.bo.RendezVous;


@SuppressWarnings("serial")
public class RdzVousTableModel extends AbstractTableModel {
	private List<RendezVous> listeRdzVous;

	private String[] columnNames = { "Heure", "Nom du client", "Animal",  "Race", "supprimer" };

	 public RdzVousTableModel(List<RendezVous> list) {
	 this.listeRdzVous = list;

	 }

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return listeRdzVous.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex){
	    return true;
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object val = null;
		switch (columnIndex) {
		case RdzVousTable.COL_HEURE:
			val = listeRdzVous.get(rowIndex).getHour();
			break;
		case RdzVousTable.COL_NOM:
			val = "test";
			break;
		case RdzVousTable.COL_ANIMAL:
			val = listeRdzVous.get(rowIndex).getAnimal().getNomAnimal();
			break;
		case RdzVousTable.COL_RACE:
			val = listeRdzVous.get(rowIndex).getAnimal().getRace();
			break;
		
		}
		return val;
	}


}


