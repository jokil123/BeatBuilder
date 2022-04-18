package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Floor extends LevelObject{
    static final float FLOOR_HEIGHT = 100f;

    private Color color;

    private float leftPosition, rightPosition;
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
        g.drawRect((gc.getWidth() / 2f) + leftPosition, yPosition,  rightPosition - leftPosition, FLOOR_HEIGHT);
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
        return leftPosition + rightPosition;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
