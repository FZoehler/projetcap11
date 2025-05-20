package br.com.fiap.servlet;

import br.com.fiap.dao.*;
import br.com.fiap.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/investment")
public class InvestmentServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Connection conn = DAOFactory.getConnection();
            Correntista user = (Correntista) request.getSession().getAttribute("user");
            
            RendaFixaDAO rendaFixaDAO = new RendaFixaDAO(conn);
            RendaVariavelDAO rendaVariavelDAO = new RendaVariavelDAO(conn);
            FundosDAO fundosDAO = new FundosDAO(conn);
            
            List<RendaFixa> rendaFixa = rendaFixaDAO.getAll();
            List<RendaVariavel> rendaVariavel = rendaVariavelDAO.getAll();
            List<Fundos> fundos = fundosDAO.getAll();
            
            request.setAttribute("rendaFixa", rendaFixa);
            request.setAttribute("rendaVariavel", rendaVariavel);
            request.setAttribute("fundos", fundos);
            
        } catch (Exception e) {
            request.setAttribute("error", "Error loading investments: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/investment.jsp").forward(request, response);
    }
}