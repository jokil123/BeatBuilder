package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class CenterBar extends LevelObject {
    float width;
    Color color;

    public CenterBar(ArrayList<LevelObject> levelObjectList, float width, Color color) {
        super(levelObjectList);
        this.width = width;
        this.color = color;
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        g.setColor(color);
        g.fillRect(gc.getWidth() / 2 - width / 2, 0, width, gc.getHeight());
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {

    }
}
