/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Date;

/**
 *
 * @author d00182295
 */
public class User {
    
    private int userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int isAdmin;
    private int active;

    public User(int userID, String email, String password, String firstName, String lastName, int isAdmin, int active) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.active = active;
    }
    
    public User(String email, String password, String firstName, String lastName, Date joined, int isAdmin, int active) {
        
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.active = active;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

 

    public int getIsAdmin() {
        return isAdmin;
    }

   

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setActive(int active) {
        this.active = active;
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.userID;
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
        final User other = (User) obj;
        if (this.userID != other.userID) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        
        String Check = null;
        
        if(isAdmin == 1){
            Check  = "This User Is An Admin";
        }else{
            Check = "This User Is Not An Admin";
        }
        
        return userID + ": Email :" + email + ", First Name :" + firstName 
                + ", Last Name :" + lastName + "// " + Check;
    }
    
}
