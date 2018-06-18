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
    Crawler crawler;
    String baseUrl;
    String properItemType;
    String properItemName;


    @Before
    public void setUp() {
        crawler = new Crawler();
        baseUrl = "http://i371829.hera.fhict.nl/tci-test-site/index.php";
        properItemType = "movie";
        properItemName = "The Lord of the Rings: The Fellowship of the Ring";
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifAnUnexistingTypeOfMediaIsPutInThrowAnException() {
        String invalidItemType = "invalidItemType";

        try {
            crawler.findSingleItem(invalidItemType, properItemName, baseUrl);
        } catch (IllegalArgumentException e) {
            assertEquals("When passing an ivalid itemType to the Crawler.findSingleItem(), an IllegalArgumentException should be thrown. Such an exception was thrown, but it was because of a bad itemName argument and not because of a bad itemType argument.",
                    String.format("Our API only supports looking for books, movies and music. You cannot look for items of type %1$s!",
                            invalidItemType), e.getMessage());
            throw e;
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown. Exception message: " + e.getMessage());
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
            crawler.findSingleItem(validItemType, properItemName, baseUrl);
        } catch (IllegalArgumentException e) {
            Assert.fail(String.format("Method getSingleItem of the Crawler class should not have thrown an IllegalArgumentException for the valid item type input \"%1$s\", when it comes with a valid item name. Despite this, it threw an exception.", validItemType));
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown. Exception message: " + e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifAnEmptyStringIsGivenAsItemNameInputWhenLookingForASingleItemThrowExceptionTest() {
        try {
            crawler.findSingleItem(properItemType, "", baseUrl);
        } catch (IllegalArgumentException e) {
            assertEquals("When passing an empty string argument as itemName of the Crawler.findSinleItem(), an IllegalArgumentException should be thrown. Such an exception was thrown, but it had the wrong message.",
                    "The name of the item that is being looked for cannot be an empty string.", e.getMessage());
            throw e;
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown. Exception message: " + e.getMessage());
        }
        Assert.fail("After calling the findSingleItem() method of the Crawler class an empty itemName argument, an IllegalArgumentException should have been thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifAnEmptyStringIsGivenAsURLThrowExceptionTest() {
        try {
            crawler.findSingleItem(properItemType, properItemName, "");
        } catch (IllegalArgumentException e) {
            assertEquals("When passing an empty string argument as baseUrl of the Crawler.findSinleItem(), an IllegalArgumentException should be thrown. Such an exception was thrown, but it had the wrong message.",
                    "The base URL that is going to be crawled cannot be an empty string.", e.getMessage());
            throw e;
        } catch (IOException o) {
            Assert.fail("Unexpected IOException thrown. Exception message: " + o.getMessage());
        }

        Assert.fail("After calling the findSingleItem() method of the Crawler with an empty string baseUrl argument, an IllegalArgumentException should have been thrown.");
    }

    @Test
    public void noExceptionsThrownWhenCorrectInputIsGivenToTheFindSingleItemMethod() {
        try {
            crawler.findSingleItem(properItemType, properItemName, baseUrl);
        } catch (IllegalArgumentException e) {
            Assert.fail("The arguments passed to the Crawler.findSingleItem were correct, but an IllegalArgumentException was thrown with message " + e.getMessage());
        } catch (IOException e){
            Assert.fail("Unexpected IOException thrown. Exception message: " + e.getMessage());
        }
    }

    @Test
    public void runTriggerUrlsRetrievalBeforeInitiatingSingleItemSearch() {
        Algorithm alg = mock(Algorithm.class);
        crawler.setAlgorithm(alg);

        try {
            crawler.findSingleItem(properItemType, properItemName, baseUrl);
        } catch (IOException e) {
            Assert.fail("Unexpected IOException thrown. Exception message: " + e.getMessage());
        }

        verify(alg).crawlWebsite(baseUrl, 0);
    }

    @Test
    public void makeSureTheIteratingMethodOfTheScraperIsCalled() throws IOException {
        Scraper scr = mock(Scraper.class);

        crawler.setScraper(scr);

        crawler.findSingleItem(properItemType, properItemName, baseUrl);

        verify(scr).findSingleItem(properItemType, properItemName, (ArrayList) crawler.getAllUrls());
    }
}