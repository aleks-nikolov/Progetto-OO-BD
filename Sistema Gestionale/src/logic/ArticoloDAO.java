package logic;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticoloDAO {

	Controller controller;
	
	public ArticoloDAO(Controller controller) {
		this.controller = controller;
	}
	
	public ArrayList<Articolo> acquisisciArticoliByFiltro(Connection conn, String filtro) {
		
		ArrayList<Articolo> articoli = new ArrayList<Articolo>();
	
		try {
			
			Articolo articolo;
			
			String comando = "SELECT * FROM articolicondescrittori AS A" + filtro + ";";
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(comando);
			
			while(rs.next()) {
				
				articolo = new Articolo();
				
				articolo.setSKU(rs.getString("SKU"));
				articolo.setTaglia(rs.getString("Taglia"));
				articolo.setColore(rs.getString("Colore"));
				articolo.setQuantita(rs.getInt("Quantita"));
				articolo.setImagePath(rs.getString("PathImmagine"));
				articolo.setSKU(rs.getString("SKU"));
				articolo.setCodiceABarre(rs.getString("CodiceABarre"));
				articolo.setMarca(rs.getString("Marca"));
				articolo.setCategoria(rs.getString("Categoria"));
				articolo.setSesso(rs.getString("Sesso"));
				articolo.setPrezzoDiListino(rs.getFloat("PrezzoDiListino"));
				articolo.setPrezzoMagazzino(rs.getFloat("PrezzoMagazzino"));
				articolo.setNome(rs.getString("Nome"));
				articolo.setDescrizione(rs.getString("Descrizione"));
				
				articoli.add(articolo);
				
			}
			
		
		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return articoli;
	}
	
	public Articolo TrovaArticolo(Connection conn, String sku) {
		
		Articolo articolo = new Articolo();
		ResultSet rs;
		
		String comando = "SELECT * FROM articolicondescrittori AS A WHERE A.sku = ?;";
		
		try {
			PreparedStatement pst = conn.prepareStatement(comando);
			pst.setString(1, sku);
			rs = pst.executeQuery();
			
			articolo.setSKU(rs.getString("SKU"));
			articolo.setTaglia(rs.getString("Taglia"));
			articolo.setColore(rs.getString("Colore"));
			articolo.setQuantita(rs.getInt("Quantita"));
			articolo.setImagePath(rs.getString("PathImmagine"));
			articolo.setSKU(rs.getString("SKU"));
			articolo.setCodiceABarre(rs.getString("CodiceABarre"));
			articolo.setMarca(rs.getString("Marca"));
			articolo.setCategoria(rs.getString("Categoria"));
			articolo.setSesso(rs.getString("Sesso"));
			articolo.setPrezzoDiListino(rs.getFloat("PrezzoDiListino"));
			articolo.setPrezzoMagazzino(rs.getFloat("PrezzoFornitore"));
			articolo.setNome(rs.getString("Nome"));
			articolo.setDescrizione(rs.getString("Descrizione"));
			
			rs.close();
			pst.close();

		} catch (SQLException e) {
			controller.MostraMessaggioErrore("Errore DAO", e.getMessage());
			e.printStackTrace();
		}
		
		return articolo;
		
	}
	
	public void InserisciArticolo(Connection conn, Articolo articolo) {
		
		try {
			
			PreparedStatement controlloEsistenzaCodice = conn.prepareStatement("SELECT DA.CodiceABarre FROM DescrittoreArticolo AS DA WHERE Da.CodiceABarre = ?;");
			controlloEsistenzaCodice.setString(1, articolo.getCodiceABarre());
			
			ResultSet rs = controlloEsistenzaCodice.executeQuery();
			
			if(rs.next() == false) {
				
				PreparedStatement pst1 = conn.prepareStatement("INSERT INTO DescrittoreArticolo VALUES(?, CAST(? AS \"Marche\"), CAST(? AS \"Categorie\"), ?, ?, ?, ?, ?);");
				pst1.setString(1, articolo.getCodiceABarre());
				pst1.setString(2, articolo.getMarca());
				pst1.setString(3, articolo.getCategoria());
				pst1.setString(4, articolo.getSesso());
				pst1.setFloat(5, articolo.getPrezzoDiListino());
				pst1.setFloat(6, articolo.getPrezzoMagazzino());
				pst1.setString(7, articolo.getNome());
				pst1.setString(8, articolo.getDescrizione());
				
				pst1.executeUpdate();
				
				pst1.close();

			}
			
			PreparedStatement pst2 = conn.prepareStatement("INSERT INTO Articolo(Taglia, Colore, Quantita, PathImmagine, CodiceABarre) VALUES(CAST(? AS \"Taglie\"), CAST(? AS \"Colori\"), ?, ?, ?);");
			pst2.setString(1, articolo.getTaglia());
			pst2.setString(2, articolo.getColore());
			pst2.setInt(3, 0); //Imposto la quantità a zero, dato che non ce ne sono esemplari in magazzino
			pst2.setString(4, articolo.getImagePath());
			pst2.setString(5, articolo.getCodiceABarre());
			
			pst2.executeUpdate();
			
			controlloEsistenzaCodice.close();
			pst2.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
