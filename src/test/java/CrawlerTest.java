import com.sun.javaws.exceptions.InvalidArgumentException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test(expected = IllegalArgumentException.class)
    public void ifAnUnexistingTypeOfMediaIsPutInThrowAnException() {
        String invalidItemType = "invalidItemType";

        try {
            crawler.findSingleItem(invalidItemType, "The Lord of the Rings: The Fellowship of the Ring", baseUrl);
        } catch (IllegalArgumentException e) {
            assertEquals("When passing an ivalid itemType to the Crawler.findSingleItem(), an IllegalArgumentException should be thrown. Such an exception was thrown, but it was because of a bad itemName argument and not because of a bad itemType argument.",
                    String.format("Our API only supports looking for books, movies and music. You cannot look for items of type %1$s!",
                            invalidItemType), e.getMessage());
            throw e;
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown.");
        }

        Assert.fail("After calling the findSingleItem() method of the Crawler class with an invalid item type argument, " +
                "an IllegalArgumentException should have been thrown.");
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
            crawler.findSingleItem(validItemType, "The Lord of the Rings: The Fellowship of the Ring", baseUrl);
        } catch (IllegalArgumentException e) {
            Assert.fail(String.format("Method getSingleItem of the Crawler class should not have thrown an IllegalArgumentException for the valid item type input \"%1$s\", when it comes with a valid item name. Despite this, it threw an exception.", validItemType));
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifAnEmptyStringIsGivenAsItemNameInputWhenLookingForASingleItemThrowExceptionTest() {
        try {
            crawler.findSingleItem("movie", "", baseUrl);
        } catch (IllegalArgumentException e) {
            assertEquals("When passing an empty string argument as itemName of the Crawler.findSinleItem(), an IllegalArgumentException should be thrown. Such an exception was thrown, but it had the wrong message.",
                    "The name of the item that is being looked for cannot be an empty tring.", e.getMessage());
            throw e;
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown.");
        }

        Assert.fail("After calling the findSingleItem() method of the Crawler class an empty itemName argument, an IllegalArgumentException should have been thrown.");
    }

    @Test
    public void runTriggerUrlsRetrievalBeforeInitiatingSingleItemSearch() {
        Algorithm alg = mock(Algorithm.class);
        crawler.setAlgorithm(alg);

        try {
            crawler.findSingleItem("movie", "The Lord of the Rings: The Fellowship of the Ring", baseUrl);
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown.");
        }

        verify(alg).crawlWebsite(baseUrl, 0);
    }

    @Test
    public void makeSureTheIteratingMethodOfTheScraperIsCalled() throws IOException {
        String itemType = "movie";
        String itemName = "The Lord of the Rings: The Fellowship of the Ring";
        Scraper scr = mock(Scraper.class);

        crawler.setScraper(scr);

        crawler.findSingleItem(itemType, itemName, baseUrl);

        verify(scr).findSingleItem(itemType, itemName, (ArrayList) crawler.getAllUrls());
    }
}