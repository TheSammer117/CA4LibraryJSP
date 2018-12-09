/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.TitleDAO;
import DTOs.Title;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omy
 */
public class ViewTitleDetailCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        //get the parameter from the request
        int titleID = Integer.parseInt(request.getParameter("titleID"));
                
        if(titleID > 0){
        //call the TitleDAO method to search a title by id
        TitleDAO tDAO = new TitleDAO("librarydb");        
        Title t = tDAO.searchByID(titleID);
        
        //get the session so that we can add the chosen title to store;
        HttpSession session = request.getSession();
        session.setAttribute("titleInfo", t);
        //set the page to view the title
        forwardToJsp = "";       
        }else{
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            // Add an error message to the session to be displayed on the error page
            session.setAttribute("errorMessage", "Sorry this book is not found.");
        }
        
        return forwardToJsp;
    }
    
}
