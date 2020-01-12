package gui;

import javax.swing.JFrame;

public class SwingController {
	
	private HomePage home;
	private FinestraVendite finestraVendite;

	
	public void Inizializza() {
		//Inizializza e apre la home page
		home = new HomePage(this);
		home.setVisible(true);
		
		//Inizializza le altre finestre
		finestraVendite = new FinestraVendite(this);
	}

	public void CambiaFrame(JFrame frameDaChiudere, JFrame frameDaMostrare) {
		frameDaChiudere.setVisible(false);
		frameDaMostrare.setVisible(true);
	}
	
	public HomePage getHomePage() {
		return home;
	}
	public FinestraVendite getFinestraVendite() {
		return finestraVendite;
	}

}
