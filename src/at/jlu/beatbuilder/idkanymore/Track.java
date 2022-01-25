package at.jlu.beatbuilder.idkanymore;

import java.util.ArrayList;

public class Track {
    public ArrayList<Note> notes = new ArrayList<>();

    public Note getNearestUnplayedNote(float timeStamp) {
        Note nearestNote = null;
        return notes.stream().filter(note ->
                        note.getStatus() == NoteStatus.QUEUE)
                .reduce((note1, note2) ->
                        Math.abs(note1.getTimeStamp() - timeStamp) < Math.abs(note2.getTimeStamp() - timeStamp) ? note1 : note2)
                .orElse(null);
    }
}
