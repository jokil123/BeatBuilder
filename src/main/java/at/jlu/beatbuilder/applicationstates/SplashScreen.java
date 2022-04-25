package at.jlu.beatbuilder.applicationstates;

import at.jlu.beatbuilder.BeatBuilder;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashScreen extends BasicGameState implements MusicListener {
    public static final int ID = 0;
    private BeatBuilder game;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        game = (BeatBuilder) sbg;

        game.music = new Music("audio/title_music_intro.wav");
        game.music.play();
        game.music.setVolume(0);
        game.music.fade(5000, BeatBuilder.MUSIC_VOLUME, false);
        game.music.addListener(this);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
        g.setColor(Color.white);
        g.drawString("BeatBuilder", gc.getWidth() / 2f - g.getFont().getWidth("BeatBuilder") / 2f, gc.getHeight() / 2f - g.getFont().getHeight("BeatBuilder") / 2f);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gc.getInput().isKeyDown(org.newdawn.slick.Input.KEY_ENTER)) {
            System.out.println("Continuing to main menu");
            sbg.enterState(MainMenu.ID);
        }
    }

    @Override
    public int getID() {
        return SplashScreen.ID;
    }


    @Override
    public void musicEnded(Music music) {
        music.removeListener(this);

        try {
            music = new Music("audio/title_music_loop.wav");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        music.loop();
        music.setVolume(BeatBuilder.MUSIC_VOLUME);
    }

    @Override
    public void musicSwapped(Music music, Music music1) {
    }
}
