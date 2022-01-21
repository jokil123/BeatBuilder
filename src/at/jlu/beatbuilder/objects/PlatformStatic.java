package at.jlu.beatbuilder.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class PlatformStatic extends GameObject {
    public Color color;

    public PlatformStatic(float x, float y, float width, float height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    public void render(Graphics g) {
        color.bind();
        g.drawRect(x, y, width, height);
    }
}
