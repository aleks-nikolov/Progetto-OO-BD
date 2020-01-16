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

public class ContenitoreArticolo_Vendita extends ContenitoreArticolo {
	private static final long serialVersionUID = 1L;
	
	private ContenutoVendita contenutoVendita;
	
	private JPanel panelloLaterale;
	private JButton btnRimuovi;
	private JLabel labelQuantità;
	private JLabel labelPrezzo;
	
	
	public ContenitoreArticolo_Vendita(ContenutoVendita cv) {
		super(cv);
		this.contenutoVendita = cv;
		
		ImpostaPanelloLaterale();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		btnRimuovi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				contenutoVendita.RimuoviArticolo(ContenitoreArticolo_Vendita.this);
			}
			
		});
		
	}
	

	public void ImpostaPanelloLaterale() {
		panelloLaterale = new JPanel();
		panelloLaterale.setBackground(getUtilità().bg);
		panelloLaterale.setPreferredSize(new Dimension(150, 10));
		getContenitoreDati().add(panelloLaterale, BorderLayout.EAST);
		panelloLaterale.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panelloLaterale.setLayout(new BoxLayout(panelloLaterale, BoxLayout.Y_AXIS));
		
		labelPrezzo = new JLabel("15,20\u20AC");
		panelloLaterale.add(labelPrezzo);
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPrezzo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		labelQuantità = new JLabel("Quantita: 10");
		labelQuantità.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelQuantità.setFont(getUtilità().arialS);
		labelQuantità.setPreferredSize(new Dimension(49, 15));
		panelloLaterale.add(labelQuantità);
		
		btnRimuovi = new JButton("X");
		btnRimuovi.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelloLaterale.add(btnRimuovi);
		btnRimuovi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRimuovi.setBackground(getUtilità().redBtn);
		btnRimuovi.setMargin(new Insets(20, 20, 20, 20));
		btnRimuovi.setPreferredSize(new Dimension(10, 10));
	}
}
