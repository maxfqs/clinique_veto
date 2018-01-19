package fr.eni.clinique_veto.ihm.agenda;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.clinique_veto.bo.Animal;
import fr.eni.clinique_veto.ihm.clients.AnimauxTableModel;


@SuppressWarnings("serial")
public class RdzVousTable extends JTable {

	public static final int COL_HEURE = 0;
	public static final int COL_NOM = 1;
	public static final int COL_ANIMAL = 2;
	public static final int COL_RACE = 3;
	
	public RdzVousTable(List<Animal> list) {
	super.setModel(new AnimauxTableModel(list));
	
    setPreferredScrollableViewportSize(new Dimension(500, 70));
    setFillsViewportHeight(true);

    this.getColumnModel().getColumn(COL_HEURE).setPreferredWidth(70);
	this.getColumnModel().getColumn(COL_NOM).setPreferredWidth(70);
	this.getColumnModel().getColumn(COL_ANIMAL).setPreferredWidth(70);
	this.getColumnModel().getColumn(COL_RACE).setPreferredWidth(70);

	this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	this.setRowHeight(30);
	}
	
}
