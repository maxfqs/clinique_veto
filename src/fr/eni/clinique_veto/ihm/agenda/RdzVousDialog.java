package fr.eni.clinique_veto.ihm.agenda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")
public class RdzVousDialog extends JDialog {
	
		private static int WIDTH = 750;
		private static int HEIGHT = 700;
		private static String[] LISTE_HEURES = {"06","07","08","09","10","11","12","13","14","15","16","17","18","19","20"};
		private static String[] LISTE_MINUTES = {"00","15","30","45"};
		
		private JPanel northPanel;
		private JPanel centerPanel;
		private JPanel southPanel;
		
		private JPanel clientPanel;

		
		private JPanel vetoPanel;
		private JLabel vetoLabel;
		private JPanel timePanel;
		private JPanel containerCombos;
		private JLabel textH;
		private JLabel dateLabel;
		private JLabel heureLabel;
		private JDatePickerImpl datePicker;
		private JLabel nomClient;
		private JLabel nomAnimal;
		
		private JLabel rechercheLabel;
		private JTextField searchClient;
		private JButton btnSearch;
		
		private JTable tableRdzVs;
		

		private JComboBox comboVeto;
		private JComboBox comboHeure;
		private JComboBox comboMin;
		
		private JButton btnValider;
		private JButton btnAnnuler;
		
		
		private String[] listeVetos = {"dr mabuse", "dr jivago"};

		
		public RdzVousDialog() {
			this.setSize(WIDTH, HEIGHT);
			this.setTitle("prise de rendez-vous");
			this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			this.setLayout(new BorderLayout());
			this.initComponents();
			this.initListeners();
		}


		private void initListeners() {
			btnSearch.addActionListener((e)->RdzVousController.get().chercherClient(searchClient.getText()));
			btnValider.addActionListener((e)-> System.out.println("validé"));
			btnAnnuler.addActionListener((e)->  this.setVisible(false));
		}


		private void initComponents() {
			
			TitledBorder title;
			
			title = BorderFactory.createTitledBorder("client");	
			clientPanel = new JPanel();
			clientPanel.setPreferredSize(new Dimension((WIDTH/3)-20,(WIDTH/3)-10));
			clientPanel.setMaximumSize(new Dimension((WIDTH/3)-20,(WIDTH/3)-10));
			clientPanel.setBorder(title);
			

			rechercheLabel = new JLabel("Rechercher par nom:");
			searchClient  =new JTextField(10);
			btnSearch = new JButton("chercher");
			nomClient = new JLabel();
			nomAnimal = new JLabel();
		
			clientPanel.add(rechercheLabel);
			clientPanel.add(searchClient);
			clientPanel.add(btnSearch);
			clientPanel.add(nomClient);
			clientPanel.add(nomAnimal);
			
			
			title = BorderFactory.createTitledBorder("vétérinaire");	
			vetoPanel = new JPanel();
			vetoPanel.setLayout(new BoxLayout(vetoPanel, BoxLayout.PAGE_AXIS));
			vetoPanel.setPreferredSize(new Dimension((WIDTH/3)-20,(WIDTH/3)-10));
			vetoPanel.setMaximumSize(new Dimension((WIDTH/3)-20,(WIDTH/3)-10));
			vetoPanel.setBorder(title);
			
			vetoLabel = new JLabel("<html><h3>vétérinaire</h3></html>");
			comboVeto = new JComboBox(listeVetos);
			comboVeto.setPreferredSize(new Dimension(50,20));
			comboVeto.setMaximumSize(new Dimension(200,30));
			
			
			vetoPanel.add(vetoLabel);
			vetoPanel.add(comboVeto);
			
			title = BorderFactory.createTitledBorder("date du rendez-vous");	
			timePanel = new JPanel();
			timePanel.setPreferredSize(new Dimension((WIDTH/3)-20,(WIDTH/3)-10));
			timePanel.setMaximumSize(new Dimension((WIDTH/3)-20,(WIDTH/3)-10));
			timePanel.setBorder(title);
			
		
			UtilDateModel model = new UtilDateModel();
			model.setSelected(true);
			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "Month");
			p.put("text.year", "Year");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			dateLabel  = new JLabel("<html><h3>Date du rendez-vous</h3></html>");
			heureLabel =  new JLabel("<html><h3>Heure</h3></html>");
			datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
			datePicker.getJDateInstantPanel();
			containerCombos = new JPanel();
			containerCombos.setLayout(new BoxLayout(containerCombos, BoxLayout.LINE_AXIS));
			comboHeure = new JComboBox(LISTE_HEURES);
			comboMin = new JComboBox(LISTE_MINUTES);
			textH = new JLabel("  h  ");
			containerCombos.add(comboHeure);
			containerCombos.add(textH);
			containerCombos.add(comboMin);
			timePanel.add(dateLabel);
			timePanel.add(datePicker);
			timePanel.add(heureLabel);
			timePanel.add(containerCombos);
			
			northPanel = new JPanel();
			northPanel.setPreferredSize(new Dimension(WIDTH-5,(WIDTH/3)+5));
			
			northPanel.add(clientPanel);
			northPanel.add(vetoPanel);
			northPanel.add(timePanel);
			
			southPanel = new JPanel();
			btnAnnuler = new JButton("annuler");
			btnValider = new JButton("valider");
			southPanel.add(btnAnnuler);
			southPanel.add(btnValider);
			
			this.add(northPanel, BorderLayout.NORTH);
			this.add(southPanel, BorderLayout.SOUTH);
		}
		
		private void addTable() {
			List<String> liste = new ArrayList<>();
		}
		
		public void setNomRendezVous(String pNomClient,String pNomAnimal) {
			nomClient.setText("Nom du client : " + pNomClient);
			nomAnimal.setText("Nom de l'animal : "+pNomAnimal );
			System.out.println("Nom du client : " + pNomClient);

		}
		
		private class DateLabelFormatter extends AbstractFormatter{

			private static final long serialVersionUID = 1L;
			
			private String datePattern = "dd-MM-yyyy";
			private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
			
			@Override
			public Object stringToValue(String text) throws ParseException {
				return dateFormatter.parseObject(text);
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				if(value != null){
					Calendar cal = (Calendar) value;
					return dateFormatter.format(cal.getTime());
				}
				return "";
			}
			
		}
		
	

	
}
