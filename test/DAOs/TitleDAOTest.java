/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Genre;
import DTOs.Title;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Helvin
 */
public class TitleDAOTest {
    
    /**
     *
     */
    public TitleDAOTest() {
    }
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

     //******************* START HERE ***********************************
    // Test of getAllTitles method, of class TitleDAO. . ..............................
    //******************* START HERE ***********************************

    /**
     * Checking for any returns form the database ...
     */
    @Test
    public void testGetAllTitles() {
        System.out.println("TEST 1 FOR GET ALL TITLE, CHECK IF IT WORKS....");
        TitleDAO instance = new TitleDAO("librarydb");
        ArrayList<Title> t = new ArrayList();
        Title expResult = null;
        for(int x = 0;x<t.size();x++){
            expResult = t.get(x);
        }
        ArrayList<Title> result = instance.getAllTitles();
        assertEquals(expResult, result);
  
    }
     //******************* START HERE ***********************************
    // Test of getAllTitles method, of class TitleDAO. . ..............................
    //******************* START HERE ***********************************

     //******************* START HERE ***********************************
    // Test of addTitle method, of class TitleDAO. . ..............................
    //******************* START HERE ***********************************

    /**
     *checking if add a title to our database works...
     */
    @Test
    public void testAddTitle() {
        System.out.println("TEST 1 FOR ADD TITLE, CHECK IF IT WORKS..");
        Title title = new Title("novel", "that guy", 10, 2, "that guy did it");
        Genre g = new Genre(25, "Fantasy");
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.addTitle(title, g);
        assertEquals(expResult, result);
    }
     //******************* END HERE ***********************************
    // Test of addTitle method, of class TitleDAO. . ..............................
    //******************* END HERE ***********************************
   
     //******************* START HERE ***********************************
    // Test of removeTitleByID method, of class TitleDAO.. . ..............................
    //******************* START HERE ***********************************

    /**
     *  checking if removing a title form our database by using titleID works 
     */
    @Test
    public void testRemoveTitleByID() {
        System.out.println("TEST 1 FOR REMOVE TITLE BY ID, CHECK IF IT WORKS..");
        int titleID = 3;
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.removeTitleByID(titleID);
        assertEquals(expResult, result);
    }
     //******************* START HERE ***********************************
    // Test of addLoan method, of class LoanDAO. . ..............................
    //******************* START HERE ***********************************
  
     //******************* START HERE ***********************************
    // Test of searchTitleByName method, of class TitleDAO.. . ..............................
    //******************* START HERE ***********************************

    /**
     *  Search an existing title to out database by novel name.
     */
    @Test
    public void testSearchTitleByName() {
        System.out.println("TEST 1 FOR SEARCH TITLE NY NAME, AIM TO PASS");
        String novelName = "All the King's Men";
        TitleDAO instance = new TitleDAO("librarydb");
        List<Title> t = new ArrayList();
        Title expResult = null;
        for(int x = 0;x<t.size();x++){
            if(t.get(x).getNovelName().equalsIgnoreCase(novelName)){
                expResult = t.get(x);
            }
        }
        List<Title> result = instance.searchTitleByName(novelName);
        assertEquals(expResult, result);
    }

    /**
     *if the novel name does not exist in out database it should not return anything  to the user.
     */
    @Test
    public void test2SearchTitleByName() {
        System.out.println("TEST 2 FOR SEARCH TITLE NY NAME, AIM TO FAIL");
        String novelName = "that guy";
        TitleDAO instance = new TitleDAO("librarydb");
        List<Title> t = new ArrayList();
        Title expResult = null;
        for(int x = 0;x<t.size();x++){
            if(t.get(x).getNovelName().equalsIgnoreCase(novelName)){
                expResult = t.get(x);
            }
        }
        List<Title> result = instance.searchTitleByName(novelName);
        assertEquals(expResult, result);
    }
     //******************* END HERE ***********************************
    // Test of searchTitleByName method, of class TitleDAO.. . ..............................
    //******************* END HERE ***********************************

     //******************* START HERE ***********************************
    // Test of searchByID method, of class TitleDAO. . ..............................
    //******************* START HERE ***********************************

    /**
     * Search title table in our database using an existing TitleID.
     */
    @Test
    public void testSearchByID() {
        System.out.println("TEST 1 FOR SEARCH BY ID, AIM TO PASS..");
        int id = 2;
        TitleDAO instance = new TitleDAO("librarydb");
        List<Title> t = new ArrayList();
        Title expResult = null;
        for(int x = 0;x<t.size();x++){
            if(t.get(x).getTitleID() == id){
                expResult = t.get(x);
            }
        }
        Title result = instance.searchByID(id);
        assertEquals(expResult, result);

    }

    /**
     *If TitleID does not exist it should not return anything to the user...
     */
    @Test
    public void test2SearchByID() {
        System.out.println("TEST 2 FOR SEARCH BY ID, AIM TO FAIL..");
        int id = 9999;
        TitleDAO instance = new TitleDAO("librarydb");
        List<Title> t = new ArrayList();
        Title expResult = null;
        for(int x = 0;x<t.size();x++){
            if(t.get(x).getTitleID() == id){
                expResult = t.get(x);
            }
        }
        Title result = instance.searchByID(id);
        assertEquals(expResult, result);

    }
     //******************* END HERE ***********************************
    // Test of searchByID method, of class TitleDAO. . ..............................
    //******************* END HERE ***********************************
  
     //******************* START HERE ***********************************
    // Test of addLoan method, of class LoanDAO.. . ..............................
    //******************* START HERE ***********************************

    /**
     *  Checking if updating a title in out database works... 
     */
    @Test
    public void testUpdateTitle() {
        System.out.println("TEST 1 FOR UPDATE TITLE, CHECK IF THIS WORKS");
        int id = 4;
        Title title = new Title("novel", "that guy", 10, 2, "that guy did it");
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.updateTitle(id, title);
        assertEquals(expResult, result);
     
    }
     //******************* END HERE ***********************************
    // Test of addLoan method, of class LoanDAO.. . ..............................
    //******************* END HERE ***********************************
   
     //******************* START HERE ***********************************
    // Test of checkAvailability method, of class TitleDAO.. . ..............................
    //******************* START HERE ***********************************

    /**
     * Checking availability of that title novel in our database...
     */
    @Test
    public void testCheckAvailability() {
        System.out.println("TEST 1 FOR CHECK AVAILIBILITY, CHECK IF THIS WORKS..");
        int titleID = 2;
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.checkAvailability(titleID);
        assertEquals(expResult, result);
 
    }
     //******************* START HERE ***********************************
    // Test of checkAvailability method, of class TitleDAO.. . ..............................
    //******************* START HERE ***********************************
   
     //******************* START HERE ***********************************
    // Test of increaseStock method, of class TitleDAO.. . ..............................
    //******************* START HERE ***********************************

    /**
     *  Increase the number of novel user can take from out database.
     */
    @Test
    public void testIncreaseStock() {
        System.out.println("TEST 1 FOR INCEASE STOCK, CHECK IF IT WORKS AIM TO PASS...");
        int titleID = 2;
        int stock = 5;
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.increaseStock(titleID, stock);
        assertEquals(expResult, result);
    }

    /**
     * there should not be any negative input to this method...
     */
    @Test
    public void test2IncreaseStock() {
        System.out.println("TEST 2 FOR INCEASE STOCK, CHECK IF IT WORKS AIM TO FAIl...");
        int titleID = 2;
        int stock = -10;
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = false;
        boolean result = instance.increaseStock(titleID, stock);
        assertEquals(expResult, result);
    }
     //******************* END HERE ***********************************
    // Test of increaseStock method, of class TitleDAO.. . ..............................
    //******************* END HERE ***********************************
 
     //******************* START HERE ***********************************
    // Test of decreaseStock method, of class TitleDAO. . ..............................
    //******************* START HERE ***********************************

    /**
     * Decrease the amount of novel user can take in our database..
     */
    @Test
    public void testDecreaseStock() {
        System.out.println("TEST 1 FOR DECREASE STOCK, CHECK IF THIS WORKS.. AIM TO PASS");
        int titleID = 2;
        int stock = 5;
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.decreaseStock(titleID, stock);
        assertEquals(expResult, result);
    }

    /**
     * there should not be any negative number in this method..
     */
    @Test
    public void test2DecreaseStock() {
        System.out.println("TEST 2 FOR DECREASE STOCK, CHECK IF THIS WORKS.. AIM TO FAIL");
        int titleID = 2;
        int stock = -5;
        TitleDAO instance = new TitleDAO("librarydb");
        boolean expResult = false;
        boolean result = instance.decreaseStock(titleID, stock);
        assertEquals(expResult, result);
    }
     //******************* END HERE ***********************************
    // Test of decreaseStock method, of class TitleDAO. . ..............................
    //******************* END HERE ***********************************
    
}
