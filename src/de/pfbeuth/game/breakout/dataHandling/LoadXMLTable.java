package de.pfbeuth.game.breakout.dataHandling;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 *  This class sorts he players according to their score (Ascending)
 */
public class LoadXMLTable {

    /** get the path the XML-data*/
    private static String outputPath = new File("src/assets/XML/playerScores.xml").getAbsolutePath();
    private static List XMLTable = new List() {
        @Override
        public int size() { return 0; }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }

        @Override
        public boolean add(Object o) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Object get(int index) {
            return null;
        }

        @Override
        public Object set(int index, Object element) {
            return null;
        }

        @Override
        public void add(int index, Object element) {

        }

        @Override
        public Object remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator listIterator() {
            return null;
        }

        @Override
        public ListIterator listIterator(int index) {
            return null;
        }

        @Override
        public List subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    private List<Player> HighscoreList = new ArrayList<Player>();
    private List<Player> temp = new ArrayList<Player>();

    /**create a table to organise the data in xml
     * return a list of player*/
    public List loadTable() {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(outputPath);

        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            XMLTable = rootNode.getChildren("Player");
            for (int i = 0; i < XMLTable.size(); i++) {

                Element node = (Element) XMLTable.get(i);

                Player o1 = new Player(node.getChildText("username"),node.getChildText("score"));
                HighscoreList.add(o1);
                temp.add(o1);
            }
        } catch (IOException io) {
        } catch (JDOMException jdomex) {
        }
        return HighscoreList;
    }

    /** it sorts the player according to their score (Ascending)*/
    public void displayHighscore(){
        boolean loop=true;
        while(loop){
            loop=false;
            for(int k=1; k < HighscoreList.size(); k++){
                if(Integer.valueOf(HighscoreList.get(k-1).getPlayerScore()) < Integer.valueOf(HighscoreList.get(k).getPlayerScore())){
                    temp.set(0,HighscoreList.get(k-1));
                    HighscoreList.set(k-1,HighscoreList.get(k));
                    HighscoreList.set(k,(Player)(temp.get(0)));
                    loop=true;
                }
            }
        }
    }

    /* ------ GETTER -----*/
    /** return an array ascending sorted lists*/
    public List<Player> getHighscoreList() {
        return HighscoreList;
    }
}