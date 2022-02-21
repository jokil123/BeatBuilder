package at.jlu.beatbuilder.beatgame.tests;

import at.jlu.beatbuilder.beatgame.PlayManager;

public class ScoreManagerTest {
    public static void main(String[] args) {
        PlayManager gm = new PlayManager();

        PlayManager.ScoreManager sm = gm.scoreManager;

        System.out.println(sm.getScore() + "; " + sm.getScoreMultiplier());

        for (int i = 0; i < 50; i++) {
            System.out.println(sm.hitNote(0f));
            System.out.println(sm.getScore() + "; " + sm.getScoreMultiplier());
        }


    }
}
