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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
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
            
            Correntista correntista = dao.findByEmail(email);
            
            if (correntista != null && BCrypt.checkpw(password, correntista.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", correntista);
                response.sendRedirect(request.getContextPath() + "/dashboard");
            } else {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred during login");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}