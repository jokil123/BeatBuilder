package at.jlu.beatbuilder.beatgame;

import at.jlu.beatbuilder.enums.NoteStatus;

public class Note {
    public float timeStamp;
    public NoteStatus status;

    public Note(float timeStamp) {
        this.timeStamp = timeStamp;
        this.status = NoteStatus.NOT_PLAYED;
    }

    public NoteStatus getStatus() {
        return status;
    }

    public void setStatus(NoteStatus status) {
        this.status = status;
    }

    public float getNoteOffset(float timeStamp) {
        return timeStamp - this.timeStamp;
    }
}
