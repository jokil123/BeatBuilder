package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class CenterBar extends LevelObject {
    float width = 2f;

    public CenterBar(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) {
        super(levelObjectList, level);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.setColor(Color.red);
        g.fillRect(gc.getWidth() / 2f - width / 2f, 0, width, gc.getHeight());

        Floor lastFloor = level.building.getLastFloor();

        g.setColor(Color.orange);
        g.fillRect(gc.getWidth() / 2f - width / 2f + lastFloor.rightPosition(), 0, width, gc.getHeight());
        g.fillRect(gc.getWidth() / 2f - width / 2f + lastFloor.leftPosition(), 0, width, gc.getHeight());
    }

    @Override
    public void update(GameContainer gc, int delta) {

    }
}
