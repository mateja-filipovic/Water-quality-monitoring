package util;

import DAOUtil.DatabaseInterface;
import models.Device;
import models.User;
import models.WorkAction;
import models.WorkApplication;
import simulacija.Sonda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLMetode implements DatabaseInterface {

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

    @Override
    public User getUserFromDB(String username, String password, int type) {
        return null;
    }

    @Override
    public List<Device> getAllDevices() {
        return null;
    }

    @Override
    public List<List<Integer>> getParams(Sonda sonda) {
        return null;
    }

    @Override
    public List<WorkAction> getAllWorkActions() {
        return null;
    }

    @Override
    public List<WorkApplication> getWorkApplications(int actionId) {
        List<WorkApplication> list = new ArrayList<>();

        return list;
    }

    @Override
    public void createWorkAction(String name, String location, String time, int idAdmin) {

    }
}