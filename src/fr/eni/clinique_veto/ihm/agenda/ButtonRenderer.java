package fr.eni.clinique_veto.ihm.agenda;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	@Override
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean isFocus, int row, int col) {
		    //On écrit dans le bouton ce que contient la cellule
		    setText("supprimer");
		    //On retourne notre bouton
		    return this;
	 }

}
