/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Genre;
import DTOs.Title;
import DTOs.TitleGenre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import interfaces.TitleGenreDAOInterface;

/**
 *Team: Hernel Provido, Sami Mahmoud, Haiyun Yu 
 * @author Haiyun Yu d00188956
 */

public class TitleGenreDAO extends DatabaseConnection implements TitleGenreDAOInterface {

    public TitleGenreDAO(String databaseName) {
        super(databaseName);
    }
    
     /**
     * Initialise a TitlegenreDao to access the specified database name
     *
     * @param databaseName The name of the MySQL database to be accessed (this
     * database should be running on localhost and listening on port 3306).
     */

    /**
     * Returns a new {@code TitleGenre} object adding into the database.
     * The method returns true if the result gets any feedback otherwise.
     *
     * @param g genre object from GenreDao class
     * @param t Title object from TitleDao class
     * @return true/false if the TitleGenre added to database.
     */
    @Override
    public boolean addTitlegenre(Genre g, Title t) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        int rs = 0;
        try {
            con = getConnection();
            String query = "INSERT INTO TitleGenre VALUES (?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, g.getGenreID());
            ps.setInt(2, t.getTitleID());
            rs = ps.executeUpdate();

            if (rs >0) {
                result = true;
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
     * Returns a new {@code Title} object which just adding into the database.
     * The method returns true if the result gets any feedback otherwise.
     * 
     * @param titleID The ID of title to find the specified row from database
     * @return the Genre from Genre table matched with 
     * the genreID from TitleGenre table. 
     */
    @Override
    public String getGenreByTitleID(int titleID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TitleGenre tg = null;
        Genre g = null;
        int genreID = 0;
        String genre = "";
        
        try{
            conn = getConnection();            
            String query = "SELECT * FROM Titlegenre WHERE titleID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, titleID);
            rs = ps.executeQuery();
            if(rs.next()){
                tg = new TitleGenre(rs.getInt("genreID"), rs.getInt("titleID"));
                genreID = tg.getGenreID();
            }            
            if(genreID > 0){
                String query2 = "SELECT * FROM Genre WHERE genreID = ?";
                ps = conn.prepareStatement(query2);
                ps.setInt(1, genreID);
                rs = ps.executeQuery();
                if(rs.next()){
                    g = new Genre(rs.getInt("genreID"), rs.getString("genre"));
                    genre = g.getGenre();
                }                
            }       
        }catch(SQLException e){
            System.out.println("Exception occured in the getGenreByTitleID()" + e.getMessage());
        }finally{
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
                System.out.println("Exception occured in finally section of searchByID() method " + e.getMessage());
            }
        }
        return genre;
    }
    
    /**
     * Returns a list of TitleGenre objects based on information in the
     * database. All TitleGenre entries in the TitleGenre table are selected 
     * from the database and stored as TitleGenre objects in a List.
     *
     * @return The TitleGenre list of all TitleGenres entries in the 
     * TitleGenre table. 
     */
    @Override
    public List<TitleGenre> getAllTitlegenre() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TitleGenre> agList = new ArrayList<>();

        try {
            con = getConnection();
            String query = "SELECT * FROM TitleGenre";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                TitleGenre ag = new TitleGenre(rs.getInt("genreID"),
                        rs.getInt("titleID"));
                agList.add(ag);
            }
        } catch (SQLException ex) {
            System.out.println("Exception occured in the getAllTitlegenre() method, " + ex.getMessage());
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
                System.out.println("Exception occured in the final section of the getAllTitlegenre() method, " + ex.getMessage());
            }
        }
        return agList;
    }
}
