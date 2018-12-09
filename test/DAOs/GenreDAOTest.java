/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Genre;
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
public class GenreDAOTest {
    
    public GenreDAOTest() {
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
    // Test of addGenre method, of class GenreDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void test1AddGenre() {
        System.out.println("TEST 1 FOR ADD GENRE, THIS IS A TEST FOR PASS");
        Genre g = new Genre(1,"fantasy");
        GenreDAO instance = new GenreDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.addGenre(g);
        assertEquals(expResult, result);
     
    }
    
    //******************* END HERE ***********************************
    // Test of addGenre method, of class GenreDAO. . ..............................
    //******************* END HERE ***********************************

   
    //******************* START HERE ***********************************
    // Test of getAllGenre method, of class GenreDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetAllGenre() {
        System.out.println("TEST TO SEE IF GET ALL GENRE WORKS...");
        GenreDAO instance = new GenreDAO("librarydb");
        // EXPRESULT WILL SHOW ALL THE GENRE ON OUR DATA BASE....
        List<Genre> a = new ArrayList();
        Genre expResult = null;
        for(int x = 0; x< a.size(); x++){
            expResult = a.get(x);
        }
        List<Genre> result = instance.getAllGenre();
        assertEquals(expResult, result);

    }
    //******************* END HERE ***********************************
    // Test of getAllGenre method, of class GenreDAO. . ..............................
    //******************* END HERE ***********************************

  
    //******************* START HERE ***********************************
    // Test of searchGenreByid method, of class GenreDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void test1SearchGenreByid() {
        System.out.println("TEST 1 SEARCH GENRE BY ID, AIM TO PASS..");
        GenreDAO instance = new GenreDAO("librarydb");
        ArrayList<Genre> g = new ArrayList();
        int genreID = g.get(1).getGenreID();
        Genre expResult = g.get(genreID);
        Genre result = instance.searchGenreByid(genreID);
        assertEquals(expResult, result); 
    }
       @Test
    public void test2SearchGenreByid() {
        System.out.println("TEST 2 SEARCH GENRE BY ID, AIM TO FAIL..");
        
        GenreDAO instance = null;
        ArrayList<Genre> g = new ArrayList();
        int genreID = 11111;
        Genre expResult = g.get(genreID);
        Genre result = instance.searchGenreByid(genreID);
        assertEquals(expResult, result); 
    }
    //******************* END HERE ***********************************
    // Test of searchGenreByid method, of class GenreDAO. . ..............................
    //******************* END HERE ***********************************
    
}
