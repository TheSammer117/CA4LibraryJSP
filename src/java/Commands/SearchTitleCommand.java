/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.TitleDAO;
import DTOs.Title;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class SearchTitleCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
            String forwardToJsp = "";
            
            //get the title's name entered into the form by user
            String novelName = request.getParameter("novelName");
            //if novelName object gets any information
            if((novelName != null) && (!novelName.equals(""))){
                //call on DAO method to search title by its name;
                TitleDAO tDAO = new TitleDAO("librarydb");
                //get the list of titles by searching name
                List<Title> tList = tDAO.searchTitleByName(novelName);
                
                //if the list gets stores any title object
                if((tList != null) && (!tList.isEmpty())){
                    //get the session so that we can pass information to it; 
                HttpSession session = request.getSession();
                
                //get the result to the session matching with the supplied name
                session.setAttribute("searchTitle", tList);
                //forward to the jsp page to check all of the result 
                forwardToJsp = ""; 
                
                }else{
                    //to view the error page if novelname is not getting any input
                forwardToJsp = "error.jsp";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Sorry book not found.");
                }
                               
            }else{
                //to view the error page if novelname is not getting any input
                forwardToJsp = "error.jsp";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", "Please enter a title name.");
            }           

            return forwardToJsp;
    }

    
}
