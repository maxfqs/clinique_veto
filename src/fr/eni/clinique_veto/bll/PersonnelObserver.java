package fr.eni.clinique_veto.bll;

import fr.eni.clinique_veto.bo.Personnel;

public interface PersonnelObserver {
	public void onNewPersonnelAdded(Personnel p);
	public void onPersonnelRemoved();
}
