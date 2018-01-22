package fr.eni.clinique_veto.ihm.agenda;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.ihm.clients.AnimauxTable;

@SuppressWarnings("serial")
public class RdzVousTableModel extends AbstractTableModel {
	private List<RendezVous> listeRdzVous;

	private String[] columnNames = { "Heure", "Nom du client", "Animal",  "Race" };

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



	// A REVOIR objet rendez vous pour données de la table
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object val = null;
//		switch (columnIndex) {
//		case RdzVousTable.COL_HEURE:
//			val = listeRdzVous.get(rowIndex).get;
//			break;
//		case RdzVousTable.COL_NOM:
//			val = listeRdzVous.get(rowIndex).getNomAnimal();
//			break;
//		case RdzVousTable.COL_ANIMAL:
//			val = listeRdzVous.get(rowIndex).getCouleur();
//			break;
//		case RdzVousTable.COL_RACE:
//			val = listeRdzVous.get(rowIndex).getEspece();
//			break;
//		
//		}
		return val;
	}


}
