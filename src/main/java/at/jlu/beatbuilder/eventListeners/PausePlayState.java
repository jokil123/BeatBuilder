package at.jlu.beatbuilder.eventListeners;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface PausePlayState {
    void pause(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime);
}
