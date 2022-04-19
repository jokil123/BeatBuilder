package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class Building extends LevelObject {
    private static final float xOffset = 200f;
    private static final float startWidth = 300f;

    private float lastSurfaceArea;

    private final ArrayList<LevelObject> floorsList = new ArrayList<>();

    public Building(ArrayList<LevelObject> levelObjectList) {
        super(levelObjectList);
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        g.drawString("Building", 100, 100);
        g.drawString("Floors: " + floorsList.size(), 100, 120);
        g.drawString("Surface area: " + lastSurfaceArea, 100, 140);

        for (int i = 0; i < floorsList.size(); i++) {
            Floor floor = (Floor)floorsList.get((floorsList.size() - 1) - i);

            float val = (float) i / 4;
            floor.setColor(new Color(val, 1 - val, 0.5f));
            floor.setYPosition(i * Floor.FLOOR_HEIGHT + xOffset);

            floor.render(gc, g, level, levelTime);
        }
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {
        if (gc.getInput().isKeyPressed(Input.KEY_O)) {
            lastSurfaceArea = addFloor((float) (Math.random() * 100 - 50), (float)Math.random() * 200f);
        }
    }

    public Floor getLastFloor() {
        try {
            return (Floor)floorsList.get(floorsList.size() - 1);
        }
        catch (IndexOutOfBoundsException e) {
            addFloor(0, startWidth);
            return getLastFloor();
        }
    }

    // returns new surface area
    public float addFloor(float xPosition /* center (+ => right) */, float width) {
        float newFloorLeftPosition = xPosition - width / 2;
        float newFloorRightPosition = xPosition + width / 2;

        float oldFloorLeftPosition, oldFloorRightPosition;

        try {
            Floor oldFloor = ((Floor)floorsList.get(floorsList.size() - 1));

            oldFloorLeftPosition = oldFloor.leftPosition();
            oldFloorRightPosition = oldFloor.rightPosition();
        } catch (IndexOutOfBoundsException e) {
            oldFloorLeftPosition = -startWidth / 2;
            oldFloorRightPosition = startWidth / 2;
        }

        float leftPosition = Math.min(Math.max(newFloorLeftPosition, oldFloorLeftPosition), 0);
        float rightPosition = Math.max(Math.min(newFloorRightPosition, oldFloorRightPosition), 0);

        if (newFloorLeftPosition > oldFloorRightPosition || newFloorRightPosition < oldFloorLeftPosition) {
            return 0;
        } else {
            new Floor(floorsList, leftPosition, rightPosition);
            return rightPosition - leftPosition;
        }
    }
}
