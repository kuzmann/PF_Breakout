package de.pfbeuth.game.breakout.gameEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpriteManager {
    private final List<GameObject> CURRENT_OBJECTS;
    private final Set<GameObject> REMOVED_OBJECTS;
    private final List<GameObject> COLLIDE_CHECKLIST;

    public SpriteManager() {
        this.CURRENT_OBJECTS = new ArrayList<>();
        this.REMOVED_OBJECTS = new HashSet<>();
        this.COLLIDE_CHECKLIST = new ArrayList<>();
    }

    public List<GameObject> getCurrentObjects(){
        return CURRENT_OBJECTS;
    }
    public void addCurrentObjects (GameObject... objects){
        CURRENT_OBJECTS.addAll(Arrays.asList(objects));
    }
    public void removeCurrentObjects (GameObject... objects){
        CURRENT_OBJECTS.removeAll(Arrays.asList(objects));
    }
    public void  resetCurrentObjects (){
        CURRENT_OBJECTS.clear();
    }
    public Set getRemovedObjects() {
        return REMOVED_OBJECTS;
    }
    public void addToRemovedObjects(GameObject... objects){
        if(objects.length > 1)
            REMOVED_OBJECTS.addAll(Arrays.asList(objects));
        else
            REMOVED_OBJECTS.add(objects[0]);
    }
    public  void resetRemovedObjects(){
        CURRENT_OBJECTS.removeAll(REMOVED_OBJECTS);
        REMOVED_OBJECTS.clear();
    }
    public List getCollideCheckList() {
        return COLLIDE_CHECKLIST;
    }
    public void resetCollideCheckList() {
        COLLIDE_CHECKLIST.clear();
        COLLIDE_CHECKLIST.addAll(CURRENT_OBJECTS);
    }
}
