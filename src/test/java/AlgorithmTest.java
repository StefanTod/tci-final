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

    @Before
    public void setUp() throws Exception {
        algorithm = new Algorithm();
    }

    private Object[] provideUrls(){
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

    @Test
    @Parameters(method = "provideUrls")
    public void testCrawlWebsite(
            String baseUrl,
            String[] expectedOutput
    ) throws IOException, JSoupException {
        algorithm.crawlWebsite(baseUrl);
        List<String> actualOutput = algorithm.getAllUrls();
        List<String> expectedOutputList = Arrays.asList(expectedOutput);
        assertThat(actualOutput.size(), is(equalTo(expectedOutputList.size())));
        for (int i = 0 ; i < actualOutput.size() ; i++){
            assertThat(expectedOutputList.contains(actualOutput.get(i)), is(true));
        }
    }

}