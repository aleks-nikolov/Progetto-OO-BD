package logic;

import java.util.ArrayList;

import javax.swing.JFrame;

import gui.*;


public class Controller {

	private TransazioneDAO tr = new TransazioneDAO();
	
	private HomePage home;
	private FinestraInventario finestraInventario;
	private FinestraVendite finestraVendite;
	private ContenutoVendita contenutoVendita;
	
	private ArrayList<String> categoria = new ArrayList<String>();
	private ArrayList<String> marca = new ArrayList<String>();
	private ArrayList<String> colore = new ArrayList<String>();
	private ArrayList<String> taglia = new ArrayList<String>();
	private ArrayList<Articolo> articoli = new ArrayList<Articolo>();
	private ArrayList<String> sessi = new ArrayList<String>();
	
	//**************************************************************************************
	
	public Controller() {
		
		tr.connect();
		tr.execution();
		InizializzaArrayList();
		InizializzaFinestre();
		
	}
	
	public void InizializzaFinestre() {
		
		//Inizializza e apre la home page
		home = new HomePage(this);
		home.setVisible(true);
		
		//Inizializza le altre finestre
		finestraVendite = new FinestraVendite(this);
		finestraInventario = new FinestraInventario(this);
		contenutoVendita = new ContenutoVendita(this);
		
	}
	
	public void InizializzaArrayList() {
		categoria.add("CATEGORIA");
		categoria.add("magliette");
		categoria.add("maglioni");
		categoria.add("giacche");
		categoria.add("pantaloni");
		categoria.add("camicie");
		categoria.add("accessori");
		
		marca.add("MARCA");
		marca.add("adidas");
		marca.add("nike");
		marca.add("h&m");
		marca.add("bershka");
		marca.add("piazzaitalia");
		
		colore.add("COLORE");
		colore.add("nero");
		colore.add("bianco");
		colore.add("rosso");
		colore.add("verde");
		colore.add("blu");
		
		taglia.add("TAGLIA");
		taglia.add("XS");
		taglia.add("S");
		taglia.add("M");
		taglia.add("L");
		taglia.add("XL");
		taglia.add("XXL");
		
		sessi.add("M");
		sessi.add("F");
		sessi.add("U");
		
		articoli.add(new Articolo("Maglietta di cotone", "Maglietta leggera di cotone", "Magliette", "Adidas", "XL", "Nero", 15.25f, 0, 5, "123", 'M', "res\\images\\magliette\\adidas_black.png"));
	}
	
	public void CambiaFrame(JFrame frameDaNascondere, JFrame frameDaMostrare) {
		ChiudiFrame(frameDaNascondere);
		frameDaMostrare.setVisible(true);
	}
	
	public void ChiudiFrame(JFrame frameDaChiudere) {
		frameDaChiudere.setVisible(false);
		frameDaChiudere.dispose();
	}
	
	public Articolo getArticolo() {
		Articolo articolo = articoli.get(0);
		
		return articolo;
	}
	
	//Getters e setters
	public ArrayList<String> getCategoria() {
		return categoria;
	}
	public ArrayList<String> getMarca() {
		return marca;
	}
	public ArrayList<String> getColore() {
		return colore;
	}
	public ArrayList<String> getTaglia() {
		return taglia;
	}
	public ArrayList<String> getSesso() {
		return sessi;
	}

	
	public HomePage getHomePage() {
		return home;
	}
	public FinestraVendite getFinestraVendite() {
		return finestraVendite;
	}
	public FinestraInventario getFinestraInventario() {
		return finestraInventario;
	}
	public ContenutoVendita getContenutoVendita() {
		return contenutoVendita;
	}

}
