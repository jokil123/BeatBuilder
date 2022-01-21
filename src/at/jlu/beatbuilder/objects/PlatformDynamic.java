package at.jlu.beatbuilder.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class PlatformDynamic extends GameObject{
    public Color color;
    public float speed;
    public Direction direction;

    public PlatformDynamic(Color color, float speed, Direction direction, float x, float y, float width, float height) {
        super(x, y, width, height);
        this.color = color;
        this.speed = speed;
        this.direction = direction;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.drawString("" + x, 100, 100);
        g.drawString("" + y, 100, 200);
        g.drawString("" + width, 100, 300);
        g.drawString("" + height, 100, 400);
    }

    public void update(int delta) {
        switch (direction) {
            case RIGHT -> x += speed * delta;
            case LEFT -> x -= speed * delta;
        }
    }
}
