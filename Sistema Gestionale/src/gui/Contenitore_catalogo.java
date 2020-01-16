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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Contenitore_catalogo extends ContenitoreArticolo {
	
	private static final long serialVersionUID = 1L;

	private ContenutoVendita contenutoVendita;

	private JPanel panelloLaterale;
	private JButton btnAggiungi;
	private JLabel labelPrezzo;
	private JLabel labelQuantità;
	private JPanel panelloQuantità;
	private JComboBox<Integer> boxQuantità;
	
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
		
		panelloLaterale.add(Box.createRigidArea(new Dimension(150, 10)));
		
		panelloQuantità = new JPanel();
		panelloQuantità.setBackground(getUtilities().bg);
		panelloQuantità.setMaximumSize(new Dimension(150, 50));
		panelloLaterale.add(panelloQuantità);
		
		labelQuantità = new JLabel("Quantit\u00E0");
		labelQuantità.setFont(getUtilities().arialS);
		panelloQuantità.add(labelQuantità);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		boxQuantità = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		boxQuantità.setFont(getUtilities().arialS);
		panelloQuantità.add(boxQuantità);
	}
}
