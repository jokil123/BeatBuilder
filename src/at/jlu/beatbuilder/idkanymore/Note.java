package at.jlu.beatbuilder.idkanymore;

public class Note {
    private NoteStatus status;
    private float timeStamp;

    public Note(float timeStamp) {
        this.status = NoteStatus.QUEUE;
        this.timeStamp = timeStamp;
    }

    public float getTimeStamp() {
        return timeStamp;
    }

    public NoteStatus getStatus() {
        return status;
    }
}
