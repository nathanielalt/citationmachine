package org.example;
import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CiteManually extends JFrame {

    private JEditorPane citationText;
    private JEditorPane citedInformationText; // New JTextArea for displaying cited information
    private JComboBox<String> citationStyleBox;
    private JTextField authorField, publisherField, publishingDateField, titleField, websiteTitleField, urlField;

    public CiteManually(String initialCitation) {
        setTitle("Manually Cite");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 900); // Adjust size as needed
        setLocationRelativeTo(null);
        // Panel flatlaf formatting
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "[grow, fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");

        // Display initial citation
        JLabel initialCitationLabel = new JLabel("Not Satisfied? Cite Manually!");
        initialCitationLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +3");
        // EditorPane so italics are allowed
        citationText = new JEditorPane("text/html", initialCitation); // Increased rows to 50
        citationText.setSize(50,100);
        citationText.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(citationText);
        scrollPane.setPreferredSize(new Dimension(600, 300)); // Adjusted scroll pane size

        panel.add(scrollPane, "gapy 10, grow");
        panel.add(initialCitationLabel, "gapy 10");
        // Various labels and fields for inputting data
        JLabel authorLabel = new JLabel("Author:");
        JLabel publisherLabel = new JLabel("Publisher:");
        JLabel publishingDateLabel = new JLabel("Publishing Date:");
        JLabel titleLabel = new JLabel("Title:");
        JLabel websiteTitleLabel = new JLabel("Website Title:");
        JLabel urlLabel = new JLabel("URL:");

        authorField = new JTextField(30);
        publisherField = new JTextField(30);
        publishingDateField = new JTextField(30);
        titleField = new JTextField(30);
        websiteTitleField = new JTextField(30);
        urlField = new JTextField(30);

        JPanel citationPanel = new JPanel(new MigLayout("wrap 2, fillx", "[right][fill]"));
        citationPanel.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        // Formatting
        citationPanel.add(authorLabel);
        citationPanel.add(authorField, "wrap, gaptop 3");
        authorField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First Last *Max one author*");
        citationPanel.add(publisherLabel);
        citationPanel.add(publisherField, "wrap, gaptop 3");
        citationPanel.add(publishingDateLabel);
        citationPanel.add(publishingDateField, "wrap, gaptop 3");
        publishingDateField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "MMMM DD, YYYY");
        citationPanel.add(titleLabel);
        citationPanel.add(titleField, "wrap, gaptop 3");
        citationPanel.add(websiteTitleLabel);
        citationPanel.add(websiteTitleField, "wrap, gaptop 3");
        citationPanel.add(urlLabel);
        citationPanel.add(urlField, "wrap, gaptop 3");

        citationStyleBox = new JComboBox<>(new String[]{"MLA9", "MLA8", "APA", "Chicago"});
        citationStyleBox.putClientProperty(FlatClientProperties.STYLE, "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:3;" +
                "innerFocusWidth:3");

        JButton citeManuallyButton = new JButton("Cite Manually");
        citeManuallyButton.putClientProperty(FlatClientProperties.STYLE, "[light]background: darken(@background,10%);" +
                "[dark]background: lighten(@background,10%);" +
                "borderWidth: 0;" +
                "focusWidth: 0;" +
                "innerFocusWidth: 0;" +
                "font: bold +5;");
        citeManuallyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Retrieves inputted data
                String author = authorField.getText();
                String publisher = publisherField.getText();
                String publishingDate = publishingDateField.getText();
                String title = titleField.getText();
                String websiteTitle = websiteTitleField.getText();
                String url = urlField.getText();

                String selectedStyle = (String) citationStyleBox.getSelectedItem();
                // In case no style is inputted, default to MLA9
                if (selectedStyle == null){
                    selectedStyle = "MLA9";
                }
                String citation = Citation.makeCitation(selectedStyle, title, author, publisher, publishingDate, Scraper.getDate(), websiteTitle, url);
                // Display the cited information in the new text area
                citedInformationText.setText(citation);
            }
        });

        // New JTextArea for displaying cited information
        citedInformationText = new JEditorPane("text/html", ""); // Increased rows to 50
        citedInformationText.setSize(50, 100);
        citedInformationText.setEditable(false);
        JScrollPane citedScrollPane = new JScrollPane(citedInformationText);
        citedScrollPane.setPreferredSize(new Dimension(600, 300)); // Adjusted scroll pane size

        panel.add(citationPanel, "span, wrap, gaptop 10");
        panel.add(citationStyleBox, "span, wrap, gaptop 10");
        panel.add(citeManuallyButton, "span, wrap, gaptop 10");
        panel.add(citedScrollPane, "gapy 10, grow");

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String initialCitation = "Initial citation from URL"; // Replace with actual citation
            CiteManually frame = new CiteManually(initialCitation);
            frame.setVisible(true);
        });
    }
}
