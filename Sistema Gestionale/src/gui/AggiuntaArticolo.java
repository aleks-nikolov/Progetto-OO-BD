package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Controller;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AggiuntaArticolo extends JFrame {

	private Controller controller;
	private Utilities utilities;
	
	private JPanel contentPane;
	
	private JTextField textNome;
	private JTextArea areaDescrizione;
	private JTextField textPrezzo;
	private JComboBox boxCategoria;
	private JComboBox boxMarca;
	private JComboBox boxColore;
	private JComboBox boxSesso;
	private JTextField textCodice;
	
	private JButton btnSalva;
	private JButton btnAnnulla;
	

	public AggiuntaArticolo(Controller controller) {
		
		this.controller = controller;
		utilities = new Utilities();
		
		ImpostaFinestra();
		ImpostaForm();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(AggiuntaArticolo.this, controller.getFinestraInventario());
			}
			
		});
		
	}
	
	public void ImpostaFinestra() {
		
		setTitle("Aggiunta nuovo articolo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(600, 200, 600, 600);
		contentPane = new JPanel();
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
		
		JLabel lblMarca = new JLabel("Marca: ");
		lblMarca.setBounds(20, 257, 120, 20);
		contentPane.add(lblMarca);
		
		JLabel lblCategoria = new JLabel("Categoria: ");
		lblCategoria.setBounds(20, 227, 120, 20);
		contentPane.add(lblCategoria);
		
		JLabel lblColore = new JLabel("Colore: ");
		lblColore.setBounds(20, 288, 120, 20);
		contentPane.add(lblColore);
		
		JLabel lblSesso = new JLabel("Sesso: ");
		lblSesso.setBounds(20, 319, 120, 20);
		contentPane.add(lblSesso);
		
		JLabel lblPrezzo = new JLabel("Prezzo:");
		lblPrezzo.setBounds(20, 366, 120, 20);
		contentPane.add(lblPrezzo);
		
		textCodice = new JTextField();
		textCodice.setColumns(10);
		textCodice.setBounds(170, 49, 250, 20);
		contentPane.add(textCodice);
		
		textNome = new JTextField();
		textNome.setBounds(170, 92, 250, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		areaDescrizione = new JTextArea();
		areaDescrizione.setBounds(170, 123, 250, 82);
		contentPane.add(areaDescrizione);
		
		boxCategoria = new JComboBox();
		boxCategoria.setBounds(172, 227, 148, 20);
		contentPane.add(boxCategoria);
		
		boxMarca = new JComboBox();
		boxMarca.setBounds(172, 257, 148, 20);
		contentPane.add(boxMarca);
		
		boxColore = new JComboBox();
		boxColore.setBounds(172, 288, 148, 20);
		contentPane.add(boxColore);
		
		boxSesso = new JComboBox();
		boxSesso.setBounds(172, 319, 148, 20);
		contentPane.add(boxSesso);
		
		textPrezzo = new JTextField();
		textPrezzo.setBounds(172, 366, 86, 20);
		contentPane.add(textPrezzo);
		textPrezzo.setColumns(10);
		
		btnSalva = new JButton("Salva");
		btnSalva.setBounds(270, 510, 150, 50);
		btnSalva.setBackground(utilities.greenBtn);
		contentPane.add(btnSalva);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(434, 510, 150, 50);
		btnAnnulla.setBackground(utilities.redBtn);
		contentPane.add(btnAnnulla);
		
		utilities.changeFont(contentPane, utilities.arialS);
		
	}
}
