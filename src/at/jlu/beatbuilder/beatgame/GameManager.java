package at.jlu.beatbuilder.beatgame;

import at.jlu.beatbuilder.beatgame.enums.NoteStatus;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameManager {
    public float playheadPosition = 0;

    public ScoreManager scoreManager;
    public BeatServer beatServer;

    public GameManager() {
        scoreManager = new ScoreManager();
        beatServer = new BeatServer();
    }

    public class ScoreManager {
        private float score = 0;
        private float scoreMultiplier = 1;

        private static float minScoreMultiplier = 0f;
        private static float maxScoreMultiplier = 10f;

        private static float scoreMultiplierBonus = 0.25f;
        private static float scoreMultiplierPenaltyMultiplier = 2f;

        public static float maxOffset = 0.5f;

        private static float perfectHitScore = 42f;


        public float hitNote(float offset) {
            float hitScore = scoreFromOffset(offset) * scoreMultiplier;
            score += hitScore;

            scoreMultiplier = Math.min(scoreMultiplier + scoreMultiplierBonus, maxScoreMultiplier);

            return hitScore;
        }

        public void missNote() {
            scoreMultiplier = Math.max(scoreMultiplier / scoreMultiplierPenaltyMultiplier, minScoreMultiplier);
        }

        private float scoreFromOffset(float offset) {
            return Math.max(0, Math.min(maxOffset - Math.abs(offset), perfectHitScore) * perfectHitScore) * 2;
        }

        public float getScore() {
            return score;
        }

        public void reset() {
            score = 0;
            scoreMultiplier = 1;
        }

        public float getScoreMultiplier() {
            return scoreMultiplier;
        }
    }

    public class BeatServer {
        ArrayList<Track> tracks = new ArrayList<Track>();

        public void scoreMissedNotes(float time) { /*  maybe rename this  */
            for (Track track : tracks) {
                track.getMissedNotes(time).forEach(note -> {
                    note.setStatus(NoteStatus.MISSED);
                    scoreManager.missNote();
                });
            }
        }

        public void hit(float time, int key) {
            ArrayList<Track> hitTracks = (ArrayList<Track>) tracks.stream().filter(track -> track.getActivationKey() == key).collect(Collectors.toList());

            for (Track track : hitTracks) {
                //System.out.println("hit notes" + track.getHitNotes(time));
                track.getHitNotes(time).forEach(note -> {
                    scoreManager.hitNote(note.getNoteOffset(time));
                });
            };
        }

        public void loadTracks(ArrayList<Track> tracks) {
            this.tracks = tracks;
        }
    }
}
