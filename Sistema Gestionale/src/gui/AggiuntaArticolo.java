package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logic.Controller;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class AggiuntaArticolo extends JFrame {

	private Controller controller;
	private Style style;
	
	private JPanel contentPane;
	
	private JTextField textCodice;
	private JTextField textNome;
	private JTextArea areaDescrizione;
	private JTextField textPrezzo;
	private JComboBox boxCategoria;
	private JComboBox boxMarca;
	private JComboBox boxColore;
	private JComboBox boxSesso;
	private JComboBox boxTaglia;

	
	private JButton btnSalva;
	private JButton btnAnnulla;
	private JTextField textPath;
	
	ArrayList<String> contenitoreDati;

//**************************************************************************************
	
	public AggiuntaArticolo(Controller controller) {
		
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
				controller.CambiaFrame(AggiuntaArticolo.this, controller.getFinestraInventario());
			}
			
		});
		
	}
	
	public void RiceviDatiForm() {
		
		boolean formCompilato = true;
		contenitoreDati = new ArrayList<String>();
		
		contenitoreDati.add(textCodice.getText());
		contenitoreDati.add(textNome.getText());
		contenitoreDati.add(areaDescrizione.getText());
		contenitoreDati.add((String) boxCategoria.getSelectedItem());
		contenitoreDati.add((String) boxMarca.getSelectedItem());
		contenitoreDati.add((String) boxColore.getSelectedItem());
		contenitoreDati.add((String) boxSesso.getSelectedItem());
		contenitoreDati.add((String) boxTaglia.getSelectedItem());
		contenitoreDati.add(textPrezzo.getText());
		
		
		for (String testo : contenitoreDati) {
			formCompilato = ControlloValidità(testo.trim());
		}
		
		contenitoreDati.add(textPath.getText());
		
		if (formCompilato) {
			NuovoArticolo();
		} else {
			
			JDialog dialog = new JDialog(this, "ATTENZIONE");
			dialog.setBounds(700, 400, 300, 100);
			dialog.setVisible(true);
			dialog.setResizable(false);
			
			JLabel messaggio = new JLabel();
			messaggio.setText("Dati mancanti: compilare tutti i campi obbligatori");
			dialog.add(messaggio);
			
		}
	}
	
	public void NuovoArticolo() {
		System.out.println("Aggiungo nuovo articolo!");
	}
	
	public boolean ControlloValidità(String testo) {
		
		if (testo.isEmpty() || testo.length() > 200
							|| testo.equals("CATEGORIA") 
							|| testo.equals("MARCA") 
							|| testo.equals("COLORE") 
							|| testo.equals("SESSO") 
							|| testo.equals("TAGLIA")) 
		{
			return false;
		}
		
		return true;
		
	}
	
	public void ImpostaFinestra() {
		
		setTitle("Aggiunta nuovo articolo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(600, 200, 600, 600);
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
		
		JLabel lblCodice = new JLabel("Codice: ");
		lblCodice.setBounds(20, 52, 120, 14);
		contentPane.add(lblCodice);
		
		JLabel lblNome = new JLabel("Nome Prodotto: ");
		lblNome.setBounds(20, 92, 140, 20);
		contentPane.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione: ");
		lblDescrizione.setBounds(18, 125, 120, 20);
		contentPane.add(lblDescrizione);
		
		JLabel lblPrezzo = new JLabel("Prezzo:");
		lblPrezzo.setBounds(20, 394, 120, 20);
		contentPane.add(lblPrezzo);
		
		JLabel lblPath = new JLabel("(Opzionale) Path Immagine:");
		lblPath.setBounds(20, 439, 140, 14);
		contentPane.add(lblPath);
		
		textCodice = new JTextField();
		textCodice.setColumns(10);
		textCodice.setBounds(170, 49, 250, 20);
		contentPane.add(textCodice);
		
		textNome = new JTextField();
		textNome.setBounds(170, 92, 250, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 124, 250, 82);
		contentPane.add(scrollPane);
		
		areaDescrizione = new JTextArea();
		areaDescrizione.setLineWrap(true);
		areaDescrizione.setWrapStyleWord(true);
		scrollPane.setViewportView(areaDescrizione);
		
		boxCategoria = new JComboBox(controller.getCategoria().toArray());
		boxCategoria.setBounds(37, 251, 148, 20);
		contentPane.add(boxCategoria);
		
		boxMarca = new JComboBox(controller.getMarca().toArray());
		boxMarca.setBounds(224, 251, 148, 20);
		contentPane.add(boxMarca);
		
		boxColore = new JComboBox(controller.getColore().toArray());
		boxColore.setBounds(125, 313, 148, 20);
		contentPane.add(boxColore);
		
		boxSesso = new JComboBox(controller.getSesso().toArray());
		boxSesso.setBounds(410, 251, 148, 20);
		contentPane.add(boxSesso);
		
		boxTaglia = new JComboBox(controller.getTaglia().toArray());
		boxTaglia.setBounds(325, 313, 148, 20);
		contentPane.add(boxTaglia);
		
		textPrezzo = new JTextField();
		textPrezzo.setBounds(172, 394, 86, 20);
		contentPane.add(textPrezzo);
		textPrezzo.setColumns(10);
		
		textPath = new JTextField();
		textPath.setColumns(10);
		textPath.setBounds(170, 436, 250, 20);
		contentPane.add(textPath);
		
		btnSalva = new JButton("Salva");
		btnSalva.setBounds(270, 510, 150, 50);
		btnSalva.setBackground(style.greenBtn);
		contentPane.add(btnSalva);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(434, 510, 150, 50);
		btnAnnulla.setBackground(style.redBtn);
		contentPane.add(btnAnnulla);
		
		
		style.changeFont(contentPane, style.defaultS);
		
		
		
	}
}
