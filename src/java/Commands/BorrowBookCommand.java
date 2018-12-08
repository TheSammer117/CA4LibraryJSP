/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.LoanDAO;
import DAOs.TitleDAO;
import DAOs.UserDAO;
import DTOs.Loan;
import DTOs.Title;
import DTOs.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class BorrowBookCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        
        //get the parameters while user required to borrow a title;
        int titleID = Integer.parseInt(request.getParameter("titleID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        
        //if titleid and userid is not blank
        if(titleID >0 && userID >0){
            //call TitleDAO method to search title object by specified id;
            TitleDAO tDAO = new TitleDAO("librarydb");
            Title t = tDAO.searchByID(titleID);
            //call UserDAO method to search user by specified id;
            UserDAO uDAO = new UserDAO("librarydb");
            User u = uDAO.findUserByID(userID);
            
            //set up status for this loan
            int status = 0;
            //call LoanDAO to create a new Loan object
            LoanDAO lDAO = new LoanDAO("librarydb");
            Loan loan = new Loan(u, t, status);
            
            //record the current timestamp as the loan started date
            long time = System.currentTimeMillis();
            java.sql.Date currentDate = new java.sql.Date(time);
            loan.setDayStarted(currentDate);
            
            //get the result by adding a new loan
            int result = lDAO.addLoan(loan);
            
            if(result >0){
            //get the session so that we can add info into it
            HttpSession session = request.getSession();
            session.setAttribute("bookBorrowed", loan); 
            //set the jsp page to display result;
            forwardToJsp = "";
            }else{
            // Set the page to be viewed the error
            forwardToJsp = "";
            }
        }else {
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            // Add an error message to the session to be displayed on the error page
            // This lets us inform the user about what went wrong
            session.setAttribute("errorMessage", "A parameter value required for updating was missing");
        }
        return forwardToJsp;
    }
    }
