/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import interfaces.UserDAOInterface;
import DTOs.Loan;
import DTOs.Genre;
import DTOs.Likes;
import DTOs.Title;
import DTOs.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author d00182295
 */
public class UserDAO extends DatabaseConnection implements UserDAOInterface {

    /**
     *the constructor deals with the connection with the database 
     * 
     * @param databaseName the name of database
     */
    public UserDAO(String databaseName) {
        super(databaseName);
    }

    /**
     *login a  user while the email and password is matching what recorded in database
     * @param e_mail
     * @param p_assword
     * @return an user object from database
     */
    @Override
    public User login(String e_mail, String p_assword) {
        // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        if (e_mail != null && p_assword != null) {
            try {
                con = this.getConnection();
                // Make query
                String query = "SELECT * FROM user WHERE email = ? AND password = ?";
                // Compile into SQL
                ps = con.prepareStatement(query);
                // (Fill in blanks of query)
                ps.setString(1, e_mail);
                ps.setString(2, p_assword);
                // Execute the SQL
                rs = ps.executeQuery();
/** private int userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int isAdmin;
    private int active;*/
                // check if user does exist inside database;......
                if (rs.next()) {
                   int userId = rs.getInt("userID");
                   String email = rs.getString("email");
                   String password = rs.getString("password");
                   String firstName = rs.getString("firstname");
                   String lastName = rs.getString("lastName");
                   int isAdmin = rs.getInt("isAdmin");
                   int active = rs.getInt("active");
                   u = new User(userId, email, password, firstName, lastName, isAdmin, active);
                } 

            } catch (SQLException ex) {
                System.out.println("An exception occurred while querying "
                        + ex.getMessage());

            } // Shut down all open components
            finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                    System.out.println("An error occurred when shutting down the login() method: " + e.getMessage());
                }
            }
        }
        return u;
    }
    /**
     * adding new user to database
     * 
     * @param email the user name
     * @param password user password
     * @param firstName first name
     * @param lastName last name
     * @param primaryAddressLine1 
     * @param primaryAddressLine2
     * @param primaryTown 
     * @param primaryCounty
     * @param primaryEircode
     * @param optAddressLine1
     * @param optAddressLine2
     * @param optTown
     * @param optCounty
     * @param optEircode
     * @return return a integer whether a new row recorded to database
     */
    @Override
    public int register(String email, String password, String firstName, String lastName, String primaryAddressLine1, String primaryAddressLine2, String primaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode) {
        // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        AddressDAO ad = new AddressDAO("librarydb");
        int newId = -1;

        try {
            con = getConnection();
            // Make query
            String query = "INSERT INTO user VALUES (NULL,?,?,?,?,?,NULL,?)";
            // Compile into SQL
            ps = con.prepareStatement(query);
            // (Fill in blanks of query)
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            int isAdmin = 0;
            ps.setInt(5, isAdmin);
            int active = 1;
            ps.setInt(6, active);
            // Execute the SQL
            ps.executeQuery();

            //find out what the id generated for this entry was
            generatedKeys = ps.getGeneratedKeys();
            //if there was a result, i.e. if the entry was inserted successfully
            if (generatedKeys.next()) {
                //get the id value that was generated by mySQL when the entry was inserted
                generatedKeys.getInt(1);
                //then add users address to address table
                User temp = getUserByEmail(email);
                int uID = temp.getUserID();
                ad.addAddress(uID, primaryAddressLine1, primaryAddressLine2, primaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);

            }

            /**
             * // To Check if that exist in the database ...... String query2 =
             * "SELECT * FROM user WHERE email = ? AND password = ?"; ps =
             * con.prepareStatement(query); ps.setString(1, email);
             * ps.setString(2, password); rs = ps.executeQuery();
             * //------------------------------------------------ // check if
             * user does exist inside database;...... if (rs != null) { check =
             * true; } else { check = false; }       *
             */
        } catch (SQLException ex) {
            System.out.println("An exception occurred while querying register()"
                    + ex.getMessage());
            newId = -1;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the register() method: " + e.getMessage());
            }
        }
        return newId;
    }

    /**
     *disable a user if a user required to remove his/her user account
     * @param userID user id
     * @param user the user object
     * @return true/false if the column "isDisable" is updated to database 
     */
    @Override
    public boolean disableUser(int userID, User user) {
        // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        List<UserDAO> users = new ArrayList();
        try {
            if (user.getIsAdmin() == 1) {
                con = getConnection();
                String query = "UPDATE user SET active = 0 WHERE userID = ? AND isAsmin = 0";
                ps = con.prepareStatement(query);
                ps.setInt(1, userID);
                rs = ps.executeQuery();
                check = true;
            } else {
                check = false;
            }
        } catch (SQLException ex) {
            System.out.println("An exception occurred while querying disableUser()"
                    + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the login() method: " + e.getMessage());
            }
        }
        return check;
    }

    /**
     *display a list of all users currently stored in database
     * @return a list of users
     */
    @Override
    public List<User> showAllUser() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> user = new ArrayList();

        try {
            conn = getConnection();

            String query = "SELECT * FROM user";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(rs.getInt("userID"), rs.getString("email"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("isAdmin"), rs.getInt("isAdmin"));
                user.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the findUserByID() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the findUserByID() method: " + e.getMessage());
            }
        }
        return user;
    }

    /**
     *Get an user account searching by the user name(email);
     * @param email the user name 
     * @return return a specific user object matching with the user name
     */
    @Override
    public User getUserByEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = getConnection();
            String query = "SELECT * FROM user WHERE email = ?";
            ps = conn.prepareStatement(query);

            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getInt("isAdmin"));
                user.setActive(rs.getInt("active"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the getUserByEmail() method");
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
                System.out.println("Exception occured in the finally section in the getUserByEmail() method");
            }
        }

        return user;
    }

    /**
     *searching an user by an user id 
     * @param userID user id 
     * @return an user object if the user is found
     */
    public User findUserByID(int userID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM user WHERE userID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            if (rs.next()) {
                u = new User(rs.getInt("userID"), rs.getString("email"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getInt("isAdmin"), rs.getInt("active"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findUserByID() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the findUserByID() method: " + e.getMessage());
            }
        }
        return u;
    }

    /**
     *update a specific user details by each fields of user object, 
     * it also can update the address to address table
     * 
     * @param email
     * @param password
     * @param firstName
     * @param lastName
     * @param primaryAddressLine1
     * @param primaryAddressLine2
     * @param PrimaryTown
     * @param primaryCounty
     * @param primaryEircode
     * @param optAddressLine1
     * @param optAddressLine2
     * @param optTown
     * @param optCounty
     * @param optEircode
     * @return an integer whether the user details updated to database, once the 
     * user profile is updated, the matching address will be also updated.
     */
    @Override
    public int updateUserProfile(String email, String password, String firstName, String lastName, String primaryAddressLine1, String primaryAddressLine2, String PrimaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode) {
          // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        AddressDAO ad = new AddressDAO("librarydb");
        int newId = -1;

        try {
            con = getConnection();
            // Make query
            String query = "UPDATE INTO user SET firstName = ?,lastName = ? WHERE email = ? AND password = ?";
            // Compile into SQL
            ps = con.prepareStatement(query);
            // (Fill in blanks of query)
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            // Execute the SQL
            ps.executeQuery();

            //find out what the id generated for this entry was
            generatedKeys = ps.getGeneratedKeys();
            //if there was a result, i.e. if the entry was inserted successfully
            if (generatedKeys.next()) {
                //get the id value that was generated by mySQL when the entry was inserted
                generatedKeys.getInt(1);
                //then Update users address to address table
                User temp = getUserByEmail(email);
                int uID = temp.getUserID();
                ad.updateAddressById(uID, primaryAddressLine1, primaryAddressLine2, PrimaryTown, primaryCounty, primaryEircode, optAddressLine1, optAddressLine2, optTown, optCounty, optEircode);

            }
            
        } catch (SQLException ex) {
            System.out.println("An exception occurred while querying register()"
                    + ex.getMessage());
            newId = -1;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the register() method: " + e.getMessage());
            }
        }
        return newId;
    }
    
    // ***************************************************************************
    // ***************************************************************************
    // ***************************************************************************
    //EXTRA CODE ... D00182295 ..
    
    
    @Override
    public boolean likeATitle(Title t, User u) {
          // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        AddressDAO ad = new AddressDAO("librarydb");
        boolean done = false;

        try {
            con = getConnection();
            // Make query
            String query = "INSERT INTO like VALUES (NULL,?,?,?)";
            // Compile into SQL
            ps = con.prepareStatement(query);
            // (Fill in blanks of query)
            ps.setInt(1, t.getTitleID());
            ps.setInt(2, u.getUserID());
            ps.setInt(3, 1);
            ps.executeQuery();
            
   
            generatedKeys = ps.getGeneratedKeys();
            //if there was a result, i.e. if the entry was inserted successfully
            if (generatedKeys.next()) {
                //get the id value that was generated by mySQL when the entry was inserted
                generatedKeys.getInt(1);
                //then add users address to address table
                done = true;
            }
   
        } catch (SQLException ex) {
            System.out.println("An exception occurred while querying likeATitle()"
                    + ex.getMessage());
            done = false;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the likeATitle() method: " + e.getMessage());
            }
        }
        return done;
    }
    
    @Override
    public boolean dislikeATitle(Title t, User u) {
          // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet generatedKeys = null;
        AddressDAO ad = new AddressDAO("librarydb");
        boolean done = false;

        try {
            con = getConnection();
            // Make query
            String query = "INSERT INTO like VALUES (NULL,?,?,?)";
            // Compile into SQL
            ps = con.prepareStatement(query);
            // (Fill in blanks of query)
            ps.setInt(1, t.getTitleID());
            ps.setInt(2, u.getUserID());
            ps.setInt(3, 0);
            ps.executeQuery();
            
            generatedKeys = ps.getGeneratedKeys();
            //if there was a result, i.e. if the entry was inserted successfully
            if (generatedKeys.next()) {
                //get the id value that was generated by mySQL when the entry was inserted
                generatedKeys.getInt(1);
                //then add users address to address table
                done = true;
            }
   
        } catch (SQLException ex) {
            System.out.println("An exception occurred while querying likeATitle()"
                    + ex.getMessage());
            done = false;
        } finally {
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the likeATitle() method: " + e.getMessage());
            }
        }
        return done;
    }

    @Override
    public Likes showLikedTitle(Title t) {
           // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AddressDAO ad = new AddressDAO("librarydb");
        Likes l = null;
        int count = -1;

        try {
           con = getConnection();

            String query = "SELECT * FROM like WHERE titleID = ? AND count = 1";
            ps = con.prepareStatement(query);
            ps.setInt(1, t.getTitleID());
            rs = ps.executeQuery();

            if (rs.next()) {
                l = new Likes(rs.getInt("userID"), rs.getInt("titleID"), rs.getInt("userID"), rs.getInt("count"));
            }
        } catch (SQLException ex) {
            System.out.println("An exception occurred while querying likeATitle()"
                    + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the likeATitle() method: " + e.getMessage());
            }
        }
        return l;
    }

    @Override
    public Likes showDislikedTitle(Title t) {
                // Required for DB interaction
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AddressDAO ad = new AddressDAO("librarydb");
        Likes l = null;
        int count = -1;

        try {
           con = getConnection();

            String query = "SELECT * FROM like WHERE titleID = ? AND count = 0";
            ps = con.prepareStatement(query);
            ps.setInt(1, t.getTitleID());
            rs = ps.executeQuery();

            if (rs.next()) {
                l = new Likes(rs.getInt("userID"), rs.getInt("titleID"), rs.getInt("userID"), rs.getInt("count"));
            }
        } catch (SQLException ex) {
            System.out.println("An exception occurred while querying likeATitle()"
                    + ex.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("An error occurred when shutting down the likeATitle() method: " + e.getMessage());
            }
        }
        return l;
    }

}
