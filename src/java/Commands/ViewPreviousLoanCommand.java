/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.LoanDAO;
import DTOs.Loan;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class ViewPreviousLoanCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        
        //get user id
        int userID = Integer.parseInt(request.getParameter("userID"));
        
        if(userID > 0){
        //create dao and retrieve all current active loans from the database
        LoanDAO lDAO = new LoanDAO("librarydb");
        ArrayList<Loan> loanList = lDAO.getPreviousLoansByUserID(userID);
        
        //get the list of loan to session
        HttpSession session = request.getSession();
        session.setAttribute("viewLoan", loanList);
        
        forwardToJsp = "";
        }else{
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            // Add an error message to the session to be displayed on the error page
            session.setAttribute("errorMessage", "The userID is missing.");
        }
        return forwardToJsp;
    }

    
}
