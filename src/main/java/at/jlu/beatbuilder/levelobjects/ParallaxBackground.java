package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Comparator;

public class ParallaxBackground extends LevelObject {
    ArrayList<ParalaxLayer> layers = new ArrayList<>();

    public ParallaxBackground(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) throws SlickException {
        super(levelObjectList, level);

        layers.add(new ParalaxLayer(0, 0.2f, 0, 0.5f, new Image("img/straße und baum.png"), 0));
        layers.add(new ParalaxLayer(0, 0.5f, 0, 1, new Image("img/autos.png"), 0));
        layers.add(new ParalaxLayer(-500, 0.5f, 0, 0.25f, new Image("img/hintere hochhäuser.png"), -1));

        layers.sort(Comparator.comparingInt(o -> o.zDepth));
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        float scrollProgress = level.playManager.getCurrentTime() / 10;

        for (ParalaxLayer layer : layers) {
            layer.renderLayer(gc, g, scrollProgress);
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {

    }
}
