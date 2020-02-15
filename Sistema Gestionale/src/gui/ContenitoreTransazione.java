package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logic.Articolo;
import logic.Controller;

public class ContenitoreTransazione extends ContenitoreArticolo {
	private static final long serialVersionUID = 1L;
	
	private ContenutoTransazione contenutoTransazione;
	private AvvisoElimina dialog;
	
	private JPanel pannelloLaterale;
	private JButton btnRimuovi;
	
	private JLabel lblQuantita;
	private JLabel lblSaldo;
	private JLabel lblPrezzo;
	
	private JComboBox<Integer> boxSaldo;
	
	private int quantità;
	private float prezzo;
	
	public ContenitoreTransazione(Controller controller, ContenutoTransazione contenutoTransazione) {
		super(controller, contenutoTransazione);
		this.contenutoTransazione = contenutoTransazione;
		
		ImpostaPanelloLaterale();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		btnRimuovi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog = new AvvisoElimina(ContenitoreTransazione.this);
			}
			
		});
		
		boxSaldo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(contenutoTransazione.getTipoTransazione().equals("vendita"))
					AggiornaPrezzo(getArticolo().getPrezzoDiListino());
				else
					AggiornaPrezzo(getArticolo().getPrezzoMagazzino());
			}
			
		});
	}
	
	public void Rimuovi() {
		contenutoTransazione.RimuoviArticolo(ContenitoreTransazione.this);
		dialog = null;
	}
	
	@Override
	public void InserisciDati(Articolo articolo) {
		super.InserisciDati(articolo);

		if(contenutoTransazione.getTipoTransazione().equals("vendita")) 
			AggiornaPrezzo(getArticolo().getPrezzoDiListino());
		else
			AggiornaPrezzo(getArticolo().getPrezzoMagazzino());
		
	}
	
	public void AggiornaPrezzo(float prezzo) {
		
		float saldo = Float.parseFloat(boxSaldo.getSelectedItem().toString());
		prezzo -= prezzo * (saldo / 100);
		
		this.prezzo = prezzo;
		
		lblPrezzo.setText(String.format("%.2f", prezzo) + "€");
		contenutoTransazione.AggiornaPrezzoTotale();
		
	}
	
	public void InserisciQuantità(int quantità) {
		this.quantità = quantità;
		lblQuantita.setText("x" + Integer.toString(quantità));
	}

	public ArrayList<String> getDatiComposizione() {
		
		ArrayList<String> dati = new ArrayList<String>();
		dati.add(String.valueOf(getArticolo().getSKU()));
		dati.add(String.valueOf(quantità));
		dati.add(String.valueOf(boxSaldo.getSelectedItem()));
		
		return dati;
	}
	
	public void ImpostaPanelloLaterale() {
		
		pannelloLaterale = new JPanel();
		pannelloLaterale.setBackground(getStyle().bg);
		pannelloLaterale.setPreferredSize(new Dimension(150, 10));
		getContenitoreDati().add(pannelloLaterale, BorderLayout.EAST);
		pannelloLaterale.setBorder(new LineBorder(getStyle().border2, 2));
		pannelloLaterale.setLayout(new BoxLayout(pannelloLaterale, BoxLayout.Y_AXIS));
		
		lblPrezzo = new JLabel();
		pannelloLaterale.add(lblPrezzo);
		lblPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPrezzo.setFont(getStyle().defaultM);
		
		lblQuantita = new JLabel();
		lblQuantita.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantita.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuantita.setFont(getStyle().defaultS);
		lblQuantita.setPreferredSize(new Dimension(49, 15));
		pannelloLaterale.add(lblQuantita);
		
		JPanel pannelloSaldo = new JPanel();
		pannelloSaldo.setBackground(getStyle().bg);
		pannelloSaldo.setPreferredSize(new Dimension(100, 15));
		pannelloSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pannelloLaterale.add(pannelloSaldo);
		
		lblSaldo = new JLabel("Saldo %");
		lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSaldo.setFont(getStyle().defaultS);
		lblSaldo.setPreferredSize(new Dimension(80, 20));
		pannelloSaldo.add(lblSaldo);
		
		boxSaldo = new JComboBox<Integer>(new Integer[] {0, 10, 20, 30, 40, 50, 60, 70, 80, 90});
		boxSaldo.setFont(getStyle().defaultS);
		boxSaldo.setPreferredSize(new Dimension(50, 20));
		pannelloSaldo.add(boxSaldo);
		
		btnRimuovi = new JButton();
		btnRimuovi.setToolTipText("rimuovi il seguente articolo");
		btnRimuovi.setAlignmentX(Component.CENTER_ALIGNMENT);
		pannelloLaterale.add(btnRimuovi);
		btnRimuovi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRimuovi.setBackground(getStyle().redBtn);
		btnRimuovi.setIcon(getStyle().deleteIcon);
		btnRimuovi.setMargin(new Insets(5, 5, 5, 5));
		btnRimuovi.setPreferredSize(new Dimension(40, 40));
		
	}
	
	public ContenutoTransazione getContenutoTransazione() {
		return contenutoTransazione;
	}
	
	public int getQuantità() {
		return quantità;
	}
	
	public float getPrezzo() {
		return prezzo;
	}
}
