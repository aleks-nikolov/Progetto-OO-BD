package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;

public class HomePage extends JFrame {
	
	private static final long serialVersionUID = 1L;
		
	private SwingController sc;
	
	private JButton btnVendite;
	private JButton btnInventario;
	private JButton btnStatistiche;
	private JButton btnEsci;
	
	public HomePage(SwingController sc) {
		//Reference a SwingController
		this.sc = sc;
		
		//Impostazione finestra
		setTitle("Home Page");
		setBounds(100, 100, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(220, 220, 220));
		getContentPane().setLayout(null);
		
		//Creazione bottoni
		btnVendite = new JButton("VENDITE");
		btnVendite.setBounds(171, 96, 160, 80);
		btnVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendite.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnVendite.setForeground(new Color(0, 0, 0));
		btnVendite.setBackground(new Color(60, 179, 113));
		getContentPane().add(btnVendite);
		
		btnInventario = new JButton("INVENTARIO");
		btnInventario.setBounds(456, 96, 160, 80);
		btnInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInventario.setForeground(Color.BLACK);
		btnInventario.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnInventario.setBackground(new Color(60, 179, 113));
		getContentPane().add(btnInventario);
		
		btnStatistiche = new JButton("STATISTICHE");
		btnStatistiche.setBounds(171, 305, 160, 80);
		btnStatistiche.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStatistiche.setForeground(Color.BLACK);
		btnStatistiche.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnStatistiche.setBackground(new Color(100, 149, 237));
		getContentPane().add(btnStatistiche);
		
		btnEsci = new JButton("ESCI");
		btnEsci.setBounds(456, 305, 160, 80);
		btnEsci.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEsci.setForeground(Color.BLACK);
		btnEsci.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnEsci.setBackground(new Color(178, 34, 34));
		getContentPane().add(btnEsci);
		
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		btnVendite.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				sc.CambiaFrame(HomePage.this, sc.getFinestraVendite());
			}});
		
		btnEsci.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
		
	}
	
	
}
