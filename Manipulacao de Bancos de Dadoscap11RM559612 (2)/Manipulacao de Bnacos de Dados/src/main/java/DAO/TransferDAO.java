
package DAO;

import models.Transfer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO {

    private final Connection connection;

    public TransferDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Transfer transfer) throws SQLException {
        // Using column position instead of column name to avoid reserved keyword issues
        String sql = "INSERT INTO Transfer VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transfer.getId());
            stmt.setFloat(2, transfer.getAmount());
            stmt.setString(3, transfer.getDate());
            stmt.setString(4, transfer.getDescription());
            stmt.setString(5, transfer.getSourceAccountId());
            stmt.setString(6, transfer.getTargetAccountId());
            stmt.setFloat(7, transfer.getAvailableBalance());
            stmt.executeUpdate();
        }
    }

    public void update(Transfer transfer) throws SQLException {
        // Using column position or a different column name approach
        String sql = "UPDATE Transfer SET amount = ?, data = ?, description = ?, sourceAccountId = ?, targetAccountId = ?, availableBalance = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, transfer.getAmount());
            stmt.setString(2, transfer.getDate());
            stmt.setString(3, transfer.getDescription());
            stmt.setString(4, transfer.getSourceAccountId());
            stmt.setString(5, transfer.getTargetAccountId());
            stmt.setFloat(6, transfer.getAvailableBalance());
            stmt.setString(7, transfer.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Transfer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public Transfer getById(String id) throws SQLException {
        String sql = "SELECT id, amount, data, description, sourceAccountId, targetAccountId, availableBalance FROM Transfer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Transfer(
                        rs.getString("id"),
                        rs.getFloat("amount"),
                        rs.getString("data"),
                        rs.getString("description"),
                        rs.getString("sourceAccountId"),
                        rs.getString("targetAccountId"),
                        rs.getFloat("availableBalance")
                );
            }
        }
        return null;
    }

    public List<Transfer> getAll() throws SQLException {
        List<Transfer> lista = new ArrayList<>();
        String sql = "SELECT id, amount, data, description, sourceAccountId, targetAccountId, availableBalance FROM Transfer";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transfer transfer = new Transfer(
                        rs.getString("id"),
                        rs.getFloat("amount"),
                        rs.getString("data"),
                        rs.getString("description"),
                        rs.getString("sourceAccountId"),
                        rs.getString("targetAccountId"),
                        rs.getFloat("availableBalance")
                );
                lista.add(transfer);
            }
        }
        return lista;
    }
}
