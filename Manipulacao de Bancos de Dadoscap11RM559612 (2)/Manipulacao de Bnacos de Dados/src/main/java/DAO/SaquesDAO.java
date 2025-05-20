package DAO;

import models.Saques;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaquesDAO {

    private final Connection connection;

    public SaquesDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Saques saque) throws SQLException {
        // Using column position instead of column name to avoid reserved keyword issues
        String sql = "INSERT INTO Saques VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, saque.getId());
            stmt.setFloat(2, saque.getAmount());
            stmt.setString(3, saque.getDate());
            stmt.setString(4, saque.getDescription());
            stmt.setString(5, saque.getSourceAccountId());
            stmt.setFloat(6, saque.getAvailableBalance());
            stmt.executeUpdate();
        }
    }

    public void update(Saques saque) throws SQLException {
        // Using column position or a different column name approach
        String sql = "UPDATE Saques SET amount = ?, data = ?, description = ?, sourceAccountId = ?, availableBalance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, saque.getAmount());
            stmt.setString(2, saque.getDate());
            stmt.setString(3, saque.getDescription());
            stmt.setString(4, saque.getSourceAccountId());
            stmt.setFloat(5, saque.getAvailableBalance());
            stmt.setString(6, saque.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Saques WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public Saques getById(String id) throws SQLException {
        String sql = "SELECT id, amount, data, description, sourceAccountId, availableBalance FROM Saques WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Saques(
                        rs.getString("id"),
                        rs.getFloat("amount"),
                        rs.getString("data"),
                        rs.getString("description"),
                        rs.getString("sourceAccountId"),
                        rs.getFloat("availableBalance")
                );
            }
        }
        return null;
    }

    public List<Saques> getAll() throws SQLException {
        List<Saques> lista = new ArrayList<>();
        String sql = "SELECT id, amount, data, description, sourceAccountId, availableBalance FROM Saques";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Saques saque = new Saques(
                        rs.getString("id"),
                        rs.getFloat("amount"),
                        rs.getString("data"),
                        rs.getString("description"),
                        rs.getString("sourceAccountId"),
                        rs.getFloat("availableBalance")
                );
                lista.add(saque);
            }
        }
        return lista;
    }
}
