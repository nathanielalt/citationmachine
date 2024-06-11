package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraper {

    public static String getTitle(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            return doc.title();

        }catch (IOException e){
            return null;
        }
    }
    public static String getAuthorCNN(String url){
    // toronto star
        try{
            Document doc = Jsoup.connect(url).get();
            return doc.selectFirst("meta[name=author]").attr("content");
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateCNN(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Elements date = doc.getElementsByClass("timestamp");
            return date.text();
        }catch (IOException e){
            return null;
        }
    }
    public static String getAuthorFox(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            return doc.selectFirst("meta[name=dc.creator]").attr("content");
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateFox(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            Elements date = doc.getElementsByClass("article-date");
            return date.text();
        }catch (IOException e){
            return null;
        }
    }
    public static String getAuthorCNE(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            return doc.selectFirst("div.article-info__column .info-text__value a.b-link").text();
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateCNE(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            return doc.selectFirst("div.article-info__column .info-text__value--date").text();
        }catch (IOException e){
            return null;
        }
    }
    public static String getPublishingDateTS(String url){
        try{
            Document doc = Jsoup.connect(url).get();
            return doc.selectFirst("div.dates .tnt-date.asset-date.text-muted").text();
        }catch (IOException e){
            return null;
        }
    }
    public static String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}


