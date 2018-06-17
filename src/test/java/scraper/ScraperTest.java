package scraper;

import junitparams.Parameters;
import models.BookModel;
import models.MovieModel;
import models.MusicModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.function.Predicate.isEqual;
import static org.mockito.ArgumentMatchers.isNotNull;
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
        movieModel = (MovieModel) scraper.findSingleItem(nameMovie, nameMovie, urlsToSearchIn);
        musicModel = (MusicModel) scraper.findSingleItem(typeBook, nameBook, urlsToSearchIn);

        Assert.assertThat(bookModel, isNotNull());
        Assert.assertThat(bookModel.getName(), is(nameBook));

        Assert.assertThat(movieModel, isNotNull());
        Assert.assertThat(movieModel.getName(), is(nameMovie));

        Assert.assertThat(musicModel, isNotNull());
        Assert.assertThat(musicModel.getName(), is(nameMusic));
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