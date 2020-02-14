package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class TransazioneDAO {

	Controller controller;
	
	public TransazioneDAO(Controller controller) {
		this.controller = controller;
	}
	
	public ArrayList<ArrayList<String>> getVendite(Connection conn){
		
		ArrayList<ArrayList<String>> datiVendite = new ArrayList<ArrayList<String>>();
		ResultSet rs;
		
		String comando = "SELECT * FROM vendite;";
		
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(comando);

			while(rs.next()) {

				ArrayList<String> datiVendita = new ArrayList<String>();
				
				datiVendita.add(rs.getString("id"));
				datiVendita.add(rs.getString("contenuto"));
				Date date = rs.getDate("data");
				datiVendita.add(date.toString());
				datiVendita.add(String.valueOf(rs.getFloat("costo")));
			
				datiVendite.add(datiVendita);
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return datiVendite;
		
	}
	
	public ArrayList<ArrayList<String>> getRifornimenti(Connection conn){
		
		ArrayList<ArrayList<String>> datiRifornimenti = new ArrayList<ArrayList<String>>();
		ResultSet rs;
		
		String comando = "SELECT * FROM rifornimenti;";
		
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(comando);
			
			while(rs.next()) {

				ArrayList<String> datiRifornimento = new ArrayList<String>();
				
				datiRifornimento.add(rs.getString("id"));
				datiRifornimento.add(rs.getString("contenuto"));
				datiRifornimento.add(rs.getString("fornitore"));
				Date date = rs.getDate("data");
				datiRifornimento.add(date.toString());
				datiRifornimento.add(String.valueOf(rs.getFloat("costo")));
			
				datiRifornimenti.add(datiRifornimento);
			}
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return datiRifornimenti;
		
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
	

	public void InserisciVendita(Connection conn, Transazione transazione) {
	
		String comando = "INSERT INTO Transazione(data) VALUES (?);";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(comando);
			pst.setDate(1, Date.valueOf(transazione.getData()));
			pst.executeUpdate();
			
			pst.close();
					
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
	}

	public void EliminaByCodice(Connection conn, String codiceTransazione) {
		
		String comando = "DELETE FROM Transazione AS T WHERE T.CodiceTransazione = ?;";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(comando);	
			pst.setInt(1, Integer.parseInt(codiceTransazione));
			pst.executeUpdate();
			
			pst.close();
			
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
