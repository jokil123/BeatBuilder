package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.Diagram;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;

import java.util.ArrayList;

public class ParalaxBackground extends LevelObject {
    ArrayList<Object> layers;

    SimpleDiagramRenderer svgimg =
            new SimpleDiagramRenderer(InkscapeLoader.load("res/img/autos.svg"));

    public ParalaxBackground(ArrayList<LevelObject> levelObjectList) throws SlickException {
        super(levelObjectList);
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        svgimg.render(g);
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {

    }
}
