/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import DTOs.Address;
import java.util.ArrayList;

/**
 *
 * @author samiwise
 */
public interface AddressDAOInterface {
    public boolean addAddress (int userID, String primaryAddressLine1, String primaryAddressLine2, String PrimaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode);
    public ArrayList<Address> getAddressesByID(int userID);
}
