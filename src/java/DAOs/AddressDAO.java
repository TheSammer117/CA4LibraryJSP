/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import interfaces.AddressDAOInterface;

/**
 *
 * @author samiwise
 */
public class AddressDAO extends DatabaseConnection implements AddressDAOInterface {

    public AddressDAO(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean addAddress(int userID, String primaryAddressLine1, String primaryAddressLine2, String PrimaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Boolean result = false;

        try {
            conn = getConnection();
            String query = "INSERT INTO address VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            int UserID = userID;
            
            ps.setInt(1, UserID);
            // THIS IS INCASE NONE OF THE PRIMARY ARE NOT FILLED... 
            if(primaryAddressLine1 == null || primaryAddressLine2 == null || PrimaryTown == null || primaryCounty == null || primaryEircode == null ){
                result = false;
            }else{
            ps.setString(2, primaryAddressLine1);
            ps.setString(3, primaryAddressLine2);
            ps.setString(4, PrimaryTown);
            ps.setString(5, primaryCounty);
            ps.setString(6, primaryEircode);
            ps.setString(7, optAddressLine1);
            ps.setString(8, optAddressLine2);
            ps.setString(9, optTown);
            ps.setString(10, optCounty);
            ps.setString(11, optEircode);
            }
           

            rs = ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in finally section in addAddress() method. " + e.getMessage());
            }
        }

        if (rs > 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
    
    @Override
    public ArrayList<Address> getAddressesByID(int userID){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address address = null;
        UserDAO ud = new UserDAO("librarydb");
        ArrayList<Address> addresses = new ArrayList<>();
        
        try{
            conn = getConnection();
            String query = "SELECT * FROM address WHERE userID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            //address.setAddress1(rs.getString("address1"));
                //address.setAddress2(rs.getString("address2"));
            
            while(rs.next()){
                address = new Address();
                address.setAddressID(rs.getInt("addressID"));
                address.setUser(ud.findUserByID(rs.getInt("userID")));
                address.setPrimaryAddressLine1(rs.getString("primaryAddressLine1"));
                address.setPrimaryAddressLine1(rs.getString("primaryAddressLine2"));
                address.setPrimaryTown(rs.getString("primaryTown"));
                address.setPrimaryCounty(rs.getString("primaryCounty"));
                address.setPrimaryEircode(rs.getString("primaryEircode"));
                address.setOptAddressLine1(rs.getString("optAddressLine1"));
                address.setOptAddressLine2(rs.getString("optAddressLine2"));
                address.setOptTown(rs.getString("optTown"));
                address.setOptCounty(rs.getString("optCounty"));
                address.setOptEircode(rs.getString("optEircode"));
                addresses.add(address);
            }
        } catch (SQLException ex){
            System.out.println("Exception occured in the getAddressesByID() method " + ex.getMessage());
            ex.printStackTrace();
        } finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(conn!=null){
                    freeConnection(conn);
                }
            } catch (SQLException e){
                System.out.println("Exception occured in the finally secion of the getAddressesByID() method " + e.getMessage());
                e.printStackTrace();
            }
        }
        return addresses;
    }
    
    @Override
    public boolean updateAddressById(int userID, String primaryAddressLine1, String primaryAddressLine2, String PrimaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode) {
          Connection conn = null;
        PreparedStatement ps = null;
        int rs = 0;
        Boolean result = null;

        try {
            conn = getConnection();
            String query = "UPDATE user SET primaryAddressLine1=?,primaryAddressLine2=?,PrimaryTown=?,primaryCounty=?,primaryEircode=?,optAddressLine1=?,optAddressLine2=?,optTown=?,optCounty=?,optEircode=? WHERE userID=?)";
            ps = conn.prepareStatement(query);
            int UserID = userID;
            
            ps.setInt(11, UserID);
            ps.setString(1, primaryAddressLine1);
            ps.setString(2, primaryAddressLine2);
            ps.setString(3, PrimaryTown);
            ps.setString(4, primaryCounty);
            ps.setString(5, primaryEircode);
            ps.setString(6, optAddressLine1);
            ps.setString(7, optAddressLine2);
            ps.setString(8, optTown);
            ps.setString(9, optCounty);
            ps.setString(10, optEircode);

            rs = ps.executeUpdate();
        } catch (SQLException se) {
            System.out.println("SQL Exception occurred: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    freeConnection(conn);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in finally section in addAddress() method. " + e.getMessage());
            }
        }

        if (rs > 0) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}
