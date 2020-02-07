package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import gui.*;


public class Controller {

	private String url = "jdbc:postgresql://dumbo.db.elephantsql.com:5432/nwiojydu";
	private String username = "nwiojydu";
	private String pass = "sxUptH8Xh2rWXqBwbZxRuW5kCH74h_Tw";
	
	private Connection conn;

	private ArticoloDAO articoloDAO = new ArticoloDAO();
	private TransazioneDAO transazioneDAO = new TransazioneDAO();
	private ComposizioneTransazioneDAO compTransazioneDAO = new ComposizioneTransazioneDAO();
	private FornitoreDAO fornitoreDAO = new FornitoreDAO();
	
	private HomePage home;
	private FinestraVendite finestraVendite;
	private FinestraInventario finestraInventario;
	private FinestraRifornimenti finestraRifornimenti;
	private ContenutoVendita contenutoVendita;
	private AggiuntaArticolo finestraAggiuntaArticolo;
	private AggiuntaFornitore finestraAggiuntaFornitore;
	
	private ArrayList<String> categoria = new ArrayList<String>();
	private ArrayList<String> marca = new ArrayList<String>();
	private ArrayList<String> colore = new ArrayList<String>();
	private ArrayList<String> taglia = new ArrayList<String>();
	private ArrayList<Articolo> articoli = new ArrayList<Articolo>();
	private ArrayList<String> sessi = new ArrayList<String>();
	
//**************************************************************************************
	
	public Controller() {
		
		ConnectToDatabase();
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
		finestraRifornimenti = new FinestraRifornimenti(this);
		contenutoVendita = new ContenutoVendita(this);
		finestraAggiuntaArticolo = new AggiuntaArticolo(this);
		finestraAggiuntaFornitore = new AggiuntaFornitore(this);

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
		
		sessi.add("SESSO");
		sessi.add("M");
		sessi.add("F");
		sessi.add("U");
		
		articoli.add(new Articolo("123", "456", "Maglietta di cotone", "Maglietta leggera di cotone", "Magliette", "Adidas", "XL", "Nero", 15.25f, 5, "M", "res\\images\\magliette\\adidas_black.png"));
	}
	
	public void ConnectToDatabase() {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver not found");
		}
		
		try {
			conn = DriverManager.getConnection(url, username, pass);
			System.out.println("Connected to database");
		} catch (SQLException e){
			System.out.println("Problem connecting to database");
			e.printStackTrace();
		}
		
	}
	
	public  ArrayList<Transazione> getVendite() {
		return transazioneDAO.getVendite(conn);
	}
	
	public  ArrayList<Transazione> getRifornimenti() {
		return transazioneDAO.getRifornimenti(conn);
	}
	
	public String getContenutoTransazione(String codiceTransazione) {
		
		String contenuto = null;
	
		ArrayList<Articolo> articoli = EstraiArticoliDaComposizione(compTransazioneDAO.getCompTransazioniDi(conn, codiceTransazione));
		
		for(Articolo a: articoli) {
			contenuto += " " + a.getNome() + " x" + a.getQuantità() + "\n";
		}
		
		return contenuto;
		
	}
	
	
    private ArrayList<Articolo> EstraiArticoliDaComposizione(ArrayList<ComposizioneTransazione> compTransazioni) {
    	
		ArrayList<Articolo> articoli = new ArrayList<Articolo>();
		
		for(ComposizioneTransazione comp: compTransazioni) {
			Articolo articolo = articoloDAO.TrovaArticolo(conn, comp.getSKU());
			articoli.add(articolo);
		}
		
		return articoli;
		
	}

    public void NuovoArticolo(Articolo articolo) {
    	articoloDAO.NuovoArticolo(conn, articolo);
    }
    
    public ArrayList<Articolo> getAllArticoli(String filtroSQL) {
    	return articoloDAO.getAllArticoli(conn, filtroSQL);
    }
    
    
    public void NuovoFornitore(Fornitore fornitore) {
    	fornitoreDAO.NuovoFornitore(conn, fornitore);
    }
    
//Metodi GUI
	public void CambiaFrame(JFrame frameDaNascondere, JFrame frameDaMostrare) {
		ChiudiFrame(frameDaNascondere);
		frameDaMostrare.setVisible(true);
	}
	
	public void ChiudiFrame(JFrame frameDaChiudere) {
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
	public FinestraRifornimenti getFinestraRifornimenti() {
		return finestraRifornimenti;
	}
	public ContenutoVendita getContenutoVendita() {
		return contenutoVendita;
	}
	public AggiuntaArticolo getFinestraAggiuntaArticolo() {
		return finestraAggiuntaArticolo;
	}
	public AggiuntaFornitore getFinestraAggiuntaFornitore() {
		return finestraAggiuntaFornitore;
	}

}
