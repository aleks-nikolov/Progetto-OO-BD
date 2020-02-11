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
			pst.setString(1, codiceTransazione);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				ComposizioneTransazione compTransazione = new ComposizioneTransazione();
				compTransazione.setCodiceTransazione(rs.getString(1));
				compTransazione.setSKU(rs.getString(2));
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
	
}
