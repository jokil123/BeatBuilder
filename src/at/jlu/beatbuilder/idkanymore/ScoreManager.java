package at.jlu.beatbuilder.idkanymore;

public class ScoreManager {
    private static float score = 0;
    private static float scoreMultiplier = 1;

    public static float getScore() {
        return score;
    }

    public static void setScore(float score) {
        ScoreManager.score = score;
    }

    public static float getScoreMultiplier() {
        return scoreMultiplier;
    }

    public static void setScoreMultiplier(float scoreMultiplier) {
        ScoreManager.scoreMultiplier = scoreMultiplier;
    }
}
