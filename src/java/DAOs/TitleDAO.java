/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Genre;
import interfaces.TitleDAOInterface;
import DTOs.Title;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Haiyun Yu D00188956
 *
 */
public class TitleDAO extends DatabaseConnection implements TitleDAOInterface {

    /**
     * Initialise a TitleDao to access the specified database name
     *
     * @param databaseName The name of the MySQL database to be accessed (this
     * database should be running on localhost and listening on port 3306).
     */
    public TitleDAO(String databaseName) {
        super(databaseName);
    }    
    
    /**
     * Returns a list of Title objects based on information in the database. All
     * titles entries in the Title table are selected from the database and
     * stored as title objects in a List.
     *
     * @return The title list of all titles entries in the Title table. This
     * List may be empty where no books are present in the database.
     */
    @Override
    public ArrayList<Title> getAllTitles() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Title> titles = new ArrayList<>();

        try {
            con = getConnection();
            String query = "SELECT * FROM Title";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Title t = new Title(rs.getInt("titleID"),
                        rs.getString("novelName"),
                        rs.getString("author"),
                        rs.getInt("stock"),
                        rs.getInt("onLoan"),
                        rs.getString("titleDescription"));
                titles.add(t);
            }
        } catch (SQLException ex) {
            System.out.println("Exception occured in the getAllTitles() method, " + ex.getMessage());
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
            } catch (SQLException ex) {
                System.out.println("Exception occured in the final section of the getAllTitles() method, " + ex.getMessage());
            }
        }
        return titles;
    }

/**
     * Returns a new {@code Title} object which just adding into the 
     * database. once a new title added to database, it should also add 
     * another row to TitleGenre to record what genre of that book is;
     *
     * @param title, title object from TitleDao class
     * @param g, genre object 
     * @return true/false if the title added its all information to database.
     */

    @Override
    public boolean addTitle(Title title, Genre g) {//fixed
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        TitleGenreDAO tgDao = new TitleGenreDAO("librarydb");
        int rs = 0;
        try {
            con = getConnection();
            String query = "INSERT INTO Title VALUES (NULL,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, title.getNovelName());
            ps.setString(2, title.getAuthor());
            ps.setInt(3, title.getStock());
            ps.setInt(4, title.getOnLoan());
            ps.setString(5, title.getTitleDescription());
            rs = ps.executeUpdate();

            if (rs >0) {                
                if(tgDao.addTitlegenre(g, title)){
                result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the final section of the addTitle() method, " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("Exception occured in the final section of the addTitle() method, " + ex.getMessage());
            }
        }

        return result;
    }
    /**
     *
     * Remove a exited Title in the database matching the specified titleID, The
     * method should return true if a title deleted.
     *
     * @param titleID The ID of title to find the specified row from database
     *
     * @return return true/false if the title deletes successfully.
     */
    @Override
    public boolean removeTitleByID(int titleID) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        int rowsAffected = 0;

        con = getConnection();
        String query = "DELETE FROM Title WHERE titleID = ? ";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, titleID);
            rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the removeTtileByID() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the removeTtileByID() method");
                e.getMessage();
            }
        }

        return result;
    }

    /**
     *
     * Get the specified title by matching the name of the title. The method
     * should return the entire title which contains the specified novel's name.
     *
     * @param novelName
     * @return return the information of a title by specified novel's name;
     * table.
     */
    @Override
    public List<Title> searchTitleByName(String novelName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title title = null;
        List<Title> tList = null;

        con = getConnection();

        String query = "SELECT * FROM Title WHERE novelName Like ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "%"+novelName+"%");
            rs = ps.executeQuery();
            if (rs.next()) {
                title = new Title(rs.getInt("titleID"), rs.getString("novelName"), rs.getString("author"), rs.getInt("stock"), rs.getInt("onLoan"), rs.getString("titleDescription"));
                tList.add(title);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the searchTitleByName() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the searchTitleByName() method: " + e.getMessage());
            }
        }
        return tList;
    }

    /**
     * Get the specified title by matching the id of the title. 
     * The method should return the entire title which contains
     * the specified novel's name.
     * 
     * @param id title id
     * @return a title object matching the specified id
     */
    
    @Override
    public Title searchByID(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Title t = null;

        try {
            conn = getConnection();

            String query = "SELECT * FROM title WHERE titleID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                t = new Title(rs.getInt("titleID"), rs.getString("novelName"), rs.getString("author"), rs.getInt("stock"), rs.getInt("onLoan"), rs.getString("titleDescription"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the searchByID() method " + e.getMessage());
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
                System.out.println("Exception occured in finally section of searchByID() method " + e.getMessage());
            }
        }
        return t;
    }

    /**
     *
     * Updates a exited Title from the database by the specified titleID,
     * The method should return true if the values updated to the table.
     *
     * @param id The ID of title to find the specified row from database
     * @param title
     * @return return true/false if the title entire changed in the Titles
     * table.
     */
    @Override
    public boolean updateTitle(int id, Title title) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Boolean result = null;

        try {
            conn = getConnection();
            String query = "UPDATE title SET novelName= ?, author= ?, stock= ?, onLoan= ?, titleDescription= ? WHERE titleID= ?";
            ps = conn.prepareStatement(query);
            String novelName = title.getNovelName();
            String author = title.getAuthor();
            int stock = title.getStock();
            int onLoan = title.getOnLoan();
            String titleDescription = title.getTitleDescription();

            // Fill in the blanks, i.e. parameterize the query
            ps.setString(1, novelName);
            ps.setString(2, author);
            ps.setInt(3, stock);
            ps.setInt(4, onLoan);
            ps.setString(5, titleDescription);
            ps.setInt(6, id);

            // Execute the query
            rs = ps.executeUpdate();
        } 
        catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        } 
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section in the updateTitle() method");
            }
        }
        if (rs > 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    /**
     *check the stock of any title by its id;
     * 
     * @param titleID The ID of title to find the specified row from database 
     * @return return true/false whether the title is available
     */
    @Override
    public boolean checkAvailability(int titleID) {
        boolean result = false;
        Title t = new Title();
        t = searchByID(titleID);
        if (t.getStock() > 0) {
            result = true;
        }
        return result;
    }
    
    /**
     *increase/decrease the stock by a given amount of titles
     * The method should return true if the result 
     *
     * @param titleID the id of a title object
     * @param stock The current stock of a title via specified id.
     * @return return true/false if the result contains any values otherwise.
     */

     @Override
    public boolean increaseStock(int titleID, int stock) {
        return changeStock(titleID, stock);
    }

    @Override
    public boolean decreaseStock(int titleID, int stock) {
        return changeStock(titleID, (stock * -1));
    }
    
    private boolean changeStock(int titleID, int stock) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            con = getConnection();

            //  if (options.equalsIgnoreCase("increase")) {
            String query = "update title set stock = stock + ? where titleID = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, stock);
            ps.setInt(2, titleID);
            rs = ps.executeQuery();
            if (rs != null) {
                check = true;
            }
      } catch (SQLException e) {
            System.out.println("Exception occured in the changeStock() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the changeStock() method: " + e.getMessage());
            }
        }
        return check;
    }
}
