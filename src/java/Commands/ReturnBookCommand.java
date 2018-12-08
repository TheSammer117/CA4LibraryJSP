/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.LoanDAO;
import DAOs.TitleDAO;
import DTOs.Loan;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 *
 * @author Haiyun Yu d00188956
 */
public class ReturnBookCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        //get the parameters and convert it to integer
        int loanID = Integer.parseInt(request.getParameter("loanID"));

        try {
            //call the LoanDAO method to return book
            LoanDAO lDAO = new LoanDAO("librarydb");
            //call the LoanDAO method to update the stock of title
            TitleDAO tDAO = new TitleDAO("librarydb");
            //get the loan object by loanID and update the status of loan
            Loan loan = lDAO.getLoanByID(loanID);
            //update the status of that loan
            loan.setStatus(0);
            int status = loan.getStatus();

            //get the session so that we can add the update result to it
            HttpSession session = request.getSession();
            //return book by update the status of the loan
            Boolean check = lDAO.updateLoanStatus(loanID, status);
            //if book returned successfully
            if(check == true){            
            session.setAttribute("bookReturned", loan);
            //set the jsp page to be viewed for the result;
            forwardToJsp = "";
            }else{
            session.setAttribute("returnFailed", loan);
            //set the jsp page to be viewed for the result;
            forwardToJsp = "";
            }           
        }//deal with where the user supplied text instead of number
        catch (NumberFormatException e) {
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();
            // Add an error message to the session to be displayed on the error page
            // This lets us inform the user about what went wrong
            session.setAttribute("errorMessage", "Text was supplied for the amount to be updated by.");
        }

        return forwardToJsp;
    }

}
