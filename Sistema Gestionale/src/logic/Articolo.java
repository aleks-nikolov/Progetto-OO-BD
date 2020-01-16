package logic;

enum marca {
	ADIDAS,
	NIKE,
	HANDM,
	PIAZZAITALIA,
	ALCOTT,
	BERSHKA,
	VERSACE,
	GUCCI,
	NAPAPIJRI
}

enum taglia {
	XS,
	S,
	M,
	L,
	XL,
	XXL
}

enum colore {
	NERO,
	GRIGIO,
	BIANCO,
	ROSSO,
	BLU,
	VERDE
}

enum categoria {
	MAGLIETTE,
	MAGLIONI,
	FELPE,
	GIACCHE,
	PANTALONI,
	CAMICIE,
	ACCESSORI
}

public class Articolo {

	private ComposizioneTransazione compTransazione;
	
	private String nome;
	private String descrizione;
	private categoria categoria;
	private marca marca;
	private taglia taglia;
	private colore colore;
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
	public categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(categoria categoria) {
		this.categoria = categoria;
	}
	public marca getMarca() {
		return marca;
	}
	public void setMarca(marca marca) {
		this.marca = marca;
	}
	public taglia getTaglia() {
		return taglia;
	}
	public void setTaglia(taglia taglia) {
		this.taglia = taglia;
	}
	public colore getColore() {
		return colore;
	}
	public void setColore(colore colore) {
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
