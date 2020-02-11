package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FornitoreDAO {

	Controller controller;
	
	public FornitoreDAO(Controller controller) {
		this.controller = controller;
	}
	
	public void InserisciFornitore(Connection conn, Fornitore fornitore) {

		try {
					
			PreparedStatement pst = conn.prepareStatement("INSERT INTO Fornitore VALUES(?, ?, ?, ?, ?, ?);");
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
	
}
