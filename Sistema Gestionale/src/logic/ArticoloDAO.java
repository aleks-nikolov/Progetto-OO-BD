package logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticoloDAO {

	public Articolo TrovaArticolo(Connection conn, String sku) {
		Articolo articolo = new Articolo();
		ResultSet rs1;
		ResultSet rs2;
		
		String comandoArticolo = "SELECT * FROM Articolo AS A WHERE A.SKU = ?;";
		String comandoDescrittore = "SELECT * FROM DescrittoreArticolo AS DA WHERE DA.CodiceABarre = ?;";
		
		try {
			PreparedStatement pst1 = conn.prepareStatement(comandoArticolo);
			pst1.setString(1, sku);
			rs1 = pst1.executeQuery();
			
			PreparedStatement pst2 = conn.prepareStatement(comandoDescrittore);
			pst2.setString(1, rs1.getString("CodiceABarre"));
			rs2 = pst2.executeQuery();
			
			articolo.setSKU(rs1.getString("SKU"));
			articolo.setTaglia(rs1.getString("Taglia"));
			articolo.setColore(rs1.getString("Colore"));
			articolo.setQuantità(rs1.getInt("Quantità"));
			articolo.setImagePath(rs1.getString("PathImmagine"));
			articolo.setSKU(rs1.getString("SKU"));
			articolo.setCodiceABarre(rs1.getString("CodiceABarre"));
			articolo.setMarca(rs2.getString("Marca"));
			articolo.setCategoria(rs2.getString("Categoria"));
			articolo.setSesso(rs2.getString("Sesso"));
			articolo.setPrezzo(rs2.getFloat("PrezzoDiListino"));
			articolo.setNome(rs2.getString("Nome"));
			articolo.setDescrizione(rs2.getString("Descrizione"));
			
			rs1.close();
			rs2.close();
			pst1.close();
			pst2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return articolo;
	}
	
	
}
