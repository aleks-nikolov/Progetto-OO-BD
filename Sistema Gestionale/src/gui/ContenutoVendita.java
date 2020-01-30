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
	private Style style;
	
	private JPanel pannelloCentrale;
	private JPanel pannelloInferiore;
	private JScrollPane scroll;
		
	private JButton btnApplica;
	private JButton btnAnnulla;
	private JButton btnAdd;

	private Component horizontalStrut;
	private Component horizontalStrut_1;

//**************************************************************************************
	
	public ContenutoVendita(Controller controller){
		
		this.controller = controller;
		style = new Style();
		
		ImpostaFinestra();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		
	}
	
	public void AggiungiListener() {
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(ContenutoVendita.this, controller.getFinestraVendite());
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
		
		Contenitore_vendita temp = new Contenitore_vendita(controller, this);
		pannelloCentrale.add(temp);
		pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public void RimuoviArticolo(Contenitore_vendita contenitoreDaRimuovere) {
		
		pannelloCentrale.remove(contenitoreDaRimuovere);
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public void ImpostaFinestra() {
		
		setTitle("Contenuto Vendita");
		setBounds(100, 100, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(style.bg);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		pannelloCentrale = new JPanel();
		pannelloCentrale.setBackground(style.bg);
		getContentPane().add(pannelloCentrale, BorderLayout.CENTER);
		
		scroll = new JScrollPane(pannelloCentrale);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));
        
        pannelloCentrale.add(new Contenitore_vendita(controller, this));
        pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
        
	}
	
	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(style.bg);
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		btnAdd = new JButton("+");
		btnAdd.setMargin(new Insets(10, 20, 10, 20));
		btnAdd.setFont(style.defaultM);
		btnAdd.setBackground(style.greenBtn);
		pannelloInferiore.add(btnAdd);
		
		horizontalStrut = Box.createHorizontalStrut(600);
		pannelloInferiore.add(horizontalStrut);
		
		btnApplica = new JButton("APPLICA");
		btnApplica.setFont(style.defaultM);
		btnApplica.setBackground(style.greenBtn);
		btnApplica.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnApplica);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		pannelloInferiore.add(horizontalStrut_1);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBackground(style.redBtn);
		btnAnnulla.setFont(style.defaultM);
		btnAnnulla.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnAnnulla);
		
	}
	
	public Controller getController() {
		return controller;
	}
}
