package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Cursor;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logic.Controller;

public class FinestraVendite extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private Style style;
	
	ArrayList<ArrayList<String>> datiVendite = new ArrayList<ArrayList<String>>();
	
	//Definizione componenti grafici
	private JPanel pannelloSuperiore;
	private JScrollPane scrollPane;
	private JPanel pannelloInferiore;
	
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnRefresh;
	private JButton btnIndietro;
	
	private DefaultTableModel tableData;
	private JTable table;
	private Component upperHorizontalStrut;
	private Component lowerHorizontalStrut;

//**************************************************************************************
	
	public FinestraVendite(Controller controller) {
		
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
				controller.CambiaFrame(FinestraVendite.this, controller.getContenutoVendita());
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!table.getSelectionModel().isSelectionEmpty()) {
					controller.EliminaTransazioneByCodice(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
					RiempiTabella();
				}
			}
			
		});
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RiempiTabella();
			}
			
		});
		
		btnIndietro.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(FinestraVendite.this, controller.getHomePage());
			}
			
		});
			
	}
	
	public void RiempiTabella() {
		
		tableData.setRowCount(0);
		
		datiVendite = controller.getVendite();
		
		for(ArrayList<String> datiVendita : datiVendite) {
			tableData.addRow(new Object[] {datiVendita.get(0), datiVendita.get(1), datiVendita.get(2), String.format("%.2f", Float.parseFloat(datiVendita.get(3))) + "€"});
		}
		
	}

	public void ImpostaFinestra() {
		
		setTitle("Vendite");
		setBounds(100, 100, 1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(style.bg);
		getContentPane().setBackground(style.bg);
		
	}
	
	public void ImpostaPannelloSuperiore() {
		
		pannelloSuperiore = new JPanel();
		pannelloSuperiore.setBorder(new LineBorder(style.border1, 2));
		pannelloSuperiore.setBackground(style.bg);
		getContentPane().add(pannelloSuperiore, BorderLayout.NORTH);
		
		btnNew = new JButton();
		btnNew.setToolTipText("aggiungi una nuova vendita");
		btnNew.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNew.setMargin(new Insets(5, 5, 5, 5));
		btnNew.setForeground(style.fg);
		btnNew.setBackground(style.greenBtn);
		btnNew.setIcon(style.addIcon);
		btnNew.setFocusable(false);
		pannelloSuperiore.add(btnNew);
		
		btnDelete = new JButton();
		btnDelete.setToolTipText("elimina la vendita selezionata");
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelete.setMargin(new Insets(5, 5, 5, 5));
		btnDelete.setForeground(style.fg);
		btnDelete.setBackground(style.redBtn);
		btnDelete.setIcon(style.deleteIcon);
		btnDelete.setFocusable(false);
		pannelloSuperiore.add(btnDelete);
		
		upperHorizontalStrut = Box.createHorizontalStrut(700);
		pannelloSuperiore.add(upperHorizontalStrut);
		
		btnRefresh = new JButton("");
		btnRefresh.setToolTipText("rinfresca tabella");
		btnRefresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRefresh.setForeground(style.fg);
		btnRefresh.setBackground(style.white);
		btnRefresh.setMargin(new Insets(5, 5, 5, 5));
		btnRefresh.setIcon(style.refreshIcon);
		btnRefresh.setFocusable(false);
		pannelloSuperiore.add(btnRefresh);
		style.changeFont(pannelloSuperiore, style.defaultS);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		tableData = new DefaultTableModel();
		tableData.setColumnIdentifiers(new Object[] {"ID", "Contenuto", "Data", "Costo"});
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
		lowerHorizontalStrut = Box.createHorizontalStrut(700);
		pannelloInferiore.add(lowerHorizontalStrut);
		
		btnIndietro = new JButton("INDIETRO");
		btnIndietro.setToolTipText("torna a home page");
		btnIndietro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnIndietro.setFont(style.defaultM);
		btnIndietro.setForeground(style.fg);
		btnIndietro.setBackground(style.redBtn);
		btnIndietro.setMargin(new Insets(5, 5, 5, 5));
		btnIndietro.setIcon(style.backIcon);
		btnIndietro.setFocusable(false);
		pannelloInferiore.add(btnIndietro);
		
	}

}
