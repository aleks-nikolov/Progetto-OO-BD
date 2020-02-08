package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.Articolo;
import logic.Controller;
import logic.Fornitore;

public class AggiuntaFornitore extends JFrame {

	private Controller controller;
	private Style style;
	
	private JPanel contentPane;
	
	private JTextField textPartita;
	private JTextField textNome;
	private JTextField textVia;
	private JTextField textCAP;
	private JTextField textTelefono;
	
	private JButton btnSalva;
	private JButton btnAnnulla;
	
	ArrayList<String> contenitoreDati;
	private JTextField textNumeroCivico;
	

//**************************************************************************************
	
	public AggiuntaFornitore(Controller controller) {
		
		this.controller = controller;
		style = new Style();
		
		ImpostaFinestra();
		ImpostaForm();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		btnSalva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RiceviDatiForm();
			}
			
		});
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(AggiuntaFornitore.this, controller.getFinestraRifornimenti());
			}
			
		});
		
	}
	
	public void RiceviDatiForm() {
		
		boolean formCompilato = true;
		contenitoreDati = new ArrayList<String>();
		
		contenitoreDati.add(textPartita.getText());
		contenitoreDati.add(textNome.getText());
		contenitoreDati.add(textVia.getText());
		contenitoreDati.add(textNumeroCivico.getText());
		contenitoreDati.add(textCAP.getText());
		contenitoreDati.add(textTelefono.getText());
		
		for (String testo : contenitoreDati) {
			formCompilato = ControlloValidit�(testo.trim());
		}

		
		if (formCompilato) {
			CostruisciFornitore(contenitoreDati);
		} else {
			
			JDialog dialog = new JDialog(this, "ATTENZIONE");
			dialog.setBounds(700, 400, 300, 100);
			dialog.setVisible(true);
			dialog.setResizable(false);
			
			JLabel messaggio = new JLabel();
			messaggio.setText("Dati mancanti: compilare tutti i campi obbligatori");
			dialog.getContentPane().add(messaggio);
			
		}
	}
	
	public void CostruisciFornitore(ArrayList<String> dati) {
		
		Fornitore fornitore = new Fornitore();

		fornitore.setPartitaIVA(dati.get(0));
		fornitore.setNome(dati.get(1));
		fornitore.setVia(dati.get(2));
		fornitore.setNumeroCivico(dati.get(3));
		fornitore.setCAP(dati.get(4));
		fornitore.setNumeroDiTelefono(dati.get(5));
		
		controller.NuovoFornitore(fornitore);
		controller.CambiaFrame(AggiuntaFornitore.this, controller.getFinestraRifornimenti());
		
	}
	
	public boolean ControlloValidit�(String testo) {
		
		if (testo.isEmpty())
		{
			return false;
		}
		
		return true;
		
	}
	
	public void ImpostaFinestra() {
		
		setTitle("Aggiunta nuovo fornitore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(600, 200, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(style.bg);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
	}
	
	public void ImpostaForm() {
		
		JLabel label = new JLabel("Inserire I dati dell'articolo");
		label.setBounds(10, 11, 250, 20);
		contentPane.add(label);
		
		JLabel lblPartita = new JLabel("PartitaIVA:");
		lblPartita.setBounds(20, 52, 120, 14);
		contentPane.add(lblPartita);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 85, 120, 14);
		contentPane.add(lblNome);
		
		JLabel lblVia = new JLabel("Via:");
		lblVia.setBounds(20, 120, 120, 20);
		contentPane.add(lblVia);
		
		JLabel lblNumeroCivico = new JLabel("Numero civico:");
		lblNumeroCivico.setBounds(346, 120, 97, 20);
		contentPane.add(lblNumeroCivico);
		
		JLabel lblCAP = new JLabel("CAP: ");
		lblCAP.setBounds(20, 163, 97, 20);
		contentPane.add(lblCAP);
		
		JLabel lblTelefono = new JLabel("Numero di telefono:");
		lblTelefono.setBounds(21, 202, 140, 14);
		contentPane.add(lblTelefono);
		
		textPartita = new JTextField();
		textPartita.setColumns(10);
		textPartita.setBounds(119, 49, 250, 20);
		contentPane.add(textPartita);
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(119, 82, 250, 20);
		contentPane.add(textNome);
		
		textVia = new JTextField();
		textVia.setBounds(119, 120, 217, 20);
		contentPane.add(textVia);
		textVia.setColumns(10);

		textNumeroCivico = new JTextField();
		textNumeroCivico.setColumns(10);
		textNumeroCivico.setBounds(464, 120, 120, 20);
		contentPane.add(textNumeroCivico);
		
		textCAP = new JTextField();
		textCAP.setBounds(123, 163, 120, 20);
		contentPane.add(textCAP);
		textCAP.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(171, 199, 250, 20);
		contentPane.add(textTelefono);
		
		style.changeFont(contentPane, style.defaultS);
		
		btnSalva = new JButton("SALVA");
		btnSalva.setBounds(200, 310, 150, 50);
		btnSalva.setBackground(style.greenBtn);
		btnSalva.setFont(style.defaultM);
		btnSalva.setIcon(style.saveIcon);
		contentPane.add(btnSalva);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBounds(400, 310, 150, 50);
		btnAnnulla.setBackground(style.redBtn);
		btnAnnulla.setFont(style.defaultM);
		btnAnnulla.setIcon(style.backIcon);
		contentPane.add(btnAnnulla);

	}
}