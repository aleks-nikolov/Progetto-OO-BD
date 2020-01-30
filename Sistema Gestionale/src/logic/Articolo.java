package logic;

public class Articolo {

	private ComposizioneTransazione compTransazione;
	
	private String codice;
	private String nome;
	private String descrizione;
	private String categoria;
	private String marca;
	private String taglia;
	private String colore;
	private float prezzoDiListino;
	private int percentualeSaldo;
	private int quantit�;
	private char sesso;
	private String imagePath;
	
//**************************************************************************************	

	public Articolo() {
		
	}
	
	public Articolo(String codice, String nome, String descrizione, String categoria, String marca, String taglia, String colore,
			float prezzoDiListino, int percentualeSaldo, int quantit�, char sesso, String imagePath) {
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.marca = marca;
		this.taglia = taglia;
		this.colore = colore;
		this.prezzoDiListino = prezzoDiListino;
		this.percentualeSaldo = percentualeSaldo;
		this.quantit� = quantit�;
		this.sesso = sesso;
		this.imagePath = imagePath;
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
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}
	
	//Getter e setter
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
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
	public int getPercentualeSaldo() {
		return percentualeSaldo;
	}
	public void setPercentualeSaldo(int percentualeSaldo) {
		this.percentualeSaldo = percentualeSaldo;
	}
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
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
