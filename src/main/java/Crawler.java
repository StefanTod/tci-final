import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Crawler {

    List<String> allUrls;
    Algorithm algorithm;

    public Crawler(){
        allUrls = new ArrayList<String>();
        algorithm = new Algorithm();
    }

    /**
     * Get website's urls.
     *
     * @return List<String>: list of urls
     */
    public List<String> triggerUrlsRetrieval(String baseUrl) throws IOException, JSoupException {
        algorithm.crawlWebsite(baseUrl, 0);
        allUrls = algorithm.getAllUrls();
        return this.allUrls;
    }
}
