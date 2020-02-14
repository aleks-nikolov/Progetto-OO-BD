package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ComposizioneTransazioneDAO {

	Controller controller;
	
	public ComposizioneTransazioneDAO(Controller controller) {
		this.controller = controller;
	}
	
	public ArrayList<ComposizioneTransazione> getCompTransazioniDi(Connection conn, String codiceTransazione) {
		
		ArrayList<ComposizioneTransazione> composizioni = new ArrayList<ComposizioneTransazione>();
		ResultSet rs;
		
		String comando = "SELECT * FROM ComposizioneTransazione AS CT WHERE CT.CodiceTransazione = ?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(comando);
			pst.setInt(1, Integer.parseInt(codiceTransazione));
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ComposizioneTransazione compTransazione = new ComposizioneTransazione();
				compTransazione.setCodiceTransazione(rs.getString(1));
				compTransazione.setSKU(rs.getInt(2));
				compTransazione.setQuantità(rs.getInt(3));
				compTransazione.setValore(rs.getFloat(4));
				composizioni.add(compTransazione);
			}
			
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return composizioni;
		
	}
	
	public void InserisciComposizione(Connection conn, ComposizioneTransazione compTransazione) {
		
		String comandoUltimaTransazine = "SELECT MAX(T.CodiceTransazione) FROM Transazione AS T;";
		String comando = "INSERT INTO ComposizioneTransazione(sku, codicetransazione, quantita, saldo) VALUES (?, ?, ?, ?);";
		
		try {
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(comandoUltimaTransazine);
			
			rs.next();
			int codiceUltimaTransazione = rs.getInt(1);
			
			PreparedStatement pst = conn.prepareStatement(comando);
			pst.setInt(1, compTransazione.getSKU());
			pst.setInt(2, codiceUltimaTransazione);
			pst.setInt(3, compTransazione.getQuantità());
			pst.setInt(4, compTransazione.getSaldo());
			
			pst.executeUpdate();
			
			pst.close();
			
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
