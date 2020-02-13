package logic;

public class Articolo {
	
	private ComposizioneTransazione compTransazione;
	
	private int sku;
	private String codiceABarre;
	private String nome;
	private String descrizione;
	private String categoria;
	private String marca;
	private String colore;
	private String sesso;
	private String taglia;
	private float prezzoDiListino;
	private float prezzoMagazzino;
	private int quantita;
	private String pathImmagine;
	
//**************************************************************************************	
	
	public Articolo() {
		
	}
	
	public Articolo(String codiceABarre, String nome, String descrizione, String categoria, String marca, String colore,
					String sesso, String taglia, Float prezzoDiListino, Float prezzoMagazzino, String pathImmagine) {
		this.codiceABarre = codiceABarre;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.marca = marca;
		this.colore = colore;
		this.sesso = sesso;
		this.taglia = taglia;
		this.prezzoDiListino = prezzoDiListino;
		this.prezzoMagazzino = prezzoMagazzino;
		this.pathImmagine = pathImmagine;
	}
	
	//Metodo equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		if (sku != other.sku)
			return false;
		return true;
	}
	
	//Getter e setter
	public int getSKU() {
		return sku;
	}
	public void setSKU(int codice) {
		this.sku = codice;
	}
	public String getCodiceABarre() {
		return codiceABarre;
	}
	public void setCodiceABarre(String codiceABarre) {
		this.codiceABarre = codiceABarre;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTaglia() {
		return taglia;
	}
	public void setTaglia(String taglia) {
		this.taglia = taglia;
	}
	public String getColore() {
		return colore;
	}
	public void setColore(String colore) {
		this.colore = colore;
	}
	public float getPrezzoDiListino() {
		return prezzoDiListino;
	}
	public void setPrezzoDiListino(float prezzoDiListino) {
		this.prezzoDiListino = prezzoDiListino;
	}
	public float getPrezzoMagazzino() {
		return prezzoMagazzino;
	}

	public void setPrezzoMagazzino(float prezzoFornitore) {
		this.prezzoMagazzino = prezzoFornitore;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantità) {
		this.quantita = quantità;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getPathImmagine() {
		return pathImmagine;
	}
	public void setPathImmagine(String imagePath) {
		this.pathImmagine = imagePath;
	}
	
}
