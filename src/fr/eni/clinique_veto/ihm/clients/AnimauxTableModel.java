package fr.eni.clinique_veto.ihm.clients;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.bo.AnimalTest;

public class AnimauxTableModel extends AbstractTableModel {


	

	private List<Animal> listeAnimaux;

	private String[] columnNames = { "Numéro", "Nom", "Sexe", "Couleur", "Race", "Espèce", "Tatouage" };

	 public AnimauxTableModel(List<Animal> list) {
	 this.listeAnimaux = list;
	 }

	

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return listeAnimaux.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object val = null;
		switch (columnIndex) {
		case AnimauxTable.COL_NUM:
			val = listeAnimaux.get(rowIndex).getCodeAnimal();
			break;
		case AnimauxTable.COL_NOM:
			val = listeAnimaux.get(rowIndex).getNomAnimal();
			break;
		case AnimauxTable.COL_COULEUR:
			val = listeAnimaux.get(rowIndex).getCouleur();
			break;
		case AnimauxTable.COL_ESPECE:
			val = listeAnimaux.get(rowIndex).getEspece();
			break;
		case AnimauxTable.COL_RACE:
			val = listeAnimaux.get(rowIndex).getRace();
			break;
		case AnimauxTable.COL_SEXE:
			val = listeAnimaux.get(rowIndex).getSexe();
			break;
		case AnimauxTable.COL_TATOUAGE:
			val = listeAnimaux.get(rowIndex).getTatouage();
			break;
		}
		return val;
	}

}
