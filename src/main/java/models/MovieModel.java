package models;

import java.util.List;

public class MovieModel implements Movie {
    String genre;
    String format;
    int year;
    String director;
    List<String> writers;
    List<String> stars;

    public MovieModel(String genre, String format, int year, String director, List<String> writers, List<String> stars){
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.director = director;
        this.writers = writers;
        this.stars = stars;
    }

    /**
     * Get director.
     *
     * @return String: director of movie
     */
    @Override
    public String getDirector() {
        return this.director;
    }

    /**
     * Get list of writers.
     *
     * @return List<String>: writers of movie
     */
    @Override
    public List<String> Writers() {
        return this.writers;
    }

    /**
     * Get list of stars.
     *
     * @return List<String>: stars of movie
     */
    @Override
    public List<String> getStars() {
        return this.stars;
    }

    /**
     * Get genre of the item.
     *
     * @return String: genre
     */
    @Override
    public String getGenre() {
        return this.genre;
    }

    /**
     * Get format of the item.
     *
     * @return String: format
     */
    @Override
    public String getFormat() {
        return this.format;
    }

    /**
     * Get year of the item.
     *
     * @return int: year
     */
    @Override
    public int getYear() {
        return this.year;
    }
}
