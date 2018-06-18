import java.io.IOException;
import java.util.*;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Algorithm {

    static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    static final int MAX_DEPTH = 10;
    static final String STRATEGY = "DFS";
    List<String> urls;
    int websiteDepth;
    int searchDepth;


    public Algorithm(){
        urls = new ArrayList<String>();
    }

    /**
     * Get list of urls.
     *
     * @return List<String>: list of Urls
     */
    public List<String> getAllUrls(){
        return this.urls;
    }

    /**
     * Get search depth.
     *
     * @return int: search depth
     */
    public int getSearchDepth() {
        return searchDepth;
    }

    /**
     * Crawl entire website.
     *
     * @param url: url to crawl for
     * @throws JSoupException
     * @throws IOException
     */
    public int crawlWebsite(String url, int depth) throws JSoupException, IOException {
        if (depth != 0 && urls.isEmpty()){
            throw new IllegalArgumentException("Depth must be 0");
        }
        urls.add(url);
        depth++;
        int maximumDepth = depth;
        try{
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            org.jsoup.nodes.Document htmlDocument = connection.get();
            if(!connection.response().contentType().contains("text/html"))
            {
                throw new JSoupException("**Failure** Retrieved something other than HTML");
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element link : linksOnPage)
            {
                String nextUrl = link.absUrl("href");
                if (!nextUrl.contains("facebook") && !nextUrl.contains("twitter")){
                    if (!urls.contains(nextUrl)){
                        int subTreeDepth = this.crawlWebsite(nextUrl, depth);
                        if (subTreeDepth > maximumDepth){
                            maximumDepth = subTreeDepth;
                        }
                    }
                }
            }
            return maximumDepth;
        }
        catch (IOException ioe){
            throw new IOException("Something went wrong with the connection");
        }
    }

    /**
     * Crawl website for resource.
     *
     * @param url
     * @param resourceName
     * @return String: url of the resource
     * @throws IOException
     */
    public String crawlResource(String url, String resourceName) throws IOException, JSoupException, SearchException {

        Stack<List<Object>> urlStack = new Stack<List<Object>>();
        List<String> discoveredUrls = new ArrayList<String>();
        this.searchDepth = 0;
        List<Object> initialStackEntry = new ArrayList<Object>();
        initialStackEntry.add(url);
        initialStackEntry.add(this.searchDepth);
        urlStack.push(initialStackEntry);
        while (!urlStack.empty()) {
            List<Object> stackEntry = urlStack.pop();
            if (!discoveredUrls.contains(stackEntry.get(0))) {
                discoveredUrls.add((String) stackEntry.get(0));
                try {
                    Connection connection = Jsoup.connect((String) stackEntry.get(0)).userAgent(USER_AGENT);
                    org.jsoup.nodes.Document htmlDocument = connection.get();
                    if(!connection.response().contentType().contains("text/html"))
                    {
                        throw new JSoupException("**Failure** Retrieved something other than HTML");
                    }
                    Elements headers = htmlDocument.select("title");
                    for (Element head : headers){
                        if (head.text().equals(resourceName)){
                            this.searchDepth = (int) stackEntry.get(1);
                            return (String) stackEntry.get(0);
                        }
                    }
                    int searchDepth = (int) stackEntry.get(1);
                    searchDepth++;
                    Elements linksOnPage = htmlDocument.select("a[href]");
                    for ( int i = linksOnPage.size() - 1; i >= 0; i--) {
                        String nextUrl = linksOnPage.get(i).absUrl("href");
                        if (!nextUrl.contains("facebook") && !nextUrl.contains("twitter")) {
                            if (!discoveredUrls.contains(nextUrl)) {
                                List<Object> newStackEntry = new ArrayList<Object>();
                                newStackEntry.add(nextUrl);
                                newStackEntry.add(searchDepth);
                                urlStack.push(newStackEntry);
                            }
                        }
                    }
                } catch (IOException ioe) {
                    throw new IOException("Something went wrong with the connection");
                }
            }
        }
        throw new SearchException("Resource not found");
    }
}
