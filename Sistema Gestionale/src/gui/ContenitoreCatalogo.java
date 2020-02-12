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
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logic.Articolo;
import logic.Controller;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ContenitoreCatalogo extends ContenitoreArticolo {
	
	private static final long serialVersionUID = 1L;

	private Catalogo catalogo;

	private JPanel panelloLaterale;
	private JButton btnAggiungi;
	private JLabel labelPrezzo;
	private JLabel labelQuantità;
	private JPanel panelloQuantità;
	private JComboBox<Integer> boxQuantità;
	
	public ContenitoreCatalogo(Controller controller, Catalogo catalogo) {
		super(controller, catalogo);
		this.catalogo = catalogo;
		
		ImpostaPanelloLaterale();
		AggiungiListener();
	}


	public void AggiungiListener() {
		
		btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				catalogo.contenutoTransazione.AggiungiArticolo(ContenitoreCatalogo.this.getArticolo(), (Integer) boxQuantità.getSelectedItem());
				catalogo.dispatchEvent(new WindowEvent(catalogo, WindowEvent.WINDOW_CLOSING));
			}
			
		});
	}
	
	@Override
	public void InserisciDati(Articolo articolo) {
		super.InserisciDati(articolo);
		if(catalogo.contenutoTransazione.getTipoTransazione().equals("vendita"))
			labelPrezzo.setText(String.format("%.2f", getArticolo().getPrezzoDiListino()) + "€");
		else
			labelPrezzo.setText(String.format("%.2f", getArticolo().getPrezzoMagazzino()) + "€");
	}
	
	public void ImpostaPanelloLaterale() {
		panelloLaterale = new JPanel();
		panelloLaterale.setBackground(getStyle().bg);
		panelloLaterale.setPreferredSize(new Dimension(150, 10));
		getContenitoreDati().add(panelloLaterale, BorderLayout.EAST);
		panelloLaterale.setBorder(new LineBorder(getStyle().border2, 2));
		panelloLaterale.setLayout(new BoxLayout(panelloLaterale, BoxLayout.Y_AXIS));
		
		labelPrezzo = new JLabel();
		panelloLaterale.add(labelPrezzo);
		labelPrezzo.setHorizontalTextPosition(SwingConstants.CENTER);
		labelPrezzo.setHorizontalAlignment(SwingConstants.CENTER);
		labelPrezzo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelPrezzo.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		btnAggiungi = new JButton();
		btnAggiungi.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelloLaterale.add(btnAggiungi);
		btnAggiungi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAggiungi.setBackground(getStyle().greenBtn);
		btnAggiungi.setMargin(new Insets(5, 5, 5, 5));
		btnAggiungi.setIcon(getStyle().addIcon);
		btnAggiungi.setPreferredSize(new Dimension(10, 10));
		
		panelloLaterale.add(Box.createRigidArea(new Dimension(150, 10)));
		
		panelloQuantità = new JPanel();
		panelloQuantità.setBackground(getStyle().bg);
		panelloQuantità.setMaximumSize(new Dimension(150, 50));
		panelloLaterale.add(panelloQuantità);
		
		labelQuantità = new JLabel("Quantit\u00E0");
		labelQuantità.setFont(getStyle().defaultS);
		panelloQuantità.add(labelQuantità);
		labelQuantità.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		boxQuantità = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		boxQuantità.setFont(getStyle().defaultS);
		panelloQuantità.add(boxQuantità);
	}
}
