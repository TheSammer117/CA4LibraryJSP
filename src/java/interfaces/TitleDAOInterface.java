/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTOs.Genre;
import DTOs.Title;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Haiyun Yu D00188956
 */
public interface TitleDAOInterface {

    //viewing the details of all books in the library;
    public ArrayList<Title> getAllTitles();

    //adding a new book in the library;
    public boolean addTitle(Title title, Genre g);

    //removing a title by its id;
    public boolean removeTitleByID(int titleID);

    //search a title by the title's name;
    public List<Title> searchTitleByName(String name);
    
    //increase the stock of titles
    public boolean increaseStock(int titleID, int stock);
    
    //decrease the stock of titles
    public boolean decreaseStock(int titleID, int stock);

    //search title by id
    public Title searchByID(int id);

    //update information of a specifed title 
    public boolean updateTitle(int id, Title title);
    
    //check the availablity of a title by its id
    public boolean checkAvailability(int titleID);

}
