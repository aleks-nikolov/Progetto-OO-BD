package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Articolo;
import logic.Controller;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import java.awt.Insets;

public class Catalogo extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	public ContenutoVendita contenutoVendita;
	private Style style;
	
	private ArrayList<Articolo> articoliInCatalogo = new ArrayList<Articolo>();
	
	private JPanel contentPane;
	private JPanel pannelloSuperiore;
	private JPanel pannelloCentrale;
	private JPanel pannelloInferiore;
	
	private JComboBox boxCategoria;
	private JComboBox boxMarca;
	private JComboBox boxTaglia;
	
	private ButtonGroup btnGroup;
	private JToggleButton tglbtnBlack;
	private JToggleButton tglbtnWhite;
	private JToggleButton tglbtnRed;
	private JToggleButton tglbtnGreen;
	private JToggleButton tglbtnBlue;
	
	private JButton btnAnnulla;
	private JButton btnRefresh;

//**************************************************************************************
	
	public Catalogo(Controller controller, ContenutoVendita contenutoVendita) {

		this.controller = controller;
		this.contenutoVendita = contenutoVendita;
		style = new Style();
		
		ImpostaFinestra();
		ImpostaPannelloSuperiore();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		RiempiCatalogo();
		
	}
	
	public void RiempiCatalogo() {
	
		SvuotaCatalogo();
		articoliInCatalogo = controller.getArticoliByFiltro(CreaFiltroSQL());
		
		for (Articolo articolo : articoliInCatalogo) {
			
			ContenitoreCatalogo contenitore = new ContenitoreCatalogo(controller, this);
			contenitore.InserisciDati(articolo);
			AggiungiContenitore(contenitore);
			
		}
		
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public void AggiungiContenitore(ContenitoreCatalogo contenitore) {
		
		pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
        pannelloCentrale.add(contenitore);
        
	}
	
	public void SvuotaCatalogo() {
		
		pannelloCentrale.removeAll();
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public String CreaFiltroSQL() {
		
		String filtro = " WHERE TRUE";
		
		if(boxCategoria.getSelectedItem().toString() != "CATEGORIA") 
			filtro += " AND DA.Categoria = " + boxCategoria.getSelectedItem().toString();

		if(boxMarca.getSelectedItem().toString() != "MARCA") 
			filtro += " AND DA.Marca = " + boxMarca.getSelectedItem().toString();

		if(boxTaglia.getSelectedItem().toString() != "TAGLIA") 
			filtro += " AND A.Taglia = " + boxTaglia.getSelectedItem().toString();

		if(tglbtnBlack.isSelected()) 
			filtro += " AND A.Colore = Nero";
		
		if(tglbtnWhite.isSelected()) 
			filtro += " AND A.Colore = Bianco";
		
		if(tglbtnRed.isSelected()) 
			filtro += " AND A.Colore = Rosso";
		
		if(tglbtnGreen.isSelected()) 
			filtro += " AND A.Colore = Verde";
		
		if(tglbtnBlue.isSelected()) 
			filtro += " AND A.Colore = Blu";
		
		return filtro;
		
	}
	
	public void AggiungiListener() {
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RiempiCatalogo();
			}
			
		});
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contenutoVendita.setEnabled(true);
				controller.ChiudiFrame(Catalogo.this);
			}
			
		});

		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                contenutoVendita.setEnabled(true);
                controller.ChiudiFrame(Catalogo.this);
            }
        });

	}

	
	public void ImpostaFinestra() {
		
		setVisible(true);
		setTitle("Catalogo");
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
		boxCategoria.setFont(style.defaultS);
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
		boxMarca.setFont(style.defaultS);
		pannelloSuperiore.add(boxMarca);
		
		boxTaglia = new JComboBox(controller.getTaglia().toArray());
		boxTaglia.setPreferredSize(new Dimension(150, 25));
		boxTaglia.setFont(style.defaultS);
		pannelloSuperiore.add(boxTaglia);
		
		btnRefresh = new JButton("");
		btnRefresh.setIcon(style.refreshIcon);
		btnRefresh.setMargin(new Insets(5, 5, 5, 5));
		pannelloSuperiore.add(btnRefresh);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		pannelloCentrale = new JPanel();
		pannelloCentrale.setBackground(style.bg);
		getContentPane().add(pannelloCentrale, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(pannelloCentrale);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBackground(style.bg);
        getContentPane().add(scroll, BorderLayout.CENTER);
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));
        
	}

	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(style.bg);
		contentPane.add(pannelloInferiore, BorderLayout.SOUTH);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(style.defaultL);
		btnAnnulla.setBackground(style.redBtn);
		btnAnnulla.setIcon(style.backIcon);
		pannelloInferiore.add(btnAnnulla);
		
	}
}
