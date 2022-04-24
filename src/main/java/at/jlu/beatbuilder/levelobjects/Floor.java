package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.*;

import java.util.ArrayList;

public class Floor extends LevelObject {
    static final float FLOOR_HEIGHT = 100f;

    private Color color;

    private final float leftPosition, rightPosition;
    private float yPosition;

    private Image floorImage;

    public Floor(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level, float leftPosition, float rightPosition) throws SlickException {
        super(levelObjectList, level);

        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;

        color = new Color((float)Math.random() * 0.25f + 0.5f, (float)Math.random() * 0.25f + 0.5f, (float)Math.random() * 0.25f + 0.5f);

        floorImage = new Image("img/building_window.png");
        floorImage = floorImage.getScaledCopy(Math.round((float)floorImage.getWidth() / floorImage.getHeight() * FLOOR_HEIGHT), Math.round(FLOOR_HEIGHT));
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.setColor(color);
        g.setLineWidth(2.5f);
        g.fillRect((gc.getWidth() / 2f) + leftPosition, yPosition, getWidth(), FLOOR_HEIGHT);

        g.setColor(Color.red);
        g.setLineWidth(1f);
        g.drawRect(getXPosition() + gc.getWidth() / 2f - 0.5f, yPosition, 1, FLOOR_HEIGHT);


        int repetitions = (int) Math.floor(getWidth() / floorImage.getWidth());
        float windowWidth = repetitions * floorImage.getWidth();

        for (float i = 0; i < repetitions; i++) {
            floorImage.drawCentered(gc.getWidth() / 2f - windowWidth / 2f + i * floorImage.getWidth() + getXPosition() + floorImage.getWidth() / 2f, yPosition + FLOOR_HEIGHT / 2f);
        }

        //g.drawImage(floorImage, last, yPosition, 0, 0, getWidth() - last, floorImage.getHeight());
    }

    @Override
    public void update(GameContainer gc, int delta) {

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
