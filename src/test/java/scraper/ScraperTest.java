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

import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.function.Predicate.isEqual;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.ArgumentMatchers.notNull;
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

    // Scraper is created with jsoup instance
    @Test
    public void testScraperProperlyInitialized(){
        Assert.assertThat(scraper, is(notNullValue()));
    }

    @Test
    public void testScraperFindsASingleItem() throws IOException {
        BookModel bookModel;
        MusicModel musicModel;
        MovieModel movieModel;
        String typeBook = "book";
        String typeMovie = "movie";
        String typeMusic = "music";
        String nameBook = "A Design Patterns: Elements of Reusable Object-Oriented Software";
        String nameMovie = "Office Space";
        String nameMusic = "No Fences";

        bookModel = (BookModel) scraper.findSingleItem(typeBook, nameBook, urlsToSearchIn);
        movieModel = (MovieModel) scraper.findSingleItem(typeMovie, nameMovie, urlsToSearchIn);
        musicModel = (MusicModel) scraper.findSingleItem(typeMusic, nameBook, urlsToSearchIn);

//        Assert.assertThat(bookModel, isNotNull());
//        Assert.assertThat(bookModel.getName(), is(nameBook));
//
//        Assert.assertThat(movieModel, isNotNull());
//        Assert.assertThat(movieModel.getName(), is(nameMovie));

        Assert.assertThat(musicModel, isNotNull());
        Assert.assertThat(musicModel.getName(), is(nameMusic));
    }

    @Test
    public void testScraperFindingSingleMovie(){
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