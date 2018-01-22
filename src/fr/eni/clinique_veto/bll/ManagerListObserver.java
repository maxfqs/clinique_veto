package fr.eni.clinique_veto.bll;

public interface ManagerListObserver {
	
	/** 
	 * Fire each time a Manager list is updated
	 * This include the add, update and remove actions
	 */
	public void onListUpdated();
}
