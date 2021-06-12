package util;

import DAOUtil.DatabaseInterface;
import models.User;
import models.WorkAction;
import models.WorkApplication;
import simulation.Device;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLMetode implements DatabaseInterface {

    private Connection conn = null;

    public void connect() {
        disconnect();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:swan.db");
            System.out.println("Uspesna konekcija!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect() {
        if(conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Uspesna diskonekcija!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void insertParameters(int id, double avgNH, double avgO, int avgORP, double avgPH, double avgTur) {
        String sql = "INSERT INTO Parametri(IdUre, NH3, O2, ORP, PH, Zamucenost) VALUES (" + id + ", " + avgNH + ", " +
                avgO + ", " + avgORP + ", " + avgPH + ", " + avgTur + ")";
        try(Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUserFromDB(String username, String password, int type) {
        int id = 0;
        String name = "";
        String lastName = "";
        String email = "";
        String sql = "SELECT IdKor, Ime, Prezime, Email" +
                "FROM Korisnik " +
                "WHERE Username = ? AND Lozinka = ? AND Tip = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, type);
            try(ResultSet rs = ps.executeQuery()) {
                id = rs.getInt("IdKor");
                name = rs.getString("Ime");
                lastName = rs.getString("Prezime");
                email = rs.getString("Email");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new User(id, name, lastName, username, password, email, type);
    }

    @Override
    public List<List<Double>> getParams(Device sonda) {

        return null;
    }

    @Override
    public List<WorkAction> getAllWorkActions() {
        List<WorkAction> list = new ArrayList<>();
        String sql = "SELECT IdAkc, Vreme, Naziv, Mesto, IdAdm FROM Akcija";
        try(Statement stmt = conn.createStatement()) {
            try(ResultSet rs = stmt.executeQuery(sql);) {
                while(rs.next()) {
                    list.add(new WorkAction(rs.getInt("IdAkc"),
                                rs.getString("Vreme"),
                                rs.getString("Naziv"),
                                rs.getInt("IdAdm"),
                                rs.getString("Mesto")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<WorkApplication> getWorkApplications(int actionId) {
        List<WorkApplication> list = new ArrayList<>();

        return list;
    }

    @Override
    public void createWorkAction(String name, String location, String time, int idAdmin) {
        String sql = "INSERT INTO Akcija(Naziv, Mesto, Vreme, IdAdm) VALUES" +
                "(" + name + ", " +  location + ", " + time + ", " + idAdmin + ")";
        try(Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}