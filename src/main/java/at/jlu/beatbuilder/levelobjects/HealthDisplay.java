package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class HealthDisplay extends LevelObject {

    public HealthDisplay(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) {
        super(levelObjectList, level);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawString("Health: " + Math.round(level.playManager.calculateHealth() * 100000f) / 1000f + "%", 25, gc.getHeight() - 25);
    }

    @Override
    public void update(GameContainer gc, int delta) {

    }
}
