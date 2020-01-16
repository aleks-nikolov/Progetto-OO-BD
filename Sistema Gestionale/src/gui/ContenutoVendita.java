package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ScrollPaneConstants;

import logic.Controller;

import javax.swing.BoxLayout;
import java.awt.Dimension;

public class ContenutoVendita extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private Utilities utilities;
	
	private JButton btnApplica;
	private JButton btnAnnulla;
	private JButton btnAdd;
		
	private JPanel pannelloInferiore;
	private JPanel panel;
	private JScrollPane scroll;
	private Component horizontalStrut;
	private Component horizontalStrut_1;

	
	public ContenutoVendita(Controller controller){
		this.controller = controller;
		utilities = new Utilities();
		
		ImpostaFinestra();
		ImpostaPanelloCentrale();
		ImpostaPanelInferiore();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(ContenutoVendita.this, controller.getVendite());
			}
			
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ContenutoVendita.this.setEnabled(false);
				Catalogo catalogo = new Catalogo(controller, ContenutoVendita.this);
			}
			
		});
	}
	
	public void AggiungiArticolo() {
		Contenitore_vendita temp = new Contenitore_vendita(this);
		panel.add(temp);
		panel.add(Box.createRigidArea(new Dimension(1200, 10)));
		panel.revalidate();
		panel.repaint();
	}
	
	public void RimuoviArticolo(Contenitore_vendita contenitoreDaRimuovere) {
		panel.remove(contenitoreDaRimuovere);
		panel.revalidate();
		panel.repaint();
	}
	
	public void ImpostaFinestra() {
		setTitle("Contenuto Vendita");
		setBounds(100, 100, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(utilities.bg);
	}
	
	public void ImpostaPanelloCentrale() {
		panel = new JPanel();
		panel.setBackground(utilities.bg);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		scroll = new JScrollPane(panel);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new Contenitore_vendita(this));
        panel.add(Box.createRigidArea(new Dimension(1200, 10)));
	}
	
	public void ImpostaPanelInferiore() {
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(utilities.bg);
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		btnAdd = new JButton("+");
		btnAdd.setMargin(new Insets(10, 20, 10, 20));
		btnAdd.setFont(utilities.arialM);
		btnAdd.setBackground(utilities.greenBtn);
		pannelloInferiore.add(btnAdd);
		
		horizontalStrut = Box.createHorizontalStrut(600);
		pannelloInferiore.add(horizontalStrut);
		
		btnApplica = new JButton("APPLICA");
		btnApplica.setFont(utilities.arialM);
		btnApplica.setBackground(utilities.greenBtn);
		btnApplica.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnApplica);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		pannelloInferiore.add(horizontalStrut_1);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBackground(utilities.redBtn);
		btnAnnulla.setFont(utilities.arialM);
		btnAnnulla.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnAnnulla);
	}
	
	public Controller getController() {
		return controller;
	}
}
