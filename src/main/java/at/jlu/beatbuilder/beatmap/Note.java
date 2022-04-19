package at.jlu.beatbuilder.beatmap;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.enums.NoteState;
import at.jlu.beatbuilder.enums.NoteType;
import at.jlu.beatbuilder.gameobjects.Building;
import at.jlu.beatbuilder.gameobjects.Floor;
import at.jlu.beatbuilder.gameobjects.LevelObject;
import org.lwjgl.Sys;
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

    @Override
    public void render(GameContainer gc, Graphics g, BeatBuilderLevel level, float levelTime) {
        Floor lastFloor = level.building.getLastFloor();

        float noteX = getNoteX(levelTime, level);
        noteX = track == 0 ? noteX : noteX * -1;
        noteX += gc.getWidth() / 2f;

        float noteY = track * 100;

        float noteWidth;

        if (hold == 0) {
            noteWidth = lastFloor.getWidth();
            g.drawString(noteWidth + "", 25, 100);
        } else {
            noteWidth = hold * level.playManager.getNoteSpeedMultiplier();
        }

        float noteHeight = 100;

        g.setColor(getDrawColor());

        if (track == 0) {
            noteX += lastFloor.leftPosition();
            g.fillRect(noteX, noteY, noteWidth, noteHeight);
        } else {
            noteX += lastFloor.rightPosition();
            g.fillRect(noteX - noteWidth, noteY, noteWidth, noteHeight);
        }
    }

    @Override
    public void update(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {
        float maxOffset = 500f;

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
                    notePlayedEvent(gc, delta, level, levelTime);
                }

                if (!gc.getInput().isKeyDown(Input.KEY_SPACE) || levelTime - (timeStamp + hold) > maxOffset) {
                    state = NoteState.PLAYED;
                    hitImperfection += Math.max(Math.abs(levelTime - timeStamp), maxOffset);
                    notePlayedEvent(gc, delta, level, levelTime);
                }

                break;
            case PLAYED:
            case MISSED:
                break;
        }
    }

    private void notePlayedEvent(GameContainer gc, int delta, BeatBuilderLevel level, float levelTime) {
        Floor lastFloor = level.building.getLastFloor();
        Building building = level.building;

        float xPosition = getNoteX(levelTime, level);

        building.addFloor(xPosition, lastFloor.getWidth());
    }

    private float getNoteX(float levelTime, BeatBuilderLevel level) {
        return ((timeStamp - levelTime) * level.playManager.getNoteSpeedMultiplier());
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
