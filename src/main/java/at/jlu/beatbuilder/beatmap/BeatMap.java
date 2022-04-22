package at.jlu.beatbuilder.beatmap;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.levelobjects.LevelObject;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class BeatMap {
    public BeatBuilderLevel level;

    public String name;
    public String author;
    public String description;

    public float difficulty;
    public float bpm;

    public String version;
    public String key;

    public ArrayList<String> tags;

    public int tracks;

    public ArrayList<LevelObject> notes = new ArrayList<>();

    public Music getBeatmapMusic() throws SlickException {
        return new Music("maps/" + name + "/audio.wav");
    }

    public BeatMap(String beatMapName, BeatBuilderLevel level) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("maps/" + beatMapName + "/data.json"));
        Map<?, ?> map = gson.fromJson(reader, Map.class);

        this.name = beatMapName;
        this.author = (String) map.get("author");
        this.description = (String) map.get("description");
        this.difficulty = (float) ((double) map.get("difficulty"));
        this.bpm = (float) (double) map.get("bpm");
        this.version = (String) map.get("version");
        this.key = (String) map.get("key");

        this.tags = (ArrayList<String>) map.get("tags");

        this.tracks = (int) (double) map.get("tracks");

        for (var note : (ArrayList<Map<?, ?>>) map.get("beatData")) {
            float timestamp = (float) (double) note.get("timestamp");

            int track = (int) (double) note.get("track");
            if (track > tracks - 1) {
                throw new RuntimeException("Track out of bounds");
            }

            float hold = note.get("hold") != null ? (float) (double) note.get("hold") : 0;

            new Note(notes, level, track, timestamp * 1000, hold * 1000);
        }

        reader.close();
    }
}
