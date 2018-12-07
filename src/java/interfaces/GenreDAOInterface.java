/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTOs.Genre;
import java.util.List;

/**
 *Team: Hernel Provido, Sami Mahmoud, Haiyun Yu 
 *  @author Hernel Provido d00182295
 */
public interface GenreDAOInterface {
    //add a new genre
    public boolean addGenre(Genre g);
    //get all genres
    public List<Genre> getAllGenre();
    //get specific genre by id
    public Genre searchGenreByid(int genreID);
    
}
