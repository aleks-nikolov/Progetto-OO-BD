package logic;

public class ComposizioneTransazione {

	private Articolo articolo;
	private Transazione transazione;
	private int quantit�;
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
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
	public float getValore() {
		return valore;
	}
	public void setValore(float valore) {
		this.valore = valore;
	}
}
