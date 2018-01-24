package fr.eni.clinique_veto.ihm.agenda;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import fr.eni.clinique_veto.bo.RendezVous;


@SuppressWarnings("serial")
public class RdzVousTableModel extends AbstractTableModel {
	private List<RendezVous> listeRdzVous;

	private String[] columnNames = { "Heure", "Nom du client", "Animal",  "Race" };

	 public RdzVousTableModel(List<RendezVous> list) {
	 this.listeRdzVous = list;

	 }

	@Override
	public String getColumnName(int col) {
		System.out.println("col name");
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		System.out.println("row count");
		return listeRdzVous.size();
	}

	@Override
	public int getColumnCount() {
		System.out.println("col count");
		return columnNames.length;
	}



	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object val = null;
		switch (columnIndex) {
		case RdzVousTable.COL_HEURE:
			val = listeRdzVous.get(rowIndex).getHour();
			break;
		case RdzVousTable.COL_NOM:
			val = listeRdzVous.get(rowIndex).getAnimal().getClient();
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


