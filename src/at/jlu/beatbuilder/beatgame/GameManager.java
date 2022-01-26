package at.jlu.beatbuilder.beatgame;

import at.jlu.beatbuilder.beatgame.enums.NoteStatus;
import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameManager implements GameAction {
    public ScoreManager scoreManager;
    public BeatServer beatServer;
    public SongManager songManager;
    public RenderManger renderManger;

    public GameManager() {
        scoreManager = new ScoreManager();
        beatServer = new BeatServer();
        songManager = new SongManager();
        renderManger = new RenderManger();
    }

    @Override
    public void render(GameContainer container, Graphics g) {
        renderManger.renderNotes(container, g);
        renderManger.renderPlayhead(container, g);
    }

    @Override
    public void update(GameContainer container, int delta) {
        float timeStep = delta / 1000f;

        songManager.advancePlayBack(timeStep);
        beatServer.scoreMissedNotes(songManager.getPlayheadPosition());

        Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            beatServer.hit(songManager.getPlayheadPosition(), Input.KEY_SPACE);
        }
    }

    public class RenderManger {
        float noteSpeedMultiplier = 500;

        public void renderNotes(GameContainer container, Graphics g) {
            for (int trackNr = 0; trackNr < beatServer.tracks.size(); trackNr++) {
                Track track = beatServer.tracks.get(trackNr);

                for (int noteNr = 0; noteNr < track.notes.size(); noteNr++) {
                    Note note = track.notes.get(noteNr);

                    float xPos = (container.getWidth() / 2) - (note.timeStamp - songManager.getPlayheadPosition()) * noteSpeedMultiplier;

                    switch (note.getStatus()) {
                        case NOT_PLAYED:
                            g.setColor(Color.red);
                            break;
                        case MISSED:
                            g.setColor(Color.yellow);
                            break;
                        case HIT:
                            g.setColor(Color.green);
                            break;
                    }

                    g.fillRect(xPos, trackNr * 20, 20, 20);

                }
            }
        }

        public void renderPlayhead(GameContainer container, Graphics g) {
            g.setColor(Color.white);
            g.fillRect(container.getWidth() / 2, 0, 5, container.getHeight());
        }
    }


    public class SongManager {
        private float playheadPosition = 0;

        public boolean playing = true;


        public float advancePlayBack(float delta) {
            if (playing) {
                playheadPosition += delta;
            }
            return playheadPosition;
        }

        public float getPlayheadPosition() {
            return playheadPosition;
        }
    }

    public class ScoreManager {
        private float score = 0;
        private float scoreMultiplier = 1;

        private static final float minScoreMultiplier = 0f;
        private static final float maxScoreMultiplier = 10f;

        private static final float scoreMultiplierBonus = 0.25f;
        private static final float scoreMultiplierPenaltyMultiplier = 1.5f;

        public static final float maxOffset = 0.1f;

        private static final float perfectHitScore = 42f;


        public float hitNote(float offset) {
            float hitScore = scoreFromOffset(offset) * scoreMultiplier;
            score += hitScore;

            scoreMultiplier = Math.min(scoreMultiplier + scoreMultiplierBonus, maxScoreMultiplier);

            System.out.println("Score: " + score);
            System.out.println("Multiplier: " + scoreMultiplier);

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
        public ArrayList<Track> tracks = new ArrayList<Track>();

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
                    //System.out.println(Math.abs(note.timeStamp - songManager.getPlayheadPosition()));
                    //System.out.println(Math.abs(note.timeStamp - songManager.getPlayheadPosition()) < GameManager.ScoreManager.maxOffset);

                    note.setStatus(NoteStatus.HIT);
                    scoreManager.hitNote(note.getNoteOffset(time));
                });
            };
        }

        public void loadTracks(ArrayList<Track> tracks) {
            this.tracks = tracks;
        }
    }
}
