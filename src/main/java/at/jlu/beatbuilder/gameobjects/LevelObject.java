package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public abstract class LevelObject {
    public LevelObject(ArrayList<LevelObject> levelObjectList) {
        levelObjectList.add(this);
    }

    abstract public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime);

    abstract public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime);
}
