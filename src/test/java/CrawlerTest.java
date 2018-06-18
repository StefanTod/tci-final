import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CrawlerTest {
    // Item findSingleItem(String typeToSearchFor, String nameToSearchFor, ArrayList<String> urlsToSearchIn) throws IOException
    Crawler crawler;
    String baseUrl;

    @Before
    public void setUp(){
        crawler = new Crawler();
        baseUrl = "http://i371829.hera.fhict.nl/tci-test-site/index.php";
    }

    //case book
    //case movie
    //case music
    //case none
    //wrong type - throw exception
    //type is book, name is movie - params with different combos
    //if links == null; find all links


    @Test (expected = IllegalArgumentException.class)
    public void ifAnUnexistingTypeOfMediaIsPutInThrowAnException(){
        crawler.findSingleItem("invalidItemType", "The Lord of the Rings: The Fellowship of the Ring");
    }

}