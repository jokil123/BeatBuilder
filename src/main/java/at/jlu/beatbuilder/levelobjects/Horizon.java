package at.jlu.beatbuilder.levelobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Horizon implements ParalaxLayer {
    private int zDepth;
    private final Color groundColor;
    private final float horizonHeight;

    public Horizon(float horizonHeight, Color groundColor) {
        this.groundColor = groundColor;
        this.horizonHeight = horizonHeight;
    }

    @Override
    public void renderLayer(GameContainer gc, Graphics g, float scrollProgress) {
        g.setColor(groundColor);
        g.fillRect(0, horizonHeight, gc.getWidth(), gc.getHeight());
    }

    @Override
    public int getZDepth() {
        return Integer.MAX_VALUE;
    }
}
