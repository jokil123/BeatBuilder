package at.jlu.beatbuilder.gameobjects;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class ScoreCounter extends LevelObject {
    private int score = 0;
    private float scoreMultiplier = 1;

    private final float scoreMultiplierIncrement = 0.1f;

    public ScoreCounter(ArrayList<LevelObject> levelObjectList) {
        super(levelObjectList);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score * scoreMultiplier;
        this.scoreMultiplier += scoreMultiplierIncrement;
//        System.out.println("Score: " + score);
//        System.out.println("Score Multiplier: " + scoreMultiplier);
    }

    public void addScoreFixed(int score) {
        this.score += score;
    }

    public void resetScore() {
        this.score = 0;
        this.scoreMultiplier = 1;
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level) {
        g.drawString("Score: " + score, 10, 10);
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level) {

    }
}
