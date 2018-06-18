package scraper;

import junitparams.Parameters;
import models.BookModel;
import models.MovieModel;
import models.MusicModel;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.*;

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
    public void testScraperFindsASingleItem() throws IOException {
        BookModel bookModel;
        MusicModel musicModel;
        MovieModel movieModel;
        BookModel bookThatDoesntExist;
        String nameBook = "A Design Patterns: Elements of Reusable Object-Oriented Software";
        String nameNonExistentBook = "Pretty sure there is no such name for a book";
        String nameMovie = "Office Space";
        String nameMusic = "No Fences";

        bookModel = (BookModel) scraper.findSingleItem("book", nameBook, urlsToSearchIn);
        bookThatDoesntExist = (BookModel) scraper.findSingleItem("book", nameNonExistentBook, urlsToSearchIn);
        movieModel = (MovieModel) scraper.findSingleItem("movie", nameMovie, urlsToSearchIn);
        musicModel = (MusicModel) scraper.findSingleItem("music", nameBook, urlsToSearchIn);

        Assert.assertTrue(bookModel != null);
        Assert.assertThat(bookModel.getName(), is(nameBook));

        Assert.assertTrue(movieModel != null);
        Assert.assertThat(movieModel.getName(), is(nameMovie));

        Assert.assertTrue(musicModel != null);
//        Assert.assertThat(musicModel.getName(), is(nameMusic));

        Assert.assertTrue(bookThatDoesntExist == null);
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
    public void testScraperExtractingMusicFromTable() throws IOException {
        MusicModel musicModel, secondMusicModel;
        secondMusicModel = new MusicModel("Elvis Forever", "Rock", "Vinyl", 2015, "Elvis Presley");
        String url = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302";
        Elements tableRows = Jsoup.connect(url).get().select("tr");

        musicModel = scraper.extractMusicModelFromTable(tableRows, "Elvis Forever");

        Assert.assertThat(musicModel.getName(), notNullValue());
        Assert.assertThat(musicModel.getGenre(), notNullValue());
        Assert.assertThat(musicModel.getArtist(), notNullValue());
        Assert.assertThat(musicModel.getFormat(), notNullValue());
        Assert.assertThat(musicModel.getYear(), notNullValue());
        Assert.assertTrue(musicModel.equals(secondMusicModel));
    }

    @Test
    public void testScraperExtractingMovieFromTable() throws IOException {
        MovieModel movieModel, secondmovieModel;
        ArrayList<String> writers = new ArrayList<>();
        ArrayList<String> stars = new ArrayList<>();
        // Yes I know it looks horrible, but its also 4:30 in the morning
        writers.add("J.R.R. Tolkien"); writers.add("Fran Walsh"); writers.add("Philippa Boyens");
        stars.add("Ron Livingston");stars.add("Jennifer Aniston");stars.add("David Herman");stars.add("Ajay Naidu");
        stars.add("Diedrich Bader");stars.add("Stephen Root");
        secondmovieModel = new MovieModel("The Lord of the Rings: The Fellowship of the Ring",
                "Drama", "Blu-ray", 2001, "Peter Jackson", writers,stars);
        String url = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302";
        Elements tableRows = Jsoup.connect(url).get().select("tr");

        movieModel = scraper.extractMovieModelFromTable(tableRows, "The Lord of the Rings: The Fellowship of the Ring");

        Assert.assertThat(movieModel.getName(), notNullValue());
        Assert.assertThat(movieModel.getGenre(), notNullValue());
        Assert.assertThat(movieModel.getFormat(), notNullValue());
        Assert.assertThat(movieModel.getYear(), notNullValue());
        Assert.assertTrue(movieModel.equals(secondmovieModel));
    }

//    @Test
//    public void testScraperExtractingMusicFromTable() throws IOException {
//        MusicModel musicModel, secondMusicModel;
//        secondMusicModel = new MusicModel("Elvis Forever", "Rock", "Vinyl", 2015, "Elvis Presley");
//        String url = "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302";
//        Elements tableRows = Jsoup.connect(url).get().select("tr");
//
//        musicModel = scraper.extractMusicModelFromTable(tableRows, "Elvis Forever");
//
//        Assert.assertThat(musicModel.getName(), notNullValue());
//        Assert.assertThat(musicModel.getGenre(), notNullValue());
//        Assert.assertThat(musicModel.getArtist(), notNullValue());
//        Assert.assertThat(musicModel.getFormat(), notNullValue());
//        Assert.assertThat(musicModel.getYear(), notNullValue());
//        Assert.assertTrue(musicModel.equals(secondMusicModel));
//    }

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
}