/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Objects;

/**
 *
 * @author HaiyunYu D00188956
 * 
 */
public class Address {
    /**

  `addressID` int(10) NOT NULL,
  `userID` int(10) NOT NULL,
  `address1` varchar(50) DEFAULT NULL,
  `address2` varchar(50) DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     **/
    
    /**
     * 
     * 
     */
    private int addressID;
    private User user;
    private String primaryAddressLine1;
    private String primaryAddressLine2;
    private String primaryTown;
    private String primaryCounty;
    private String primaryEircode;
    private String optAddressLine1;
    private String optAddressLine2;
    private String optTown;
    private String optCounty;
    private String optEircode;

    public Address() {
    }

  

    public Address(int addressID, User user, String primaryAddressLine1, String primaryAddressLine2, String primaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode) {
        this.addressID = addressID;
        this.user = user;
        this.primaryAddressLine1 = primaryAddressLine1;
        this.primaryAddressLine2 = primaryAddressLine2;
        this.primaryTown = primaryTown;
        this.primaryCounty = primaryCounty;
        this.primaryEircode = primaryEircode;
        this.optAddressLine1 = optAddressLine1;
        this.optAddressLine2 = optAddressLine2;
        this.optTown = optTown;
        this.optCounty = optCounty;
        this.optEircode = optEircode;
    }
    
       public Address(User user, String primaryAddressLine1, String primaryAddressLine2, String primaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode) {
        this.user = user;
        this.primaryAddressLine1 = primaryAddressLine1;
        this.primaryAddressLine2 = primaryAddressLine2;
        this.primaryTown = primaryTown;
        this.primaryCounty = primaryCounty;
        this.primaryEircode = primaryEircode;
        this.optAddressLine1 = optAddressLine1;
        this.optAddressLine2 = optAddressLine2;
        this.optTown = optTown;
        this.optCounty = optCounty;
        this.optEircode = optEircode;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.addressID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.addressID != other.addressID) {
            return false;
        }
        return true;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPrimaryAddressLine1() {
        return primaryAddressLine1;
    }

    public void setPrimaryAddressLine1(String primaryAddressLine1) {
        this.primaryAddressLine1 = primaryAddressLine1;
    }

    public String getPrimaryAddressLine2() {
        return primaryAddressLine2;
    }

    public void setPrimaryAddressLine2(String primaryAddressLine2) {
        this.primaryAddressLine2 = primaryAddressLine2;
    }

    public String getPrimaryTown() {
        return primaryTown;
    }

    public void setPrimaryTown(String primaryTown) {
        this.primaryTown = primaryTown;
    }

    public String getPrimaryCounty() {
        return primaryCounty;
    }

    public void setPrimaryCounty(String primaryCounty) {
        this.primaryCounty = primaryCounty;
    }

    public String getPrimaryEircode() {
        return primaryEircode;
    }

    public void setPrimaryEircode(String primaryEircode) {
        this.primaryEircode = primaryEircode;
    }

    public String getOptAddressLine1() {
        return optAddressLine1;
    }

    public void setOptAddressLine1(String optAddressLine1) {
        this.optAddressLine1 = optAddressLine1;
    }

    public String getOptAddressLine2() {
        return optAddressLine2;
    }

    public void setOptAddressLine2(String optAddressLine2) {
        this.optAddressLine2 = optAddressLine2;
    }

    public String getOptTown() {
        return optTown;
    }

    public void setOptTown(String optTown) {
        this.optTown = optTown;
    }

    public String getOptCounty() {
        return optCounty;
    }

    public void setOptCounty(String optCounty) {
        this.optCounty = optCounty;
    }

    public String getOptEircode() {
        return optEircode;
    }

    public void setOptEircode(String optEircode) {
        this.optEircode = optEircode;
    }

    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID + ", user=" + user + ", primaryAddressLine1=" + primaryAddressLine1 + ", primaryAddressLine2=" + primaryAddressLine2 + ", primaryTown=" + primaryTown + ", primaryCounty=" + primaryCounty + ", primaryEircode=" + primaryEircode + ", optAddressLine1=" + optAddressLine1 + ", optAddressLine2=" + optAddressLine2 + ", optTown=" + optTown + ", optCounty=" + optCounty + ", optEircode=" + optEircode + '}';
    }

   
    


    

    

    
    
}
