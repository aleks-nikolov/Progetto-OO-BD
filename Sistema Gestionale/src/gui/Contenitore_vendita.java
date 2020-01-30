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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logic.Controller;

public class Contenitore_vendita extends ContenitoreArticolo {
	private static final long serialVersionUID = 1L;
	
	private ContenutoVendita contenutoVendita;
	private AvvisoElimina dialog;
	
	private JPanel panelloLaterale;
	private JButton btnRimuovi;
	private JLabel labelQuantità;
	private JLabel labelPrezzo;
	
	public Contenitore_vendita(Controller controller, ContenutoVendita contenutoVendita) {
		super(controller, contenutoVendita);
		this.contenutoVendita = contenutoVendita;
		
		ImpostaPanelloLaterale();
		AggiungiListener();
		InserisciDati();
	}
	
	public void AggiungiListener() {
		
		btnRimuovi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog = new AvvisoElimina(Contenitore_vendita.this);
				
			}
			
		});
	}
	
	public void Rimuovi() {
		contenutoVendita.RimuoviArticolo(Contenitore_vendita.this);
		dialog = null;
	}
	
	@Override
	public void InserisciDati() {
		super.InserisciDati();
		labelPrezzo.setText(Float.toString(getPrezzoDiListino()));
		labelQuantità.setText(Integer.toString(getQuantità()));
	}
	
	public void ImpostaPanelloLaterale() {
		panelloLaterale = new JPanel();
		panelloLaterale.setBackground(getStyle().bg);
		panelloLaterale.setPreferredSize(new Dimension(150, 10));
		getContenitoreDati().add(panelloLaterale, BorderLayout.EAST);
		panelloLaterale.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panelloLaterale.setLayout(new BoxLayout(panelloLaterale, BoxLayout.Y_AXIS));
		
		labelPrezzo = new JLabel();
		panelloLaterale.add(labelPrezzo);
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPrezzo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		labelQuantità = new JLabel();
		labelQuantità.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelQuantità.setFont(getStyle().defaultS);
		labelQuantità.setPreferredSize(new Dimension(49, 15));
		panelloLaterale.add(labelQuantità);
		
		btnRimuovi = new JButton("X");
		btnRimuovi.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelloLaterale.add(btnRimuovi);
		btnRimuovi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRimuovi.setBackground(getStyle().redBtn);
		btnRimuovi.setMargin(new Insets(20, 20, 20, 20));
		btnRimuovi.setPreferredSize(new Dimension(10, 10));
	}
	
	public ContenutoVendita getContenutoVendita() {
		return contenutoVendita;
	}
}
