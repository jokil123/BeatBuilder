package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class PlayManager extends LevelObject {
    boolean isPlaying = true;
    float currentTime = 0;
    float noteSpeedMultiplier = 1;

    public PlayManager(ArrayList<LevelObject> levelObjectList) {
        super(levelObjectList);
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level) {

    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level) {
        if (isPlaying) {
            currentTime += delta;
        }
    }

    public void pause() {
        isPlaying = false;
    }

    public void resume() {
        isPlaying = true;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void reset() {
        currentTime = 0;
    }
}
