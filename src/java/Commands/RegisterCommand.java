/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.UserDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class RegisterCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        
        //get parameters from the form where user entered values
        String email = request.getParameter("email");
        String  password= request.getParameter("password");
        String  firstName= request.getParameter("firstName");
        String  lastName= request.getParameter("lastName");
        String primaryAddressLine1 = request.getParameter("primaryAddressLine1");
        String primaryAddressLine2 = request.getParameter("primaryAddressLine2");
        String primaryTown = request.getParameter("primaryTown");
        String primaryCountry = request.getParameter("primaryCountry");
        String primaryEircode = request.getParameter("primaryEircode");
        String optAddressLine1 = request.getParameter("optAddressLine1");
        String optAddressLine2 = request.getParameter("optAddressLine2");
        String optTown = request.getParameter("optTown");
        String optCountry = request.getParameter("optCountry");
        String optEircode = request.getParameter("optEircode");
        
        //check if above paramaters are not blank
        if(email != null 
                && password != null 
                && firstName != null
                && lastName != null
                && primaryAddressLine1 != null
                && primaryAddressLine2 != null
                && primaryTown != null
                && primaryCountry != null
                && primaryEircode != null
                && optAddressLine1 != null
                && optAddressLine2 != null
                && optTown != null
                && optCountry != null
                && optEircode != null
                && (!email.equals("")) 
                && (!password.equals(""))
                && (!firstName.equals(""))
                && (!lastName.equals(""))){
            UserDAO uDAO = new UserDAO("librarydb") ;
            //get session
            HttpSession session = request.getSession();
            //add new user
            int registed = uDAO.register(email, password, firstName, lastName, primaryAddressLine1, primaryAddressLine2, primaryTown, primaryCountry, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCountry, optEircode);
            session.setAttribute("register", registed);
            forwardToJsp = "";
        }else{
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            // Add an error message to the session to be displayed on the error page
            session.setAttribute("errorMessage", "Some parameters is blank;");
        }
        return forwardToJsp;
    }

    
}
