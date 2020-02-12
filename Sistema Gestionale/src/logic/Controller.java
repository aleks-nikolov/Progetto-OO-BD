package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.*;


public class Controller {

	private String url = "jdbc:postgresql://dumbo.db.elephantsql.com:5432/nwiojydu";
	private String username = "nwiojydu";
	private String pass = "sxUptH8Xh2rWXqBwbZxRuW5kCH74h_Tw";
	
	private Connection conn;

	private ArticoloDAO articoloDAO = new ArticoloDAO(this);
	private TransazioneDAO transazioneDAO = new TransazioneDAO(this);
	private ComposizioneTransazioneDAO compTransazioneDAO = new ComposizioneTransazioneDAO(this);
	private FornitoreDAO fornitoreDAO = new FornitoreDAO(this);
	
	private HomePage home;
	private FinestraVendite finestraVendite;
	private FinestraInventario finestraInventario;
	private FinestraRifornimenti finestraRifornimenti;
	private ContenutoTransazione contenutoVendita;
	private ContenutoTransazione contenutoRifornimento;
	private AggiuntaArticolo finestraAggiuntaArticolo;
	private AggiuntaFornitore finestraAggiuntaFornitore;
	
	private ArrayList<String> categoria = new ArrayList<String>();
	private ArrayList<String> marca = new ArrayList<String>();
	private ArrayList<String> colore = new ArrayList<String>();
	private ArrayList<String> taglia = new ArrayList<String>();
	private ArrayList<Articolo> articoli = new ArrayList<Articolo>();
	private ArrayList<String> sessi = new ArrayList<String>();
	private ArrayList<String> fornitori = new ArrayList<String>();
 	
//**************************************************************************************
	
	public Controller() {
		
		ConnectToDatabase();
		InizializzaArrayList();
		InizializzaFinestre();
		
	}
	
	public void InizializzaFinestre() {
		
		//Inizializza e apre la home page
		home = new HomePage(this);
		
		//Inizializza le altre finestre
		finestraVendite = new FinestraVendite(this);
		finestraInventario = new FinestraInventario(this);
		finestraRifornimenti = new FinestraRifornimenti(this);
		contenutoVendita = new ContenutoTransazione(this, "vendita");
		contenutoRifornimento = new ContenutoTransazione(this, "rifornimento");
		finestraAggiuntaArticolo = new AggiuntaArticolo(this);
		finestraAggiuntaFornitore = new AggiuntaFornitore(this);
		
		home.setVisible(true);

	}
	
	public void InizializzaArrayList() {
		categoria.add("CATEGORIA");
		categoria.add("Magliette");
		categoria.add("Maglioni");
		categoria.add("Giacche");
		categoria.add("Pantaloni");
		categoria.add("Camicie");
		categoria.add("Accessori");
		
		marca.add("MARCA");
		marca.add("Adidas");
		marca.add("Nike");
		marca.add("H&M");
		marca.add("Bershka");
		marca.add("Piazzaitalia");
		
		colore.add("COLORE");
		colore.add("Nero");
		colore.add("Bianco");
		colore.add("Rosso");
		colore.add("Verde");
		colore.add("Vlu");
		
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

	}
	
	public void ConnectToDatabase() {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			MostraMessaggioErrore("Errore", "Driver non trovato!");
		}
		
		try {
			conn = DriverManager.getConnection(url, username, pass);
		} catch (SQLException e){
			MostraMessaggioErrore("Errore", e.getMessage());
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
			contenuto += " " + a.getNome() + " x" + a.getQuantita() + "\n";
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

    public void CostruisciArticolo(ArrayList<String> dati) {
		
		Articolo articolo = new Articolo();

		articolo.setCodiceABarre(dati.get(0));
		articolo.setNome(dati.get(1));
		articolo.setDescrizione(dati.get(2));
		articolo.setCategoria(dati.get(3));
		articolo.setMarca(dati.get(4));
		articolo.setColore(dati.get(5));
		articolo.setSesso(dati.get(6));
		articolo.setTaglia(dati.get(7));
		articolo.setPrezzoDiListino(Float.parseFloat(dati.get(8)));
		articolo.setPrezzoMagazzino(Float.parseFloat(dati.get(9)));
		articolo.setImagePath(dati.get(10));
		
		NuovoArticolo(articolo);
		
	}
    
    public void NuovoArticolo(Articolo articolo) {
    	articoloDAO.InserisciArticolo(conn, articolo);
    }
    
    public ArrayList<Articolo> getArticoliByFiltro(ArrayList<String> filtroSQL) {
    	return articoloDAO.acquisisciArticoliByFiltro(conn, filtroSQL);
    }
    
    
    public void NuovoFornitore(Fornitore fornitore) {
    	fornitoreDAO.InserisciFornitore(conn, fornitore);
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
	
	public void MostraMessaggioErrore(String titolo, String testo) {
		JOptionPane.showMessageDialog(null, testo, titolo,  JOptionPane.ERROR_MESSAGE);
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
	public ArrayList<String> getFornitori() {
		return fornitori;
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
	public ContenutoTransazione getContenutoVendita() {
		return contenutoVendita;
	}
	public ContenutoTransazione getContenutoRifornimento() {
		return contenutoRifornimento;
	}
	public AggiuntaArticolo getFinestraAggiuntaArticolo() {
		return finestraAggiuntaArticolo;
	}
	public AggiuntaFornitore getFinestraAggiuntaFornitore() {
		return finestraAggiuntaFornitore;
	}

}
