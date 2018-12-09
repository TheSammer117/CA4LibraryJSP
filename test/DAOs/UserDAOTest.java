/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.User;
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
public class UserDAOTest {
    
    public UserDAOTest() {
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

    /**
     * Test of login method, of class UserDAO.
     */
    
    @Test
    public void testLogin() {
        System.out.println("TEST 1 FOR LOGIN, CHECK IF THIS WORKS");
        String e_mail = "tester@tester.com";
        String p_assword = "123";
        UserDAO instance = new UserDAO("librarydb");
        ArrayList<User> u = new ArrayList();
        User expResult = null;
        for(int x = 0;x<u.size();x++){
            if(u.get(x).getEmail().equalsIgnoreCase(e_mail) && u.get(x).getPassword().equalsIgnoreCase(p_assword)){
                expResult = u.get(x);
            }
        }
        User result = instance.login(e_mail, p_assword);
        assertEquals(expResult, result);
    }

    /**
     * Test of register method, of class UserDAO.
     */
    @Test
    public void testRegister() {
        System.out.println("TEST 1 FOR REGISTER, CHECK IF THIS WORKS..");
        String email = "that@guy.com";
        String password = "1234qwe";
        String firstName = "that";
        String lastName = "guy";
        String primaryAddressLine1 = "some";
        String primaryAddressLine2 = "where";
        String primaryTown = "that town";
        String primaryCounty = "that country";
        String primaryEircode = "1q2we2";
        String optAddressLine1 = null;
        String optAddressLine2 = null;
        String optTown = null;
        String optCounty = null;
        String optEircode = null;
        UserDAO instance = new UserDAO("librarydb");
        // CANT GET THE GENERATED NUMBER.....................
        int expResult = 0;
        int result = instance.register(email, password, firstName, lastName, primaryAddressLine1, primaryAddressLine2, primaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);
        assertEquals(expResult, result);

    }

    /**
     * Test of disableUser method, of class UserDAO.
     */
    @Test
    public void testDisableUser() {
        System.out.println("TEST 1 FOR DISABLE USER, CHECK IF THIS WORKS...");
        int userID = 10;
        User user = new User(10, "admin@admin.com", "admin", "Ayesha", "Khan", 1, 1);
        UserDAO instance = new UserDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.disableUser(userID, user);
        assertEquals(expResult, result);

    }

    /**
     * Test of showAllUser method, of class UserDAO.
     */
    @Test
    public void testShowAllUser() {
        System.out.println("TEST 1 FOR SHOW ALL USER, CHECK IF THIS WORKS..");
        UserDAO instance = new UserDAO("librarydb");
        List<User> u = new ArrayList();
        User expResult = null;
        for(int x = 0;x<u.size();x++){
            expResult = u.get(x);
        }
        List<User> result = instance.showAllUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserByEmail method, of class UserDAO.
     */
    @Test
    public void testGetUserByEmail() {
        System.out.println("TEST 1 FOR GET USER BY EMAIL, CHECK IF THIS WORKS..");
        String email = "jamesWord@gmail.com";
        UserDAO instance = new UserDAO("librarydb");
        
        List<User> u = new ArrayList();
        User expResult = null;
        for(int x = 0;x<u.size();x++){
            if(u.get(x).getEmail().equalsIgnoreCase(email)){
                expResult = u.get(x);
            }   
        }
        
        User result = instance.getUserByEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of findUserByID method, of class UserDAO.
     */
    @Test
    public void testFindUserByID() {
        System.out.println("TEST 1 FOR FIND USER BY ID, AIM TO PASS");
        int userID = 6;
        UserDAO instance = new UserDAO("librarydb");
        
        List<User> u = new ArrayList();
        User expResult = null;
        for(int x = 0;x<u.size();x++){
            if(u.get(x).getUserID()== userID){
                expResult = u.get(x);
            }   
        }
        
        User result = instance.findUserByID(userID);
        assertEquals(expResult, result);
    }
      @Test
    public void test2FindUserByID() {
        System.out.println("TEST 2 FOR FIND USER BY ID, AIM TO FAIL");
        int userID = 9999;
        UserDAO instance = new UserDAO("librarydb");
        
        List<User> u = new ArrayList();
        User expResult = null;
        for(int x = 0;x<u.size();x++){
            if(u.get(x).getUserID()== userID){
                expResult = u.get(x);
            }   
        }
        
        User result = instance.findUserByID(userID);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateUserProfile method, of class UserDAO.
     */
    @Test
    public void testUpdateUserProfile() {
        System.out.println("updateUserProfile");
        String email = "change@change.com";
        String password = "change";
        String firstName = "change";
        String lastName = "changing";
        String primaryAddressLine1 = "chenge address";
        String primaryAddressLine2 = " change address2";
        String PrimaryTown = "change town";
        String primaryCounty = "change country";
        String primaryEircode = "1dd12";
        String optAddressLine1 = null;
        String optAddressLine2 = null;
        String optTown = null;
        String optCounty = null;
        String optEircode = null;
        UserDAO instance = new UserDAO("librarydb");
        // CANT GET THE GENERATED NUMBER.....................
        int expResult = 0;
        int result = instance.updateUserProfile(email, password, firstName, lastName, primaryAddressLine1, primaryAddressLine2, PrimaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);
        assertEquals(expResult, result);
    }
    
}
