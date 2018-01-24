package fr.eni.clinique_veto.ihm.agenda;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;



@SuppressWarnings("serial")
public class AgendaFrame extends JPanel {

	private static int WIDTH = 600;
	private static int HEIGHT = 500;
	
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel vetoPanel;
	private JPanel centerPanel;
	private JLabel comboVetoLabel;
	private JComboBox<String> comboVeto;
	private JDatePickerImpl datePicker;
	private JButton dossierBtn;
	
	public AgendaFrame() {
		this.setSize(WIDTH, HEIGHT);
		this.initComponent();
		this.initListeners();
	}

	private void initListeners() {
		datePicker.addActionListener((e)->{
			java.util.Date date  = (java.util.Date) datePicker.getModel().getValue();
			System.out.println(date);
			System.out.println(comboVeto.getSelectedItem());
		});
		comboVeto.addActionListener((e)->{
			System.out.println(comboVeto.getSelectedItem());
			java.util.Date date  = (java.util.Date) datePicker.getModel().getValue();
			System.out.println(date);
		});
		
	}

	private void initComponent() {
		vetoPanel = new JPanel();
		
		// init false combo
		String[] listeVeto = {"toto", "robert","william cramps"};
		comboVeto = new JComboBox(listeVeto);
		comboVetoLabel = new JLabel("vétérinaire");
		
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.getJDateInstantPanel();
		
		vetoPanel.add(comboVetoLabel);
		vetoPanel.add(comboVeto);
		
		northPanel = new JPanel();
		northPanel.add(vetoPanel);
		northPanel.add(datePicker);
		
		TitledBorder title;
		title = BorderFactory.createTitledBorder("De");	
		northPanel.setBorder(title);
		
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		this.add(centerPanel, BorderLayout.CENTER);
		
		southPanel = new JPanel();
		dossierBtn = new JButton("Dossier médical");
		
		southPanel.add(dossierBtn);
		this.add(southPanel, BorderLayout.SOUTH);
		
	}
	
	public JComboBox<String> getVetCBox() {
		return comboVeto;
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
