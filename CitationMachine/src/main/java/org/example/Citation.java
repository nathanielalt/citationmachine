package org.example;
// File that makes citations
public class Citation {
    public static String makeCitation(String style, String title, String author, String publisher, String publishingDate, String accessDate, String newsletter, String url) {
        if (Scraper.isUrlValid(url)){
            // If a valid url is inputted
            switch (style) {
                // Calls ths selected method based on style and the scraper successfully scraped the proper date
                case "MLA9" -> {
                    if (author != null && publishingDate != null){
                        return MLA9(author, title, newsletter, publisher, publishingDate, url);
                    }else{
                        // Returns if no data could be scraped
                        return "Wrong website type!";
                    }

                }
                case "MLA8" -> {
                    if (author != null && publishingDate != null){
                        return MLA8(author, title, newsletter, publisher, publishingDate, url, accessDate);
                    }else{
                        return "Wrong website type!";
                    }
                }
                case "APA" -> {
                    if (author != null && publishingDate != null){
                        return APA(author, title, newsletter, publishingDate, url);
                    }else{
                        return "Wrong website type!";
                    }
                }
                case "Chicago" -> {
                    if (author != null && publishingDate != null){
                        return Chicago(author, title, newsletter, publishingDate, url);
                    }else{
                        return "Wrong website type!";
                    }
                }
            }
        }else{
            // If the url is invalid
            return "Invalid Url";
        }
        return null;
    }
    // MLA9 formatting
    public static String MLA9(String author, String title, String newsletter, String publisher, String publishingDate, String url) {
        String[] authorArray = author.split(" ");
        // If the author is more than one word
        if (authorArray.length > 1){
            // Arranges it in form Last, First
            author = authorArray[1] + ", " + authorArray[0];
        }
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        // If the date is more than two words
        if (dateArray.length > 2){
            // Arranges it in form Month Day, Year
            publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        }
        return author + ". " + "\"" + title + ".\" " + "<i>" + newsletter + "</i>" +  ", " + publisher + ", " + publishingDate + " " + url;
    }
    // Formatting for MLA8
    public static String MLA8(String author, String title, String newsletter, String publisher, String publishingDate, String url, String date) {
        String[] authorArray = author.split(" ");
        if (authorArray.length > 1){
            author = authorArray[1] + ", " + authorArray[0];
        }
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        if (dateArray.length > 2){
            publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        }
        return author + ". " + "\"" + title + ".\" " + "<i>" + newsletter + "</i>" + ", " + publisher + ", " + publishingDate +  " " + url + ". Accessed " + date + ".";
    }
    // Formatting for APA
    public static String APA(String author, String title, String newsletter, String publishingDate, String url) {
        String[] authorArray = author.split(" ");
        if (authorArray.length > 1){
            // Arranges in form Last, First Initial
            char first = authorArray[0].charAt(0);
            author = authorArray[1] + ", " + first;
        }
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        if (dateArray.length > 2){
            publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        }
        return author + ". " + "(" + publishingDate + "). " + "<i>" + title + "</i>" + ". " + newsletter + ". " + url;
    }
    // Chicago Formatting
    public static String Chicago(String author, String title, String newsletter, String publishingDate, String url) {
        String[] authorArray = author.split(" ");
        if (authorArray.length > 1){
            author = authorArray[1] + ", " + authorArray[0];
        }
        return author + ". " + "\"" + title + ".\" " + newsletter + ". " + publishingDate + ". " + url;
    }
}