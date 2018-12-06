/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTOs.Genre;
import DTOs.Title;
import DTOs.TitleGenre;
import java.util.List;

 /**
 * Team: Hernel Provido, Sami Mahmoud, Haiyun Yu 
 * @author Haiyun Yu d00188956
 */
public interface TitleGenreDAOInterface {
    
    //add a new TitleGenre link
    public boolean addTitlegenre(Genre g, Title t);
    //Get genres for a specified book
    public String getGenreByTitleID(int titleID);
    //get books for a specified genre
    public List<TitleGenre> getAllTitlegenre();
    
    
    
}
