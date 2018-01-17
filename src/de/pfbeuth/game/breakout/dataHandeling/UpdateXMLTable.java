package de.pfbeuth.game.breakout.dataHandeling;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class UpdateXMLTable {

    private static String outputPath = new File("out/playerScores.xml").getAbsolutePath();

    public void add(CreatePlayer Player) {

        try {

            SAXBuilder builder = new SAXBuilder();
            File xmlFile;
            xmlFile = new File(outputPath);

            Document doc = (Document) builder.build(xmlFile);
            Element rootNode = doc.getRootElement();

            // add new Element to root
            Element player = new Element("Player");
            rootNode.addContent(player);

            Element username = new Element("username").setText(Player.getUsername());
            player.addContent(username);
            addChildElement(player, "score", Player.getScore());

            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());

            String outputString = outputter.outputString(doc);
            System.out.println(outputString);

            outputter.output(doc,new FileWriter(outputPath));

            System.out.println("File updated!");

        } catch (IOException io) {
            io.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
    }

    public static Element addChildElement(Element parent, String elementName, int score){
        Element child = new Element(elementName);
        String s = Integer.toString(score);
        child.setText(s);
        parent.addContent(child);
        return child;
    }
}