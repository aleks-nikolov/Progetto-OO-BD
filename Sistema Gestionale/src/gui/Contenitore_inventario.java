package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logic.Controller;

public class Contenitore_inventario extends ContenitoreArticolo {
	private static final long serialVersionUID = 1L;

	private Inventario inventario;
	
	private JPanel panelloLaterale;
	private JLabel labelQuantità;
	private JLabel labelPrezzo;
	private JLabel lblPrezzo;
	private JLabel lblQuantitInMagazzino;
	
	public Contenitore_inventario(Controller controller, Inventario inventario) {
		super(controller, inventario);
		this.inventario = inventario;
		
		ImpostaPanelloLaterale();
		InserisciDati();
	}

	@Override
	public void InserisciDati() {
		super.InserisciDati();
		labelPrezzo.setText(Float.toString(getPrezzoDiListino()));
		labelQuantità.setText(Integer.toString(getQuantità()));
	}
	
	public void ImpostaPanelloLaterale() {
		panelloLaterale = new JPanel();
		panelloLaterale.setBackground(getUtilities().bg);
		panelloLaterale.setPreferredSize(new Dimension(150, 10));
		getContenitoreDati().add(panelloLaterale, BorderLayout.EAST);
		panelloLaterale.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		panelloLaterale.setLayout(new BoxLayout(panelloLaterale, BoxLayout.Y_AXIS));
		
		lblPrezzo = new JLabel("PREZZO");
		lblPrezzo.setFont(getUtilities().arialS);
		panelloLaterale.add(lblPrezzo);
		
		labelPrezzo = new JLabel();
		panelloLaterale.add(labelPrezzo);
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPrezzo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		lblQuantitInMagazzino = new JLabel("quantit\u00E0 in magazzino:");
		lblQuantitInMagazzino.setFont(getUtilities().arialS);
		panelloLaterale.add(lblQuantitInMagazzino);
		
		labelQuantità = new JLabel();
		labelQuantità.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelQuantità.setFont(getUtilities().arialS);
		labelQuantità.setPreferredSize(new Dimension(49, 15));
		panelloLaterale.add(labelQuantità);
	}
	
}
