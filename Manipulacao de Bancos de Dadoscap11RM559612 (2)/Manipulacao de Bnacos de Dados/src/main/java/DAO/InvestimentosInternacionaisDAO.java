package DAO;

import models.InvestimentosInternacionais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvestimentosInternacionaisDAO {

    private Connection connection;

    public InvestimentosInternacionaisDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(InvestimentosInternacionais i) throws SQLException {
        String sql = "INSERT INTO InvestimentosInternacionais (id, balance, euaS_P500, euaNasdaq, euaDowJones, europaDax, europaIbex35, europaEuroStoxx50) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, i.getIdInvestimentosInternacionais());
            stmt.setFloat(2, i.getBalance());
            stmt.setString(3, i.getEuaS_P500());
            stmt.setString(4, i.getEuaNasdaq());
            stmt.setString(5, i.getEuaDowJones());
            stmt.setString(6, i.getEuropaDax());
            stmt.setString(7, i.getEuropaIbex35());
            stmt.setString(8, i.getEuropaEuroStoxx50());
            stmt.executeUpdate();
        }
    }

    public void update(InvestimentosInternacionais i) throws SQLException {
        String sql = "UPDATE InvestimentosInternacionais SET balance = ?, euaS_P500 = ?, euaNasdaq = ?, euaDowJones = ?, europaDax = ?, europaIbex35 = ?, europaEuroStoxx50 = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, i.getBalance());
            stmt.setString(2, i.getEuaS_P500());
            stmt.setString(3, i.getEuaNasdaq());
            stmt.setString(4, i.getEuaDowJones());
            stmt.setString(5, i.getEuropaDax());
            stmt.setString(6, i.getEuropaIbex35());
            stmt.setString(7, i.getEuropaEuroStoxx50());
            stmt.setString(8, i.getIdInvestimentosInternacionais());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM InvestimentosInternacionais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public InvestimentosInternacionais getById(String id) throws SQLException {
        String sql = "SELECT * FROM InvestimentosInternacionais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new InvestimentosInternacionais(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("euaS_P500"),
                        rs.getString("euaNasdaq"),
                        rs.getString("euaDowJones"),
                        rs.getString("europaDax"),
                        rs.getString("europaIbex35"),
                        rs.getString("europaEuroStoxx50")
                );
            }
        }
        return null;
    }

    public List<InvestimentosInternacionais> getAll() throws SQLException {
        List<InvestimentosInternacionais> lista = new ArrayList<>();
        String sql = "SELECT * FROM InvestimentosInternacionais";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                InvestimentosInternacionais i = new InvestimentosInternacionais(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("euaS_P500"),
                        rs.getString("euaNasdaq"),
                        rs.getString("euaDowJones"),
                        rs.getString("europaDax"),
                        rs.getString("europaIbex35"),
                        rs.getString("europaEuroStoxx50")
                );
                lista.add(i);
            }
        }
        return lista;
    }
}
