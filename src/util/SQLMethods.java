package util;

import DAOUtil.DatabaseInterface;
import models.User;
import models.WorkAction;
import models.WorkApplication;
import simulation.Device;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLMethods implements DatabaseInterface {

    private Connection conn = null;

    public void connect() {
        disconnect();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:swan.db");
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
        int cnt = 0;
        String sql1 = "SELECT COUNT(*) FROM Parametri WHERE IdUre = " + sonda.getId();
        try(Statement stmt = conn.createStatement()) {
            try(ResultSet rs = stmt.executeQuery(sql1);) {
                cnt = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<List<Double>> allValues = new ArrayList<>();
        String sql2 = "SELECT PH, Zamucenost, O2, NH3, ORP FROM Parametri " +
                "WHERE IdUre = " + sonda.getId() + "OFFSET " + cnt + " - 5";
        try(Statement stmt = conn.createStatement()) {
            try(ResultSet rs = stmt.executeQuery(sql2)) {
                while(rs.next()) {
                    List<Double> particularValues = new ArrayList<>();
                    particularValues.add(rs.getDouble("PH"));
                    particularValues.add(rs.getDouble("Zamucenost"));
                    particularValues.add(rs.getDouble("O2"));
                    particularValues.add(rs.getDouble("NH3"));
                    particularValues.add(rs.getDouble("ORP"));
                    allValues.add(particularValues);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allValues;
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
        int idP = 0, idK = 0, type = 0;
        String name = "", lastName = "", username = "", password = "", email = "";
        String sql = "SELECT P.IdPri, K.IdKor, K.Ime, K.Prezime, K.Username, K.Lozinka, K.Email, K.Tip" +
                "FROM Prijava P INNER JOIN Korisnik K USING(IdKor) WHERE K.IdAkc = " + actionId;
        try(Statement stmt = conn.createStatement()) {
            try(ResultSet rs = stmt.executeQuery(sql);) {
                while(rs.next()) {
                    idP = rs.getInt("P.IdPri");
                    idK = rs.getInt("K.IdKor");
                    type = rs.getInt("K.Tip");
                    name = rs.getString("K.Ime");
                    lastName = rs.getString("K.Prezime");
                    username = rs.getString("K.Username");
                    password = rs.getString("K.Lozinka");
                    email = rs.getString("K.Email");
                    list.add(new WorkApplication(idP, new User(idK, name, lastName, username, password, email, type)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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