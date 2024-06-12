package org.example;


import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Cite extends JPanel {
    String currentStyle = "MLA9";
    String currentNews = "Post Media";
    public Cite(){
        components();
    }
    // global variables for components of the GUI

    private JTextField url_text;
    private JButton cite;
    private JTextArea result;
    private void components(){
        // setting the layout of the window so that everything is centered
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        // calling the global variables to use them in the GUI
        url_text = new JTextField();
        cite = new JButton("Cite");
        result = new JTextArea(20,50);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        result.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(result);



        // creating a panel inside the window
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[grow, fill]"));
        // style and color for the panel
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;"
                + "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");


        // placeholder text in the Enter url text field
        url_text.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Enter the url");


        // style and color for the cite button
        cite.putClientProperty(FlatClientProperties.STYLE, "[light]background: darken(@background,10%);" +
                "[dark]background: lighten(@background,10%);" +
                "borderWidth: 0;" +
                "focusWidth: 0;" +
                "innerFocusWidth: 0;" +
                "font: bold +5;");
        cite.addActionListener(_ -> {
            String url = url_text.getText();
            result.setText(getCitation(url));

        });


        // creating a title
        JLabel title = new JLabel("Welcome Back!");
        // Styling the title
        title.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");



        // creating a label to prompt the user to start citing from different websites
        JLabel startCiting = new JLabel("Start citing information from TorStar, Post Media, or The Canadian Encyclopedia!");


        // styling the label
        startCiting.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");


        // adding the title to the panel
        panel.add(title);

        // adding the label to the panel and giving it a gap from everything else
        panel.add(startCiting, "gapy 8");



        // creating a label that prompts the user to use the text field below and adding a gap
        panel.add(new JLabel("URL"), "gapy 8");

        // adding the text field where the user inputs the url of the website they wish to cite
        panel.add(url_text);


        // creating a label that prompts the user to pick the citation style they wish to use
        JLabel citationStyle = new JLabel("Pick a citation style:");
        citationStyle.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");


        // prompting the user to pick a website from the dropdown list
        JLabel website = new JLabel("Pick a website to cite:");
        website.putClientProperty(FlatClientProperties.STYLE, "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");

        // adding the label
        panel.add(website,"gapy 8");


        // creating a combobox with the list of all the websites available to cite
        String[] websites = {"Post Media", "TorStar", "The Canadian Encyclopedia"};
        JComboBox<String> websiteList = new JComboBox<>(websites);
        websiteList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) websiteList.getSelectedItem();
                switch (selected) {
                    case "Post Media" -> currentNews = "Post Media";
                    case "TorStar" -> currentNews = "TorStar";
                    case "The Canadian Encyclopedia" -> currentNews = "CNE";
                    case null -> {}
                    default -> throw new IllegalStateException("Unexpected value: " + selected);
                }
            }
        });

        // styling the dropdown list
        websiteList.putClientProperty(FlatClientProperties.STYLE, "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:3;" +
                "innerFocusWidth:3");





        // creating a combo box for the different citation options that the user may wish to use
        String[] options = {"MLA9","MLA8", "APA", "Chicago"};
        JComboBox<String> optionList = new JComboBox<>(options);
        optionList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) optionList.getSelectedItem();
                switch (selected) {
                    case "MLA9" -> currentStyle = "MLA9";
                    case "MLA8" -> currentStyle = "MLA8";
                    case "APA" -> currentStyle = "APA";
                    case "Chicago" -> currentStyle = "Chicago";
                    case null -> {}
                    default -> throw new IllegalStateException("Unexpected value: " + selected);
                }
            }
        });



                        // styling the combobox
        optionList.putClientProperty(FlatClientProperties.STYLE, "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:3;" +
                "innerFocusWidth:3");





        // adding all the other components with gaps to make everything look even
        panel.add(websiteList, "gapy 3");
        panel.add(citationStyle,"gapy 8");
        panel.add(optionList, "gapy 3");
        panel.add(cite, "gapy 10");
        panel.add(scrollPane, "gapy 10, grow");


        // adding the panel
        add(panel);

    }
    public String getCitation(String url){
        String title = "";
        String accessDate = Scraper.getDate();
        String author = "";
        String publisher = "";
        String publishingDate = "";
        String newsletter = "";
        switch (currentNews) {
            case "Post Media" -> {
                title = Scraper.getTitle(url);
                author = Scraper.getAuthorPM(url);
                publisher = "Postmedia Network Inc";
                publishingDate = Scraper.getPublishingDatePM(url);
                newsletter = Scraper.getNewsletterPM(url);
            }
            case "TorStar" -> {
                title = Scraper.getTitle(url);
                author = Scraper.getAuthorTS(url);
                publisher = "Jordan Bitove";
                publishingDate = Scraper.getPublishingDateTS(url);
                newsletter = Scraper.getNewsletterTS(url);
            }
            case "CNE" -> {
                title = Scraper.getTitleCNE(url);
                author = Scraper.getAuthorCNE(url);
                publisher = "Historica Canada";
                publishingDate = Scraper.getPublishingDateCNE(url);
                newsletter = "The Canadian Encyclopedia";
            }
        }
        switch (currentStyle) {
            case "MLA9" -> {
                if (author != null && publishingDate != null) {
                    return MLA9(author, title, newsletter, publisher, publishingDate, url);
                }else{
                    return "Wrong website type!";
                }
            }
            case "MLA8" -> {
                if (author != null && publishingDate != null) {
                    return MLA8(author, title, newsletter, publisher, publishingDate, url, accessDate);
                }else{
                    return "Wrong website type!";
                }
            }
            case "APA" -> {
                if (author != null && publishingDate != null) {
                    return APA(author, title, newsletter, publishingDate, url);
                }else{
                    return "Wrong website type!";
                }
            }
            case "Chicago" -> {
                if (author != null && publishingDate != null) {
                    return Chicago(author, title, newsletter, publishingDate, url);
                }else{
                    return "Wrong website type!";
                }
            }
        }
        return null;
    }
    public String MLA9(String author, String title, String newsletter, String publisher, String publishingDate, String url){
        String[] authorArray = author.split(" ");
        author = authorArray[1] + ", " + authorArray[0];
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        return author + ". " + "\"" + title + ".\" " + newsletter + ", " + publisher + ", " + publishingDate + "\n" + url;
    }
    public String MLA8(String author, String title, String newsletter, String publisher, String publishingDate, String url, String date){
        String[] authorArray = author.split(" ");
        author = authorArray[1] + ", " + authorArray[0];
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        return author + ". " + "\"" + title + ".\" " + newsletter + ", " + publisher + ", " + publishingDate + "\n" + url + ". Accessed " + date + ".";
    }
    public String APA(String author, String title, String newsletter, String publishingDate, String url){
        String[] authorArray = author.split(" ");
        char first = authorArray[0].charAt(0);
        author = authorArray[1] + ", " + first;
        String newDate = publishingDate.replace(",", "");
        String[] dateArray = newDate.split(" ");
        publishingDate = dateArray[1] + " " + dateArray[0] + " " + dateArray[2];
        return author + ". " + "(" + publishingDate + "). " + title + ". " + newsletter + ". " + url;
    }
    public String Chicago(String author, String title, String newsletter, String publishingDate, String url){
        String[] authorArray = author.split(" ");
        author = authorArray[1] + ", " + authorArray[0];
        return author + ". " + "\"" + title + ".\" " + newsletter + ". " + publishingDate + ". " + url;
    }
}