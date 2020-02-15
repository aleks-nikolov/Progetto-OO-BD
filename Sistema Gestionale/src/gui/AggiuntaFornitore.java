package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import logic.Controller;

public class AggiuntaFornitore extends JFrame {
	private static final long serialVersionUID = 1L;
	
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
		
		contenitoreDati.add(textNome.getText());
		contenitoreDati.add(textVia.getText());
		contenitoreDati.add(textNumeroCivico.getText());
		contenitoreDati.add(textCAP.getText());
		contenitoreDati.add(textTelefono.getText());
		
		if(textPartita.getText().length() == 11)
			contenitoreDati.add(textPartita.getText());
		else
			contenitoreDati.add("");
		
		for (String testo : contenitoreDati) {
			formCompilato = ControlloValidità(testo.trim());
		}
		
		if (formCompilato) {
			controller.NuovoFornitore(contenitoreDati);
			controller.CambiaFrame(AggiuntaFornitore.this, controller.getFinestraRifornimenti());
		} else {
			controller.MostraMessaggioAvviso("ATTENZIONE", "Dati mancanti o invalidi");
			contenitoreDati.removeAll(contenitoreDati);
		}
	}
	
	public boolean ControlloValidità(String testo) {
		
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
		
		JLabel label = new JLabel("Inserire I dati del fornitore");
		label.setBounds(10, 11, 250, 20);
		contentPane.add(label);
		
		JLabel lblPartita = new JLabel("PartitaIVA (11 caratteri):");
		lblPartita.setBounds(20, 52, 349, 14);
		contentPane.add(lblPartita);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 116, 120, 14);
		contentPane.add(lblNome);
		
		JLabel lblVia = new JLabel("Via:");
		lblVia.setBounds(20, 151, 120, 20);
		contentPane.add(lblVia);
		
		JLabel lblNumeroCivico = new JLabel("Numero civico:");
		lblNumeroCivico.setBounds(346, 151, 120, 20);
		contentPane.add(lblNumeroCivico);
		
		JLabel lblCAP = new JLabel("CAP: ");
		lblCAP.setBounds(20, 194, 97, 20);
		contentPane.add(lblCAP);
		
		JLabel lblTelefono = new JLabel("Numero di telefono:");
		lblTelefono.setBounds(21, 233, 170, 14);
		contentPane.add(lblTelefono);
		
		textPartita = new JTextField();
		textPartita.setColumns(10);
		textPartita.setBounds(30, 77, 250, 20);
		contentPane.add(textPartita);
		
		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBounds(119, 113, 250, 20);
		contentPane.add(textNome);
		
		textVia = new JTextField();
		textVia.setBounds(119, 151, 217, 20);
		contentPane.add(textVia);
		textVia.setColumns(10);

		textNumeroCivico = new JTextField();
		textNumeroCivico.setColumns(10);
		textNumeroCivico.setBounds(464, 151, 120, 20);
		contentPane.add(textNumeroCivico);
		
		textCAP = new JTextField();
		textCAP.setBounds(123, 194, 120, 20);
		contentPane.add(textCAP);
		textCAP.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(201, 230, 250, 20);
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
