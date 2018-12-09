/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.GenreDAO;
import DAOs.TitleDAO;
import DTOs.Genre;
import DTOs.Title;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omy
 */
public class AddTitleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";

        //get parameters from the form
        int titleID = Integer.parseInt(request.getParameter("titleID"));
        String novelName = request.getParameter("novelName");
        String author = request.getParameter("author");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int onLoan = Integer.parseInt(request.getParameter("onLoan"));
        String titleDescription = request.getParameter("titleDescription");
        String genre = request.getParameter("genre");

        //check if all variables are not null
        if (titleID > 0
                && novelName != null
                && author != null
                && stock >= 0
                && onLoan >= 0
                && titleDescription != null
                && (!novelName.equals(""))
                && (!author.equals(""))
                && (!titleDescription.equals(""))) {
            //call titleDAO method to add new title
            TitleDAO tDAO = new TitleDAO("librarydb");
            Title title = new Title(titleID, novelName, author, stock, onLoan, titleDescription);
            
            //call genreDAO method to search Genre
            GenreDAO gDAO= new GenreDAO("librarydb");
            Genre g =gDAO.searchGenreByGenre(genre);
            
            Boolean addedTitle = tDAO.addTitle(title, g);
            //get the session to hold information
            HttpSession session = request.getSession();
            if(addedTitle == true){
                Title newTitle = tDAO.searchByID(titleID);
                session.setAttribute("addedTitle", newTitle);
            }
            // Set the page to be viewed to the results page
                forwardToJsp = "";
        }else{
            // Set the page to be viewed to the error page
            forwardToJsp = "error.jsp";
            // Get the session so we can add information to it
            HttpSession session = request.getSession();

            session.setAttribute("errorMessage", "A parameter values required for adding was missing.");
        }

        return forwardToJsp;
    }

}
