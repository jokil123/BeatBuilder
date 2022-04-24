package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.eventListeners.PausePlayState;
import at.jlu.beatbuilder.eventListeners.UnpausePlayState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class PlayManager extends LevelObject {
    private boolean isPlaying = true;
    private float currentTime = 0;
    private final float noteSpeedMultiplier = 0.5f;
    private final float floorGraceTime = 25f;
    private static final float startWidth = 300f;

    ArrayList<PausePlayState> pauseHandlers = new ArrayList<>();
    ArrayList<UnpausePlayState> unpauseHandlers = new ArrayList<>();

    public PlayManager(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) {
        super(levelObjectList, level);
    }

    @Override
    public void render(GameContainer gc, Graphics g) {

    }

    @Override
    public void update(GameContainer gc, int delta) {
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

    public void togglePause() {
        isPlaying = !isPlaying;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public float getNoteSpeedMultiplier() {
        return noteSpeedMultiplier;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }

    public float getFloorGraceTime() {
        return floorGraceTime;
    }

    public float getStartWidth() {
        return startWidth;
    }

    public float timeToPixel(float time) {
        return time * getNoteSpeedMultiplier();
    }

    public float calculateHealth() throws SlickException {
        return level.building.getLastFloor().getWidth() / startWidth;
    }
}
