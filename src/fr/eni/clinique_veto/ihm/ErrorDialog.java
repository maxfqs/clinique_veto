package fr.eni.clinique_veto.ihm;

import javax.swing.JOptionPane;

public class ErrorDialog {
	public static void showError(String message) {
		JOptionPane.showMessageDialog(null,
			    message,
			    "erreur",
			    JOptionPane.WARNING_MESSAGE);
	}
}
