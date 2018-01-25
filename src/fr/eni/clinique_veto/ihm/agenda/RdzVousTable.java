package fr.eni.clinique_veto.ihm.agenda;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.clinique_veto.bo.RendezVous;


@SuppressWarnings("serial")
public class RdzVousTable extends JTable {

	public static final int COL_HEURE = 0;
	public static final int COL_NOM = 1;
	public static final int COL_ANIMAL = 2;
	public static final int COL_RACE = 3;
	
	private List<RendezVous> rdvs;
	private RdzVousTableModel model;
	
	public RdzVousTable() {
		rdvs = new ArrayList<RendezVous>();		
		model = new RdzVousTableModel(rdvs);
		setModel(model);
	
	
	    setPreferredScrollableViewportSize(new Dimension(500, 70));
	    setFillsViewportHeight(true);
	
	    this.getColumnModel().getColumn(COL_HEURE).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_NOM).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_ANIMAL).setPreferredWidth(70);
		this.getColumnModel().getColumn(COL_RACE).setPreferredWidth(70);
	
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
		this.setRowHeight(30);
	}
	
	public void updateData(List<RendezVous> data) {
		rdvs.clear();
		rdvs.addAll(data);
		model.fireTableDataChanged();
	}

	
}
