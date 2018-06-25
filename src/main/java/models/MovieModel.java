package models;

import java.util.List;
import java.util.Objects;

public class MovieModel implements Movie {
    private String genre;
    private String format;
    private int year;
    private String director;
    private List<String> writers;
    private List<String> stars;
    private String name;

    public MovieModel(String name, String genre, String format, int year, String director, List<String> writers, List<String> stars){
        this.name = name;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() { return this.name; }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        MovieModel movieModel = (MovieModel) o;
        return name == (movieModel.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(genre, format, year, director, writers, stars, name);
    }
}
