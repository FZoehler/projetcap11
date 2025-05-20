package br.com.fiap.servlet;

import DAO.CorrentistaDAO;
import DAO.DAOFactory;
import models.Correntista;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String password = request.getParameter("password");
        
        try {
            Correntista correntista = new Correntista();
            correntista.setIdCorrentista("C" + UUID.randomUUID().toString().substring(0, 8));
            correntista.setNomeCompleto(nome);
            correntista.setEmail(email);
            correntista.setTelefone(telefone);
            correntista.setEndereco(endereco);
            correntista.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            
            CorrentistaDAO dao = new CorrentistaDAO(DAOFactory.getConnection()) {
                @Override
                public void update(Correntista entity) throws SQLException {}
                @Override
                public void delete(String id) throws SQLException {}
                @Override
                public Correntista findById(String id) throws SQLException { return null; }
                @Override
                public List<Correntista> findAll() throws SQLException { return null; }
            };
            
            dao.insert(correntista);
            
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            request.setAttribute("error", "Error during registration: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}