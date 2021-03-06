/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import DTOs.Title;
import DTOs.Loan;
import DTOs.User;
import java.util.ArrayList;
import DTOs.Genre;
import DTOs.Likes;
import java.util.Date;
import java.util.List;
/**
 *
 * @author d00182295
 */
public interface UserDAOInterface {
    public User login(String e_mail, String p_assword);
    public int register(String email, String password, String firstName, String lastName, String primaryAddressLine1, String primaryAddressLine2, String PrimaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode);
    public boolean disableUser(int userID, User user);
    public User getUserByEmail(String email);
    public List<User> showAllUser();
    public int updateUserProfile(String email, String password, String firstName, String lastName, String primaryAddressLine1, String primaryAddressLine2, String PrimaryTown, String primaryCounty, String primaryEircode, String optAddressLine1, String optAddressLine2, String optTown, String optCounty, String optEircode);
    
    // EXTRA CODE.... d00182295
    public boolean likeATitle(Title t, User u);
    public boolean dislikeATitle(Title t, User u);
    public Likes showLikedTitle(Title t);
    public Likes showDislikedTitle(Title t);
    
}
