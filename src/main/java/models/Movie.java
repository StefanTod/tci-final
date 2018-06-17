import java.util.List;

public interface Movie extends Item {
    /**
     * Get director.
     *
     * @return String: director of movie
     */
    public String getDirector();

    /**
     * Get list of writers.
     *
     * @return List<String>: writers of movie
     */
    public List<String> Writers();

    /**
     * Get list of stars.
     *
     * @return List<String>: stars of movie
     */
    public List<String> getStars();
}
