/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Loan;
import DTOs.Title;
//import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces.LoanDAOInterface;

/**
 *
 * @author samiwise
 */
public class LoanDAO extends DatabaseConnection implements LoanDAOInterface {

    /**
     *the constructor deals with the connection with the database 
     * 
     * @param databaseName the name of database
     */
    public LoanDAO(String databaseName) {
        super(databaseName);
    }

    /**
     * This will add a loan object to the db<p>
     *
     * @param loan
     * @return boolean response if it was successful or not
     */
    @Override
    public int addLoan(Loan loan) {
        Connection conn = null;
        PreparedStatement ps = null;
        TitleDAO td = new TitleDAO("librarydb");
        ResultSet generatedKeys = null;
        int newId = -1;
        if (td.checkAvailability(loan.getLoanID())) {
            try {
                conn = getConnection();
                String query = "INSERT INTO loan VALUES(NULL,?,?,?,NULL,NULL,NULL)";
                ps = conn.prepareStatement(query);
                int userID = loan.getUser().getUserID();
                int titleID = loan.getTitle().getTitleID();
                int status = loan.getStatus();

                ps.setInt(1, userID);
                ps.setInt(2, titleID);
                ps.setInt(3, status);

                ps.executeUpdate();

                //find out what the generated key was
                generatedKeys = ps.getGeneratedKeys();
                //if there was a result, i.e if the entry was inserted
                if (generatedKeys.next()) {
                    newId = generatedKeys.getInt(1);

                }
            } /**
             * catch (MySQLIntegrityConstraintViolationException e) {
             * System.out.println("Constraint Exception occured: " +
             * e.getMessage()); rs = -1; }
             */
            catch (SQLException se) {
                System.out.println("SQL Exception occurred: " + se.getMessage());
                se.printStackTrace();
                newId = -1;
            } catch (Exception e) {
                System.out.println("Exception occurred: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (generatedKeys != null) {
                        generatedKeys.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        freeConnection(conn);
                    }
                } catch (SQLException e) {
                    System.out.println("Exception occured in finally section in addLoan() method. " + e.getMessage());
                }
            }
        } else {
            newId = -2;
        }
        return newId;

    }

    /**
     * This returns an arraylist of loan object by userID<p>
     * @param userID
     * @return ArrayList of Loan
     */
    @Override
    public ArrayList<Loan> getAllLoansByUserID(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = null;
        UserDAO ud = new UserDAO("librarydb");
        TitleDAO td = new TitleDAO("librarydb");
        ArrayList<Loan> loanList = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM loan WHERE userID = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                loan = new Loan();
                loan.setLoanID(rs.getInt("loanID"));
                loan.setUser(ud.findUserByID(rs.getInt("userID")));
                loan.setTitle(td.searchByID(rs.getInt("titleID")));
                loan.setStatus(rs.getInt("status"));
                loanList.add(loan);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getloanByUserID() method " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the getLoanByUserID() method");
            }
        }
        return loanList;
    }

    /**
     * This updates the status of a book for if the book has been returned or
     * not
     *
     * @param loanID
     * @param status
     * @return boolean indicating if it worked or not
     */
    @Override
    public boolean updateLoanStatus(int loanID, int status) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = null;
        TitleDAO td = new TitleDAO("librarydb");

        try {
            conn = getConnection();
            String query = "UPDATE loan SET status = ? WHERE loanID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, loanID);
            int i = ps.executeUpdate();
            if (i > 0) {
                System.out.println("Status has been updated!");
                Title t = getTitleByLoanID(loanID);
                td.increaseStock(t.getTitleID(), 1);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the updateLoanStatus() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in finally section of updateLoanStatus() " + e.getMessage());
            }
        }
        return false;
    }
    
    /**
     * This is used to get the single title currently on loan
     *
     * @param userID the id of loan
     * @return a loan object by specific loan id
     */
    @Override
    public Title getTitleByLoanID(int loanID){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title title = null;
        
        try{
            conn = getConnection();
            String query = "SELECT * FROM title WHERE titleID = (SELECT titleID FROM loan WHERE loanID = ?)";
            ps = conn.prepareStatement(query);
            
           ps.setInt(1, loanID);
           rs = ps.executeQuery();
           
           while(rs.next()){
               title = new Title();
               title.setTitleID(rs.getInt("titleID"));
               title.setNovelName(rs.getString("novelName"));
               title.setAuthor(rs.getString("author"));
               title.setStock(rs.getInt("stock"));
               title.setOnLoan(rs.getInt("onLoan"));
               title.setTitleDescription(rs.getString("titleDescription"));
               title.setIsDisable(rs.getInt("isDisable"));
           }
        }catch(SQLException e){
            System.out.println("Exception occured in the getTitleByLoanID() method");
            e.printStackTrace();
        } finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(conn != null){
                    freeConnection(conn);
                }
            } catch(SQLException e){
                System.out.println("Exception occured in the finallu section of the getTitleByLoanID() method");
            }
        }
        return title;
    }

    /**
     * This is used to get all titles currently on loan
     *
     * @param userID
     * @return ArrayList of loan objects
     */
    @Override
    public ArrayList<Loan> getActiveLoansByUserID(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = null;
        UserDAO ud = new UserDAO("librarydb");
        TitleDAO td = new TitleDAO("librarydb");
        ArrayList<Loan> loanList = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM loan WHERE userID = ? AND status = 0";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                loan = new Loan();
                // Get the pieces of a customer from the resultset
                loan.setLoanID(rs.getInt("loanID"));
                loan.setUser(ud.findUserByID(rs.getInt("userID")));
                loan.setTitle(td.searchByID(rs.getInt("titleID")));
                loan.setStatus(rs.getInt("status"));
                loanList.add(loan);

            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getLoanStatusByUserID() method");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the getLoanStatusByUserID() method " + e.getMessage());
            }
        }

        return loanList;
    }
    
    /**
     * This is used to get all titles currently on loan
     *
     * @param userID
     * @return ArrayList of loan objects
     */
    @Override
    public ArrayList<Loan> getPreviousLoansByUserID(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = null;
        UserDAO ud = new UserDAO("librarydb");
        TitleDAO td = new TitleDAO("librarydb");
        ArrayList<Loan> loanList = new ArrayList();

        try {
            conn = getConnection();
            String query = "SELECT * FROM loan WHERE userID = ? AND status = 1";
            ps = conn.prepareStatement(query);

            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                loan = new Loan();
                // Get the pieces of a customer from the resultset
                loan.setLoanID(rs.getInt("loanID"));
                loan.setUser(ud.findUserByID(rs.getInt("userID")));
                loan.setTitle(td.searchByID(rs.getInt("titleID")));
                loan.setStatus(rs.getInt("status"));
                loanList.add(loan);

            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getLoanStatusByUserID() method");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the getLoanStatusByUserID() method " + e.getMessage());
            }
        }

        return loanList;
    }

    /**
     * Used to return a specific loan object by its ID
     *
     * @param loanID
     * @return loan object
     */
    @Override
    public Loan getLoanByID(int loanID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = null;
        UserDAO ud = new UserDAO("librarydb");
        TitleDAO td = new TitleDAO("librarydb");

        try {
            conn = getConnection();
            String query = "SELECT * FROM loan WHERE loanID = ?";
            ps = conn.prepareStatement(query);

            ps.setInt(1, loanID);
            rs = ps.executeQuery();

            while (rs.next()) {
                loan = new Loan();
                // Get the pieces of a customer from the resultset
                loan.setLoanID(rs.getInt("loanID"));
                loan.setUser(ud.findUserByID(rs.getInt("userID")));
                loan.setTitle(td.searchByID(rs.getInt("titleID")));
                loan.setStatus(rs.getInt("status"));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the getLoanByID() method");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the getLoanByID() method");
            }
        }

        return loan;

    }
}
