package br.com.fiap.servlet;

import br.com.fiap.dao.CorrentistaDAO;
import br.com.fiap.model.Correntista;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
            
            CorrentistaDAO dao = new CorrentistaDAO(DAOFactory.getConnection());
            dao.insert(correntista);
            
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            request.setAttribute("error", "Error during registration: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}