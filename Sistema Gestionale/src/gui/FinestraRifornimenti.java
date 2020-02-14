package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logic.Controller;

public class FinestraRifornimenti extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private Style style;
	
	ArrayList<ArrayList<String>> datiRifornimenti = new ArrayList<ArrayList<String>>();
	
	//Definizione componenti grafici
	private JPanel pannelloSuperiore;
	private JScrollPane scrollPane;
	private JPanel pannelloInferiore;
	
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JButton btnAggiungi;
	private JButton btnIndietro;
	
	private DefaultTableModel tableData;
	private JTable table;
	private Component upperHorizontalStrut;
	private Component lowerHorizontalStrut;

//**************************************************************************************
	
	public FinestraRifornimenti(Controller controller) {
		
		//Reference a SwingController
		this.controller = controller;
		style = new Style();
		
		ImpostaFinestra();
		ImpostaPannelloSuperiore();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		RiempiTabella();
		
	}
	
	public void AggiungiListener() {
		
		btnNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraRifornimenti.this, controller.getContenutoRifornimento());
			}
			
		});
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RiempiTabella();
			}
			
		});
		
		btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraRifornimenti.this, controller.getFinestraAggiuntaFornitore());
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.EliminaTransazioneByCodice(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 1)));
			}
			
		});
		
		btnIndietro.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(FinestraRifornimenti.this, controller.getFinestraInventario());
			}
			
		});
			
	}
	
	public void RiempiTabella() {
		
		tableData.setRowCount(0);
		
		datiRifornimenti = controller.getRifornimenti();
		
		for(ArrayList<String> datiRifornimento : datiRifornimenti) {
			tableData.addRow(new Object[] {datiRifornimento.get(0), datiRifornimento.get(1), datiRifornimento.get(2), datiRifornimento.get(3), datiRifornimento.get(4)});
		}
		
	}
	
	
	public void ImpostaFinestra() {
		
		setTitle("Rifornimenti");
		setBounds(100, 100, 1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(style.bg);
		
	}
	
	public void ImpostaPannelloSuperiore() {
		
		pannelloSuperiore = new JPanel();
		pannelloSuperiore.setBorder(new LineBorder(style.border1, 2));
		pannelloSuperiore.setBackground(style.bg);
		getContentPane().add(pannelloSuperiore, BorderLayout.NORTH);
		
		btnNew = new JButton();
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setMargin(new Insets(5, 5, 5, 5));
		btnNew.setForeground(style.fg);
		btnNew.setBackground(style.greenBtn);
		btnNew.setIcon(style.addIcon);
		pannelloSuperiore.add(btnNew);
		
		btnDelete = new JButton();
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setMargin(new Insets(5, 5, 5, 5));
		btnDelete.setForeground(style.fg);
		btnDelete.setBackground(style.redBtn);
		btnDelete.setIcon(style.deleteIcon);
		pannelloSuperiore.add(btnDelete);
		
		upperHorizontalStrut = Box.createHorizontalStrut(700);
		pannelloSuperiore.add(upperHorizontalStrut);
		
		btnRefresh = new JButton("");
		btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefresh.setForeground(style.fg);
		btnRefresh.setBackground(new Color(255, 255, 255));
		btnRefresh.setMargin(new Insets(5, 5, 5, 5));
		btnRefresh.setIcon(style.refreshIcon);
		pannelloSuperiore.add(btnRefresh);
		style.changeFont(pannelloSuperiore, style.defaultS);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		tableData = new DefaultTableModel();
		tableData.setColumnIdentifiers(new Object[] {"ID", "Contenuto", "Fornitore", "Data", "Costo"});
		table = new JTable(tableData);
		table.setBackground(style.bg);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(style.bg);;
		scrollPane.setBorder(new LineBorder(style.border1, 2));
		
		//Centra i dati della tabella in ogni cella
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for(int i = 0; i < table.getColumnCount(); i++){
	         table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.getColumnModel().getColumn(1).setPreferredWidth(700);
		table.setRowHeight(30);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(style.bg);
		pannelloInferiore.setBorder(new LineBorder(style.border1, 2));
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		pannelloInferiore.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lowerHorizontalStrut = Box.createHorizontalStrut(500);
		pannelloInferiore.add(lowerHorizontalStrut);
		
		btnAggiungi = new JButton("AGGIUNGI FORNITORE");
		btnAggiungi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAggiungi.setFont(style.defaultS);
		btnAggiungi.setForeground(style.fg);
		btnAggiungi.setBackground(style.greenBtn);
		btnAggiungi.setMargin(new Insets(5, 5, 5, 5));
		btnAggiungi.setIcon(style.addIcon);
		pannelloInferiore.add(btnAggiungi);
		
		btnIndietro = new JButton("INDIETRO");
		btnIndietro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIndietro.setFont(style.defaultS);
		btnIndietro.setForeground(style.fg);
		btnIndietro.setBackground(style.redBtn);
		btnIndietro.setMargin(new Insets(5, 5, 5, 5));
		btnIndietro.setIcon(style.backIcon);
		pannelloInferiore.add(btnIndietro);
		
	}

}
