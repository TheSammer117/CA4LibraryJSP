/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Address;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author d00182295
 */
public class AddressDAOTest {
    
    public AddressDAOTest() {
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
     * d00182295
     */
    
    //******************* START HERE ***********************************
    // Test of addAddress method, of class AddressDAO . ..............................
    //******************* START HERE ***********************************
    @Test
    public void test1AddAddress() {
        System.out.println("TEST 1 TO ADD ADDRESS TO THE DATABASE. (AIM TO PASS) ");
        int userID = 0;
        String primaryAddressLine1 = "40 crescent";
        String primaryAddressLine2 = "CastleBlayney";
        String PrimaryTown = "Monaghan";
        String primaryCounty = "Ireland";
        String primaryEircode = "1111";
        String optAddressLine1 = "Shercock";
        String optAddressLine2 = "the crescent";
        String optTown = "Dundalk";
        String optCounty = "Ireland";
        String optEircode = "2222";
        AddressDAO instance = new AddressDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.addAddress(userID, primaryAddressLine1, primaryAddressLine2, PrimaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);
        assertEquals(expResult, result);
    }
        public void test2AddAddress() {
        System.out.println("TEST 1 TO ADD ADDRESS TO THE DATABASE. (AIM TO GET ERRORS (GET ERRORS WHEN PRIMARY ARE NOT FILLED))");
        int userID = 1;
        String primaryAddressLine1 = null;
        String primaryAddressLine2 = null;
        String PrimaryTown = null;
        String primaryCounty = null;
        String primaryEircode = null;
        String optAddressLine1 = "Shercock";
        String optAddressLine2 = "the crescent";
        String optTown = "Dundalk";
        String optCounty = "Ireland";
        String optEircode = "2222";
        AddressDAO instance = new AddressDAO("librarydb");
        boolean expResult = false;
        boolean result = instance.addAddress(userID, primaryAddressLine1, primaryAddressLine2, PrimaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);
        assertEquals(expResult, result);
    }
          public void test3AddAddress() {
        System.out.println("TEST 1 TO ADD ADDRESS TO THE DATABASE. AIM TO PASS WHEN OPTIONAL ARE NOT FILLED)");
        int userID = 2;
       String primaryAddressLine1 = "40 crescent";
        String primaryAddressLine2 = "CastleBlayney";
        String PrimaryTown = "Monaghan";
        String primaryCounty = "Ireland";
        String primaryEircode = "1111";
        String optAddressLine1 = null;
        String optAddressLine2 = null;
        String optTown = null;
        String optCounty = null;
        String optEircode = null;
        AddressDAO instance = new AddressDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.addAddress(userID, primaryAddressLine1, primaryAddressLine2, PrimaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);
        assertEquals(expResult, result);
    }
    //******************* END HERE ***********************************
    // Test of addAddress method, of class AddressDAO ..............................
    //******************* END HERE ***********************************

   
    //******************* START HERE ***********************************
    // Test of getAddressesByID method, of class AddressDAO ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetAddressesByID() {
        System.out.println("TEST 1 FOR GET ADDRESS BY ID. AIM TO PASS (PASS WHEN ID DOES EXIST)");
        int userID = 0;
        AddressDAO instance = new AddressDAO("librarydb");
        // EXPRESULT WILL GET INFORMATION OF EXISTING USER.
        ArrayList<Address> expResult = null;
        ArrayList<Address> result = instance.getAddressesByID(userID);
        assertEquals(expResult, result);
    }
    public void test2GetAddressesByID() {
        System.out.println("TEST 2 FOR GET ADDRESS BY ID. AIM TO FAIL (FAIL WHEN ID DOES NOT EXIST)");
        int userID = 9999;
        AddressDAO instance = new AddressDAO("librarydb");
        //EXPRESUTLS SHOULD GET BACK ERRORS.
        ArrayList<Address> expResult = null;
        ArrayList<Address> result = instance.getAddressesByID(userID);
        assertEquals(expResult, result);
    }
    //******************* END HERE ***********************************
    // Test of getAddressesByID method, of class AddressDAO ..............................
    //******************* END HERE ***********************************
   
     //******************* START HERE ***********************************
    // Test of updateAddressById method, of class AddressDAO. ..............................
    //******************* START HERE ***********************************
    @Test
    public void testUpdateAddressById() {
        System.out.println("TEST 1 FOR UPDATE ADDRESS BY ID. THIS IS TO CHECK IF THIS MOTHOD WORKS");
        int userID = 0;
        String primaryAddressLine1 = "newAddress";
        String primaryAddressLine2 = "newAddress";
        String PrimaryTown = "newTown";
        String primaryCounty = "newCountry";
        String primaryEircode = "12323";
        String optAddressLine1 = "newAddress2";
        String optAddressLine2 = "newAddress2";
        String optTown = "newTown2";
        String optCounty = "newCountry2";
        String optEircode = "12323";
        AddressDAO instance = new AddressDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.updateAddressById(userID, primaryAddressLine1, primaryAddressLine2, PrimaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);
        assertEquals(expResult, result);
         //******************* END HERE ***********************************
    // Test of updateAddressById method, of class AddressDAO. ..............................
    //******************* END HERE ***********************************
    
    }
    
}
