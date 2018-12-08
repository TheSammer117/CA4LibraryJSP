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
import java.util.List;
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
        
        //get the title details by title's name
        String novelName = request.getParameter("novelName");       

        //check if the novelName is not blank
        if ((novelName != null) && (!novelName.equals(""))) {
            //call the title dao method to search 
            TitleDAO tDAO = new TitleDAO("librarydb");
            List<Title> tList = tDAO.searchTitleByName(novelName);

            //check if the list of title stored any title object
            if (tList != null) {
                for (int i = 0; i <= tList.size(); i++) {
                    Title t = tList.get(i);
                    int titleID = t.getTitleID();

                    //check if any title object from the list is available to loan
                    if (tDAO.checkAvailability(titleID) == true) {
                        //get the session so we can get the user ID
                        HttpSession session = request.getSession();
                        UserDAO uDAO = new UserDAO("librarydb");
                        User user = (User) session.getAttribute("userInfo");
                        int userID = user.getUserID();
                        
                        //not sure how to set the status value
                        int status = 0;
                        LoanDAO lDAO = new LoanDAO("librarydb");
                        //create a newe Loan object to hold all infomation 
                        Loan loan = new Loan();
                        loan.setUser(user);
                        loan.setTitle(t);
                        loan.setStatus(status);
                        int result = lDAO.addLoan(loan);
                        //change the stock of the record title
                        tDAO.decreaseStock(titleID, 1);
                        //if the loan is created successfully
                        if(result >0){
                            forwardToJsp = "";
                        }else{
                            forwardToJsp = "error.jsp";
                            session.setAttribute("errorMessage", "Sorry Please try again.");
                        }
                    } else {
                        forwardToJsp = "error.jsp";
                        HttpSession session = request.getSession();
                        session.setAttribute("errorMessage", "Sorry this book is currently not available.");
                    }
                }
            } else {
                forwardToJsp = "error.jsp";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Sorry .book not found.");
            }
        } else {
            forwardToJsp = "error.jsp";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", "A parameter value required for searching was missing.");
        }
        return forwardToJsp;
    }

}
