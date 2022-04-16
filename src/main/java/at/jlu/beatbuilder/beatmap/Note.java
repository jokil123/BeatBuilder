package at.jlu.beatbuilder.beatmap;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.enums.NoteState;
import at.jlu.beatbuilder.enums.NoteType;
import at.jlu.beatbuilder.gameobjects.LevelObject;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class Note extends LevelObject {
    NoteState state;
    NoteType type;
    float timeStamp;
    float hold;

    int track;

    float hitImperfection = 0;

    public Note(ArrayList<LevelObject> gameObjects, int track, float timeStamp, float hold) {
        super(gameObjects);
        this.track = track;
        this.timeStamp = timeStamp;

        this.state = NoteState.NOT_PLAYED;

        if (hold == 0) {
            this.hold = 0;
            this.type = NoteType.SINGLE;
        } else {
            this.hold = hold;
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
        float noteX = ((timeStamp - levelTime) * level.playManager.getNoteSpeedMultiplier());
        noteX = track == 0 ? noteX : noteX * -1;
        noteX += gc.getWidth() / 2f;

        float noteY = track * 100;
        float noteWidth = (hold != 0 ? hold : 50) * level.playManager.getNoteSpeedMultiplier();
        float noteHeight = 100;

        g.setColor(getDrawColor());

        if (track == 0) {
            g.fillRect(noteX, noteY, noteWidth, noteHeight);
        } else {
            g.fillRect(noteX - noteWidth, noteY, noteWidth, noteHeight);
        }

    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {
        float maxOffset = 100f;

        switch (state) {
            case NOT_PLAYED:
                if (Math.abs(levelTime - timeStamp) < maxOffset) {
                    if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
                        state = NoteState.PLAYING;
                        hitImperfection += Math.abs(levelTime - timeStamp);
                    }
                }

                if (levelTime - (timeStamp) > maxOffset) {
                    state = NoteState.MISSED;
                }

                break;
            case PLAYING:
                if (hold == 0) {
                    state = NoteState.PLAYED;
                }

                if (!gc.getInput().isKeyDown(Input.KEY_SPACE) || levelTime - (timeStamp + hold) > maxOffset) {
                    state = NoteState.PLAYED;
                    hitImperfection += Math.max(Math.abs(levelTime - timeStamp), maxOffset);
                }

                break;
            case PLAYED:
                break;
            case MISSED:
                break;
        }
    }

    public Color getDrawColor() {
        return switch (state) {
            case NOT_PLAYED -> Color.white;
            case PLAYING -> Color.blue;
            case PLAYED -> Color.darkGray;
            case MISSED -> Color.red;
        };
    }
}
