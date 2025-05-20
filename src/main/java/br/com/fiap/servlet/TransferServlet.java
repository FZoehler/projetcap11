package br.com.fiap.servlet;

import br.com.fiap.dao.TransferDAO;
import br.com.fiap.model.Transfer;
import br.com.fiap.model.Correntista;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/transfer.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            Correntista user = (Correntista) request.getSession().getAttribute("user");
            String targetAccount = request.getParameter("targetAccount");
            float amount = Float.parseFloat(request.getParameter("amount"));
            String description = request.getParameter("description");
            
            Transfer transfer = new Transfer(
                "TRF" + UUID.randomUUID().toString().substring(0, 8),
                amount,
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()),
                description,
                user.getIdContaCorrente(),
                targetAccount,
                10000.0f // Example available balance
            );
            
            TransferDAO dao = new TransferDAO(DAOFactory.getConnection());
            dao.insert(transfer);
            
            request.setAttribute("success", "Transfer completed successfully!");
        } catch (Exception e) {
            request.setAttribute("error", "Error processing transfer: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/transfer.jsp").forward(request, response);
    }
}