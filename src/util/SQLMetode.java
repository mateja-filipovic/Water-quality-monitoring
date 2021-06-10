package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLMetode {

    private Connection konekcija = null;

    public void konektujSe() {
        diskonektujSe();
        try {
            Class.forName("org.sqlite.JDBC");
            konekcija = DriverManager.getConnection("jdbc:sqlite:swan.db");
            System.out.println("Uspesna konekcija!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void diskonektujSe() {
        if(konekcija != null) {
            try {
                konekcija.close();
                konekcija = null;
                System.out.println("Uspesna diskonekcija!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void unesiVrednosti(int id, double dohvProsekNH, double dohvProsekO, int dohvProsekORP, double dohvProsekPH, double dohvProsekZam) {
        System.out.println("Ubacujem");
        String sql = "INSERT INTO Parametri(IdUre, NH3, O2, ORP, PH, Zamucenost) VALUES (" + id + ", " + dohvProsekNH + ", " +
                dohvProsekO + ", " + dohvProsekORP + ", " + dohvProsekPH + ", " + dohvProsekZam + ")";
        try(Statement stmt = konekcija.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
