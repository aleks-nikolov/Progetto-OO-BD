package logic;

public class ComposizioneTransazione {

	private int skuArticolo;
	private String codiceTransazione;
	private int quantità;
	private int saldo = 0;
	private float valore;
	
//**************************************************************************************
	
	//Getters e setters
	public int getSKU() {
		return skuArticolo;
	}
	public void setSKU(int sku) {
		this.skuArticolo = sku;
	}
	public String getCodiceTransazione() {
		return codiceTransazione;
	}
	public void setCodiceTransazione(String codiceTransazione) {
		this.codiceTransazione = codiceTransazione;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public float getValore() {
		return valore;
	}
	public void setValore(float valore) {
		this.valore = valore;
	}
}
