/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Genre;
import DTOs.Title;
import interfaces.GenreDAOInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *Team: Hernel Provido, Sami Mahmoud, Haiyun Yu 
 * @author d00182295
 */

public class GenreDAO extends DatabaseConnection implements GenreDAOInterface{

    public GenreDAO(String databaseName) {
        super(databaseName);
    }

    
    /**
     * Returns a new {@code Title} object which just adding into the 
     * database. once a new genre added to database;
     *
     * @param g, genre object 
     * @return true/false if the genre object added to database
     */
    @Override
    public boolean addGenre(Genre g) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean result = false;
        int rs = 0;
        try {
            con = getConnection();
            String query = "INSERT INTO TitleGenre VALUES (?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, g.getGenreID());
            ps.setString(2, g.getGenre());
            rs = ps.executeUpdate();

            if (rs >0) {
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR ON addGenre() method, " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException ex) {
                System.out.println("ERROR ON addGenre() method, " + ex.getMessage());
            }
        }

        return result;
    }

    /**
     * Returns a list of Genre objects from the database. 
     *
     * @return The list of all genres from database.
     */
    @Override
    public List<Genre> getAllGenre() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Genre> gList = new ArrayList<>();

        try {
            con = getConnection();
            String query = "SELECT * FROM Genre";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Genre g = new Genre(rs.getInt("genreID"),
                        rs.getString("genre"));
                gList.add(g);
            }
        } catch (SQLException ex) {
            System.out.println("ERROR ON getAllGenre() method, " + ex.getMessage());
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
                System.out.println("ERROR ON getAllGenre() method, " + ex.getMessage());
            }
        }
        return gList;
    }

        /**
     * Get the specified Genre by matching the id of a exited genre 
     * from database;
     * The method should return the Genre object by a id;
     *
     * @param genreID the id of a genre object
     * @return return the information of a genre by specified id;
     * table.
     */
    @Override
    public Genre searchGenreByid(int genreID) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Genre g = null;
        
        try{
            conn = getConnection();
            
            String query = "SELECT * FROM Genre WHERE genreID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, genreID);
            rs = ps.executeQuery();
            if(rs.next()){
                g = new Genre(rs.getInt("genreID"), rs.getString("genre"));
            }
        } catch(SQLException e){
            System.out.println("ERROR ON searchGenreByid() method, " + e.getMessage());
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
                System.out.println("ERROR ON searchGenreByid() method, " + e.getMessage());
            }
        }
        return g;
    }

    /**
     * Get the specified Genre by matching the name of a exited genre 
     * from database;
     * The method should return the Genre object
     * which contains the specified genre.
     *
     * @param genre the name of genre object
     * @return return the information of a genre by specified genre;
     * table.
     */
    @Override
    public Genre searchGenreByGenre(String genre) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Genre g = null;

        con = getConnection();

        String query = "SELECT * FROM Genre WHERE genre = ? ";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, genre);
            rs = ps.executeQuery();
            if (rs.next()) {
                g = new Genre(rs.getInt("genreID"), rs.getString("genre"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the searchGenreByGenre() method: " + e.getMessage());
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
                System.out.println("Exception occured in the finally section of the searchGenreByGenre() method: " + e.getMessage());
            }
        }
        return g;
    }
    
}
