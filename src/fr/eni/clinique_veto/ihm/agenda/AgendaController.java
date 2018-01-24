package fr.eni.clinique_veto.ihm.agenda;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import fr.eni.clinique_veto.bll.EspecesManager;
import fr.eni.clinique_veto.bll.PersonnelManager;
import fr.eni.clinique_veto.bo.Personnel;
import fr.eni.clinique_veto.bo.RendezVous;
import fr.eni.clinique_veto.ihm.MenuController;

public class AgendaController implements MenuController {
	private static AgendaController instance;
	private AgendaFrame panel;
	private List<RendezVous> rdvs;
	private List<Personnel> vets;
	
	private AgendaController() {
		this.panel = new AgendaFrame();
		this.rdvs = new ArrayList<RendezVous>();
		this.vets = new ArrayList<Personnel>();
		
		initCBox();
		hide();
	}
	
	public static AgendaController get() {
		if(instance == null) {
			instance = new AgendaController();
		}
		
		return instance;
	}
	
	private void initCBox() {
		panel.getVetCBox().addItemListener(new ItemListener() {			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() != ItemEvent.SELECTED) return;
				
				Personnel v = vets.get(panel.getVetCBox().getSelectedIndex());
			}
		});		
	}
	
	private void fillVetCBox() {
		vets = PersonnelManager.get().getVeto();
		String[] noms = new String[vets.size()];
		int i = 0;
		
		for(Personnel v: vets) noms[i++] = v.getNom();
		
		panel.getVetCBox().setModel(new DefaultComboBoxModel<String>(noms));
	}
	
	
	@Override
	public void show() {
		fillVetCBox();
		panel.setVisible(true);	
	}

	@Override
	public void hide() {
		panel.setVisible(false);		
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

}
