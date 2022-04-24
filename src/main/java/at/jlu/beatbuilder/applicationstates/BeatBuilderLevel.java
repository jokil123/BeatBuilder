package at.jlu.beatbuilder.applicationstates;

import at.jlu.beatbuilder.BeatBuilder;
import at.jlu.beatbuilder.beatmap.BeatMap;
import at.jlu.beatbuilder.levelobjects.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

public class BeatBuilderLevel extends BasicGameState {
    public static final int ID = 2;
    private StateBasedGame sbg;

    public PlayManager playManager;

    public BeatMap levelBeatMap;

    public ScoreCounter scoreCounter;

    public Building building;

    public ArrayList<LevelObject> gameObjects = new ArrayList<>();

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        gameObjects.forEach(gameObject -> {
            try {
                gameObject.render(gc, g);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        });

        g.drawString("Level: " + levelBeatMap.name, 10, 25);
        g.drawString("Time: " + playManager.getCurrentTime(), 10, 25 + 15);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        gameObjects.forEach(gameObject -> {
            try {
                gameObject.update(gc, delta);
            } catch (SlickException e) {
                e.printStackTrace();
            }
        });

        if (gc.getInput().isKeyPressed(Input.KEY_R)) {
            reset();
        }

        if (gc.getInput().isKeyPressed(Input.KEY_P)) {
            playManager.togglePause();
        }

        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(BeatBuilder.MAINMENU);
        }
    }

    public void loadLevel(String levelName, StateBasedGame sbg) throws SlickException {
        clearLevel();

        this.sbg = sbg;

        try {
            levelBeatMap = new BeatMap(levelName, this);
        } catch (IOException e) {
            System.out.println("Could not load beatmap, beatmap does not exist");
            return;
        }

        sbg.enterState(BeatBuilderLevel.ID);

//        new ParallaxBackground(gameObjects);

        new Sky(gameObjects, this);

        new Grid(gameObjects, this);

        scoreCounter = new ScoreCounter(gameObjects, this);
        playManager = new PlayManager(gameObjects, this);

        spawnNotes();

        new CenterBar(gameObjects, this);

        building = new Building(gameObjects, this);

        new HealthDisplay(gameObjects, this);

        System.out.println("Loaded level: " + levelName);
    }

    private void spawnNotes() {
        gameObjects.addAll(levelBeatMap.notes);
    }

    public void reset() throws SlickException {
        loadLevel(levelBeatMap.name, sbg);
    }

    public void clearLevel() {
        gameObjects.clear();
    }

    @Override
    public int getID() {
        return BeatBuilderLevel.ID;
    }
}
