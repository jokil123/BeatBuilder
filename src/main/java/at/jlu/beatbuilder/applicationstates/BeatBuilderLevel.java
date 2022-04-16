package at.jlu.beatbuilder.applicationstates;

import at.jlu.beatbuilder.beatmap.BeatMap;
import at.jlu.beatbuilder.gameobjects.*;
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

    public ArrayList<LevelObject> gameObjects = new ArrayList<>();

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        gameObjects.forEach(gameObject -> gameObject.render(gc, g, this, playManager.getCurrentTime()));

        g.drawString("Level: " + levelBeatMap.name, 10, 25);
        g.drawString("Time: " + playManager.getCurrentTime(), 10, 25+15);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        gameObjects.forEach(gameObject -> gameObject.update(gc, delta, this, playManager.getCurrentTime()));
        // scoreCounter.addScore(10);

        gc.getInput();
        if (gc.getInput().isKeyPressed(Input.KEY_R)) {
            reset();
        }

        gc.getInput();
        if (gc.getInput().isKeyPressed(Input.KEY_P)) {
            playManager.togglePause();
        }
    }

    public void loadLevel(String levelName, StateBasedGame sbg) {
        clearLevel();

        this.sbg = sbg;

        try {
            this.levelBeatMap = new BeatMap(levelName);
        } catch (IOException e) {
            System.out.println("Could not load beatmap, beatmap does not exist");
            return;
        }

        sbg.enterState(BeatBuilderLevel.ID);

//        new ParalaxBackground(gameObjects);

        this.scoreCounter = new ScoreCounter(gameObjects);
        this.playManager = new PlayManager(gameObjects);

        SpawnNotes();

        new CenterBar(gameObjects, 2, Color.red);

        System.out.println("Loaded level: " + levelName);
    }

    private void SpawnNotes() {
        gameObjects.addAll(levelBeatMap.notes);
    }

    public void reset() {
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
