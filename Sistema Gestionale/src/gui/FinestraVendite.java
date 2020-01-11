package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Cursor;

public class FinestraVendite extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private SwingController sc;
	
	//Definizione componenti grafici
	private JPanel pannelloSuperiore;
	private JScrollPane scrollPane;
	private JPanel pannelloInferiore;
	
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JButton btnIndietro;
	
	private JTable table;

	public FinestraVendite(SwingController sc) {
		//Reference a SwingController
		this.sc = sc;
		
		//Impostazione finestra
		setTitle("Vendite");
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Impostazione pannello superiore
		pannelloSuperiore = new JPanel();
		getContentPane().add(pannelloSuperiore, BorderLayout.NORTH);
		btnNew = new JButton("+");
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnNew.setMargin(new Insets(10, 15, 10, 15));
		btnNew.setBackground(new Color(60, 179, 113));
		pannelloSuperiore.add(btnNew);
		btnDelete = new JButton("-");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnDelete.setMargin(new Insets(10, 15, 10, 15));
		btnDelete.setBackground(new Color(165, 42, 42));
		pannelloSuperiore.add(btnDelete);
		btnRefresh = new JButton("REFRESH");
		btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefresh.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnRefresh.setBackground(new Color(255, 255, 255));
		btnRefresh.setMargin(new Insets(10, 10, 10, 10));
		pannelloSuperiore.add(btnRefresh);
		
		//Impostazione pannello centrale e tabella
		table = new JTable();
		scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		//Impostazione pannello inferiore
		pannelloInferiore = new JPanel();
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		pannelloInferiore.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnIndietro = new JButton("INDIETRO");
		btnIndietro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIndietro.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnIndietro.setBackground(new Color(165, 42, 42));
		btnIndietro.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnIndietro);
		
		AggiungiListener();
	}
	
	public void AggiungiListener() {
		
		btnIndietro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				sc.CambiaFrame(FinestraVendite.this, sc.getHomePage());
			}});

	}

}
