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

    /**
     * the addressDAO constructor deals with the connection to database
     * @param databaseName the name of database
     * 
     */
    public AddressDAO(String databaseName) {
        super(databaseName);
    }

    /**
     *Return true/false when a new address updates to database
     * 
     * @param userID the id of a specific user
     * @param primaryAddressLine1 the primary address line1
     * @param primaryAddressLine2 the primary address line2
     * @param PrimaryTown the primary address town
     * @param primaryCounty the primary address country
     * @param primaryEircode the primary address eircode
     * @param optAddressLine1 the optional address line 1
     * @param optAddressLine2 the optional address line 2
     * @param optTown the optional address town
     * @param optCounty the optional address country
     * @param optEircode the optional address eircode 
     * @return a boolean while the new address recorded to the database
     */
    
     /**
     * This will allow a user to add his address(s) to the database when registering
     *
     * @param userID parameter is used to find the user in the db.
     * @param primaryAddressLine1 is a compulsory part of the address
     * @param primaryAddressLine2 is an optional part of the address
     * @param PrimaryTown is a compulsory part of the address
     * @param primaryCounty is a compulsory part of the address
     * @param primaryEircode is a compulsory part of the address
     * @param optAddressLine1 is an optional part of the address if they have a second address
     * @param optAddressLine2 is an optional part of the address if they have a second address
     * @param optTown is an optional part of the address if they have a second address
     * @param optCounty is an optional part of the address if they have a second address
     * @param optEircode is an optional part of the address if they have a second address
     
     * @return boolean indicating success or failure
     */
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
            System.out.println("ERROR ON addAddress() method, line 1 " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERROR ON addAddress() method, line 2 " + e.getMessage());
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
                System.out.println("ERROR ON addAddress() method, line 3 " + e.getMessage());
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
     *This method will return a list of addresses using the userID as a parameter
     * 
     * @param userID the user id
     * @return a arraylist of address(s) of the user
     */
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
            System.out.println("ERROR ON getAddressesByID() method, line 1  " + ex.getMessage());
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
                System.out.println("ERROR ON getAddressesByID() method, line 2" + e.getMessage());
                e.printStackTrace();
            }
        }
        return addresses;
    }
    
    /**
     *Return true/false while the fields of an address was updated to database.
     * @param userID the id of a specific user
     * @param primaryAddressLine1 the primary address line1
     * @param primaryAddressLine2 the primary address line2
     * @param PrimaryTown the primary address town
     * @param primaryCounty the primary address country
     * @param primaryEircode the primary address eircode
     * @param optAddressLine1 the optional address line 1
     * @param optAddressLine2 the optional address line 2
     * @param optTown the optional address town
     * @param optCounty the optional address country
     * @param optEircode the optional address eircode 
     * @return true/false if an exited address has been updated
     */
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
            System.out.println("ERROR ON updateAddressById() method, line 1 " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERROR ON updateAddressById() method, line 2 " + e.getMessage());
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
                System.out.println("ERROR ON updateAddressById() method, line 3 " + e.getMessage());
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
