package gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.FlowLayout;

public class HomePageFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
		
	private SwingController sc;
	private Utilities utilities;
	
	private JButton btnVendite;
	private JButton btnInventario;
	private JButton btnStatistiche;
	private JButton btnEsci;
	private JPanel footer;
	
	public HomePageFrame(SwingController sc) {
		//Reference a SwingController
		this.sc = sc;
		utilities = new Utilities();
		
		ImpostaFinestra();
		ImpostaFooter();
		AggiungiListener();
		ImpostaFooter();
	}
	
	public void AggiungiListener() {
		
		btnVendite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sc.CambiaFrame(HomePageFrame.this, sc.getVendite());
			}});
		
		btnEsci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
		
	}
	
	public void ImpostaFinestra() {
		setTitle("Home Page");
		setBounds(100, 100, 850, 478);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(utilities.bg);
		getContentPane().setLayout(null);
		
		btnVendite = new JButton("VENDITE");
		btnVendite.setBounds(193, 40, 170, 75);
		btnVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendite.setFont(utilities.arialM);
		btnVendite.setForeground(utilities.fg);
		btnVendite.setBackground(utilities.greenBtn);
		getContentPane().add(btnVendite);
		
		btnInventario = new JButton("INVENTARIO");
		btnInventario.setBounds(476, 40, 170, 75);
		btnInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInventario.setFont(utilities.arialM);
		btnInventario.setForeground(utilities.fg);
		btnInventario.setBackground(utilities.greenBtn);
		getContentPane().add(btnInventario);
		
		btnStatistiche = new JButton("STATISTICHE");
		btnStatistiche.setBounds(193, 209, 170, 75);
		btnStatistiche.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStatistiche.setFont(utilities.arialM);
		btnStatistiche.setForeground(utilities.fg);
		btnStatistiche.setBackground(utilities.blueBtn);
		getContentPane().add(btnStatistiche);
		
		btnEsci = new JButton("ESCI");
		btnEsci.setBounds(476, 209, 170, 75);
		btnEsci.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEsci.setFont(utilities.arialM);
		btnEsci.setForeground(utilities.fg);
		btnEsci.setBackground(utilities.redBtn);
		getContentPane().add(btnEsci);
		
		
	}
	
	public void ImpostaFooter() {
		footer = new JPanel();
		footer.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.GRAY));
		footer.setBackground(utilities.bg);
		footer.setBounds(0, 339, 834, 100);
		getContentPane().add(footer);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
}
