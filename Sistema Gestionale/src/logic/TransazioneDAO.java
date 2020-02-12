package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

public class TransazioneDAO {

	Controller controller;
	
	public TransazioneDAO(Controller controller) {
		this.controller = controller;
	}
	
	public ArrayList<Transazione> getVendite(Connection conn){
		
		ArrayList<Transazione> vendite = new ArrayList<Transazione>();
		ResultSet rs;
		
		String comando = "SELECT * FROM Transazione AS T WHERE T.PartitaIva IS NULL;";
		
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(comando);
			
			while(rs.next()) {
				Transazione transazione = new Transazione();
				transazione.setCodiceTransazione(rs.getString(1));
				transazione.setData(rs.getDate(2).toLocalDate());
				transazione.setValoreTotale(rs.getFloat(3));
				vendite.add(transazione);
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return vendite;
		
	}
	
	public ArrayList<Transazione> getRifornimenti(Connection conn){
		
		ArrayList<Transazione> rifornimenti = new ArrayList<Transazione>();
		ResultSet rs;
		
		String comando = "SELECT * FROM Transazione AS T WHERE T.PartitaIva IS NOT NULL;";
		
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(comando);
			
			while(rs.next()) {
				Transazione transazione = new Transazione();
				transazione.setCodiceTransazione(rs.getString(1));
				transazione.setData(rs.getDate(2).toLocalDate());
				transazione.setValoreTotale(rs.getFloat(3));
				transazione.setPartitaIva(rs.getString(4));
				rifornimenti.add(transazione);
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return rifornimenti;
		
	}
	
	public void InserisciRifornimento(Connection conn, Transazione transazione) {
	
		String comando = "INSERT INTO Transazione(data, partitaiva) VALUES (?, ?);";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(comando);
			pst.setDate(1, Date.valueOf(transazione.getData()));
			pst.setString(2, transazione.getPartitaIva());
			pst.executeUpdate();
			
			pst.close();
					
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
}
