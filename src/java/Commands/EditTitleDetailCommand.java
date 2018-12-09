/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import DAOs.TitleDAO;
import DTOs.Title;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu
 * @author Haiyun Yu d00188956 
 */
public class EditTitleDetailCommand implements Command {

    @Override
    public String exceute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "";
        
        //get parameters from request
        int titleID = Integer.parseInt(request.getParameter("titileID"));
        String novelName = request.getParameter("novelName");
        String author = request.getParameter("author");
        int stock = Integer.parseInt(request.getParameter("stock"));
        int onLoan = Integer.parseInt(request.getParameter("onLoan"));
        String titleDescription = request.getParameter("titleDescription");
        
        if(titleID > 0){
            //call the tDAO method to update the exited title's information
            TitleDAO tDAO = new TitleDAO("librarydb");
            Title t = new Title(titleID, novelName, author, stock, onLoan, titleDescription);
            boolean updated = tDAO.updateTitle(titleID, t);
            HttpSession session = request.getSession();
            session.setAttribute("updateTitle", updated);
            
            //if the update is successfully
            if(updated == true){
                Title updatedTitle = tDAO.searchByID(titleID);
                session.setAttribute("titleDetails", updatedTitle);
            }
            
            forwardToJsp = "";
        }
        return forwardToJsp;
    }
    
}
