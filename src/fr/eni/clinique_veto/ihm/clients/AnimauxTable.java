package fr.eni.clinique_veto.ihm.clients;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.clinique_veto.bo.Animal;


public class AnimauxTable extends JTable {

	public static final int COL_NUM = 0;
	public static final int COL_NOM = 1;
	public static final int COL_SEXE = 2;
	public static final int COL_COULEUR = 3;
	public static final int COL_RACE = 4;
	public static final int COL_ESPECE = 5;
	public static final int COL_TATOUAGE = 6;
	
	private AnimauxTableModel modelTable;
	
	public AnimauxTable(List<Animal> list) {
		modelTable = new AnimauxTableModel(list);
		super.setModel(modelTable);
		
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);

        this.getColumnModel().getColumn(COL_NUM).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_NOM).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_ESPECE).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_RACE).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_SEXE).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_COULEUR).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_TATOUAGE).setPreferredWidth(70);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		
		this.setRowHeight(30);

	}

	public AnimauxTableModel getModelTable() {
		return modelTable;
	}
}
