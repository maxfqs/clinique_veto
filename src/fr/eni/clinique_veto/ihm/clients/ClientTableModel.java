package fr.eni.clinique_veto.ihm.clients;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import fr.eni.clinique_veto.bo.client.Client;

@SuppressWarnings("serial")
public class ClientTableModel extends AbstractTableModel {


	private List<Client> listeClients;

	private String[] columnNames = { "Nom", "Prénom", "Code Postal", "Ville", "Email" };

	 public  ClientTableModel(List<Client> listeClients) {
	 this.listeClients = listeClients;
	 }

	

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return listeClients.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object val = null;
		switch (columnIndex) {
		case ClientTable.COL_NOM:
			val = listeClients.get(rowIndex).getNomClient();
			break;
		case ClientTable.COL_PRENOM:
			val = listeClients.get(rowIndex).getPrenomClient();
			break;
		case ClientTable.COL_CODEPOSTAL:
			val = listeClients.get(rowIndex).getCodePostal();
			break;
		case ClientTable.COL_VILLE:
			val = listeClients.get(rowIndex).getVille();
			break;
		case ClientTable.COL_EMAIL:
			val = listeClients.get(rowIndex).getEmail();
			break;
		}
		return val;
	}

}
