package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ScrollPaneConstants;
import logic.Articolo;
import logic.ComposizioneTransazione;
import logic.Controller;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class ContenutoTransazione extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private Style style;
	private String tipoTransazione;
	
	private ArrayList<ContenitoreTransazione> contenitori;
	
	private JPanel pannelloCentrale;
	private JPanel pannelloInferiore;
	private JScrollPane scroll;
		
	private JButton btnApplica;
	private JButton btnAnnulla;
	private JButton btnAdd;

	private JComboBox boxFornitore;
	
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;

//**************************************************************************************
	
	public ContenutoTransazione(Controller controller, String tipoTransazione){
		
		this.controller = controller;
		//tipoTransazione può essere "vendita" o "rifornimento"
		this.tipoTransazione = tipoTransazione;
		style = new Style();
		contenitori = new ArrayList<ContenitoreTransazione>();
		
		ImpostaFinestra();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		
	}
	
	
	public void AggiungiArticolo(Articolo articolo, int quantità) {
		
		ContenitoreTransazione contenitore = new ContenitoreTransazione(controller, this);

		contenitore.InserisciDati(articolo);
		contenitore.InserisciQuantità(quantità);
		
		contenitori.add(contenitore);
		pannelloCentrale.add(contenitore);
		pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public void RimuoviArticolo(ContenitoreTransazione contenitoreDaRimuovere) {
		
		contenitori.remove(contenitoreDaRimuovere);
		pannelloCentrale.remove(contenitoreDaRimuovere);
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public void CreaNuovaVendita() {
		
	}
	
	public void CreaNuovoRifornimento() {
		
		LocalDate date = LocalDate.now();
		String partitaIVA = controller.getPartitaByNome(boxFornitore.getSelectedItem().toString());
		
		controller.NuovoRifornimento(date, partitaIVA);
		
		for (ContenitoreTransazione contenitore : contenitori) {
			
			controller.NuovaComposizioneTransazione(contenitore.getDatiComposizione());
			
		}
		
	}
	
	
	public void AggiungiListener() {
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ContenutoTransazione.this.setEnabled(false);
				Catalogo catalogo = new Catalogo(controller, ContenutoTransazione.this);
			}
			
		});
		
		btnApplica.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(tipoTransazione.equals("vendita")) {
					CreaNuovaVendita();
				} else {
					CreaNuovoRifornimento();
				}
				
			}
			
		});
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.CambiaFrame(ContenutoTransazione.this, controller.getFinestraVendite());
			}
			
		});
		
		
		
	}

	
	public void ImpostaFinestra() {
		
		if(tipoTransazione == "vendita")
			setTitle("Contenuto Vendita");
		else
			setTitle("Contenuto Rifornimento");
			
		setBounds(100, 100, 1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(style.bg);
		getContentPane().setBackground(style.bg);
		
	}
	
	public void ImpostaPannelloCentrale() {
		
		pannelloCentrale = new JPanel();
		pannelloCentrale.setBackground(style.bg);
		getContentPane().add(pannelloCentrale, BorderLayout.CENTER);
		
		scroll = new JScrollPane(pannelloCentrale);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scroll, BorderLayout.CENTER);
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));
 
        pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
        
	}
	
	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(style.bg);
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		btnAdd = new JButton();
		btnAdd.setMargin(new Insets(5, 5, 5, 5));
		btnAdd.setFont(style.defaultM);
		btnAdd.setBackground(style.greenBtn);
		btnAdd.setIcon(style.addIcon);
		pannelloInferiore.add(btnAdd);
		
		if(tipoTransazione.equals("rifornimento")) {
			
			horizontalStrut = Box.createHorizontalStrut(200);
			pannelloInferiore.add(horizontalStrut);
		
			boxFornitore = new JComboBox(controller.getFornitori().toArray());
			boxFornitore.setPreferredSize(new Dimension(200, 25));
			boxFornitore.setFont(style.defaultS);
			pannelloInferiore.add(boxFornitore);
			
			horizontalStrut_1 = Box.createHorizontalStrut(200);
			pannelloInferiore.add(horizontalStrut_1);
			
		}
		else {
			
			horizontalStrut = Box.createHorizontalStrut(600);
			pannelloInferiore.add(horizontalStrut);
			
		}
		
		btnApplica = new JButton("APPLICA");
		btnApplica.setFont(style.defaultM);
		btnApplica.setBackground(style.greenBtn);
		btnApplica.setMargin(new Insets(5, 5, 5, 5));
		btnApplica.setIcon(style.saveIcon);
		pannelloInferiore.add(btnApplica);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		pannelloInferiore.add(horizontalStrut_2);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBackground(style.redBtn);
		btnAnnulla.setFont(style.defaultM);
		btnAnnulla.setMargin(new Insets(5, 5, 5, 5));
		btnAnnulla.setIcon(style.backIcon);
		pannelloInferiore.add(btnAnnulla);
		
	}
	
	public Controller getController() {
		return controller;
	}
	
	public String getTipoTransazione() {
		return tipoTransazione;
	}
}
