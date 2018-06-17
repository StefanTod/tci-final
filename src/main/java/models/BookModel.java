package models;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookModel implements Book {
    String genre;
    String format;
    int year;
    List<String> authors;
    String publisher;
    String isbn;

    public BookModel(String genre, String format, int year, List<String> authors, String publisher, String isbn){
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getAuthors() {
        return this.authors;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String getPublisher() {
        return this.publisher;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIsbn() {
        return this.isbn;
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
}
