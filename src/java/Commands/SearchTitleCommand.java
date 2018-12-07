/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            if(novelName != null || !novelName.endsWith("")){
                
            }
            

            //using searchTitleByName method on this command;
            return null;
    }

    
}
