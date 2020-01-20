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
import java.awt.Cursor;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import logic.Controller;

public class FinestraVendite extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private Utilities utilities;
	
	//Definizione componenti grafici
	private JPanel pannelloSuperiore;
	private JScrollPane scrollPane;
	private JPanel pannelloInferiore;
	
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JButton btnIndietro;
	
	private JTable table;
	private Component upperHorizontalStrut;
	private Component lowerHorizontalStrut;

//**************************************************************************************
	
	public FinestraVendite(Controller controller) {
		
		//Reference a SwingController
		this.controller = controller;
		utilities = new Utilities();
		
		ImpostaFinestra();
		ImpostaPannelloSuperiore();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		
	}
	
	public void AggiungiListener() {
		
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraVendite.this, controller.getContenutoVendita());
			}
			
		});
		
		btnIndietro.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(FinestraVendite.this, controller.getHomePage());
			}
			
		});
			
	}

	
	public void ImpostaFinestra() {
		
		setTitle("Vendite");
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(utilities.bg);
		
	}
	
	public void ImpostaPannelloSuperiore() {
		
		pannelloSuperiore = new JPanel();
		pannelloSuperiore.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		getContentPane().add(pannelloSuperiore, BorderLayout.NORTH);
		
		btnNew = new JButton("+");
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setMargin(new Insets(10, 15, 10, 15));
		btnNew.setForeground(utilities.fg);
		btnNew.setBackground(utilities.greenBtn);
		pannelloSuperiore.add(btnNew);
		
		btnDelete = new JButton("-");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setMargin(new Insets(10, 15, 10, 15));
		btnDelete.setForeground(utilities.fg);
		btnDelete.setBackground(utilities.redBtn);
		pannelloSuperiore.add(btnDelete);
		
		btnRefresh = new JButton("REFRESH");
		btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefresh.setForeground(utilities.fg);
		btnRefresh.setBackground(new Color(255, 255, 255));
		btnRefresh.setMargin(new Insets(10, 10, 10, 10));
		pannelloSuperiore.add(btnRefresh);
		
		upperHorizontalStrut = Box.createHorizontalStrut(700);
		pannelloSuperiore.add(upperHorizontalStrut);
		utilities.changeFont(pannelloSuperiore, utilities.arialS);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		DefaultTableModel tableData = new DefaultTableModel();
		tableData.setColumnIdentifiers(new Object[] {"ID", "Contenuto", "Data", "Costo"});
		table = new JTable(tableData);
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		pannelloInferiore.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lowerHorizontalStrut = Box.createHorizontalStrut(700);
		pannelloInferiore.add(lowerHorizontalStrut);
		
		btnIndietro = new JButton("INDIETRO");
		btnIndietro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIndietro.setFont(utilities.arialS);
		btnIndietro.setForeground(utilities.fg);
		btnIndietro.setBackground(utilities.redBtn);
		btnIndietro.setMargin(new Insets(10, 20, 10, 20));
		pannelloInferiore.add(btnIndietro);
		
	}

}
