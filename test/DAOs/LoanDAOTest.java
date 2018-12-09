/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Loan;
import DTOs.Title;
import DTOs.User;
import java.util.ArrayList;
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
public class LoanDAOTest {
    
    public LoanDAOTest() {
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
    // Test of addLoan method, of class LoanDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testAddLoan() {
        System.out.println("TEST 1 FOR ADD LAON, AIM TO PASS");
        User u = new User(1, "test@test", "123qwe", "anna", "last", 0, 1);
        Title t = new Title(1, "novel", "that guy", 10, 1, "that guy did it");
        Loan l = new Loan(1, u, t, 1);
        Loan loan = new Loan(u,t,l.getStatus());
        LoanDAO instance = new LoanDAO("librarydb");
        // CANT GET GENERATED KEYS FROM ADDLAONS.............
        int expResult = 0;
        int result = instance.addLoan(loan);
        assertEquals(expResult, result);
    }
  
    //******************* START HERE ***********************************
    // Test of addLoan method, of class LoanDAO. . ..............................
    //******************* START HERE ***********************************

    //******************* START HERE ***********************************
    // Test of getAllLoansByUserID method, of class LoanDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetAllLoansByUserID() {
        System.out.println("TEST 1 FOR GET ALL LOAN BY ID, AIM TO PASS");
        int userID = 4;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Loan> l = new ArrayList();
        Loan expResult = null;
        for(int x = 0;x<l.size();x++){
            int check = l.get(x).getLoanID();
            if(check == userID){
            expResult = l.get(x);
            }
        }
        ArrayList<Loan> result = instance.getAllLoansByUserID(userID);
        assertEquals(expResult, result);
    }
    @Test
    public void test2GetAllLoansByUserID() {
        System.out.println("TEST 2 FOR Get ALL LAON BY ID, AIM TO FAIL");
         int userID = 9999;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Loan> l = new ArrayList();
        Loan expResult = null;
        for(int x = 0;x<l.size();x++){
            int check = l.get(x).getLoanID();
            if(check == userID){
            expResult = l.get(x);
            }
        }
        ArrayList<Loan> result = instance.getAllLoansByUserID(userID);
        assertEquals(expResult, result);
    }
    //******************* START HERE ***********************************
    // Test of getAllLoansByUserID method, of class LoanDAO. . ..............................
    //******************* START HERE ***********************************
    
    //******************* START HERE ***********************************
    // Test of updateLoanStatus method, of class LoanDAO. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testUpdateLoanStatus() {
        System.out.println("TEST 1 FOR UPDATE LOAN STATUS, CHECK IF UPDATE WORKS");
        int loanID = 1;
        int status = 1;
        LoanDAO instance = new LoanDAO("librarydb");
        boolean expResult = true;
        boolean result = instance.updateLoanStatus(loanID, status);
        assertEquals(expResult, result);
    }
//******************* START HERE ***********************************
    // Test of updateLoanStatus method, of class LoanDAO.. . ..............................
    //******************* START HERE ***********************************
  
    //******************* START HERE ***********************************
    // Test of getTitleByLoanID method, of class LoanDAO.. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetTitleByLoanID() {
        System.out.println("TEST 1 FOR GET TITLE BY LOAN ID, AIM TO PASS");
        int loanID = 11;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Title> l = new ArrayList();
        Title expResult = null;
        for(int x = 0;x<l.size();x++){
            int check = l.get(x).getTitleID();
            if(check == loanID){
            expResult = l.get(x);
            }
        }
        Title result = instance.getTitleByLoanID(loanID);
        assertEquals(expResult, result);
    }
     @Test
    public void test2GetTitleByLoanID() {
        System.out.println("TEST 2 FOR GET TITLE BY LOAN ID, AIM TO FAIl");
        int loanID = 9999;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Title> l = new ArrayList();
        Title expResult = null;
        for(int x = 0;x<l.size();x++){
            int check = l.get(x).getTitleID();
            if(check == loanID){
            expResult = l.get(x);
            }
        }
        Title result = instance.getTitleByLoanID(loanID);
        assertEquals(expResult, result);
    }
    //******************* END HERE ***********************************
    // Test of getTitleByLoanID method, of class LoanDAO. . ..............................
    //******************* END HERE ***********************************

    //******************* START HERE ***********************************
    // Test of getActiveLoansByUserID method, of class LoanDAO.. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetActiveLoansByUserID() {
        System.out.println("TEST 1 GET ACTIVE LOANS BY USER ID, AIM TO PASS");
        int userID = 1;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Loan> l = new ArrayList();
        Loan expResult = null;
        int num = 0;
        for(int x = 0;x<l.size();x++){
            if(l.get(x).getStatus() == num && l.get(x).getUser().getUserID() == userID){
                expResult = l.get(x);
            }
        }
        ArrayList<Loan> result = instance.getActiveLoansByUserID(userID);
        assertEquals(expResult, result);
    }
     @Test
    public void test2GetActiveLoansByUserID() {
        System.out.println("TEST 2 GET ACTIVE LOANS BY USER ID, AIM TO FAIL");
        int userID = 11111;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Loan> l = new ArrayList();
        Loan expResult = null;
        int num = 0;
        for(int x = 0;x<l.size();x++){
            if(l.get(x).getStatus() == num && l.get(x).getUser().getUserID() == userID){
                expResult = l.get(x);
            }
        }
        ArrayList<Loan> result = instance.getActiveLoansByUserID(userID);
        assertEquals(expResult, result);
    }
    @Test
    public void test3GetActiveLoansByUserID() {
        System.out.println("TEST 3 GET ACTIVE LOANS BY USER ID, STATUS = 3 AIM TO FAIL");
        int userID = 1;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Loan> l = new ArrayList();
        Loan expResult = null;
        int num = 3;
        for(int x = 0;x<l.size();x++){
            if(l.get(x).getStatus() == num && l.get(x).getUser().getUserID() == userID){
                expResult = l.get(x);
            }
        }
        ArrayList<Loan> result = instance.getActiveLoansByUserID(userID);
        assertEquals(expResult, result);
    }
    //******************* END HERE ***********************************
    // Test of getActiveLoansByUserID method, of class LoanDAO.. . ..............................
    //******************* END HERE ***********************************

    //******************* START HERE ***********************************
    // Test of getLoanByID method, of class LoanDAO.. . ..............................
    //******************* START HERE ***********************************
    @Test
    public void testGetLoanByID() {
        System.out.println("TEST 1 FOR GET LOAN BY ID, AIM TO PASS");
        int loanID = 4;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Loan> l = new ArrayList();
        Loan expResult = null;
        for(int x = 0;x<l.size();x++){
            if(l.get(x).getLoanID() == loanID){
                expResult = l.get(x);
            }
        }
        Loan result = instance.getLoanByID(loanID);
        assertEquals(expResult, result);
   
    }
    @Test
    public void test2GetLoanByID() {
        System.out.println("TEST 1 FOR GET LOAN BY ID, AIM TO FAIL");
        int loanID = 9999;
        LoanDAO instance = new LoanDAO("librarydb");
        ArrayList<Loan> l = new ArrayList();
        Loan expResult = null;
        for(int x = 0;x<l.size();x++){
            if(l.get(x).getLoanID() == loanID){
                expResult = l.get(x);
            }
        }
        Loan result = instance.getLoanByID(loanID);
        assertEquals(expResult, result);
   
    }
    //******************* END HERE ***********************************
    // Test of getLoanByID method, of class LoanDAO.. . ..............................
    //******************* END HERE ***********************************
}
