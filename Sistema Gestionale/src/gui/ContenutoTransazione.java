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
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ScrollPaneConstants;
import logic.Articolo;
import logic.Controller;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import javax.swing.JLabel;

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
	
	private JLabel lblTotale;
	private JLabel lblValoreTotale;

	private JComboBox boxFornitore;
	
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	
//**************************************************************************************
	
	public ContenutoTransazione(Controller controller, String tipoTransazione){
		
		this.controller = controller;
		//tipoTransazione pu� essere "vendita" o "rifornimento"
		this.tipoTransazione = tipoTransazione;
		style = new Style();
		contenitori = new ArrayList<ContenitoreTransazione>();
		
		ImpostaFinestra();
		ImpostaPannelloCentrale();
		ImpostaPannelloInferiore();
		AggiungiListener();
		
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
				
				RimuoviAllArticoli();
				
				if(tipoTransazione.equals("vendita"))
					controller.CambiaFrame(ContenutoTransazione.this, controller.getFinestraVendite());
				else
					controller.CambiaFrame(ContenutoTransazione.this, controller.getFinestraRifornimenti());

			}
			
		});
		
		btnAnnulla.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(tipoTransazione.equals("vendita"))
					controller.CambiaFrame(ContenutoTransazione.this, controller.getFinestraVendite());
				else
					controller.CambiaFrame(ContenutoTransazione.this, controller.getFinestraRifornimenti());
			}
			
		});
		
	}
	
	public void AggiungiArticolo(Articolo articolo, int quantit�) {
		
		ContenitoreTransazione contenitore = new ContenitoreTransazione(controller, this);

		contenitore.InserisciDati(articolo);
		contenitore.InserisciQuantit�(quantit�);
		
		contenitori.add(contenitore);
		pannelloCentrale.add(contenitore);
		pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
		AggiornaPrezzoTotale();
		
	}
	
	public void RimuoviArticolo(ContenitoreTransazione contenitoreDaRimuovere) {
		
		contenitori.remove(contenitoreDaRimuovere);
		pannelloCentrale.remove(contenitoreDaRimuovere);
		pannelloCentrale.revalidate();
		pannelloCentrale.repaint();
		
	}
	
	public void RimuoviAllArticoli() {
		
		//Si usa un ArrayList ausiliaria per evitare la ConcurrentModificationException
		ArrayList<ContenitoreTransazione> daRimuovere = new ArrayList<ContenitoreTransazione>();
		
		for(ContenitoreTransazione contenitore : contenitori) {
			daRimuovere.add(contenitore);
			pannelloCentrale.remove(contenitore);
			pannelloCentrale.revalidate();
			pannelloCentrale.repaint();
		}
		
		contenitori.removeAll(daRimuovere);
		
	}

	public void AggiornaPrezzoTotale() {
		
		float valoreTotale = 0.0f;
		
		for (ContenitoreTransazione contenitore : contenitori) {
			valoreTotale += (contenitore.getPrezzo() * (float) contenitore.getQuantit�());
		}
		
		lblValoreTotale.setText(String.format("%.2f", valoreTotale) + "�");
		
	}
	
	public void CreaNuovaVendita() {
		
		LocalDate date = LocalDate.now();
		
		controller.NuovaVendita(date);
		
		for (ContenitoreTransazione contenitore : contenitori) {
			controller.NuovaComposizioneTransazione(contenitore.getDatiComposizione());
		}
		
	}
	
	public void CreaNuovoRifornimento() {
		
		LocalDate date = LocalDate.now();
		String partitaIVA = controller.getPartitaByNome(boxFornitore.getSelectedItem().toString());
		
		controller.NuovoRifornimento(date, partitaIVA);
		
		for (ContenitoreTransazione contenitore : contenitori) {
			controller.NuovaComposizioneTransazione(contenitore.getDatiComposizione());
		}
		
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
		scroll.getVerticalScrollBar().setUnitIncrement(20);
        getContentPane().add(scroll, BorderLayout.CENTER);
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.Y_AXIS));
 
        pannelloCentrale.add(Box.createRigidArea(new Dimension(1200, 10)));
        
	}
	
	public void ImpostaPannelloInferiore() {
		
		pannelloInferiore = new JPanel();
		pannelloInferiore.setBackground(style.bg);
		getContentPane().add(pannelloInferiore, BorderLayout.SOUTH);
		
		btnAdd = new JButton();
		btnAdd.setToolTipText("apri catalogo");
		btnAdd.setMargin(new Insets(5, 5, 5, 5));
		btnAdd.setFont(style.defaultM);
		btnAdd.setBackground(style.greenBtn);
		btnAdd.setIcon(style.addIcon);
		btnAdd.setFocusable(false);
		pannelloInferiore.add(btnAdd);
		
		if(tipoTransazione.equals("rifornimento")) {
			
			horizontalStrut = Box.createHorizontalStrut(200);
			pannelloInferiore.add(horizontalStrut);
		
			boxFornitore = new JComboBox<>(controller.getFornitori().toArray());
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
		btnApplica.setToolTipText("salva transazione");
		btnApplica.setFont(style.defaultM);
		btnApplica.setBackground(style.greenBtn);
		btnApplica.setMargin(new Insets(5, 5, 5, 5));
		btnApplica.setIcon(style.saveIcon);
		btnApplica.setFocusable(false);
		pannelloInferiore.add(btnApplica);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		pannelloInferiore.add(horizontalStrut_2);
		
		btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setToolTipText("torna indietro");
		btnAnnulla.setBackground(style.redBtn);
		btnAnnulla.setFont(style.defaultM);
		btnAnnulla.setMargin(new Insets(5, 5, 5, 5));
		btnAnnulla.setIcon(style.backIcon);
		btnAnnulla.setFocusable(false);
		pannelloInferiore.add(btnAnnulla);
		
		horizontalStrut_2 = Box.createHorizontalStrut(10);
		
		lblTotale = new JLabel("TOTALE:");
		lblTotale.setFont(style.defaultM);
		pannelloInferiore.add(lblTotale);
		
		lblValoreTotale = new JLabel("0.00�");
		lblValoreTotale.setFont(style.defaultM);
		pannelloInferiore.add(lblValoreTotale);
		
	}
	
	public Controller getController() {
		return controller;
	}
	
	public String getTipoTransazione() {
		return tipoTransazione;
	}
}
