package at.jlu.beatbuilder.levelobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface ParalaxLayer {
    int zDepth = 0;

    void renderLayer(GameContainer gc, Graphics g, float scrollProgress);

    int getZDepth();
}
