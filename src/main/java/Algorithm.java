import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
     * Set search depth.
     *
     * @param searchDepth: search depth
     */
    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    /**
     * Crawl entire website.
     *
     * @param url: url to crawl for
     * @throws JSoupException
     * @throws IOException
     */
    public void crawlWebsite(String url) throws JSoupException, IOException {
        urls.add(url);
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
                        this.crawlWebsite(nextUrl);
                    }
                }
            }
        }
        catch (IOException ioe){
            throw new IOException("Something went wrong with the connection");
        }
    }
}
