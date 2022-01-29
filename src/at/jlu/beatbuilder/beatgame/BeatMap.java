package at.jlu.beatbuilder.beatgame;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class BeatMap {
    public String name;
    public String author;
    public String description;

    public int difficulty;
    public float bpm;

    public String version;
    public String key;

    public ArrayList<String> tags;


    public Music getBeatmapMusic() throws SlickException {
        return new Music("src/at.jlu.beatbuilder/maps/" + name + name + ".wav");
    }


    public static BeatMap loadBeatmap(String beatMapName) {
        return null;
    }
}
