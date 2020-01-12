package gui;

import javax.swing.JFrame;

public class SwingController {
	/* La classe è associata a tutte le finestre possibili del programma.
	 * Si occupa del controllo tra i passaggi da un frame all'altro e di
	 *  qualsiasi funzionalità dell'interfaccia grafica.*/
	
	
	private HomePageFrame home;
	private VenditeFrame finestraVendite;
	private AggiungiArticoliFrame aggiungiArticoli;
	
	public void Inizializza() {
		//Inizializza e apre la home page
		home = new HomePageFrame(this);
		home.setVisible(true);
		
		//Inizializza le altre finestre
		finestraVendite = new VenditeFrame(this);
		aggiungiArticoli = new AggiungiArticoliFrame(this);
	}

	public void CambiaFrame(JFrame frameDaNascondere, JFrame frameDaMostrare) {
		frameDaNascondere.setVisible(false);
		frameDaMostrare.setVisible(true);
	}
	
	
	//Getter degli oggetti Frame
	public HomePageFrame getHomePage() {
		return home;
	}
	public VenditeFrame getVendite() {
		return finestraVendite;
	}
	public AggiungiArticoliFrame getAggiungiArticoli() {
		return aggiungiArticoli;
	}

}
