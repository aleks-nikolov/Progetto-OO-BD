package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TransazioneDAO {

	
	
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
				transazione.setData(rs.getDate(2));
				transazione.setValoreTotale(rs.getFloat(3));
				vendite.add(transazione);
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
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
				transazione.setData(rs.getDate(2));
				transazione.setValoreTotale(rs.getFloat(3));
				transazione.setPartitaIva(rs.getString(4));
				rifornimenti.add(transazione);
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rifornimenti;
		
	}
	
	
	
	
}
