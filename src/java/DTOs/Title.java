/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.util.Objects;

/**
 *
 * @author omy
 */
public class Title {

    /**
     * `titleID` int(10) NOT NULL, `novelName` varchar(50) NOT NULL, `author`
     * varchar(50) NOT NULL, `stock` int(5) NOT NULL, `onLoan` int(5) NOT NULL,
     * `titleDescription` varchar(5000) NOT NULL
     *
     */

    private int titleID;
    private String novelName;
    private String author;
    private int stock;
    private int onLoan;
    private String titleDescription;
    private int isDisable;   

    public Title() {
    }

    public Title(int titleID, String novelName, String author, int stock, int onLoan, String titleDescription) {
        this.titleID = titleID;
        this.novelName = novelName;
        this.author = author;
        this.stock = stock;
        this.onLoan = onLoan;
        this.titleDescription = titleDescription;
    }

    public Title(String novelName, String author, int stock, int onLoan, String titleDescription) {

        this.novelName = novelName;
        this.author = author;
        this.stock = stock;
        this.onLoan = onLoan;
        this.titleDescription = titleDescription;
    }

    public int getTitleID() {
        return titleID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOnLoan() {
        return onLoan;
    }

    public void setOnLoan(int onLoan) {
        this.onLoan = onLoan;
    }

    public String getTitleDescription() {
        return titleDescription;
    }

    public void setTitleDescription(String titleDescription) {
        this.titleDescription = titleDescription;
    }
    
     public int getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(int isDisable) {
        this.isDisable = isDisable;
    }

    @Override
    public String toString() {
        return "Title{" + "titleID=" + titleID + ", novelName=" + novelName + ", author=" + author + ", stock=" + stock + ", onLoan=" + onLoan + ", titleDescription=" + titleDescription + ", isDisable=" + isDisable + '}';
    }

    @Override
    public int hashCode() {
        return ((Integer)this.getTitleID()).hashCode() 
                + this.getAuthor().hashCode()
                + this.getNovelName().hashCode()
                + this.getTitleDescription().hashCode()
                + ((Integer) this.getStock()).hashCode()
                + ((Integer) this.getOnLoan()).hashCode()
                + ((Integer) this.getIsDisable()).hashCode();
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
        final Title other = (Title) obj;
        return (this.getTitleID() == other.getTitleID())
                && this.getNovelName().equals(other.getNovelName())
                && this.getAuthor().equals(other.getAuthor())
                && this.getTitleDescription().equals(other.getTitleDescription())
                && (this.getStock() == other.getStock())
                && (this.getOnLoan() == other.getOnLoan())
                && (this.getIsDisable() == other.getIsDisable());
    }

   
}
