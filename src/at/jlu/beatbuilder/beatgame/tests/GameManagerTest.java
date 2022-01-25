package at.jlu.beatbuilder.beatgame.tests;

import at.jlu.beatbuilder.beatgame.GameManager;
import at.jlu.beatbuilder.beatgame.Note;
import at.jlu.beatbuilder.beatgame.Track;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class GameManagerTest {
    public static void main(String[] args) {
        GameManager gm = new GameManager();

        Track testTrack = new Track(Input.KEY_SPACE);
        testTrack.notes.add(new Note(1));

        ArrayList<Track> testTracks = new ArrayList<Track>();
        testTracks.add(testTrack);

        gm.beatServer.loadTracks(testTracks);

        gm.beatServer.hit(1f, Input.KEY_SPACE);

        System.out.println(gm.scoreManager.getScore() + " " + gm.scoreManager.getScoreMultiplier());

        gm.beatServer.hit(100f, Input.KEY_SPACE);

        System.out.println(gm.scoreManager.getScore() + " " + gm.scoreManager.getScoreMultiplier());
    }
}
