package at.jlu.beatbuilder.applicationstates;

import at.jlu.beatbuilder.beatmap.BeatMap;
import at.jlu.beatbuilder.gameobjects.PlayManager;
import at.jlu.beatbuilder.gameobjects.ScoreCounter;
import at.jlu.beatbuilder.gameobjects.LevelObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;

public class BeatBuilderLevel extends BasicGameState {
    public PlayManager playManager;

    public BeatMap levelBeatMap;

    public static final int ID = 2;
    public ScoreCounter scoreCounter;

    public ArrayList<LevelObject> gameObjects = new ArrayList<LevelObject>();

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        gameObjects.forEach(gameObject -> gameObject.render(gc, g, this));

        g.drawString("Level: " + levelBeatMap.name, 10, 25);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        gameObjects.forEach(gameObject -> gameObject.update(gc, delta, this));
        scoreCounter.addScore(10);
    }

    public void loadLevel(String levelName, StateBasedGame sbg) {
        try {
            levelBeatMap = new BeatMap(levelName);
        } catch (IOException e) {
            System.out.println("Could not load beatmap, beatmap does not exist");
            return;
        }

        sbg.enterState(BeatBuilderLevel.ID);
        this.scoreCounter = new ScoreCounter(gameObjects);
        this.playManager = new PlayManager(gameObjects);

        SpawnNotes();

        System.out.println("Loaded level: " + levelName);
    }

    private void SpawnNotes() {
        gameObjects.addAll(levelBeatMap.notes);
    }

    @Override
    public int getID() {
        return BeatBuilderLevel.ID;
    }
}
