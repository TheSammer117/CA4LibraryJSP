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
 *
 * @author samiwise
 */
public class LoginCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
         String forwardToJsp = null;

        //get email and password info that the user 
        //entered in the login.jsp form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && password != null && !email.equals("") && !password.equals("")) {
            UserDAO uDao = new UserDAO("librarydb");
            User u = uDao.login(email, password);
            //if user found, they exist and we log them in
            if (u != null) {
                //put user in session
                //we can track if user has logged in 
                //if it's there, they have completed this process
                //if it's not, then they haven't
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", u);
                forwardToJsp = "userProfile.jsp";
            } else {
                //The email and/or password didnt match someone in the db
                //send the user to login failed page and inform them of this
                String errorMessage = "No user found matching those details."
                        + "Please <a href='login.jsp'>go back</a> and try again";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            //The email and/or password was missing
            //send user to the loginFailed page and inform them
            String errorMessage = "Your email and/or password was missing."
                    + "Please <a href='login.jsp'>go back</a> and try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }
    
}
