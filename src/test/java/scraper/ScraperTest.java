package scraper;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import models.*;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(JUnitParamsRunner.class)
public class ScraperTest {

    Scraper scraper;
    ArrayList<String> urlsToSearchIn;

    @Before
    public void setUp() {
        scraper = new Scraper();
        urlsToSearchIn = new ArrayList<>();
        urlSeederHelper();
    }

    @Test
    public void testScraperProperlyInitialized(){
        Assert.assertThat(scraper, is(notNullValue()));
    }

    @Test
    @Parameters(method = "getBooksToSearchFor")
    public void testScraperFindsASingleBook(String type, String name) throws IOException {
        BookModel bookModel;

        bookModel = (BookModel) scraper.findSingleItem(type, name, urlsToSearchIn);

        Assert.assertTrue(bookModel != null);
        Assert.assertThat(bookModel.getName(), is(name));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScraperReturningExceptionOnEmptyName() throws IOException {
        scraper.findSingleItem("","", urlsToSearchIn);
    }

    @Test
    @Parameters(method = "getMoviesToSearchFor")
    public void testScraperFindsASingleMovie(String type, String name) throws IOException {
        MovieModel movieModel;

        movieModel = (MovieModel) scraper.findSingleItem(type, name, urlsToSearchIn);

        Assert.assertTrue(movieModel != null);
        Assert.assertThat(movieModel.getName(), is(name));
    }

    @Test
    @Parameters(method = "getMusicToSearchFor")
    public void testScraperFindsASingleMusic(String type, String name) throws IOException {
        MusicModel musicModel;

        musicModel = (MusicModel) scraper.findSingleItem(type, name, urlsToSearchIn);

        Assert.assertTrue(musicModel!= null);
        Assert.assertThat(musicModel.getName(), is(name));
    }

    @Test
    public void testScraperFindingAllItems() throws IOException {
        ArrayList<Item> allFoundItems;

        allFoundItems = scraper.findAllItems(urlsToSearchIn);

        Assert.assertTrue(allFoundItems.size() == 12);
    }

    @Test
    public void testScraperFindingSingleMovie() throws IOException {
        String nameToSearchFor = "Office Space";
        String url1 = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101";
        String url2 = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202";

        MovieModel movieFromFirstUrl= scraper.findSingleMovie(nameToSearchFor, url1);
        MovieModel movieFromSecondUrl= scraper.findSingleMovie(nameToSearchFor, url2);

        Assert.assertTrue(movieFromFirstUrl == null);
        Assert.assertTrue(movieFromSecondUrl != null);
        Assert.assertThat(movieFromSecondUrl.getName(), is(nameToSearchFor));
    }

    @Test
    public void testScraperFindingSingleMusic() throws IOException {
        String nameToSearchFor = "Elvis Forever";
        String url1 = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101";
        String url2 = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302";

        MusicModel musicFromFirstUrl= scraper.findSingleMusic(nameToSearchFor, url1);
        MusicModel musicFromSecondUrl= scraper.findSingleMusic(nameToSearchFor, url2);

        Assert.assertTrue(musicFromFirstUrl == null);
        Assert.assertTrue(musicFromSecondUrl != null);
        Assert.assertThat(musicFromSecondUrl.getName(), is(nameToSearchFor));
    }

    @Test
    public void testScraperFindingSingleBook() throws IOException {
        String nameToSearchFor = "A Design Patterns: Elements of Reusable Object-Oriented Software";
        String url1 = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202";
        String url2 = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101";

        BookModel bookFromFirstUrl= scraper.findSingleBook(nameToSearchFor, url1);
        BookModel bookFromSecondUrl= scraper.findSingleBook(nameToSearchFor, url2);

        Assert.assertTrue(bookFromFirstUrl == null);
        Assert.assertTrue(bookFromSecondUrl != null);
        Assert.assertThat(bookFromSecondUrl.getName(), is(nameToSearchFor));
    }

    @Test
    @Parameters(method = "getDifferentMusic")
    public void testScraperExtractingMusicFromTable(String name, String genre, String format, int year, String artist, String pageId) throws IOException {
        MusicModel musicModel, secondMusicModel;
        String url; Elements tableRows;

        secondMusicModel = new MusicModel(name, genre, format, year, artist);
        url = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=" + pageId;
        tableRows = Jsoup.connect(url).get().select("tr");
        musicModel = scraper.extractMusicModelFromTable(tableRows, name);

        Assert.assertThat(musicModel.getName(), notNullValue());
        Assert.assertThat(musicModel.getGenre(), notNullValue());
        Assert.assertThat(musicModel.getArtist(), notNullValue());
        Assert.assertThat(musicModel.getFormat(), notNullValue());
        Assert.assertThat(musicModel.getYear(), notNullValue());
        Assert.assertTrue(musicModel.equals(secondMusicModel));
    }

    @Test
    @Parameters(method = "getDifferentMovies")
    public void testScraperExtractingMovieFromTable(String name, String genre, String format, int year, String director, List<String> writers, List<String> stars, String pageId) throws IOException {
        MovieModel movieModel, secondmovieModel;
        String url; Elements tableRows;

        secondmovieModel = new MovieModel(name, genre, format, year, director, writers, stars);
        url = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=" + pageId;
        tableRows = Jsoup.connect(url).get().select("tr");
        movieModel = scraper.extractMovieModelFromTable(tableRows, name);

        Assert.assertThat(movieModel.getName(), notNullValue());
        Assert.assertThat(movieModel.getGenre(), notNullValue());
        Assert.assertThat(movieModel.getFormat(), notNullValue());
        Assert.assertThat(movieModel.getYear(), notNullValue());
        Assert.assertTrue(movieModel.equals(secondmovieModel));
    }

    @Test
    @Parameters(method = "getDifferentBooks")
    public void testScraperExtractingBookFromTable(String name, String genre, String type, int year, List<String> authors, String publisher, String isbn, String pageId ) throws IOException {
        BookModel bookModel, secondBookModel;
        String url; Elements tableRows;

        secondBookModel = new BookModel(name, genre, type, year, authors, publisher, isbn);
        url = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=" + pageId;
        tableRows = Jsoup.connect(url).get().select("tr");
        bookModel = scraper.extractBookModelFromTable(tableRows, name);

        Assert.assertThat(bookModel.getName(), notNullValue());
        Assert.assertThat(bookModel.getGenre(), notNullValue());
        Assert.assertThat(bookModel.getFormat(), notNullValue());
        Assert.assertThat(bookModel.getYear(), notNullValue());
        Assert.assertTrue(bookModel.equals(secondBookModel));
    }

    @Test
    public void testScraperVerifyCallingFindSingleItemMethod() throws IOException {
        Scraper scraper = mock(Scraper.class);

        scraper.findSingleItem("book","SomeBookName", urlsToSearchIn);

        verify(scraper).findSingleItem("book", "SomeBookName", urlsToSearchIn);
    }

    @Test
    public void testScraperVerifyCallingExtractItemFromTableMethod(){
        Scraper scraper = mock(Scraper.class);

        scraper.extractBookModelFromTable(new Elements(),"SomeBookName");
        scraper.extractMovieModelFromTable(new Elements(),"SomeMovieName");
        scraper.extractMusicModelFromTable(new Elements(),"SomeMusicName");

        verify(scraper).extractBookModelFromTable(new Elements(), "SomeBookName");
        verify(scraper).extractMovieModelFromTable(new Elements(), "SomeMovieName");
        verify(scraper).extractMusicModelFromTable(new Elements(), "SomeMusicName");
    }

    // Spying on Scraper to see if findSingleItem() calls according findSingleBook/Music/Movie()
    @Test
    public void testScraperCheckIfFindItemCallsSearchingMethods() throws IOException {
        Scraper scraper = spy(new Scraper());
        // This url is always the first one passed.
        String firstUrlPassed = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101";

        scraper.findSingleItem("book","SomeBook", urlsToSearchIn);
        scraper.findSingleItem("music","SomeMusic", urlsToSearchIn);
        scraper.findSingleItem("movie","SomeMovie", urlsToSearchIn);

        verify(scraper, times(1)).findSingleBook("SomeBook",firstUrlPassed);
        verify(scraper, times(1)).findSingleMusic("SomeMusic",firstUrlPassed);
        verify(scraper, times(1)).findSingleMovie("SomeMovie",firstUrlPassed);
    }

    private void urlSeederHelper(){
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/index.php");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304");
        urlsToSearchIn.add("http://i371829.hera.fhict.nl/tci-test-site/catalog.php");
    }

    private static final Object[] getDifferentBooks(){
        return new Object[]{
                new Object[] {"Clean Code: A Handbook of Agile Software Craftsmanship", "Tech", "Ebook", 2008, Arrays.asList("Robert C. Martin"), "Prentice Hail", "978-0132350884", "102"},
                new Object[] {"The Clean Coder: A Code of Conduct for Professional Programmers", "Tech", "Audio", 2011, Arrays.asList("Robert C. Martin"), "Prentice Hail", "007-6092046981", "104"},
                new Object[] {"A Design Patterns: Elements of Reusable Object-Oriented Software", "Tech", "Paperback", 1994, Arrays.asList("Erich Gamma", "Richard Helm", "Ralph Johnson", "John Vlissides"), "Prentice Hail", "978-0201633610", "101"},
                new Object[] {"Refactoring: Improving the Design of Existing Code", "Tech", "Hardcover", 1999, Arrays.asList("Martin Fowler", "Kent Beck", "John Brant", "William Opdyke", "Don Roberts") , "Addison-Wesley Professional", "978-0201485677", "103"}
        };
    }

    private static final Object[] getDifferentMovies(){
        return new Object[]{
                new Object[] {"Forrest Gump", "Drama", "DVD", 1994,"Robert Zemeckis", Arrays.asList("Winston Groom", "Eric Roth"), Arrays.asList("Tom Hanks, Rebecca Williams", "Sally Field", "Michael Conner Humphreys"), "201"},
                new Object[] {"The Lord of the Rings: The Fellowship of the Ring", "Drama", "Blu-ray", 2001, "Peter Jackson", Arrays.asList("J.R.R. Tolkien", "Fran Walsh", "Philippa Boyens", "Peter Jackson"), Arrays.asList("Ron Livingston", "Jennifer Aniston", "David Herman", "Ajay Naidu", "Diedrich Bader", "Stephen Root"), "203"},
                new Object[] {"Office Space", "Comedy", "Blu-ray", 1999, "Mike Judge", Arrays.asList("William Goldman"), Arrays.asList("Ron Livingston", "Jennifer Aniston", "David Herman", "Ajay Naidu", "Diedrich Bader", "Stephen Root"), "202"},
                new Object[] {"The Princess Bride", "Comedy", "DVD", 1987, "Rob Reiner", Arrays.asList("William Goldman"), Arrays.asList("Cary Elwes", "Mandy Patinkin", "Robin Wright", "Chris Sarandon", "Christopher Guest", "Wallace Shawn", "AndrÃ© the Giant", "Fred Savage", "Peter Falk", "Billy Crystal"), "204"}
        };
    }

    private static final Object[] getDifferentMusic(){
        return new Object[]{
                new Object[] {"Beethoven: Complete Symphonies", "Clasical", "CD", 2012, "Ludwig van Beethoven", "301"},
                new Object[] {"Elvis Forever", "Rock", "Vinyl", 2015, "Elvis Presley", "302"},
                new Object[] {"No Fences", "Country", "Cassette", 1990, "Garth Brooks", "303"},
                new Object[] {"The Very Thought of You", "Jaz", "MP3", 2008, "Nat King Cole", "201"},
        };
    }

    private static final Object[] getBooksToSearchFor(){
        return new Object[]{
                new Object[]{"book", "Clean Code: A Handbook of Agile Software Craftsmanship"},
                new Object[]{"book", "The Clean Coder: A Code of Conduct for Professional Programmers"},
                new Object[]{"book", "A Design Patterns: Elements of Reusable Object-Oriented Software"},
                new Object[]{"book", "Refactoring: Improving the Design of Existing Code"}
        };
    }

    private static final Object[] getMoviesToSearchFor(){
        return new Object[]{
                new Object[]{"movie", "Forrest Gump"},
                new Object[]{"movie", "The Lord of the Rings: The Fellowship of the Ring"},
                new Object[]{"movie", "Office Space"},
                new Object[]{"movie", "The Princess Bride"}
        };
    }

    private static final Object[] getMusicToSearchFor(){
        return new Object[]{
                new Object[]{"music", "Beethoven: Complete Symphonies"},
                new Object[]{"music", "Elvis Forever"},
                new Object[]{"music", "No Fences"},
                new Object[]{"music", "The Very Thought of You"}
        };
    }
}