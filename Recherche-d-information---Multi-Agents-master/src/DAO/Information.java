package DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Information {
	private int id;
	private String Ville;
	private String NomHotel;
	private String date;
	private boolean disponibilite;

	public Information(int id, String Ville, String NomHotel, String date, boolean disponibilite) {
		this.id = id;
		this.Ville = Ville;
		this.NomHotel = NomHotel;
		this.date = date;
		this.disponibilite = disponibilite;
	}

	public Information(String Ville, String NomHotel, String date, boolean disponibilite) {
		this.Ville = Ville;
		this.NomHotel = NomHotel;
		this.date = date;
		this.disponibilite = disponibilite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVille() {
		return Ville;
	}

	public void setVille(String Ville) {
		this.Ville = Ville;
	}

	public String getNomHotel() {
		return NomHotel;
	}

	public void setNomHotel(String NomHotel) {
		this.NomHotel = NomHotel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}



	@Override
	public String toString() {
		return Ville + ":" + NomHotel + ":" + date + ":" + disponibilite ;
	}

	public static String ListToString(List<Information> infos) {
		if (infos != null && !infos.isEmpty()) {
			String message = "";

			for(Iterator var2 = infos.iterator(); var2.hasNext(); message = message + ";") {
				Information f = (Information)var2.next();
				message = message + f.toString();
			}

			return message;
		} else {
			return null;
		}
	}

	public static List<Information> StringToList(String infos) {
		List<Information> info = new ArrayList();
		String[] var2 = infos.split(";");
		int var3 = var2.length;

		for(int var4 = 0; var4 < var3; ++var4) {
			String row = var2[var4];
			String[] columns = row.split(":");
			Information n = new Information(columns[0], columns[1], columns[2], Boolean.parseBoolean(columns[3]));
			info.add(n);
		}

		return info;
	}
}
