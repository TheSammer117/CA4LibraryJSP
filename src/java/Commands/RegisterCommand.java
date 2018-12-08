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
public class RegisterCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        
        //get parameters from the form where user entered values
        //register(String email, String password, String firstName, String lastName, 
        //String primaryAddressLine1, String primaryAddressLine2, String primaryTown, String primaryCounty, String primaryEircode, 
        //String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode)
        String email = request.getParameter("email");
        String  password= request.getParameter("password");
        String  firstName= request.getParameter("firstName");
        String  lastName= request.getParameter("lastName");
        
        return forwardToJsp;
    }

    
}
