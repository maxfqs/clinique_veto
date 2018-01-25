package fr.eni.clinique_veto.ihm.agenda;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ButtonEditor extends DefaultCellEditor {
	
	  protected JButton button;
	  private boolean   isPushed;
	  private ButtonListener bListener = new ButtonListener();

	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(bListener);
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 

	    bListener.setRow(row);
	
	    bListener.setColumn(column);

	    bListener.setTable(table);

	    button.setText("supprimer");
	
	    return button;
	  }

	  class ButtonListener implements ActionListener{        
	    private int column, row;
	    private JTable table;
	    private int nbre = 0;
	        
	    public void setColumn(int col){this.column = col;}
	    public void setRow(int row){this.row = row;}
	    public void setTable(JTable table){this.table = table;}
	        
	    public void actionPerformed(ActionEvent event) {
	    	RdzVousController.get().removeRdzVous(((RdzVousTable)table).getSelected());
            ((RdzVousTableModel)table.getModel()).fireTableRowsDeleted(this.row, this.row);
             cancelCellEditing();
	    }
	  }     
}
