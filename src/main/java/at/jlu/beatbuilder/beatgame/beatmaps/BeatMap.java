package at.jlu.beatbuilder.beatgame.beatmaps;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

//import com.google.gson.JsonParser;
import com.google.gson.*;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class BeatMap {
    public String name;
    public String author;
    public String description;

    public float difficulty;
    public float bpm;

    public String version;
    public String key;

    public ArrayList<String> tags;

    public ArrayList<BeatMapNote> notes;

    public Music getBeatmapMusic() throws SlickException {
        return new Music("maps" + name + "/" + name + ".wav");
    }

    public BeatMap(String beatMapName) {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("maps/" + beatMapName + "/" + beatMapName + ".json"));
            Map<?, ?> map = gson.fromJson(reader, Map.class);

            this.name = beatMapName;
            this.author = (String) map.get("author");
            this.description = (String) map.get("description");
            this.difficulty = (float) ((double) map.get("difficulty"));
            this.bpm = (float) (double) map.get("bpm");
            this.version = (String) map.get("version");
            this.key = (String) map.get("key");

            this.tags = (ArrayList<String>) map.get("tags");

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static BeatMap loadBeatmap(String beatMapName) {
        return null;
    }
}
