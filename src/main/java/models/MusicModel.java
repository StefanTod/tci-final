package models;

public class MusicModel implements Music {
    private String genre;
    private String format;
    private String artist;
    private String name;
    private int year;

    public MusicModel(String genre, String format, int year, String artist){
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.artist = artist;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getArtist() {
        return this.artist;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGenre() {
        return this.genre;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormat() {
        return this.format;
    }

    /**
     * {@inheritDoc}
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
}
