package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformationDAO {

	java.sql.Connection cnx;
	java.sql.PreparedStatement stmt;
	ResultSet res;

	public InformationDAO() throws ClassNotFoundException, SQLException {
		cnx = MySqlManager.getConnection();
	}

	public List<Information> FindInformationByNom(String Ville, String NomHotel, String Date) {
		String query = "SELECT * FROM chambres WHERE Ville = ? AND NomHotel = ? AND date = ? ";
		try {
			stmt = cnx.prepareStatement(query);
			stmt.setString(1, Ville.toLowerCase());
			stmt.setString(2, NomHotel.toLowerCase());
			stmt.setString(3, Date);
			res = stmt.executeQuery();

			List<Information> infos = new ArrayList<>();
			while (res.next()) {
				Information info = new Information(
						res.getInt("id"),
						res.getString("Ville"),
						res.getString("NomHotel"),
						res.getString("date"),
						res.getBoolean("disponibilite")
				);
				System.out.println("DAO info: " + info);
				infos.add(info);
			}
			System.out.println("Informations List: " + Information.ListToString(infos));
			return infos;
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception here (logging, user-friendly message, etc.)
		} finally {
			try {
				if (res != null) res.close();
				if (stmt != null) stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
