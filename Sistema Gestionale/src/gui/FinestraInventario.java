package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import logic.Articolo;
import logic.Controller;
import java.awt.Component;

public class FinestraInventario extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private Style style;
	
	private ArrayList<Articolo> articoliInMagazzino = new ArrayList<Articolo>();
	
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
	
	private JButton btnRefresh;
	private JButton btnAggiungi;
	private JButton btnRifornimenti;
	private JButton btnIndietro;

	private Component horizontalStrut;
	//**************************************************************************************
	
	public FinestraInventario(Controller controller) {
		
		this.controller = controller;
		style = new Style();
		
		ImpostaFinestra();
		ImpostaPannelloSuperiore();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		RiempiInventario();
		
	}
	
	public void AggiungiListener() {
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RiempiInventario();
			}
			
		});
		
		btnRifornimenti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraInventario.this, controller.getFinestraRifornimenti());
			}
			
		});
		
		btnAggiungi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraInventario.this, controller.getFinestraAggiuntaArticolo());
			}
			
		});

		btnIndietro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.CambiaFrame(FinestraInventario.this, controller.getHomePage());
			}
			
		});

	}
	
	public void RiempiInventario() {
		
		SvuotaInventario();
		articoliInMagazzino = controller.getArticoliByFiltro(CreaFiltro());
		
		for (Articolo articolo : articoliInMagazzino) {
			
			ContenitoreInventario contenitore = new ContenitoreInventario(controller, this);
			contenitore.InserisciDati(articolo);
			AggiungiContenitore(contenitore);
			
		}
		
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public void SvuotaInventario() {
		
		pannelloCentrale.removeAll();
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}

	public void AggiungiContenitore(ContenitoreInventario contenitore) {
		
        pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
        pannelloCentrale.add(contenitore);
        
	}
	
	//Crea un filtro del tipo {categoria, marca, taglia, colore} che viene poi convertito in un where statement da ArticoloDAO
	public ArrayList<String> CreaFiltro() {
		
		ArrayList<String> filtro = new ArrayList<String>();
		
		filtro.add(0, boxCategoria.getSelectedItem().toString());

		filtro.add(1, boxMarca.getSelectedItem().toString());

		filtro.add(2, boxTaglia.getSelectedItem().toString());

	
		if(btnGroup.getSelection() == null) {
			filtro.add(3, "COLORE");
		} else {
			
		if(tglbtnBlack.isSelected()) 
			filtro.add(3, "NERO");
		
		if(tglbtnWhite.isSelected()) 
			filtro.add(3, "BIANCO");
		
		if(tglbtnRed.isSelected()) 
			filtro.add(3, "ROSSO");
		
		if(tglbtnGreen.isSelected()) 
			filtro.add(3, "VERDE");
		
		if(tglbtnBlue.isSelected()) 
			filtro.add(3, "BLU");
		
		}
		
		
		return filtro;
		
	}
	
	public void ImpostaFinestra() {
		
		setTitle("Inventario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(style.bg);
		setContentPane(contentPane);
		
	}
	
	public void ImpostaPannelloSuperiore() {
		
		pannelloSuperiore = new JPanel();
		pannelloSuperiore.setBackground(style.bg);
		contentPane.add(pannelloSuperiore, BorderLayout.NORTH);
		
		boxCategoria = new JComboBox(controller.getCategoria().toArray());
		boxCategoria.setPreferredSize(new Dimension(150, 25));
		pannelloSuperiore.add(boxCategoria);
		
		btnGroup = new ButtonGroup();
		
		tglbtnBlack = new JToggleButton();
		tglbtnBlack.setPreferredSize(new Dimension(25, 25));
		tglbtnBlack.setBackground(style.black);
		btnGroup.add(tglbtnBlack);
		pannelloSuperiore.add(tglbtnBlack);
		
		tglbtnWhite = new JToggleButton();
		tglbtnWhite.setPreferredSize(new Dimension(25, 25));
		tglbtnWhite.setBackground(style.white);
		btnGroup.add(tglbtnWhite);
		pannelloSuperiore.add(tglbtnWhite);
		
		tglbtnRed = new JToggleButton();
		tglbtnRed.setPreferredSize(new Dimension(25, 25));
		tglbtnRed.setBackground(style.redBtn);
		btnGroup.add(tglbtnRed);
		pannelloSuperiore.add(tglbtnRed);
		
		tglbtnGreen = new JToggleButton();
		tglbtnGreen.setPreferredSize(new Dimension(25, 25));
		tglbtnGreen.setBackground(style.greenBtn);
		btnGroup.add(tglbtnGreen);
		pannelloSuperiore.add(tglbtnGreen);
		
		tglbtnBlue = new JToggleButton();
		tglbtnBlue.setPreferredSize(new Dimension(25, 25));
		tglbtnBlue.setBackground(style.blueBtn);
		btnGroup.add(tglbtnBlue);
		pannelloSuperiore.add(tglbtnBlue);
		
		boxMarca = new JComboBox(controller.getMarca().toArray());
		boxMarca.setPreferredSize(new Dimension(150, 25));
		pannelloSuperiore.add(boxMarca);
		
		boxTaglia = new JComboBox(controller.getTaglia().toArray());
		boxTaglia.setPreferredSize(new Dimension(150, 25));
		pannelloSuperiore.add(boxTaglia);
		
		btnRefresh = new JButton("");
		btnRefresh.setToolTipText("rinfresca filtro");
		btnRefresh.setIcon(style.refreshIcon);
		btnRefresh.setMargin(new Insets(5, 5, 5, 5));
		pannelloSuperiore.add(btnRefresh);
		
		style.changeFont(pannelloSuperiore, style.defaultS);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		pannelloCentrale = new JPanel();
		pannelloCentrale.setBackground(style.bg);
		getContentPane().add(pannelloCentrale, BorderLayout.CENTER);
		
		scroll = new JScrollPane(pannelloCentrale);
		scroll.setBackground(style.bg);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));

        
	}
	
	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(style.bg);
		contentPane.add(pannelloInferiore, BorderLayout.SOUTH);
		
		horizontalStrut = Box.createHorizontalStrut(600);
		pannelloInferiore.add(horizontalStrut);
		
		btnAggiungi = new JButton("Aggiungi Merce");
		btnAggiungi.setToolTipText("aggiungi un nuovo tipo di articolo in inventario");
		btnAggiungi.setMargin(new Insets(5, 5, 5, 5));
		btnAggiungi.setBackground(style.greenBtn);
		btnAggiungi.setIcon(style.addIcon);
		pannelloInferiore.add(btnAggiungi);
		
		btnRifornimenti = new JButton("Rifornimenti");
		btnRifornimenti.setToolTipText("apri finestra rifornimenti");
		btnRifornimenti.setMargin(new Insets(5, 5, 5, 5));
		btnRifornimenti.setBackground(style.greenBtn);
		btnRifornimenti.setIcon(style.inventoryIcon);
		pannelloInferiore.add(btnRifornimenti);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setToolTipText("torna a home page");
		btnIndietro.setMargin(new Insets(5, 5, 5, 5));
		btnIndietro.setBackground(style.redBtn);
		btnIndietro.setIcon(style.backIcon);
		pannelloInferiore.add(btnIndietro);
		
		style.changeFont(pannelloInferiore, style.defaultL);
		
	}
}
