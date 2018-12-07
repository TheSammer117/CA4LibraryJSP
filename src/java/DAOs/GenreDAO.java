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
    
}
