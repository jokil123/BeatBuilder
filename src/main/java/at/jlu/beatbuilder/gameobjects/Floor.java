package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Floor extends LevelObject {
    static final float FLOOR_HEIGHT = 100f;

    private Color color;

    private final float leftPosition, rightPosition;
    private float yPosition;

    public Floor(ArrayList<LevelObject> levelObjectList, float leftPosition, float rightPosition) {
        super(levelObjectList);

        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        g.setColor(color);
        g.setLineWidth(2.5f);
        g.drawRect((gc.getWidth() / 2f) + leftPosition, yPosition, getWidth(), FLOOR_HEIGHT);

        g.setColor(Color.red);
        g.setLineWidth(1f);
        g.drawRect(getXPosition() + gc.getWidth() / 2f - 0.5f, yPosition, 1, FLOOR_HEIGHT);
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {

    }

    public void setYPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float leftPosition() {
        return leftPosition;
    }

    public float rightPosition() {
        return rightPosition;
    }

    public float getWidth() {
        return rightPosition - leftPosition;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getXPosition() {
        return rightPosition - getWidth() / 2f;
    }
}
