/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.LoanDAO;
import DTOs.Loan;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class PayOverdueFeeCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";

        //get the parameter to hold user id
        int userID = Integer.parseInt(request.getParameter("userID"));
        
        if (userID > 0) {
            //call the LoanDAO method to search all loans by specified user id 
            LoanDAO lDAO = new LoanDAO("librarydb");
            ArrayList<Loan> loanList = lDAO.getActiveLoansByUserID(userID);

            //get the session so that we can add result to it
            HttpSession session = request.getSession();
            
            for (int i = 1; i <= loanList.size(); i++) {
                Loan loan = loanList.get(i);
                //get the due date from the specified loan
                Date dueDate = loan.getDueDate();
                //get the current Date
                long time = System.currentTimeMillis();
                Date currentDate = new Date(time);   
                //check if the currentDate is over the due Date of the Loan object
                if (currentDate.after(dueDate)) {
                    session.setAttribute("overdueFee", loan);
                }
            }            
            forwardToJsp = "";
        }else{
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            // Add an error message to the session to be displayed on the error page
            session.setAttribute("errorMessage", "A userID is not found.");
        }
        return forwardToJsp;
    }

}
