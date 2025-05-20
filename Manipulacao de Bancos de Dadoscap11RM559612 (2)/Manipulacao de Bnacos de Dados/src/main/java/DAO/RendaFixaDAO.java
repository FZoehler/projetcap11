package DAO;

import models.RendaFixa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RendaFixaDAO {

    private Connection connection;

    public RendaFixaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(RendaFixa r) throws SQLException {
        String sql = "INSERT INTO RendaFixa (id, balance, cdb, lci, cri, cra) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getIdRendaFixa());
            stmt.setFloat(2, r.getBalance());
            stmt.setString(3, r.getCdb());
            stmt.setString(4, r.getLci());
            stmt.setString(5, r.getCri());
            stmt.setString(6, r.getCra());
            stmt.executeUpdate();
        }
    }

    public void update(RendaFixa r) throws SQLException {
        String sql = "UPDATE RendaFixa SET balance = ?, cdb = ?, lci = ?, cri = ?, cra = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, r.getBalance());
            stmt.setString(2, r.getCdb());
            stmt.setString(3, r.getLci());
            stmt.setString(4, r.getCri());
            stmt.setString(5, r.getCra());
            stmt.setString(6, r.getIdRendaFixa());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM RendaFixa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public RendaFixa getById(String id) throws SQLException {
        String sql = "SELECT * FROM RendaFixa WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new RendaFixa(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("cdb"),
                        rs.getString("lci"),
                        rs.getString("cri"),
                        rs.getString("cra")
                );
            }
        }
        return null;
    }

    public List<RendaFixa> getAll() throws SQLException {
        List<RendaFixa> lista = new ArrayList<>();
        String sql = "SELECT * FROM RendaFixa";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                RendaFixa r = new RendaFixa(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("cdb"),
                        rs.getString("lci"),
                        rs.getString("cri"),
                        rs.getString("cra")
                );
                lista.add(r);
            }
        }
        return lista;
    }
}

