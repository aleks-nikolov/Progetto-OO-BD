package logic;

import gui.*;
import java.util.ArrayList;
import gui.*;

public class MainController {

	private ArrayList<String> categoria = new ArrayList<String>();
	private ArrayList<String> marca = new ArrayList<String>();
	private ArrayList<String> colore = new ArrayList<String>();
	private ArrayList<String> taglia = new ArrayList<String>();
	private ArrayList<Articolo> articoli = new ArrayList<Articolo>();
	
	public MainController() {
		InizializzaArrayList();
		
		SwingController sc = new SwingController(this);
		sc.Inizializza();
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
		
		articoli.add(new Articolo("Maglietta di cotone", "Maglietta leggera di cotone", "Magliette", "Adidas", "XL", "Nero", 15.25f, 0, 5, "123", 'M', "res\\images\\magliette\\adidas_black.png"));
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


}
