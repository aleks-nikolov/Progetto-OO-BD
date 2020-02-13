package logic;

import java.time.LocalDate;

public class Transazione {

	private ComposizioneTransazione compTransazione;
	private Fornitore fornitore;
	private String codiceTransazione;
	private float valoreTotale;
	private LocalDate data;
	private String partitaIva;
	
//**************************************************************************************
	
	
	//Getters e setters
	public ComposizioneTransazione getCompTransazione() {
		return compTransazione;
	}
	public void setCompTransazione(ComposizioneTransazione compTransazione) {
		this.compTransazione = compTransazione;
	}
	public Fornitore getFornitore() {
		return fornitore;
	}
	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}
	public String getCodiceTransazione() {
		return codiceTransazione;
	}
	public void setCodiceTransazione(String codiceTransazione) {
		this.codiceTransazione = codiceTransazione;
	}
	public float getValoreTotale() {
		return valoreTotale;
	}
	public void setValoreTotale(float valoreTotale) {
		this.valoreTotale = valoreTotale;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}
}
