package br.com.fiap.oracle;


import DAO.ContaCorrenteDAO;
import DAO.CorrentistaDAO;
import models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DAOFactory.getConnection()) {
            // Generate a unique account ID
            String idConta = generateUniqueAccountId(conn);

            ContaCorrente conta = new ContaCorrente(idConta, 10000.0f, 5000.0f, 2000.0f);
            Correntista cliente = new Correntista("C001", "João Silva", "(11) 99999-8888",
                    "joao@email.com", "Rua A, 123", "CPF: 000.000.000-00");
            cliente.setIdContaCorrente(idConta);

            Deposito deposito = new Deposito("DEP" + UUID.randomUUID().toString().substring(0, 3), 2000f, dataAtual(), "Depósito inicial", "CC002");
            Saques saque = new Saques("SAQ" + UUID.randomUUID().toString().substring(0, 3), 500f, dataAtual(), "Saque", idConta, 10000f);
            Transfer transf = new Transfer("TRF" + UUID.randomUUID().toString().substring(0, 3), 300f, dataAtual(), "Transferência", idConta, "CC003", 10000f);

            ContaCorrenteDAO dao = new ContaCorrenteDAO(conn);
            dao.insert(conta);

            new CorrentistaDAO(conn) {
                @Override
                public void update(Correntista entity) throws SQLException {

                }

                @Override
                public void delete(String id) throws SQLException {

                }

                @Override
                public Correntista findById(String id) throws SQLException {
                    return null;
                }

                @Override
                public List<Correntista> findAll() throws SQLException {
                    return List.of();
                }
            }.insert(cliente);
            new DepositoDAO(conn).insert(deposito);
            new SaquesDAO(conn).insert(saque);
            new TransferDAO(conn).insert(transf);

            System.out.println("✅ Inserções realizadas com sucesso com o ID: " + idConta);

        } catch (SQLException e) {
            System.out.println("❌ Erro durante as inserções: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String generateUniqueAccountId(Connection conn) throws SQLException {
        ContaCorrenteDAO dao = new ContaCorrenteDAO(conn);
        String prefix = "CC";
        int counter = 1;

        while (true) {
            String candidateId = prefix + String.format("%03d", counter);
            if (!dao.existeConta(candidateId)) {
                return candidateId;
            }
            counter++;
        }
    }

    private static String dataAtual() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}

