package at.jlu.beatbuilder.beatgame.tests;

import at.jlu.beatbuilder.beatgame.GameManager;
import org.newdawn.slick.Game;

public class ScoreManagerTest {
    public static void main(String[] args) {
        GameManager gm = new GameManager();

        GameManager.ScoreManager sm = gm.scoreManager;

        System.out.println(sm.getScore() + "; " + sm.getScoreMultiplier());

        for (int i = 0; i < 50; i++) {
            System.out.println(sm.hitNote(0f));
            System.out.println(sm.getScore() + "; " + sm.getScoreMultiplier());
        }


    }
}
