import models.Item;
import scraper.Scraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Crawler {
    List<String> allUrls;
    Algorithm algorithm;
    Scraper scraper;

    /**
     * A regular constructor for the Crawler class.
     */
    public Crawler() {
        allUrls = new ArrayList<String>();
        algorithm = new Algorithm();
        scraper = new Scraper();
    }

    /**
     * The method which initiates the actual scraping process in the Scraper class for a single item.
     * Once an item with the desired item name and item type is found from the base url, it is returned.
     * @param itemType The type of the item that is going to be looked for. This can be "book", "movie" or "music".
     * @param itemName The title of the item.
     * @param baseUrl The base url of the site that is going to be scraped.
     * @return Either the item, that has been found, or an empty object.
     * @throws IllegalArgumentException One of the arguments of the method is incorrect in some way.
     * @throws IOException Classical IOException.
     * @throws JSoupException An exception arising from JSoup.
     */
    public Item initiateSingleItemSearch(String itemType, String itemName, String baseUrl) throws IllegalArgumentException, IOException, JSoupException {
        if (!itemType.equals("book") && !itemType.equals("movie") && !itemType.equals("music")) {
            throw new IllegalArgumentException(String.format("Our API only supports looking for books, movies and music. You cannot look for items of type %1$s!", itemType));
        }

        if (itemName.length() < 1) {
            throw new IllegalArgumentException("The name of the item that is being looked for cannot be an empty string.");
        }

        if (baseUrl.length() < 1) {
            throw new IllegalArgumentException("The base URL that is going to be crawled cannot be an empty string.");
        }

        triggerUrlsRetrieval(baseUrl);
        ArrayList toQuerry = new ArrayList<String>();

        //this.. shouldn't be needed in my personal opinion, but the algorithm
        //is returning an ArrayList now and a LinkedList then, I have absolutely
        //no idea why is that so. It was a last minute fix, so apologies about the
        //messy job.

        if(getAllUrls().getClass() == LinkedList.class){
            LinkedList<String> newList = (LinkedList) getAllUrls();
            for (String l : newList) {
                toQuerry.add(l);
            }
        } else if (getAllUrls().getClass() == ArrayList.class){
            toQuerry = (ArrayList) getAllUrls();
        }

        return scraper.findSingleItem(itemType, itemName, toQuerry);
    }

    /**
     * The method that initiates the scraping for every single item in the website.
     * @param baseUrl The base url of the site that is going to be scraped.
     * @return An ArrayList of every single book, movie or song that can be found on the pointed website.
     * @throws IOException Classical IOException.
     * @throws JSoupException An exception arising from JSoup.
     */
    public ArrayList findAllItems(String baseUrl) throws IOException, JSoupException {
        if (baseUrl.length() < 1)
            throw new IllegalArgumentException("baseUrl parameter cannot be an empty string.");

        triggerUrlsRetrieval(baseUrl);
        ArrayList toQuerry = new ArrayList<String>();

        //this.. shouldn't be needed in my personal opinion, but the algorithm
        //is returning an ArrayList now and a LinkedList then, I have absolutely
        //no idea why is that so. It was a last minute fix, so apologies about the
        //messy job.
        if(getAllUrls().getClass() == LinkedList.class){
            LinkedList<String> newList = (LinkedList) getAllUrls();
            for (String l : newList) {
                toQuerry.add(l);
            }
        } else if (getAllUrls().getClass() == ArrayList.class){
            toQuerry = (ArrayList) getAllUrls();
        }

        return scraper.findAllItems(toQuerry);
    }

    /**
     * A getter for the algorithm. Mostly used for testing.
     * @return The algorithm used.
     */
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Scraper getScraper() {
        return scraper;
    }

    public void setScraper(Scraper scraper) {
        this.scraper = scraper;
    }

    public List<String> getAllUrls() {
        return allUrls;
    }

    public void setAllUrls(List<String> allUrls) {
        this.allUrls = allUrls;
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
