package logic;

public class ComposizioneTransazione {

	private String skuArticolo;
	private String codiceTransazione;
	private int quantit�;
	private int saldo;
	private float valore;
	
//**************************************************************************************
	
	//Getters e setters
	public String getSKU() {
		return skuArticolo;
	}
	public void setSKU(String sku) {
		this.skuArticolo = sku;
	}
	public String getCodiceTransazione() {
		return codiceTransazione;
	}
	public void setCodiceTransazione(String codiceTransazione) {
		this.codiceTransazione = codiceTransazione;
	}
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
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
