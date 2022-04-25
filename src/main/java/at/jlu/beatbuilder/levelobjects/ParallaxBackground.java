package at.jlu.beatbuilder.levelobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.Comparator;

public class ParallaxBackground extends LevelObject {
    ArrayList<ParalaxLayer> layers = new ArrayList<>();

    public ParallaxBackground(ArrayList<LevelObject> levelObjectList, BeatBuilderLevel level) throws SlickException {
        super(levelObjectList, level);

        layers.add(new BuildingFoundation(0));
        //layers.add(new ParalaxImageLayer(0, 0.1f, 300f, 0.5f, new Image("img/autos.png"), 1, true));
        layers.add(new ParalaxImageLayer(0, 0.25f, 300f, 0.5f, new Image("img/straße und baum squashed.png"), 2, true));
        layers.add(new ParalaxImageLayer(0, 0.15f, 300f, 0.3f, new Image("img/vordere wohnhäuser.png"), 3, true));
        layers.add(new ParalaxImageLayer(0, 0.08f, 300f, 0.2f, new Image("img/mittlere hochhäuser.png"), 4, true));
        layers.add(new ParalaxImageLayer(0, 0.08f, 300f, 0.1f, new Image("img/hintere hochhäuser.png"), 5, true));
        layers.add(new ParalaxImageLayer(100, 0.05f, 300f, 0.05f, new Image("img/hintere hochhäuser.png"), 6, true));

        layers.add(new Horizon(300f, new Color(36, 135, 31)));

        layers.sort(Comparator.comparing(ParalaxLayer::getZDepth).reversed());
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
//        float scrollProgress = level.playManager.getCurrentTime() / 10;
        float scrollProgress = level.building.getBuildingHeight();


        for (ParalaxLayer layer : layers) {
            layer.renderLayer(gc, g, scrollProgress);
        }
    }

    @Override
    public void update(GameContainer gc, int delta) {

    }
}
