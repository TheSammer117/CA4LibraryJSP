/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.TitleDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author omy
 */
public class ViewTitlesCommand  implements Command{

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
       String forwardToJsp = "";
       //create a titileDAO and retrieve all titles from the database.
       //to use a parameterized version of the DAO claas
       
       TitleDAO tDAO = new TitleDAO("librarydb");
       
       //if the string title getting any feedback
        return null;
       
    }
    
    
}
