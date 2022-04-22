package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.eventListeners.PausePlayState;
import at.jlu.beatbuilder.eventListeners.UnpausePlayState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class MusicManager implements UnpausePlayState, PausePlayState {

    public MusicManager() {

    }

    @Override
    public void pause(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {

    }

    @Override
    public void unpause(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {

    }
}
