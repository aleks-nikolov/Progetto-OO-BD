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
	private Utilit‡Aspetto utilit‡;
	
	private JButton btnVendite;
	private JButton btnInventario;
	private JButton btnStatistiche;
	private JButton btnEsci;
	private JPanel footer;
	
	public HomePageFrame(SwingController sc) {
		//Reference a SwingController
		this.sc = sc;
		utilit‡ = new Utilit‡Aspetto();
		
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
		getContentPane().setBackground(utilit‡.bg);
		getContentPane().setLayout(null);
		
		btnVendite = new JButton("VENDITE");
		btnVendite.setBounds(193, 40, 170, 75);
		btnVendite.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVendite.setFont(utilit‡.arialM);
		btnVendite.setForeground(utilit‡.fg);
		btnVendite.setBackground(utilit‡.greenBtn);
		getContentPane().add(btnVendite);
		
		btnInventario = new JButton("INVENTARIO");
		btnInventario.setBounds(476, 40, 170, 75);
		btnInventario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInventario.setFont(utilit‡.arialM);
		btnInventario.setForeground(utilit‡.fg);
		btnInventario.setBackground(utilit‡.greenBtn);
		getContentPane().add(btnInventario);
		
		btnStatistiche = new JButton("STATISTICHE");
		btnStatistiche.setBounds(193, 209, 170, 75);
		btnStatistiche.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStatistiche.setFont(utilit‡.arialM);
		btnStatistiche.setForeground(utilit‡.fg);
		btnStatistiche.setBackground(utilit‡.blueBtn);
		getContentPane().add(btnStatistiche);
		
		btnEsci = new JButton("ESCI");
		btnEsci.setBounds(476, 209, 170, 75);
		btnEsci.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEsci.setFont(utilit‡.arialM);
		btnEsci.setForeground(utilit‡.fg);
		btnEsci.setBackground(utilit‡.redBtn);
		getContentPane().add(btnEsci);
		
		
	}
	
	public void ImpostaFooter() {
		footer = new JPanel();
		footer.setBorder(new MatteBorder(2, 0, 0, 0, (Color) Color.GRAY));
		footer.setBackground(utilit‡.bg);
		footer.setBounds(0, 339, 834, 100);
		getContentPane().add(footer);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
}
