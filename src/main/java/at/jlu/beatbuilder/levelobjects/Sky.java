package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class Sky extends LevelObject {
    public Sky(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) {
        super(levelObjectList, level);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {

    }
}
