package at.jlu.beatbuilder;

import at.jlu.beatbuilder.applicationstates.BeatBuilderLevel;
import at.jlu.beatbuilder.applicationstates.DelayConfig;
import at.jlu.beatbuilder.applicationstates.MainMenu;
import at.jlu.beatbuilder.applicationstates.SplashScreen;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class BeatBuilder extends StateBasedGame {
    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU = 1;
    public static final int LEVEL = 2;
    public static final int DELAY_CONFIG = 3;

    public static final float MUSIC_VOLUME = 0.05f;

    public Music music;

    public BeatBuilder(String title) {
        super(title);
    }

    @Override
    public void initStatesList(org.newdawn.slick.GameContainer gc) {
        addState(new SplashScreen());
        addState(new MainMenu());
        addState(new BeatBuilderLevel());
        addState(new DelayConfig());
    }

    public static void main(String[] args) {
        try {
            org.newdawn.slick.AppGameContainer appgc = new AppGameContainer(new BeatBuilder("BeatBuilder"));
            appgc.setDisplayMode(800, 600, false);
            appgc.setVSync(true);
            appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
