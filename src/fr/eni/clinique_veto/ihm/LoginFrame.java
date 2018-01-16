package fr.eni.clinique_veto.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = 2599708452804266382L;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 300;
	
	private  JTextField usernameField;
	private JPasswordField passwordField;
	private JPanel containerLogin;
	private JPanel containerBtn;
	private JButton valider;
	private JButton annuler;
	
	public LoginFrame() {
		this.setTitle(CVApp.APP_TITLE + " - Login");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
		this.initListeners();
	}


	private void initComponents() {
		containerLogin = new JPanel();
		containerLogin.setLayout(new GridBagLayout());
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Se connecter");
		containerLogin.setBorder(title);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
	
		valider = new JButton("OK");
		annuler = new JButton("Annuler");
		containerBtn = new JPanel();
		
		containerBtn.add(annuler);
		containerBtn.add(valider);		
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		containerLogin.add( new JLabel("Utilisateur:"), gbc);
		
		gbc.gridx = 1;
		containerLogin.add( getUsernameField(), gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		containerLogin.add( new JLabel("Mot de passe:"), gbc);
		
		gbc.gridx = 1;
		containerLogin.add( getPasswordField(), gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 1;
		
		containerLogin.add(containerBtn, gbc);
		
		this.setContentPane(containerLogin);
	}

	public JTextField getUsernameField() {
		if (usernameField == null) {
			usernameField = new JTextField(10);
		}
		return usernameField;
	}
	
	public JTextField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField(10);
		}
		return passwordField;
	}
	
	
	private void initListeners() {		
		annuler.addActionListener((e)-> System.exit(1));
		
		valider.addActionListener((e)-> {
			LoginController.get().logUser();
		});
	}
}
