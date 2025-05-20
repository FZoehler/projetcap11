package DAO;

import models.Fundos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FundosDAO {

    private Connection connection;

    public FundosDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Fundos f) throws SQLException {
        String sql = "INSERT INTO Fundos (id, balance, instituicoesFinanceiras, fundosConservadores, fundosAltoRisco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, f.getIdFundos());
            stmt.setFloat(2, f.getBalance());
            stmt.setString(3, f.getInstituicoesFinanceiras());
            stmt.setString(4, f.getFundosConservadores());
            stmt.setString(5, f.getFundosAltoRisco());
            stmt.executeUpdate();
        }
    }

    public void update(Fundos f) throws SQLException {
        String sql = "UPDATE Fundos SET balance = ?, instituicoesFinanceiras = ?, fundosConservadores = ?, fundosAltoRisco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setFloat(1, f.getBalance());
            stmt.setString(2, f.getInstituicoesFinanceiras());
            stmt.setString(3, f.getFundosConservadores());
            stmt.setString(4, f.getFundosAltoRisco());
            stmt.setString(5, f.getIdFundos());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Fundos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    public Fundos getById(String id) throws SQLException {
        String sql = "SELECT * FROM Fundos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Fundos(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("instituicoesFinanceiras"),
                        rs.getString("fundosConservadores"),
                        rs.getString("fundosAltoRisco")
                );
            }
        }
        return null;
    }

    public List<Fundos> getAll() throws SQLException {
        List<Fundos> lista = new ArrayList<>();
        String sql = "SELECT * FROM Fundos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fundos f = new Fundos(
                        rs.getString("id"),
                        rs.getFloat("balance"),
                        rs.getString("instituicoesFinanceiras"),
                        rs.getString("fundosConservadores"),
                        rs.getString("fundosAltoRisco")
                );
                lista.add(f);
            }
        }
        return lista;
    }
}
