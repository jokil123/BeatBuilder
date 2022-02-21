package at.jlu.beatbuilder;

import at.jlu.beatbuilder.beatgame.Track;
import at.jlu.beatbuilder.beatmap.BeatMap;
import at.jlu.beatbuilder.beatgame.PlayManager;
import at.jlu.beatbuilder.beatgame.Note;


import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;

public class BeatBuilder extends BasicGame {
    public PlayManager gameManager;

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

        bm.getBeatmapMusic().play();

        gameManager = new PlayManager();


        Track track = new Track(Input.KEY_SPACE);
        track.notes.add(new Note(1));

        ArrayList<Track> tracks = new ArrayList<>();
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
