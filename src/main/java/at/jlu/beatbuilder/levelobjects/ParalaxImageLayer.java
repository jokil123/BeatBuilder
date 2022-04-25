package at.jlu.beatbuilder.levelobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ParalaxImageLayer implements ParalaxLayer {
    float xPosition;
    float scale;
    float offset;
    float speed;
    Image image;
    int zDepth;
    boolean repeat;

    public ParalaxImageLayer(float xPosition, float scale, float offset, float speed, Image image, int zDepth, boolean repeat) {
        this.scale = scale;
        this.xPosition = xPosition;
        this.offset = offset;
        this.speed = speed;
        this.image = image.getScaledCopy(scale);
        this.zDepth = zDepth;
        this.repeat = repeat;
    }

    public void renderLayer(GameContainer gc, Graphics g, float scrollProgress) {
        float y = (scrollProgress * speed) + offset;

        if (repeat) {
            for (float i = 0; i < gc.getWidth() + image.getWidth(); i += image.getWidth()) {
                g.drawImage(image, i - image.getWidth(), y - image.getHeight());
                // System.out.println(i);
            }
        } else {
            g.drawImage(image, xPosition, y - image.getHeight());
        }
    }

    public int getZDepth() {
        return zDepth;
    }
}
