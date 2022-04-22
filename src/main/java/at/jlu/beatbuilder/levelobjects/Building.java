package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class Building extends LevelObject {
    private static final float xOffset = 200f;

    private float lastSurfaceArea;

    private final ArrayList<LevelObject> floorsList = new ArrayList<>();

    public Building(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) {
        super(levelObjectList, level);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        g.drawString("Building", 100, 100);
        g.drawString("Floors: " + floorsList.size(), 100, 120);
        g.drawString("Surface area: " + lastSurfaceArea, 100, 140);

        for (int i = 0; i < floorsList.size(); i++) {
            Floor floor = (Floor) floorsList.get((floorsList.size() - 1) - i);

            float val = (float) i / 4;
            floor.setColor(new Color(val, 1 - val, 0.5f));
            floor.setYPosition(i * Floor.FLOOR_HEIGHT + xOffset);

            floor.render(gc, g);
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (gc.getInput().isKeyPressed(Input.KEY_O)) {
            lastSurfaceArea = addFloor((float) (Math.random() * 100 - 50), (float) Math.random() * 200f);
        }
    }

    public Floor getLastFloor() {
        try {
            return (Floor) floorsList.get(floorsList.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            if (floorsList.size() == 0) {
                new Floor(floorsList, level, -level.playManager.getStartWidth() / 2, level.playManager.getStartWidth() / 2);
                return getLastFloor();
            } else {
                throw e;
            }
        }
    }

    // returns new surface area
    public float addFloor(float xPosition /* center (+ => right) */, float width) {
        float relativeXPosition = xPosition + getLastFloor().getXPosition();

        float newFloorLeftPosition = relativeXPosition - width / 2;
        float newFloorRightPosition = relativeXPosition + width / 2;

        float oldFloorLeftPosition, oldFloorRightPosition;

        try {
            Floor oldFloor = ((Floor) floorsList.get(floorsList.size() - 1));

            oldFloorLeftPosition = oldFloor.leftPosition();
            oldFloorRightPosition = oldFloor.rightPosition();
        } catch (IndexOutOfBoundsException e) {
            oldFloorLeftPosition = -level.playManager.getStartWidth() / 2;
            oldFloorRightPosition = level.playManager.getStartWidth() / 2;
        }

        if (Math.abs(newFloorLeftPosition - oldFloorLeftPosition) < level.playManager.timeToPixel(level.playManager.getFloorGraceTime())
                || Math.abs(newFloorRightPosition - oldFloorRightPosition) < level.playManager.timeToPixel(level.playManager.getFloorGraceTime())) {
            System.out.println("perfect");
            newFloorLeftPosition = oldFloorLeftPosition;
            newFloorRightPosition = oldFloorRightPosition;
        }

        float leftPosition = Math.max(newFloorLeftPosition, oldFloorLeftPosition);
        float rightPosition = Math.min(newFloorRightPosition, oldFloorRightPosition);

        if (newFloorLeftPosition > oldFloorRightPosition || newFloorRightPosition < oldFloorLeftPosition) {
            return 0;
        } else {
            new Floor(floorsList, level, leftPosition, rightPosition);
            return rightPosition - leftPosition;
        }
    }
}
