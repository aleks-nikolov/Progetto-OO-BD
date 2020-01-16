package logic;

public class ComposizioneTransazione {

	private Articolo articolo;
	private Transazione transazione;
	private int quantità;
	private float valore;
	
	//Getters e setters
	public Articolo getArticolo() {
		return articolo;
	}
	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}
	public Transazione getTransazione() {
		return transazione;
	}
	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public float getValore() {
		return valore;
	}
	public void setValore(float valore) {
		this.valore = valore;
	}
}
