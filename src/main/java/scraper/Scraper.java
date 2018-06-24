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
import java.lang.reflect.Array;
import java.util.*;

public class Scraper {

    public Scraper() {
    }

    public Item findSingleItem(String typeToSearchFor, String nameToSearchFor, List<String> urlsToSearchIn) throws IOException {
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

    public ArrayList<Item> findAllItems(List<String> urlsToSearchIn) throws IOException {
        ArrayList<Item> allFoundItems = new ArrayList<>();
        for (String url: urlsToSearchIn) {
            if(url.contains("details")) {
                Document document = Jsoup.connect(url).get();
                String title = document.select("h1").get(1).text();
                Elements tableRows = document.select("tr");
                if (url.contains("details.php?id=1")) {
                    BookModel searchedBook = extractBookModelFromTable(tableRows, title);
                    if (searchedBook != null) {
                        allFoundItems.add(searchedBook);
                    }
                }
                if (url.contains("details.php?id=2")) {
                    MovieModel searchedMovie = extractMovieModelFromTable(tableRows, title);
                    if (searchedMovie != null) {
                        allFoundItems.add(searchedMovie);
                    }
                }
                if (url.contains("details.php?id=3")) {
                    MusicModel searchedMusic = extractMusicModelFromTable(tableRows, title);
                    if (searchedMusic != null) {
                        allFoundItems.add(searchedMusic);
                    }
                }
            }
        }
        return allFoundItems;
    }

    public MusicModel findSingleMusic(String nameToSearchFor, String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String title = document.select("h1").get(1).text();
        Elements tableRows = document.select("tr");
        if(title.equals(nameToSearchFor)){
            return extractMusicModelFromTable(tableRows, title);
        }
        return null;
    }

    public MovieModel findSingleMovie(String nameToSearchFor, String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String title = document.select("h1").get(1).text();
        Elements tableRows = document.select("tr");
        if(title.equals(nameToSearchFor)){
            return extractMovieModelFromTable(tableRows, title);
        }
        return null;
    }

    public BookModel findSingleBook(String nameToSearchFor, String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        String title = document.select("h1").get(1).text();
        Elements tableRows = document.select("tr");
        if(title.equals(nameToSearchFor)){
            return extractBookModelFromTable(tableRows, title);
        }
        return null;
    }

    public MusicModel extractMusicModelFromTable(Elements tableRows,String name) {
        String genre = "", format = "", artist = "";
        int year = 0;
        for(int i = 0; i < tableRows.size(); i++){
            switch(i){
                case 1:
                    genre = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
                case 2:
                    format = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
                case 3:
                    year = Integer.parseInt(tableRows.get(i).select("td").text().toLowerCase());
                    break;
                case 4:
                    artist = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
            }
        }
        return new MusicModel(name, genre, format, year, artist);
    }

    public MovieModel extractMovieModelFromTable(Elements tableRows, String name) {
        String genre = "", format = "", director = "";
        ArrayList<String> writers = new ArrayList<>();
        ArrayList<String> stars = new ArrayList<>();
        int year = 0;
        for(int i = 0; i < tableRows.size(); i++){
            switch(i){
                case 1:
                    genre = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
                case 2:
                    format = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
                case 3:
                    year = Integer.parseInt(tableRows.get(i).select("td").text().toLowerCase());
                    break;
                case 4:
                    director = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
                case 5:
                    String rawWriters = tableRows.get(i).select("td").toString().toLowerCase();
                    writers.addAll(Arrays.asList(trimmedStrings(rawWriters)));
                    break;
                case 6:
                    String rawStars = tableRows.get(i).select("td").toString().toLowerCase();
                    stars.addAll(Arrays.asList(trimmedStrings(rawStars)));
                    break;
            }
        }
        return new MovieModel(name, genre, format, year, director, writers, stars);
    }

    public BookModel extractBookModelFromTable(Elements tableRows, String name) {
        String genre = "", format = "", publisher = "", isbn = "";
        ArrayList<String> authors = new ArrayList<>();
        int year = 0;
        for(int i = 0; i < tableRows.size(); i++){
            switch(i){
                case 1:
                    genre = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
                case 2:
                    format = tableRows.get(i).select("td").toString().toLowerCase();
                    break;
                case 3:
                    year = Integer.parseInt(tableRows.get(i).select("td").text().toLowerCase());
                    break;
                case 4:
                    String rawAuthors = tableRows.get(i).select("td").toString().toLowerCase();
                    authors.addAll(Arrays.asList(trimmedStrings(rawAuthors)));
                case 5:
                    publisher = tableRows.get(i).select("td").text().toLowerCase();
                    break;
                case 6:
                    isbn = tableRows.get(i).select("td").text().toLowerCase();
                    break;
            }
        }
        return new BookModel(name, genre, format, year, authors, publisher, isbn);
    }

    private String[] trimmedStrings(String rawString){
        return rawString.trim().split("\\s*,\\s*");
    }
}
