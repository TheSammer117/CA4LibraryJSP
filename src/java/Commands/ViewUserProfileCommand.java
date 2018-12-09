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
public class ViewUserProfileCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";

        //get the information
        int userID = Integer.parseInt(request.getParameter("userID "));

        //check if user id is not blank
        try {
            //search user by id 
            UserDAO uDAO = new UserDAO("librarydb");
            User user = uDAO.findUserByID(userID);

            //set the session to hold the user information
            HttpSession session = request.getSession();

            session.setAttribute("userInfo", user);

            forwardToJsp = "";

        } catch (NumberFormatException e) {
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();
            // Add an error message to the session to be displayed on the error page
            // This lets us inform the user about what went wrong
            session.setAttribute("errorMessage", "the user id required for display current user details is missing.");

        }

        return forwardToJsp;
    }
}
