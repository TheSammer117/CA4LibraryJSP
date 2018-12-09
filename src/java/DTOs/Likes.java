/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author Helvin
 */
public class Likes {
    private int likeID;
    private int titleID;
    private int userID;
    private int count;

    public Likes(int likeID, int titleID, int userID, int count) {
        this.likeID = likeID;
        this.titleID = titleID;
        this.userID = userID;
        this.count = count;
    }

    public Likes() {
    }

    public int getLikeID() {
        return likeID;
    }

    public void setLikeID(int likeID) {
        this.likeID = likeID;
    }

    public int getTitleID() {
        return titleID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.likeID;
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
        final Likes other = (Likes) obj;
        if (this.likeID != other.likeID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Likes{" + "likeID=" + likeID + ", titleID=" + titleID + ", userID=" + userID + ", count=" + count + '}';
    }
    
}
