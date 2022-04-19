package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.eventListeners.PausePlayState;
import at.jlu.beatbuilder.eventListeners.UnpausePlayState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class PlayManager extends LevelObject {
    boolean isPlaying = true;
    float currentTime = 0;
    float noteSpeedMultiplier = 0.5f;

    ArrayList<PausePlayState> pauseHandlers = new ArrayList<>();
    ArrayList<UnpausePlayState> unpauseHandlers = new ArrayList<>();

    public PlayManager(ArrayList<LevelObject> levelObjectList) {
        super(levelObjectList);
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {

    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {
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
}
