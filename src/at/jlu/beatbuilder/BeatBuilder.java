package at.jlu.beatbuilder;

import at.jlu.beatbuilder.objects.Direction;
import at.jlu.beatbuilder.objects.GameObject;
import at.jlu.beatbuilder.objects.PlatformDynamic;
import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;

public class BeatBuilder extends BasicGame {
    public ArrayList<GameObject> gameObjects = new ArrayList<>();

    public BeatBuilder() {
        super("BeatBuilder");
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new BeatBuilder());
        container.setDisplayMode(800, 600, false);
        container.setMinimumLogicUpdateInterval(25);
        container.setTargetFrameRate(120);
        container.setShowFPS(true);
        container.start();
    }

    @Override
    public void init(GameContainer container) throws SlickException {
       gameObjects.add(new PlatformDynamic(Color.red, 0.1f, Direction.RIGHT, 0,100,100, 20));

    }

    public void update(GameContainer container, int delta) throws SlickException {
        if (container.getInput().isKeyDown(Input.KEY_SPACE)) {

        }

        gameObjects.forEach(gameObject -> gameObject.update(delta));
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        gameObjects.forEach(gameObject -> gameObject.render(g));
        g.drawString("FPS: " + container.getFPS(), 50, 50);
    }

}
