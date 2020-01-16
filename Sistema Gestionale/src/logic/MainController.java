package logic;

import gui.*;
import java.util.ArrayList;

import gui.*;

public class MainController {

	private static ArrayList<String> categoria = new ArrayList<String>();
	private static ArrayList<String> marca = new ArrayList<String>();
	private static ArrayList<String> colore = new ArrayList<String>();
	private static ArrayList<String> taglia = new ArrayList<String>();
	
	public MainController() {
		SwingController sc = new SwingController(this);
		sc.Inizializza();
		
		InizializzaArrayList();
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
