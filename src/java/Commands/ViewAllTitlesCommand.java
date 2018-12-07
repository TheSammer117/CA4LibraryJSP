/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.TitleDAO;
import DTOs.Title;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author omy
 */
public class ViewAllTitlesCommand  implements Command{

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
       String forwardToJsp = "";
       //create a titileDAO and retrieve all titles from the database.
       //to use a parameterized version of the DAO claas
       
       TitleDAO tDAO = new TitleDAO("librarydb");
       //get all titles from the database and store all titles into one arraylist
       ArrayList<Title> tList = tDAO.getAllTitles();
       
       
       //add the retrieved list to the session so that the JSP can access it 
       //and display all inforamtion;
       //Get the session so that we can add new variable into it for storage.
       HttpSession session = request.getSession();
       
       //store the list in the session
       session.setAttribute("titleList", tList);
       //set the page to be viewed to the results page
       forwardToJsp = "";
       
       
        return forwardToJsp;
       
    }
    
    
}
