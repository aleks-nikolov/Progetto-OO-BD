package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;

import logic.Controller;


public class HomePage extends JFrame {
	
	private static final long serialVersionUID = 1L;
		
	private Controller controller;
	private Style style;
	
	//Definizione componenti grafici
	private JButton btnVendite;
	private JButton btnInventario;
	private JButton btnEsci;
	
//**************************************************************************************
	
	public HomePage(Controller controller) {
		//Reference a SwingController
		this.controller = controller;
		style = new Style();
		
		ImpostaFinestra();
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		btnVendite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(HomePage.this, controller.getFinestraVendite());
			}
			
		});
		
		btnInventario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(HomePage.this, controller.getFinestraInventario());
			}
			
		});
		
		btnEsci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
	}
	
	public void ImpostaFinestra() {
		
		setTitle("Home Page");
		setBounds(700, 250, 500, 478);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(style.bg);
		getContentPane().setLayout(null);
		
		btnVendite = new JButton("VENDITE");
		btnVendite.setBounds(140, 50, 200, 60);
		btnVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendite.setForeground(style.fg);
		btnVendite.setBackground(style.greenBtn);
		btnVendite.setIcon(style.sellsIcon);
		getContentPane().add(btnVendite);
		
		btnInventario = new JButton("INVENTARIO");
		btnInventario.setBounds(140, 180, 200, 60);
		btnInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInventario.setForeground(style.fg);
		btnInventario.setBackground(style.greenBtn);
		btnInventario.setIcon(style.inventoryIcon);
		getContentPane().add(btnInventario);
		
		btnEsci = new JButton("ESCI");
		btnEsci.setBounds(150, 320, 170, 60);
		btnEsci.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEsci.setForeground(style.fg);
		btnEsci.setBackground(style.redBtn);
		btnEsci.setIcon(style.exitIcon);
		getContentPane().add(btnEsci);
		
		style.changeFont(getContentPane(), style.defaultM);
	}
	
}
