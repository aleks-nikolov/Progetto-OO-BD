package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private JLabel labelQuantità;
	private JLabel labelSaldo;
	private JLabel labelPrezzo;
	
	private JComboBox<Integer> boxSaldo;
	
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
	}
	
	public void Rimuovi() {
		contenutoTransazione.RimuoviArticolo(ContenitoreTransazione.this);
		dialog = null;
	}
	
	@Override
	public void InserisciDati(Articolo articolo) {
		super.InserisciDati(articolo);
		
		if(contenutoTransazione.getTipoTransazione().equals("vendita"))
			labelPrezzo.setText(String.format("%.2f", getArticolo().getPrezzoDiListino()) + "€");
		else
			labelPrezzo.setText(String.format("%.2f", getArticolo().getPrezzoMagazzino()) + "€");
		
		
	}
	
	public void InserisciQuantità(int quantità) {
		labelQuantità.setText("x" + Integer.toString(quantità));
	}
	
	public void ImpostaPanelloLaterale() {
		
		pannelloLaterale = new JPanel();
		pannelloLaterale.setBackground(getStyle().bg);
		pannelloLaterale.setPreferredSize(new Dimension(150, 10));
		getContenitoreDati().add(pannelloLaterale, BorderLayout.EAST);
		pannelloLaterale.setBorder(new LineBorder(getStyle().border2, 2));
		pannelloLaterale.setLayout(new BoxLayout(pannelloLaterale, BoxLayout.Y_AXIS));
		
		labelPrezzo = new JLabel();
		pannelloLaterale.add(labelPrezzo);
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPrezzo.setFont(getStyle().defaultM);
		
		labelQuantità = new JLabel();
		labelQuantità.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelQuantità.setFont(getStyle().defaultS);
		labelQuantità.setPreferredSize(new Dimension(49, 15));
		pannelloLaterale.add(labelQuantità);
		
		JPanel pannelloSaldo = new JPanel();
		pannelloSaldo.setBackground(getStyle().bg);
		pannelloSaldo.setPreferredSize(new Dimension(100, 15));
		pannelloSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
		pannelloLaterale.add(pannelloSaldo);
		
		labelSaldo = new JLabel("Saldo %");
		labelSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		labelSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelSaldo.setFont(getStyle().defaultS);
		labelSaldo.setPreferredSize(new Dimension(80, 20));
		pannelloSaldo.add(labelSaldo);
		
		boxSaldo = new JComboBox<Integer>(new Integer[] {0, 10, 20, 30, 40, 50, 60, 70, 80, 90});
		boxSaldo.setFont(getStyle().defaultS);
		boxSaldo.setPreferredSize(new Dimension(50, 20));
		pannelloSaldo.add(boxSaldo);
		
		btnRimuovi = new JButton();
		btnRimuovi.setAlignmentX(Component.CENTER_ALIGNMENT);
		pannelloLaterale.add(btnRimuovi);
		btnRimuovi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRimuovi.setBackground(getStyle().redBtn);
		btnRimuovi.setIcon(getStyle().deleteIcon);
		btnRimuovi.setMargin(new Insets(5, 5, 5, 5));
		btnRimuovi.setPreferredSize(new Dimension(40, 40));
		
	}
	
	public ContenutoTransazione getContenutoVendita() {
		return contenutoTransazione;
	}
}
