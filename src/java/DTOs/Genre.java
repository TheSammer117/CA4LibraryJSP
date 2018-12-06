/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 *
 * @author d00188956
 */
public class Genre {
    private int genreID;
    private String genre;

    public Genre(int genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }

    public int getGenreID() {
        return genreID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        return (((Integer)this.genreID).hashCode()
                + this.getGenre().hashCode());
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
        final Genre other = (Genre) obj;
        return (this.getGenreID() == other.getGenreID())
                && (this.getGenre().equals(other.getGenre()));
    }
    
    
    
}
