package dao;

import database.DatabaseConnection;
import model.Pflanze;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GartenDAO {

    public void addToGarten(int userId, int pflanzeId) throws Exception {
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO garten (user_id, pflanze_id) VALUES (?, ?)");

        stmt.setInt(1, userId);
        stmt.setInt(2, pflanzeId);

        stmt.executeUpdate();
        conn.close();
    }

    public List<Pflanze> getMeinGarten(int userId) throws Exception {
        List<Pflanze> list = new ArrayList<>();

        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement("""
                SELECT p.* FROM pflanzen p
                JOIN garten g ON p.id = g.pflanze_id
                WHERE g.user_id = ?
        """);

        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

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
}