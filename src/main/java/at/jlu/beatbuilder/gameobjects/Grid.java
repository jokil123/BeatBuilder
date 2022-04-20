package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Grid extends LevelObject {
    float thickness = 1f;
    float spacingX = 50f;
    float spacingY = 100f;

    Color gridColor = new Color(1, 1, 1, 0.251f);

    public Grid(ArrayList<LevelObject> levelObjectList) {
        super(levelObjectList);
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        g.setColor(gridColor);

        for (float x = 0; x < gc.getWidth(); x += spacingX) {
            g.fillRect(x + gc.getWidth() / 2f - thickness / 2, 0, thickness, gc.getHeight());
            g.fillRect(x - gc.getWidth() / 2f - thickness / 2, 0, thickness, gc.getHeight());
        }

        for (float y = 0; y < gc.getHeight(); y += spacingY) {
            g.fillRect(0, y + gc.getHeight() / 2f - thickness / 2, gc.getWidth(), thickness);
            g.fillRect(0, y - gc.getHeight() / 2f - thickness / 2, gc.getWidth(), thickness);
        }
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {

    }
}
