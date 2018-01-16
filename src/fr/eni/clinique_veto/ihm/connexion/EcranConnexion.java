package fr.eni.clinique_veto.ihm.connexion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EcranConnexion extends JFrame{
	private JLabel nom, password;
	private JTextField tfNom, tfPassword;
	private JButton valider;
	private JPanel panelChamps, panelValider;
	

	public JTextField getTfNom() {
		if(tfNom == null) tfNom = new JTextField(10);
		return tfNom;
	}

	public JTextField getTfPassword() {
		if(tfPassword == null) tfPassword = new JTextField(10);
		return tfPassword;
	}

	public JButton getValider() {
		if(valider == null) valider = new JButton("Valider");
		return valider;
	}
	
	
	
	
	public EcranConnexion() {
		
		//int frameWidth = 200;
	    //int frameHeight = 150;
	    //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    //setBounds((int) screenSize.getWidth() - frameWidth, 200, frameWidth, frameHeight);
	    setTitle("Connexion Personnels");
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(250, 150);
		setResizable(false);
		
		initIhm();
	}

	public void initIhm(){
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		panelChamps = new JPanel();
		panelChamps.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		nom = new JLabel("Nom");
		password = new JLabel("Password");
		
		gbc.gridy = 0;
		gbc.gridx = 0;
		panelChamps.add(nom, gbc);
		
		gbc.gridy = 0;
		gbc.gridx = 1;
		panelChamps.add(getTfNom(), gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 0;
		panelChamps.add(password, gbc);
		
		gbc.gridy = 1;
		gbc.gridx = 1;
		panelChamps.add(getTfPassword(), gbc);
	
		panelPrincipal.add(panelChamps, BorderLayout.NORTH);
		
		valider = new JButton("Valider");
		panelValider = new JPanel();
		panelValider.setLayout(new FlowLayout());
		panelValider.add(getValider());
		panelPrincipal.add(panelValider, BorderLayout.CENTER);
		
		setContentPane(panelPrincipal);
	}
	
}
