/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class ChangeLanguageCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        
        String language = request.getParameter("language");
        
        if(language != null){
            //create a locale based on the suuplied language
            Locale currentLocale = new Locale(language);
            //sotre the locale and the chosen language in the session
            HttpSession session = request.getSession();
            session.setAttribute("currentLocale", language);
            session.setAttribute("language", language);
            //reset the resource bundle so that it will be updated
            //to reflect the current locale when the page is reloaded
            session.setAttribute("dataBundle", null);
        }
        
        try{
            // Find the page path that sent us here
            String refererPage = new URI(request.getHeader("referer")).getPath();
            // Break the page path up into pieces based on /
            String[] pathPieces = refererPage.split("/");
            // Get the actual page name (this will always be the last part)
            forwardToJsp = pathPieces[pathPieces.length-1];
            
        } catch (URISyntaxException ex)
        {
            //Display an error message to the log
            System.out.println("An error occured when trying to get the page that sent the client here: " + ex.getMessage());
            // If something goes wrong, default the user back to the home page
            forwardToJsp = "index.jsp";
        }    
        return forwardToJsp;
    }

    
}
