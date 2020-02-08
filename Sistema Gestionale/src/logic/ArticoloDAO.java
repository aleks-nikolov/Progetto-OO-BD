package logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticoloDAO {

	public ArrayList<Articolo> acquisisciArticoliByFiltro(Connection conn, String filtro) {
		
		ArrayList<Articolo> articoli = new ArrayList<Articolo>();
		Articolo articolo = new Articolo();
		
	
		try {
			
			String comando = "SELECT * FROM Articolo AS A JOIN DescrittoreArticolo AS DA ON A.CodiceABarre = DA.CodiceABarre" + filtro + ";";
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(comando);
			
			while(rs.next()) {
				
				articolo.setSKU(rs.getString("SKU"));
				articolo.setTaglia(rs.getString("Taglia"));
				articolo.setColore(rs.getString("Colore"));
				articolo.setQuantità(rs.getInt("Quantità"));
				articolo.setImagePath(rs.getString("PathImmagine"));
				articolo.setSKU(rs.getString("SKU"));
				articolo.setCodiceABarre(rs.getString("CodiceABarre"));
				articolo.setMarca(rs.getString("Marca"));
				articolo.setCategoria(rs.getString("Categoria"));
				articolo.setSesso(rs.getString("Sesso"));
				articolo.setPrezzo(rs.getFloat("PrezzoDiListino"));
				articolo.setNome(rs.getString("Nome"));
				articolo.setDescrizione(rs.getString("Descrizione"));
				
				articoli.add(articolo);
				
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return articoli;
	}
	
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
	
	public void InserisciArticolo(Connection conn, Articolo articolo) {
		
		try {
			
			PreparedStatement pst2 = conn.prepareStatement("INSERT INTO DescrittoreArticolo VALUES(?, CAST(? AS \"Marche\"), CAST(? AS \"Categorie\"), ?, ?, ?, ?);");
			pst2.setString(1, articolo.getCodiceABarre());
			pst2.setString(2, articolo.getMarca());
			pst2.setString(3, articolo.getCategoria());
			pst2.setString(4, articolo.getSesso());
			pst2.setFloat(5, articolo.getPrezzo());
			pst2.setString(6, articolo.getNome());
			pst2.setString(7, articolo.getDescrizione());
			
			pst2.executeUpdate();

			PreparedStatement pst = conn.prepareStatement("INSERT INTO Articolo(Taglia, Colore, Quantità, CodiceABarre) VALUES(CAST(? AS \"Taglie\"), CAST(? AS \"Colori\"), ?, ?, ?);");
			pst.setString(1, articolo.getTaglia());
			pst.setString(2, articolo.getColore());
			pst.setInt(3, 0); //Imposto la quantità a zero, dato che non ce ne sono esemplari in magazzino
			pst.setString(4, articolo.getImagePath());
			pst.setString(5, articolo.getCodiceABarre());
			
			pst.executeUpdate();
			pst.close();
			pst2.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
