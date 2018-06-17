import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(JUnitParamsRunner.class)
public class AlgorithmTest {

    Algorithm algorithm;
    String baseUrl;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        algorithm = new Algorithm();
        baseUrl = "http://i371829.hera.fhict.nl/tci-test-site/index.php";
    }

    private Object[] provideCheckUrls(){
        return new Object[]{
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/index.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/index.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"},

                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"},

                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"},

                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"},

                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"},

                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"},

                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"},

                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music",
                        "http://i371829.hera.fhict.nl/tci-test-site/suggest.php",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303",
                        "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304",
                        "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"}
        };
    }

    private Object[] provideUrlsDepth() {
        return new Object[]{
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/index.php", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=books", 11},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=movies", 11},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=music", 11},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Books", 10},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Movies", 10},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php?cat=Music", 9},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/suggest.php", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=101", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=103", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=104", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=202", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=203", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=204", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=301", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=303", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/details.php?id=304", 12},
                new Object[]{"http://i371829.hera.fhict.nl/tci-test-site/catalog.php", 11}
                };
    }

    private Object[] provideResources(){
        return new Object[]{
                new Object[]{"Clean Code: A Handbook of Agile Software Craftsmanship", "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=102"},
                new Object[]{"Forrest Gump", "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=201"},
                new Object[]{"Elvis Forever", "http://i371829.hera.fhict.nl/tci-test-site/details.php?id=302"}
        };
    }

    @Test
    @Parameters(method = "provideCheckUrls")
    public void testCrawlWebsite(
            String baseUrl,
            String[] expectedOutput
    ) throws IOException, JSoupException {
        algorithm.crawlWebsite(baseUrl, 0);
        List<String> actualOutput = algorithm.getAllUrls();
        List<String> expectedOutputList = Arrays.asList(expectedOutput);
        assertThat(actualOutput.size(), is(equalTo(expectedOutputList.size())));
        for (int i = 0 ; i < actualOutput.size() ; i++){
            assertThat(expectedOutputList.contains(actualOutput.get(i)), is(true));
        }
    }

    @Test
    public void testCrawlWebsiteException() throws IOException, JSoupException {
        String baseUrl = "http://i341887.hera.fhict.nl/index.php";
        exception.expect(JSoupException.class);
        algorithm.crawlWebsite(baseUrl, 0);
    }

    @Test
    @Parameters(method = "provideUrlsDepth")
    public void testCrawlSetsDepth(String baseUrl, int expectedDepth) throws IOException, JSoupException {
        int actualDepth = algorithm.crawlWebsite(baseUrl, 0);
        assertThat(actualDepth, is(equalTo(expectedDepth)));
    }

    @Test
    @Parameters({"-1", "-1245"})
    public void testCrawlSetsDepthException(int wrongDepth) throws IOException, JSoupException {
        exception.expect(IllegalArgumentException.class);
        algorithm.crawlWebsite(baseUrl, wrongDepth);
    }

    @Test
    @Parameters(method = "provideResources")
    public void testCrawlResource(String resourceName, String expectedUrl) throws IOException, JSoupException, SearchException {
        String actualUrl = algorithm.crawlResource(baseUrl, resourceName);
        assertThat(actualUrl, is(equalTo(expectedUrl)));
    }

    @Test
    public void testCrawlResourceJSoupException() throws IOException, JSoupException, SearchException {
        String baseUrl = "http://i341887.hera.fhict.nl/index.php";
        exception.expect(JSoupException.class);
        algorithm.crawlResource(baseUrl, "Some resource");
    }

    @Test
    public void testCrawlResourceSearchException() throws IOException, JSoupException, SearchException {
        exception.expect(SearchException.class);
        algorithm.crawlResource(baseUrl, "No resource");
    }

}