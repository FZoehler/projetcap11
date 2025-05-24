package br.com.fiap.dao;

import br.com.fiap.models.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements DAO<Usuario> {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (id, email, senha, nome) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getId());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getNome());
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET email = ?, senha = ?, nome = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, usuario.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Usuario findById(String id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("id"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("nome")
                );
            }
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                usuarios.add(new Usuario(
                    rs.getString("id"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("nome")
                ));
            }
        }
        return usuarios;
    }

    public Usuario findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                    rs.getString("id"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("nome")
                );
            }
        }
        return null;
    }
}