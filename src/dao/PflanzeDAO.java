package dao;

import database.DatabaseConnection;
import model.Pflanze;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PflanzeDAO {

    public void add(Pflanze p) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO pflanzen (name, ertrag, dauer, groesse) VALUES (?, ?, ?, ?)");

        stmt.setString(1, p.getName());
        stmt.setInt(2, p.getErtrag());
        stmt.setInt(3, p.getDauer());
        stmt.setDouble(4, p.getGroesse());

        stmt.executeUpdate();
        conn.close();
    }

    public List<Pflanze> getAll() throws Exception {
        List<Pflanze> list = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM pflanzen");

        while (rs.next()) {
            Pflanze p = new Pflanze();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setErtrag(rs.getInt("ertrag"));
            p.setDauer(rs.getInt("dauer"));
            p.setGroesse(rs.getDouble("groesse"));
            list.add(p);
        }
        conn.close();
        return list;
    }

    public void delete(int id) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM pflanzen WHERE id=?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        conn.close();
    }
}