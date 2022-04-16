package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ParalaxLayer {
    float xPosition;
    float scale;

    float offset;
    float speed;

    Image image;

    int zDepth;

    public ParalaxLayer(float xPosition, float scale, float offset, float speed, Image image, int zDepth) {
        this.scale = scale;
        this.xPosition = xPosition;
        this.offset = offset;
        this.speed = speed;
        this.image = image.getScaledCopy(scale);
        this.zDepth = zDepth;
    }

    public void renderLayer(GameContainer gc, Graphics g, float scrollProgress) {
        float y = (scrollProgress * speed) + offset;
        g.drawImage(image, xPosition, y);
    }
}
