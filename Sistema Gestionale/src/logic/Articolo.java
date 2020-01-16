package logic;

public class Articolo {

	private ComposizioneTransazione compTransazione;
	
	private String nome;
	private String descrizione;
	private String categoria;
	private String marca;
	private String taglia;
	private String colore;
	private float prezzoDiListino;
	private int percentualeSaldo;
	private int quantità;
	private String codice;
	private char sesso;
	private String imagePath;
	
	
	
	//Getters e setters
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
	public int getPercentualeSaldo() {
		return percentualeSaldo;
	}
	public void setPercentualeSaldo(int percentualeSaldo) {
		this.percentualeSaldo = percentualeSaldo;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public char getSesso() {
		return sesso;
	}
	public void setSesso(char sesso) {
		this.sesso = sesso;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
