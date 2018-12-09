/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTOs.Loan;
import DTOs.Title;
import java.util.ArrayList;

/**
 *
 * @author samiwise
 */
public interface LoanDAOInterface {
    public int addLoan (Loan borrowed);
    public ArrayList<Loan> getAllLoansByUserID(int userID);
    public boolean updateLoanStatus (int BorrowedID, int status);
    public ArrayList<Loan> getActiveLoansByUserID(int userID);
     public ArrayList<Loan> getPreviousLoansByUserID(int userID);
    public Loan getLoanByID(int borrowedID);
    public Title getTitleByLoanID(int loanID);
    
}
