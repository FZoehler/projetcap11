package DAO;

import models.Correntista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class CorrentistaDAO implements DAO<Correntista> {
    private final Connection connection;

    public CorrentistaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Correntista correntista) throws SQLException {
        String sql = "INSERT INTO Correntista (IDCorrentista, NomeCompleto, Telefone, Email, Endereco, DadosPessoais, Password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correntista.getIdCorrentista());
            stmt.setString(2, correntista.getNomeCompleto());
            stmt.setString(3, correntista.getTelefone());
            stmt.setString(4, correntista.getEmail());
            stmt.setString(5, correntista.getEndereco());
            stmt.setString(6, correntista.getDadosPessoais());
            stmt.setString(7, correntista.getPassword());
            stmt.executeUpdate();
        }
    }

    public Correntista findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Correntista WHERE Email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Correntista correntista = new Correntista();
                correntista.setIdCorrentista(rs.getString("IDCorrentista"));
                correntista.setNomeCompleto(rs.getString("NomeCompleto"));
                correntista.setTelefone(rs.getString("Telefone"));
                correntista.setEmail(rs.getString("Email"));
                correntista.setEndereco(rs.getString("Endereco"));
                correntista.setDadosPessoais(rs.getString("DadosPessoais"));
                correntista.setPassword(rs.getString("Password"));
                return correntista;
            }
        }
        return null;
    }
}