package at.jlu.beatbuilder.beatmap;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.gameobjects.LevelObject;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Note extends LevelObject {
    NoteType type;
    float timeStamp;
    float hold;

    int track;

    public Note(ArrayList<LevelObject> gameObjects, int track, float timeStamp, float hold) {
        super(gameObjects);
        this.track = track;
        this.timeStamp = timeStamp;
        this.hold = 0;

        if (hold == 0) {
            this.type = NoteType.SINGLE;
        } else {
            this.type = NoteType.STREAM;
        }
    }

    public float getStart() {
        return timeStamp;
    }

    public float getEnd() {
        return timeStamp + hold;
    }

    public int getTrack() {
        return track;
    }

    public NoteType getType() {
        return type;
    }

    public float getHold() {
        return hold;
    }

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level) {

    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level) {

    }
}
