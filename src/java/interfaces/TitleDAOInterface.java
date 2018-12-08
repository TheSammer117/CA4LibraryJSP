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

    //update the details of a exited title in the library;
    public boolean updateTitleDetail(int titleID, Title title);

    //removing a title by its id;
    public boolean removeTitleByID(int titleID);

    //search a title by the title's name;
    public List<Title> searchTitleByName(String name);
    //decrease/increase the stock of titles
    //   private boolean changeStock(int titleID, int stock, String options);
    public boolean increaseStock(int titleID, int stock);

    public boolean decreaseStock(int titleID, int stock);

    public Title searchByID(int id);

    public boolean updateTitle(int id, Title title);

    public boolean checkAvailability(int titleID);

}
