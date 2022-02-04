package at.jlu.beatbuilder.beatgame.beatmaps;

import at.jlu.beatbuilder.beatgame.enums.NoteType;

public class BeatMapNote {
    NoteType type = NoteType.SINGLE;

    float timeStamp;
    float hold;

    public BeatMapNote(NoteType type, float timeStamp, float hold) {
        this.type = type;
        this.timeStamp = timeStamp;
        this.hold = hold;
    }
}
