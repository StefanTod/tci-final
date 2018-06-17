package scraper;

import models.BookModel;
import models.Item;
import models.MovieModel;
import models.MusicModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Scraper {

    public Item findSingleItem(String typeToSearchFor, String nameToSearchFor, ArrayList<String> urlsToSearchIn) throws IOException {
        for (String url: urlsToSearchIn) {
            if(url.contains("details")){
                switch (typeToSearchFor){
                    case "book":
                        BookModel searchedBook = findSingleBook(nameToSearchFor, url);
                        if(searchedBook != null){
                            return searchedBook;
                        }
                        break;
                    case "movie":
                        MovieModel searchedMovie = findSingleMovie(nameToSearchFor, url);
                        if(searchedMovie != null){
                            return searchedMovie;
                        }
                        break;
                    case "music":
                        MusicModel searchedMusic = findSingleMusic(nameToSearchFor, url);
                        if(searchedMusic != null){
                            return searchedMusic;
                        }
                        break;
                }
            }
        }
        return null;
    }

}
