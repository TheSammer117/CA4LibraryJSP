/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.UserDAO;
import DTOs.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class EditUserDetailsCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        
        //get parameters from the form where user entered values
        int userID = Integer.parseInt(request.getParameter("userID"));
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
                && (!email.equals("")) 
                && (!password.equals(""))
                && (!firstName.equals(""))
                && (!lastName.equals(""))){
            try{
            UserDAO uDAO = new UserDAO("librarydb") ;
            //aedit user details
            int edited = uDAO.updateUserProfile(email, password, firstName, lastName, primaryAddressLine1, primaryAddressLine2, primaryTown, primaryCountry, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCountry, optEircode);
            //get session so that we can hold the edited user details;
            HttpSession session = request.getSession();
            session.setAttribute("userEdited", edited);
            
            //if the user is edited successfully
            if(edited >0){
                User user = uDAO.findUserByID(userID);
                session.setAttribute("userInfo", user);
            }
            forwardToJsp = "";
            
            }catch(NumberFormatException e){
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Text was supplied for the amount to be updated by.");
            }
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
