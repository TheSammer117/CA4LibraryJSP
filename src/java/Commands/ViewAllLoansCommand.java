/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.LoanDAO;
import DTOs.Loan;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 *
 * @author Haiyun Yu d00188956
 */
public class ViewAllLoansCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        //get userID from request
        int userID = Integer.parseInt(request.getParameter("userID"));
        if (userID > 0) {
            //create a loanDAO and retrieve the list of loans by userID
            LoanDAO lDAO = new LoanDAO("librarydb");
            //get all loans from the database
            List<Loan> loanList = lDAO.getAllLoansByUserID(userID);

            //add the list of loans to the session
            HttpSession session = request.getSession();
            session.setAttribute("loanList", lDAO);

            forwardToJsp = "";
        } else {
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            // Add an error message to the session to be displayed on the error page
            // This lets us inform the user about what went wrong
            session.setAttribute("errorMessage", "A parameter value required for searching was missing");
        }
        return forwardToJsp;
    }

}
