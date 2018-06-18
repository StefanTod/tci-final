package models;

import java.util.List;

public class BookModel implements Book {
    private String genre;
    private String format;
    private String publisher;
    private String isbn;
    private String name;
    private List<String> authors;
    private int year;

    public BookModel(String name, String genre, String format, int year, List<String> authors, String publisher, String isbn){
        this.name = name;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() { return this.name; }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BookModel bookModel = (BookModel) o;
        return name == (bookModel.getName());
    }
}
