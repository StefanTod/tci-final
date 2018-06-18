import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Crawler {

    List<String> allUrls;
    Algorithm algorithm;

    public Crawler() {
        allUrls = new ArrayList<String>();
        algorithm = new Algorithm();
    }

    public List<String> getAllUrls() {
        return allUrls;
    }

    public Item findSingleItem(String itemType, String itemName, String baseUrl) throws IllegalArgumentException {
        if (!itemType.equals("book") && !itemType.equals("movie") && !itemType.equals("music")) {
            throw new IllegalArgumentException(String.format("Our API only supports looking for books, movies and music. You cannot look for items of type %1$s!", itemType));
        }

        if (itemName.length() < 1) {
            throw new IllegalArgumentException("The name of the item that is being looked for cannot be an empty tring.");
        }

        algorithm.getAllUrls();
        algorithm.crawlWebsite(baseUrl, 0);

        return null;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

}
