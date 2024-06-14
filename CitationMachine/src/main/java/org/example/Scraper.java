package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Scraper {
    // Method to check if the url is valid
    public static boolean isUrlValid(String url) {
        try {
            URL obj = new URL(url);
            obj.toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
            // Error catching
        }
    }

    public static String getTitle(String url){
        // Method to get the titles from most websites
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.title();

        }catch (IOException e){
            return null;
        }
    }
    public static String getAuthorTS(String url){
        // Method to get author from TorStar websites
        // Toronto Star, The Hamilton Spectator, etc.
        try{
            Document doc = Jsoup.connect(url).get();
            Element author = doc.selectFirst("meta[name=author]");
            if (author != null){
                return author.attr("content");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateTS(String url){
        // Method for getting the publishing date from TorStar website
        try{
            Document doc = Jsoup.connect(url).get();
            Element date =  doc.selectFirst("div.dates .tnt-date.asset-date.text-muted");
            if (date != null){
                return date.text();
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getNewsletterTS(String url){
        // Method for getting the news provider owned by TorStar
        try{
            Document doc = Jsoup.connect(url).get();
            Element news = doc.selectFirst("meta[property=og:site_name]");
            if (news != null){
                return news.attr("content");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getAuthorPM(String url){
        // Method to get author from Post Media websites
        // Calgary Herald, Vancouver Sun, Saskatoon Star Phoenix, etc.
        try{
            Document doc = Jsoup.connect(url).get();
            Elements author = doc.getElementsByClass("published-by__author");
            if (author.isEmpty()){
                return null;
            }else{
                return author.text();
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDatePM(String url){
        // Method to get publishing date from Post Media websites
        try{
            Document doc = Jsoup.connect(url).get();
            Elements date =  doc.getElementsByClass("published-date__since");
            if (date.isEmpty()){
                return null;
            }else{
                String publishedDate = date.text();
                return publishedDate.replace("Published ", "");
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getNewsletterPM(String url){
        // Method to get the news provider from Post Media owned websites
        try{
            Document doc = Jsoup.connect(url).get();
            Element news = doc.selectFirst("link[title]");
            if (news != null){
                String newsletter = news.attr("title");
                return newsletter.replace(" » Feed", "");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getTitleCNE(String url){
        // Gets the title from Canadian Encyclopedia Websites
        try{
            Document doc = Jsoup.connect(url).get();
            Element title = doc.selectFirst("meta[name=title]");
            if(title != null){
                return title.attr("content");
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }

    public static String getAuthorCNE(String url){
        // Method to get author from Canadian Encyclopedia
        try{
            Document doc = Jsoup.connect(url).get();
            Element author = doc.selectFirst("div.article-info__column .info-text__value a.b-link");
            if(author != null){
                return author.text();
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateCNE(String url){
        // Method to get publishing date from Canadian Encyclopedia
        try{
            Document doc = Jsoup.connect(url).get();
            Element date =  doc.selectFirst("div.article-info__column .info-text__value--date");
            if (date != null){
                return date.text();
            }else{
                return null;
            }
        }catch (IOException e){
            return null;
        }
    }

    public static String getDate(){
        // Method to retrieve the current date, used for the access date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}