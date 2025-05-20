package DAO;

import models.Deposito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepositoDAO {

    private Connection connection;

    public DepositoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Deposito deposito) throws SQLException {
        String sql = "INSERT INTO Deposito (id, amount, data_operacao, description, targetAccountId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, deposito.getId());
            stmt.setFloat(2, deposito.getAmount());
            stmt.setString(3, deposito.getDate());
            stmt.setString(4, deposito.getDescription());
            stmt.setString(5, deposito.getTargetAccountId());
            stmt.executeUpdate();
        }
    }

    public void update(Deposito deposito) throws SQLException {
        String sql = "UPDATE Deposito SET amount = ?, data_operacao = ?, description = ?, targetAccountId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, deposito.getAmount());
            stmt.setString(2, deposito.getDate());
            stmt.setString(3, deposito.getDescription());
            stmt.setString(4, deposito.getTargetAccountId());
            stmt.setString(5, deposito.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Deposito WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public Deposito getById(String id) throws SQLException {
        String sql = "SELECT * FROM Deposito WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Deposito(
                        rs.getString("id"),
                        rs.getFloat("amount"),
                        rs.getString("data_operacao"),
                        rs.getString("description"),
                        rs.getString("targetAccountId")
                );
            }
        }
        return null;
    }

    public List<Deposito> getAll() throws SQLException {
        List<Deposito> lista = new ArrayList<>();
        String sql = "SELECT * FROM Deposito";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Deposito d = new Deposito(
                        rs.getString("id"),
                        rs.getFloat("amount"),
                        rs.getString("data_operacao"),
                        rs.getString("description"),
                        rs.getString("targetAccountId")
                );
                lista.add(d);
            }
        }
        return lista;
    }
}
