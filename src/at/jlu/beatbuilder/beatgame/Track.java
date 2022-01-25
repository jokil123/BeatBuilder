package at.jlu.beatbuilder.beatgame;

import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Track {
    public ArrayList<Note> notes;

    public int activationKey;

    public Track(int activationKey) {
        notes = new ArrayList<>();
        this.activationKey = activationKey;
    }

    public ArrayList<Note> getMissedNotes(float timeStamp) {
        return (ArrayList<Note>) notes.stream().filter(note -> note.timeStamp < timeStamp - GameManager.ScoreManager.maxOffset).collect(Collectors.toList());
    }

    public ArrayList<Note> getHitNotes(float timeStamp) {
        return (ArrayList<Note>) notes.stream().filter(note -> {
            System.out.println(Math.abs(note.timeStamp - timeStamp));
            System.out.println(GameManager.ScoreManager.maxOffset);
            System.out.println(Math.abs(note.timeStamp - timeStamp) < GameManager.ScoreManager.maxOffset);
            return Math.abs(note.timeStamp - timeStamp) < GameManager.ScoreManager.maxOffset;

        }).collect(Collectors.toList());
    }

    public int getActivationKey() {
        return activationKey;
    }
}
