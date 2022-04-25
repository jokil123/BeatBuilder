package at.jlu.beatbuilder.levelobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class BuildingFoundation implements ParalaxLayer {
    public static final float FOUNDATION_WIDTH = 325f;
    public static final float FOUNDATION_HEIGHT = 15f;
    public static final float FOUNDATION_Y = 200f;
    public static final Color FOUNDATION_COLOR = new Color(110, 110, 110);

    public static final Color GROUND_COLOR = new Color(63, 156, 45);

    public int zDepth;

    public BuildingFoundation(int zDepth) {
        this.zDepth = zDepth;
    }

    @Override
    public void renderLayer(GameContainer gc, Graphics g, float scrollProgress) {
        g.setColor(FOUNDATION_COLOR);
        g.fillRect(gc.getWidth() / 2f - FOUNDATION_WIDTH / 2f, FOUNDATION_Y + scrollProgress, FOUNDATION_WIDTH, FOUNDATION_HEIGHT);

        g.setColor(GROUND_COLOR);
        g.fillRect(0, FOUNDATION_Y + FOUNDATION_HEIGHT + scrollProgress, gc.getWidth(), gc.getHeight());
    }
    
    public int getZDepth() {
        return zDepth;
    }
}
