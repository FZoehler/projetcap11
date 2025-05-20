package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.ContaCorrente;

public class ContaCorrenteDAO {
    private Connection conn;

    public ContaCorrenteDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean existeConta(String idContaCorrente) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ContaCorrente WHERE idContaCorrente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idContaCorrente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void insert(ContaCorrente conta) throws SQLException {
        if (existeConta(conta.getIdContaCorrente())) {
            throw new SQLException("O ID " + conta.getIdContaCorrente() + " já existe.");
        }

        String sql = "INSERT INTO ContaCorrente (idContaCorrente, limiteDeCredito, cartoes, chequeEspecial) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getIdContaCorrente());
            stmt.setDouble(2, conta.getLimiteDeCredito());
            stmt.setDouble(3, conta.getCartoes());
            stmt.setDouble(4, conta.getChequeEspecial());
            stmt.executeUpdate();
        }
    }
}
