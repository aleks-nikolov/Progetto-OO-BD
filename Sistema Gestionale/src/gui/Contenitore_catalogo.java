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

public class Contenitore_catalogo extends ContenitoreArticolo {
	
	private static final long serialVersionUID = 1L;

	private ContenutoVendita contenutoVendita;

	private JPanel panelloLaterale;
	private JButton btnAggiungi;
	private JLabel labelPrezzo;
	
	public Contenitore_catalogo(ContenutoVendita cv) {
		super(cv);
		this.contenutoVendita = cv;
		
		ImpostaPanelloLaterale();
		AggiungiListener();
		InserisciDati();
	}

	

	public void AggiungiListener() {
		
		btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
	}
	
	@Override
	public void InserisciDati() {
		super.InserisciDati();
		labelPrezzo.setText(Float.toString(getPrezzoDiListino()));
	}
	
	public void ImpostaPanelloLaterale() {
		panelloLaterale = new JPanel();
		panelloLaterale.setBackground(getUtilities().bg);
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
		
		btnAggiungi = new JButton("+");
		btnAggiungi.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelloLaterale.add(btnAggiungi);
		btnAggiungi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAggiungi.setBackground(getUtilities().greenBtn);
		btnAggiungi.setMargin(new Insets(20, 20, 20, 20));
		btnAggiungi.setPreferredSize(new Dimension(10, 10));
	}
}
