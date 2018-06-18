import com.sun.javaws.exceptions.InvalidArgumentException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class CrawlerTest {
    // Item findSingleItem(String typeToSearchFor, String nameToSearchFor, ArrayList<String> urlsToSearchIn) throws IOException
    Crawler crawler;
    String baseUrl;

    @Before
    public void setUp() {
        crawler = new Crawler();
        baseUrl = "http://i371829.hera.fhict.nl/tci-test-site/index.php";
    }

    //case book
    //case movie
    //case music
    //case none
    //type is book, name is movie - params with different combos
    //if links == null; find all links


    @Test(expected = IllegalArgumentException.class)
    public void ifAnUnexistingTypeOfMediaIsPutInThrowAnException() {
        crawler.findSingleItem("invalidItemType", "The Lord of the Rings: The Fellowship of the Ring");

        Assert.fail("After calling the findSingleItem() method of the Craweler class with an invalid item type argument, an IllegalArgumentException should have been thrown.");
    }


    private static Object[] getAllLegitItemTypes() {
        return new Object[]{
                new Object[]{"book"},
                new Object[]{"music"},
                new Object[]{"movie"}
        };
    }

    @Test
    @Parameters(method = "getAllLegitItemTypes")
    public void ifAnyOfTheSupportedTypesOfMediaAreRequestedDontThrowAnException(String validItemType) {
        try {
            crawler.findSingleItem(validItemType, "The Lord of the Rings: The Fellowship of the Ring");
        } catch (IllegalArgumentException e) {
            Assert.fail(String.format("Method getSingleItem of the Crawler class should not have thrown an IllegalArgumentException for the valid item type input \"%1$s\", when it comes with a valid item name. Despite this, it threw an exception.", validItemType));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifAnEmptyStringIsGivenAsItemNameInputWhenLookingForASingleItemThrowExceptionTest() {
        try {
            crawler.findSingleItem("movie", "");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "The name of the item that is being looked for cannot be an empty tring.");
            throw e;
        }
    }
}