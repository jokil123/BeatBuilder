package at.jlu.beatbuilder;

import at.jlu.beatbuilder.beatgame.beatmaps.BeatMap;
import at.jlu.beatbuilder.beatgame.GameManager;
import at.jlu.beatbuilder.beatgame.Note;
import at.jlu.beatbuilder.beatgame.Track;


import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;

public class BeatBuilder extends BasicGame {
    public GameManager gameManager;

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
        BeatMap bm = new BeatMap("test");

        gameManager = new GameManager();


        Track track = new Track(Input.KEY_SPACE);
        track.notes.add(new Note(1));

        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(track);

        gameManager.beatServer.loadTracks(tracks);
    }

    public void update(GameContainer container, int delta) throws SlickException {
        gameManager.update(container, delta);
        if (Math.random() < 0.05) {
            gameManager.beatServer.tracks.get(0).notes.add(new Note(gameManager.songManager.getPlayheadPosition() + 5));
        }
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        gameManager.render(container, g);
    }

}
