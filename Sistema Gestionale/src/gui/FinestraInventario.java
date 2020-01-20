package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import logic.Controller;
import java.awt.Component;

public class FinestraInventario extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private Utilities utilities;
	
	private JPanel contentPane;
	private JPanel pannelloSuperiore;
	private JPanel pannelloCentrale;
	private JPanel pannelloInferiore;
	private JScrollPane scroll;
	
	private JComboBox boxCategoria;
	private JComboBox boxMarca;
	private JComboBox boxTaglia;
	
	private ButtonGroup btnGroup;
	private JToggleButton tglbtnBlack;
	private JToggleButton tglbtnWhite;
	private JToggleButton tglbtnRed;
	private JToggleButton tglbtnGreen;
	private JToggleButton tglbtnBlue;
	
	private JButton btnAggiungi;
	private JButton btnRifornimenti;
	private JButton btnIndietro;

	private Component horizontalStrut;
	//**************************************************************************************
	
	public FinestraInventario(Controller controller) {
		
		this.controller = controller;
		utilities = new Utilities();
		
		ImpostaFinestra();
		ImpostaPannelloSuperiore();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		
	}
	
	public void AggiungiListener() {
		
		btnIndietro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraInventario.this, controller.getHomePage());
			}
			
		});
		
		btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraInventario.this, controller.getFinestraAggiuntaArticolo());
			}
			
		});

	}
	
	public void ImpostaFinestra() {
		
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	public void ImpostaPannelloSuperiore() {
		
		pannelloSuperiore = new JPanel();
		contentPane.add(pannelloSuperiore, BorderLayout.NORTH);
		
		boxCategoria = new JComboBox(controller.getCategoria().toArray());
		boxCategoria.setPreferredSize(new Dimension(150, 25));
		pannelloSuperiore.add(boxCategoria);
		
		btnGroup = new ButtonGroup();
		
		tglbtnBlack = new JToggleButton();
		tglbtnBlack.setPreferredSize(new Dimension(25, 25));
		tglbtnBlack.setBackground(utilities.black);
		btnGroup.add(tglbtnBlack);
		pannelloSuperiore.add(tglbtnBlack);
		
		tglbtnWhite = new JToggleButton();
		tglbtnWhite.setPreferredSize(new Dimension(25, 25));
		tglbtnWhite.setBackground(utilities.white);
		btnGroup.add(tglbtnWhite);
		pannelloSuperiore.add(tglbtnWhite);
		
		tglbtnRed = new JToggleButton();
		tglbtnRed.setPreferredSize(new Dimension(25, 25));
		tglbtnRed.setBackground(utilities.redBtn);
		btnGroup.add(tglbtnRed);
		pannelloSuperiore.add(tglbtnRed);
		
		tglbtnGreen = new JToggleButton();
		tglbtnGreen.setPreferredSize(new Dimension(25, 25));
		tglbtnGreen.setBackground(utilities.greenBtn);
		btnGroup.add(tglbtnGreen);
		pannelloSuperiore.add(tglbtnGreen);
		
		tglbtnBlue = new JToggleButton();
		tglbtnBlue.setPreferredSize(new Dimension(25, 25));
		tglbtnBlue.setBackground(utilities.blueBtn);
		btnGroup.add(tglbtnBlue);
		pannelloSuperiore.add(tglbtnBlue);
		
		boxMarca = new JComboBox(controller.getMarca().toArray());
		boxMarca.setPreferredSize(new Dimension(150, 25));
		pannelloSuperiore.add(boxMarca);
		
		boxTaglia = new JComboBox(controller.getTaglia().toArray());
		boxTaglia.setPreferredSize(new Dimension(150, 25));
		pannelloSuperiore.add(boxTaglia);
		
		utilities.changeFont(pannelloSuperiore, utilities.arialS);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		pannelloCentrale = new JPanel();
		pannelloCentrale.setBackground(utilities.bg);
		getContentPane().add(pannelloCentrale, BorderLayout.CENTER);
		
		scroll = new JScrollPane(pannelloCentrale);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));
        
        pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
        pannelloCentrale.add(new Contenitore_inventario(controller, this));
        
	}
	
	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		contentPane.add(pannelloInferiore, BorderLayout.SOUTH);
		
		horizontalStrut = Box.createHorizontalStrut(700);
		pannelloInferiore.add(horizontalStrut);
		
		btnAggiungi = new JButton("Aggiungi Merce");
		btnAggiungi.setBackground(utilities.greenBtn);
		pannelloInferiore.add(btnAggiungi);
		
		btnRifornimenti = new JButton("Rifornimenti");
		btnRifornimenti.setBackground(utilities.greenBtn);
		pannelloInferiore.add(btnRifornimenti);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setBackground(utilities.redBtn);
		pannelloInferiore.add(btnIndietro);
		
		utilities.changeFont(pannelloInferiore, utilities.arialL);
		
	}
}
