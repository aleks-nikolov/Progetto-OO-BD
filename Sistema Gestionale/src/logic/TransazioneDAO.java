package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransazioneDAO {
	private String url = "jdbc:postgresql://192.168.1.6:5432/NegozioAbbigliamento";
	private String username = "postgres";
	private String pass = "postgres";
	
	Connection conn;
	
	public void connect() {
		try {
			conn = DriverManager.getConnection(url, username, pass);
			System.out.println("Connected to database");
		} catch (SQLException e){
			System.out.println("Problem connecting to database");
			e.printStackTrace();
		}
	}
	
	public void execution() {
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM \"Transazione\"");
			while(rs.next()) {
				System.out.println(rs.getString("CodiceTransazione"));
			}
			
		} catch (SQLException e) {
			System.out.println("Problem with statement");
			e.printStackTrace();
		}
	}
}
