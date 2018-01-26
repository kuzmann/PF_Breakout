package de.pfbeuth.game.breakout.gameEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *  This class manages the scene objects and keep watch on
 *  the current and removed objects
 **/
class SpriteManager {
    private final List<GameObject> CURRENT_OBJECTS;
    private final Set<GameObject> REMOVED_OBJECTS;

    /** ------ CONSTRUCTOR ------ */
    public SpriteManager() {
        this.CURRENT_OBJECTS = new ArrayList<>();
        this.REMOVED_OBJECTS = new HashSet<>();
    }

    /** adds all passed GameObjects to the CURRENT_OBJECTS List */
    void addCurrentObjects (GameObject... objects){
        CURRENT_OBJECTS.addAll(Arrays.asList(objects));
    }
    /** adds all passed GameObjects to the REMOVED_OBJECTS SET */
    void addToRemovedObjects(GameObject... objects){
        if(objects.length > 1)
            REMOVED_OBJECTS.addAll(Arrays.asList(objects));
        else
            REMOVED_OBJECTS.add(objects[0]);
    }
    /** removes all passed GameObjects from CURRENT_OBJECTS List */
    void removeCurrentObjects (GameObject... objects){
        CURRENT_OBJECTS.removeAll(Arrays.asList(objects));
    }
    /** resets the CURRENT_OBJECTS List */
    void  resetCurrentObjects (){
        CURRENT_OBJECTS.clear();
    }
    /** resets the REMOVED_OBJECTS SET */
    void resetRemovedObjects(){
        CURRENT_OBJECTS.removeAll(REMOVED_OBJECTS);
        REMOVED_OBJECTS.clear();
    }
    /** ------ GETTER ------ */
    List<GameObject> getCurrentObjects(){
        return CURRENT_OBJECTS;
    }
}