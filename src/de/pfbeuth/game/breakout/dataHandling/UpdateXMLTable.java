package de.pfbeuth.game.breakout.dataHandling;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  This class adds a new player element to the xml file
 */

public class UpdateXMLTable {

    /** set path to XML-data*/
    private static String outputPath = new File("src/assets/XML/playerScores.xml").getAbsolutePath();

    /** add a new player element to xml */
    public void add(CreatePlayer Player) {
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile;
            xmlFile = new File(outputPath);

            Document doc = builder.build(xmlFile);
            Element rootNode = doc.getRootElement();

            // add new Element to root
            Element player = new Element("Player");
            rootNode.addContent(player);
            // get the username from class Player
            Element username = new Element("username").setText(Player.getUsername());
            player.addContent(username);
            // get the score from class Player and add it as a child element to xml
            addChildElement(player, "score", Player.getScore());

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());

            //String outputString = outputter.outputString(doc);


            outputter.output(doc,new FileWriter(outputPath));

        } catch (IOException io) {
            io.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    /** adds a child element */
    private void addChildElement(Element parent, String elementName, int score){
        Element child = new Element(elementName);
        String s = Integer.toString(score);
        child.setText(s);
        parent.addContent(child);
    }
}