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

import logic.Articolo;
import logic.Controller;

import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Contenitore_catalogo extends ContenitoreArticolo {
	
	private static final long serialVersionUID = 1L;

	private Catalogo catalogo;

	private JPanel panelloLaterale;
	private JButton btnAggiungi;
	private JLabel labelPrezzo;
	private JLabel labelQuantit�;
	private JPanel panelloQuantit�;
	private JComboBox<Integer> boxQuantit�;
	
	public Contenitore_catalogo(Controller controller, Catalogo catalogo) {
		super(controller, catalogo);
		this.catalogo = catalogo;
		
		ImpostaPanelloLaterale();
		AggiungiListener();
	}

	

	public void AggiungiListener() {
		
		btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				catalogo.contenutoVendita.AggiungiArticolo(Contenitore_catalogo.this.getArticolo());
			}
			
		});
	}
	
	@Override
	public void InserisciDati(Articolo articolo) {
		super.InserisciDati(articolo);
		labelPrezzo.setText(Float.toString(getArticolo().getPrezzo()));
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
		
		btnAggiungi = new JButton();
		btnAggiungi.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelloLaterale.add(btnAggiungi);
		btnAggiungi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAggiungi.setBackground(getStyle().greenBtn);
		btnAggiungi.setMargin(new Insets(5, 5, 5, 5));
		btnAggiungi.setIcon(getStyle().addIcon);
		btnAggiungi.setPreferredSize(new Dimension(10, 10));
		
		panelloLaterale.add(Box.createRigidArea(new Dimension(150, 10)));
		
		panelloQuantit� = new JPanel();
		panelloQuantit�.setBackground(getStyle().bg);
		panelloQuantit�.setMaximumSize(new Dimension(150, 50));
		panelloLaterale.add(panelloQuantit�);
		
		labelQuantit� = new JLabel("Quantit\u00E0");
		labelQuantit�.setFont(getStyle().defaultS);
		panelloQuantit�.add(labelQuantit�);
		labelQuantit�.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		boxQuantit� = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		boxQuantit�.setFont(getStyle().defaultS);
		panelloQuantit�.add(boxQuantit�);
	}
}
