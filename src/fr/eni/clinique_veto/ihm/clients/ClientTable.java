package fr.eni.clinique_veto.ihm.clients;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.clinique_veto.bo.client.Client;

@SuppressWarnings("serial")
public class ClientTable extends JTable {


	public static final int COL_NOM = 0;
	public static final int COL_PRENOM = 1;
	public static final int COL_CODEPOSTAL = 2;
	public static final int COL_VILLE = 3;
	public static final int COL_EMAIL = 4;
	
	public ClientTable(List<Client> listeClients) {
		super.setModel(new ClientTableModel(listeClients));
		
        setPreferredScrollableViewportSize(new Dimension(500, 70));
        setFillsViewportHeight(true);

        this.getColumnModel().getColumn(COL_NOM).setPreferredWidth(100);
		this.getColumnModel().getColumn(COL_PRENOM).setPreferredWidth(100);
		this.getColumnModel().getColumn(COL_CODEPOSTAL).setPreferredWidth(50);
		this.getColumnModel().getColumn(COL_VILLE).setPreferredWidth(100);
		this.getColumnModel().getColumn(COL_EMAIL).setPreferredWidth(150);

		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		this.setRowHeight(30);
	}
}
