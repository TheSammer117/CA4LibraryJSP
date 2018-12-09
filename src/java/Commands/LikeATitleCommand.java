/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.TitleDAO;
import DAOs.UserDAO;
import DTOs.Likes;
import DTOs.Title;
import DTOs.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author d00188956 
 */
public class LikeATitleCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";

        //get the information
        int titleID = Integer.parseInt(request.getParameter("titleID"));
        int userID = Integer.parseInt(request.getParameter("userID "));

        //check if user id is not blank
        try {
            //call TitleDAO method to search title object by specified id;
            TitleDAO tDAO = new TitleDAO("librarydb");
            Title t = tDAO.searchByID(titleID);
            //call UserDAO method to search user by specified id;
            UserDAO uDAO = new UserDAO("librarydb");
            User u = uDAO.findUserByID(userID);
            //search user by id 
            boolean done = uDAO.likeATitle(t, u);
            Likes like = null;
            
            if(done == true){
                like = uDAO.showLikedTitle(t);
            }

            //set the session to hold the user information
            HttpSession session = request.getSession();

            session.setAttribute("likesInfo", like);

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
