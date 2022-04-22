package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public abstract class LevelObject {
    public BeatBuilderLevel level;

    public LevelObject(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) {
        levelObjectList.add(this);
        this.level = level;
    }

    abstract public void render(GameContainer gc, Graphics g);

    abstract public void update(GameContainer gc, int delta);
}
