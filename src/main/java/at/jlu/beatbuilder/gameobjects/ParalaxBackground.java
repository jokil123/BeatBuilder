package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ParalaxBackground extends LevelObject {
    ArrayList<ParalaxLayer> layers = new ArrayList<>();

    public ParalaxBackground(ArrayList<LevelObject> levelObjectList) throws SlickException {
        super(levelObjectList);

        layers.add(new ParalaxLayer(0, 0.2f, 0, 0.5f, new Image("res/img/straße und baum.png"),0));
        layers.add(new ParalaxLayer(0, 0.5f, 0, 1, new Image("res/img/autos.png"),0));
        layers.add(new ParalaxLayer(-500, 0.5f, 0, 0.25f, new Image("res/img/hintere hochhäuser.png"),-1));

        layers.sort(Comparator.comparingInt(o -> o.zDepth));
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        float scrollProgress = levelTime / 10;

        for (ParalaxLayer layer : layers) {
            layer.renderLayer(gc, g, scrollProgress);
        }
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {

    }
}
