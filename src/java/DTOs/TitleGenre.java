/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;


public class TitleGenre {
    private int genreID;
    private int titleID;

    public TitleGenre(int genreID, int titleID) {
        this.genreID = genreID;
        this.titleID = titleID;
    }

    public int getGenreID() {
        return genreID;
    }

    public int getTitleID() {
        return titleID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public void setTitleID(int titleID) {
        this.titleID = titleID;
    }

    @Override
    public String toString() {
        return "TitleGenre{" + "genreID=" + genreID + ", titleID=" + titleID + '}';
    }

    @Override
    public int hashCode() {
        return ((Integer) this.getGenreID()).hashCode() 
                +((Integer) this.getTitleID()).hashCode();
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
        final TitleGenre other = (TitleGenre) obj;
        return (this.getGenreID() == other.getGenreID())
                && (this.getTitleID() == other.getTitleID());
    }
    
    
    
}
