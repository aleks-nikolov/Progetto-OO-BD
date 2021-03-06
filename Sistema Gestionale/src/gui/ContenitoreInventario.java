package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logic.Articolo;
import logic.Controller;

public class ContenitoreInventario extends ContenitoreArticolo {
	private static final long serialVersionUID = 1L;

	private FinestraInventario inventario;
	
	private JPanel panelloLaterale;
	private JLabel labelQuantità;
	private JLabel labelPrezzo;
	private JLabel lblPrezzo;
	private JLabel lblQuantitInMagazzino;
	
	public ContenitoreInventario(Controller controller, FinestraInventario inventario) {
		super(controller, inventario);
		this.inventario = inventario;
		
		ImpostaPanelloLaterale();
	}

	@Override
	public void InserisciDati(Articolo articolo) {
		super.InserisciDati(articolo);
		labelPrezzo.setText(String.format("%.2f", getArticolo().getPrezzoDiListino()) + "");
		labelQuantità.setText(Integer.toString(getArticolo().getQuantita()));
	}
	
	public void ImpostaPanelloLaterale() {
		panelloLaterale = new JPanel();
		panelloLaterale.setBackground(getStyle().bg);
		panelloLaterale.setPreferredSize(new Dimension(150, 10));
		getContenitoreDati().add(panelloLaterale, BorderLayout.EAST);
		panelloLaterale.setBorder(new LineBorder(getStyle().border2, 2));
		panelloLaterale.setLayout(new BoxLayout(panelloLaterale, BoxLayout.Y_AXIS));
		
		lblPrezzo = new JLabel("PREZZO");
		lblPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPrezzo.setFont(getStyle().defaultS);
		panelloLaterale.add(lblPrezzo);
		
		labelPrezzo = new JLabel();
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelloLaterale.add(labelPrezzo);
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setFont(getStyle().defaultM);
		
		lblQuantitInMagazzino = new JLabel("<html>QUANTITÀ IN<br/> MAGAZZINO</html>");
		lblQuantitInMagazzino.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantitInMagazzino.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuantitInMagazzino.setFont(getStyle().defaultS);
		panelloLaterale.add(lblQuantitInMagazzino);
		
		labelQuantità = new JLabel();
		labelQuantità.setHorizontalAlignment(SwingConstants.CENTER);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelQuantità.setFont(getStyle().defaultM);
		labelQuantità.setPreferredSize(new Dimension(49, 15));
		panelloLaterale.add(labelQuantità);
	}
	
}
