package DAO;

import models.RendaVariavel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendaVariavelDAO {

    private Connection connection;

    public RendaVariavelDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(RendaVariavel r) throws SQLException {
        String sql = "INSERT INTO RendaVariavel (id, balance, bovespa, bmsf) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getIdRendaVariavel());
            stmt.setFloat(2, r.getBalance());
            stmt.setString(3, r.getBovespa());
            stmt.setString(4, r.getBmsf());
            stmt.executeUpdate();
        }
    }

    public void update(RendaVariavel r) throws SQLException {
        String sql = "UPDATE RendaVariavel SET balance = ?, bovespa = ?, bmsf = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, r.getBalance());
            stmt.setString(2, r.getBovespa());
            stmt.setString(3, r.getBmsf());
            stmt.setString(4, r.getIdRendaVariavel());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM RendaVariavel WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public RendaVariavel getById(String id) throws SQLException {
        String sql = "SELECT * FROM RendaVariavel WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new RendaVariavel(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("bovespa"),
                        rs.getString("bmsf")
                );
            }
        }
        return null;
    }

    public List<RendaVariavel> getAll() throws SQLException {
        List<RendaVariavel> lista = new ArrayList<>();
        String sql = "SELECT * FROM RendaVariavel";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RendaVariavel r = new RendaVariavel(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("bovespa"),
                        rs.getString("bmsf")
                );
                lista.add(r);
            }
        }
        return lista;
    }
}


