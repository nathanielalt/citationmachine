package org.example;


import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Cite extends JPanel {
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
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;"
                + "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");


        // placeholder text in the Enter url text field
        url_text.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Enter the url");


        // style and color for the cite button
        cite.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background: darken(@background,10%);" +
                "[dark]background: lighten(@background,10%);" +
                "borderWidth: 0;" +
                "focusWidth: 0;" +
                "innerFocusWidth: 0;" +
                "font: bold +5;");
        cite.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String url = url_text.getText();
                String title = Scraper.getTitle(url);
                String author = Scraper.getAuthorCNN(url);
                result.setText(author);


            }
        });


        // creating a title
        JLabel title = new JLabel("Welcome Back!");
        // Styling the title
        title.putClientProperty(FlatClientProperties.STYLE,"" + "font:bold +10");



        // creating a label to prompt the user to start citing from different websites
        JLabel startCiting = new JLabel("Start citing information from CNN, Fox News, Toronto Star,");

        // printing the rest on a second line
        JLabel startCitingLineTwo = new JLabel("and The Canadian Encyclopedia!");

        // styling the label
        startCiting.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");



        // styling the label
        startCitingLineTwo.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");


        // adding the title to the panel
        panel.add(title);

        // adding the label to the panel and giving it a gap from everything else
        panel.add(startCiting, "gapy 8");
        panel.add(startCitingLineTwo);


        // creating a label that prompts the user to use the text field below and adding a gap
        panel.add(new JLabel("URL"), "gapy 8");

        // adding the text field where the user inputs the url of the website they wish to cite
        panel.add(url_text);


        // creating a label that prompts the user to pick the citation style they wish to use
        JLabel citationStyle = new JLabel("Pick a citation style:");
        citationStyle.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");


        // prompting the user to pick a website from the dropdown list
        JLabel website = new JLabel("Pick a website to cite:");
        website.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%);");

        // adding the label
        panel.add(website,"gapy 8");


        // creating a combobox with the list of all the websites available to cite
        String websites[] = {"CNN", "Fox News", "Toronto Star", "The Canadian Encyclopedia"};
        JComboBox<String> websiteList = new JComboBox<>(websites);

        // styling the dropdown list
        websiteList.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:3;" +
                "innerFocusWidth:3");





        // creating a combo box for the different citation options that the user may wish to use
        String options[] = {"APA", "MLA", "Chicago", };
        JComboBox<String> optionList = new JComboBox<>(options);


        // styling the combobox
        optionList.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
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
}