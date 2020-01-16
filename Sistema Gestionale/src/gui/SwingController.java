package gui;

import logic.*;

import java.util.ArrayList;

import javax.swing.JFrame;

public class SwingController {
	/* La classe è associata a tutte le finestre e alcuni componenti grafici del programma.
	 * Si occupa del controllo tra i passaggi da un frame all'altro e di
	 *  altre funzionalità dell'interfaccia grafica.*/
	private MainController mc;

	private HomePageFrame home;
	private VenditeFrame finestraVendite;
	private ContenutoVendita contenutoVendita;

	public SwingController(MainController mc) {
		this.mc = mc;
	}
	
	public void Inizializza() {
		//Inizializza e apre la home page
		home = new HomePageFrame(this);
		home.setVisible(true);
		
		//Inizializza le altre finestre
		finestraVendite = new VenditeFrame(this);
		contenutoVendita = new ContenutoVendita(this);
	}

	public void CambiaFrame(JFrame frameDaNascondere, JFrame frameDaMostrare) {
		frameDaNascondere.setVisible(false);
		frameDaMostrare.setVisible(true);
	}
	
	
	public Articolo getArticolo() {
		return mc.getArticolo();
	}
	
	
	//Getter degli oggetti Frame
	public HomePageFrame getHomePage() {
		return home;
	}
	public VenditeFrame getVendite() {
		return finestraVendite;
	}
	public ContenutoVendita getContenutoVendita() {
		return contenutoVendita;
	}
	
	//Getter per la logica
	
	public ArrayList<String> getCategoria() {
		return mc.getCategoria();
	}

	public ArrayList<String> getMarca() {
		return mc.getMarca();
	}
	
	public ArrayList<String> getColore() {
		return mc.getColore();
	}
	
	public ArrayList<String> getTaglia() {
		return mc.getTaglia();
	}
}
