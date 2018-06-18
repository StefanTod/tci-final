package models;

import java.util.List;

public interface Book extends Item{
    /**
     * Get list of authors.
     *
     * @return List<String>: authors of book
     */
    public List<String> getAuthors();

    /**
     * Get publisher.
     *
     * @return String: publisher of book
     */
     public String getPublisher();

    /**
     * Get ISBN.
     *
     * @return String: ISBN of book
     */
    public String getIsbn();
}
