package fr.eni.clinique_veto.ihm;

import java.awt.Component;

import javax.swing.JOptionPane;

import fr.eni.clinique_veto.bll.BLLError;

public class ErrorDialog {
	
	private static void display(Component parent, String message) {
		JOptionPane.showMessageDialog(parent,
			    message,
			    "erreur",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showError(String message) {
		display(null, message);
	}
	
	public static void showError(BLLError error, Component parent) {
		display(parent, error.toString());
	}
}
