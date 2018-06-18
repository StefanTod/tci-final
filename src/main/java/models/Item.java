package models;

public interface Item {
    /**
     * Get genre of the item.
     *
     * @return String: genre
     */
    public String getGenre();

    /**
     * Get format of the item.
     *
     * @return String: format
     */
    public String getFormat();

    /**
     * Get year of the item.
     *
     * @return int: year
     */
    public int getYear();

    /**
     * Get the name of the item.
     *
     * @return String: name
     */
    public String getName();
}
