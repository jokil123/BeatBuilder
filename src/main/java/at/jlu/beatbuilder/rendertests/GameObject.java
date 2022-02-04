package at.jlu.beatbuilder.rendertests;

import org.newdawn.slick.Graphics;

public abstract class GameObject {
    public float x;
    public float y;
    public float width;
    public float height;

    public GameObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    public void update(int delta) {}
    public void render(Graphics g) {}
}
