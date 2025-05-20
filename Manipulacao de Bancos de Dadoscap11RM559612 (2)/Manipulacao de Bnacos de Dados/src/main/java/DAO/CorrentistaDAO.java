package DAO;

import models.Correntista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class CorrentistaDAO implements DAO<Correntista> {
    private final Connection connection;

    public CorrentistaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Correntista correntista) throws SQLException {
        String sql = "INSERT INTO Correntista (IDCorrentista, NomeCompleto, Telefone, Email, Endereco, DadosPessoais) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, correntista.getIdCorrentista());
            stmt.setString(2, correntista.getNomeCompleto());
            stmt.setString(3, correntista.getTelefone());
            stmt.setString(4, correntista.getEmail());
            stmt.setString(5, correntista.getEndereco());
            stmt.setString(6, correntista.getDadosPessoais());

            stmt.executeUpdate();
        }
    }


}
