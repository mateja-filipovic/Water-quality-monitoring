package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}
