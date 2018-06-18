import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.*;

public class CrawlerTest {

    Crawler crawler;
    String baseUrl;

    @Before
    public void setUp(){
        crawler = new Crawler();
        baseUrl = "http://i371829.hera.fhict.nl/tci-test-site/index.php";
    }

    @Test
    public void testTriggerUrlsRetrieval() throws IOException, JSoupException {
        String[] expectedUrlsArray = {"http://i371829.hera.fhict.nl/tci-test-site/index.php",
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
                "http://i371829.hera.fhict.nl/tci-test-site/catalog.php"};
        List<String> actualUrls = crawler.triggerUrlsRetrieval(baseUrl);
        List<String> expectedUrlsList = Arrays.asList(expectedUrlsArray);
        assertThat(actualUrls.size(), is(equalTo(expectedUrlsList.size())));
        for (int i = 0 ; i < actualUrls.size() ; i++){
            assertThat(expectedUrlsList.contains(actualUrls.get(i)), is(true));
        }
    }

}