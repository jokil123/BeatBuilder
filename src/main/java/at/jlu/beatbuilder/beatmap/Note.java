package at.jlu.beatbuilder.beatmap;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.enums.NoteState;
import at.jlu.beatbuilder.enums.NoteType;
import at.jlu.beatbuilder.gameobjects.LevelObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class Note extends LevelObject {
    NoteState state;
    NoteType type;
    float timeStamp;
    float hold;

    int track;

    public Note(ArrayList<LevelObject> gameObjects, int track, float timeStamp, float hold) {
        super(gameObjects);
        this.track = track;
        this.timeStamp = timeStamp;
        this.hold = 0;

        this.state = NoteState.NOT_PLAYED;

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
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        float noteX = (timeStamp * 100 - levelTime * 0.1f * level.playManager.getNoteSpeedMultiplier());
        noteX = track == 0 ? noteX : noteX * -1;
        noteX += gc.getWidth() / 2f;

        float noteY = track * 100;
        float noteWidth = (hold != 0 ? hold : 1) * 10;
        float noteHeight = 100;

        g.setColor(getDrawColor());
        g.fillRect(noteX, noteY, noteWidth, noteHeight);
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {
        switch (state) {
            case NOT_PLAYED:
                break;
            case PLAYING:
                break;
            case PLAYED:
                break;
            case MISSED:
                break;
        }
    }

    public Color getDrawColor() {
        Color color;

        switch (state) {
            case NOT_PLAYED:
                color = Color.white;
                break;
            case PLAYING:
                color = Color.blue;
                break;
            case PLAYED:
                color = Color.darkGray;
                break;
            case MISSED:
                color = Color.red;
                break;
            default:
                color = Color.white;
        }

        return color;
    }
}
