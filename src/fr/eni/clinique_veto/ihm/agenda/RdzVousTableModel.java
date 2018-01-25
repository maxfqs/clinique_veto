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
		RendezVous rdv = listeRdzVous.get(rowIndex);
		
		switch (columnIndex) {
			case RdzVousTable.COL_HEURE:
				val = rdv.getHour();
				break;
			case RdzVousTable.COL_NOM:
				val = rdv.getClient().getNomClient();
				break;
			case RdzVousTable.COL_ANIMAL:
				val = rdv.getAnimal().getNomAnimal();
				break;
			case RdzVousTable.COL_RACE:
				val = rdv.getAnimal().getRace();
				break;
		
		}
		
		return val;
	}



}


