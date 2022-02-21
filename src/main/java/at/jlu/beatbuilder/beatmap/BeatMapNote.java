package at.jlu.beatbuilder.beatmap;

import at.jlu.beatbuilder.enums.NoteType;

public class BeatMapNote {
    NoteType type = NoteType.SINGLE;

    float timeStamp;
    float hold;

    int track;

    public BeatMapNote(NoteType type, float timeStamp, float hold, int track) {
        this.type = type;
        this.timeStamp = timeStamp;
        this.hold = hold;
        this.track = track;
    }
}
