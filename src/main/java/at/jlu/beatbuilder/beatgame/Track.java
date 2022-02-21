package at.jlu.beatbuilder.beatgame;

import at.jlu.beatbuilder.enums.NoteStatus;

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
        return (ArrayList<Note>) notes.stream().filter(note -> note.timeStamp < timeStamp - PlayManager.ScoreManager.maxOffset && note.getStatus() == NoteStatus.NOT_PLAYED).collect(Collectors.toList());
    }

    public ArrayList<Note> getHitNotes(float timeStamp) {
        return (ArrayList<Note>) notes.stream().filter(note -> {
            return Math.abs(note.timeStamp - timeStamp) < PlayManager.ScoreManager.maxOffset && note.getStatus() == NoteStatus.NOT_PLAYED;

        }).collect(Collectors.toList());
    }

    public int getActivationKey() {
        return activationKey;
    }
}
