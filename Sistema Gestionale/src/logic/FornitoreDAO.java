package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FornitoreDAO {

	Controller controller;
	
	public FornitoreDAO(Controller controller) {
		this.controller = controller;
	}
	
	public void InserisciFornitore(Connection conn, Fornitore fornitore) {

		String comando = "INSERT INTO Fornitore VALUES(?, ?, ?, ?, ?, ?);";
		
		try {
					
			PreparedStatement pst = conn.prepareStatement(comando);
			pst.setString(1, fornitore.getPartitaIVA());
			pst.setString(2, fornitore.getNome());
			pst.setString(3, fornitore.getVia());
			pst.setString(4, fornitore.getNumeroCivico());
			pst.setString(5, fornitore.getCAP());
			pst.setString(6, fornitore.getNumeroDiTelefono());
					
			pst.executeUpdate();

			pst.close();	
					
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> getNomiFornitori(Connection conn) {
		
		ArrayList<String> fornitori = new ArrayList<String>();
		String comando = "SELECT F.Nome FROM Fornitore AS F;";
		
		try {
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(comando);
			
			while(rs.next()) {
				fornitori.add(rs.getString("Nome"));
			}
			
			st.close();
			rs.close();
			
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		
		return fornitori;
		
	}
	
	public String getPartitaFornitore(Connection conn, String nome) {
		
		String partitaIVA = "";
		String comando = "SELECT F.partitaiva FROM Fornitore AS F WHERE F.Nome = ?;";
		
		try {
			
			PreparedStatement pst = conn.prepareStatement(comando);
			pst.setString(1, nome);
			ResultSet rs = pst.executeQuery();
			
			rs.next();
			partitaIVA = rs.getString("partitaiva");
			
			pst.close();
			rs.close();
			
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return partitaIVA;
		
	}
	
}
