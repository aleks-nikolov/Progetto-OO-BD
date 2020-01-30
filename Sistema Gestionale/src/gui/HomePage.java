package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import logic.Controller;

import java.awt.FlowLayout;

public class HomePage extends JFrame {
	
	private static final long serialVersionUID = 1L;
		
	private Controller controller;
	private Style style;
	
	//Definizione componenti grafici
	private JButton btnVendite;
	private JButton btnInventario;
	private JButton btnStatistiche;
	private JButton btnEsci;
	
	private JPanel footer;
	
//**************************************************************************************
	
	public HomePage(Controller controller) {
		//Reference a SwingController
		this.controller = controller;
		style = new Style();
		
		ImpostaFinestra();
		ImpostaFooter();
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
		setBounds(100, 100, 850, 478);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(style.bg);
		getContentPane().setLayout(null);
		
		btnVendite = new JButton("VENDITE");
		btnVendite.setBounds(193, 40, 170, 75);
		btnVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendite.setForeground(style.fg);
		btnVendite.setBackground(style.greenBtn);
		getContentPane().add(btnVendite);
		
		btnInventario = new JButton("INVENTARIO");
		btnInventario.setBounds(476, 40, 170, 75);
		btnInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInventario.setForeground(style.fg);
		btnInventario.setBackground(style.greenBtn);
		getContentPane().add(btnInventario);
		
		btnStatistiche = new JButton("STATISTICHE");
		btnStatistiche.setBounds(193, 209, 170, 75);
		btnStatistiche.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStatistiche.setForeground(style.fg);
		btnStatistiche.setBackground(style.blueBtn);
		getContentPane().add(btnStatistiche);
		
		btnEsci = new JButton("ESCI");
		btnEsci.setBounds(476, 209, 170, 75);
		btnEsci.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEsci.setForeground(style.fg);
		btnEsci.setBackground(style.redBtn);
		getContentPane().add(btnEsci);
		
		style.changeFont(getContentPane(), style.defaultM);
	}
	
	public void ImpostaFooter() {
		
		footer = new JPanel();
		footer.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.GRAY));
		footer.setBackground(style.bg);
		footer.setBounds(0, 339, 834, 100);
		getContentPane().add(footer);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
	}
}
