/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Genre;
import DTOs.Title;
import DTOs.TitleGenre;
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
public class TitleGenreDAOTest {
    
    public TitleGenreDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  
    //******************* START HERE ***********************************
    // Test of addTitlegenre method, of class TitleGenreDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testAddTitlegenre() {
        System.out.println("TEST 1 FOR ADD TITLE GENRE, CHECK IF THIS WORKS...");
        Genre g = new Genre(26, "Other world");
        Title t = new Title("novel", "that guy", 4, 2, "that guy did it");
        TitleGenreDAO instance = new TitleGenreDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.addTitlegenre(g, t);
        assertEquals(expResult, result);
    }


    //******************* START HERE ***********************************
    // Test of getGenreByTitleID method, of class TitleGenreDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetGenreByTitleID() {
        System.out.println("TEST 1 FOR GET GENRE BY TITLE ID, AIM TO PASS");
        int titleID = 1;
        TitleGenreDAO instance = new TitleGenreDAO("librarydb");
        String expResult = "Science fiction";
        String result = instance.getGenreByTitleID(titleID);
        assertEquals(expResult, result);
    }
    @Test
    public void test2GetGenreByTitleID() {
        System.out.println("TEST 2 FOR GET GENRE BY TITLE ID, AIM TO FAIL");
        int titleID = 9999;
        TitleGenreDAO instance = new TitleGenreDAO("librarydb");
        String expResult = "Never Exist";
        String result = instance.getGenreByTitleID(titleID);
        assertEquals(expResult, result);
    }

    //******************* START HERE ***********************************
    // Test of getAllTitlegenre method, of class TitleGenreDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetAllTitlegenre() {
        System.out.println("TEST 1 FOR GET ALL TITLE GENRE, TEST IT WORKS..");
        TitleGenreDAO instance = new TitleGenreDAO("librarydb");
        List<TitleGenre> tg = new ArrayList();
        TitleGenre expResult = null;
        for(int x = 0;x<tg.size();x++){
            expResult = tg.get(x);
        }
        List<TitleGenre> result = instance.getAllTitlegenre();
        assertEquals(expResult, result);
    }
    
}
