package at.jlu.beatbuilder.applicationstates;

import at.jlu.beatbuilder.BeatBuilder;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.util.ArrayList;

public class MainMenu extends BasicGameState implements MusicListener {
    public static final int ID = 1;

    private Music music;

    public ArrayList<String> levelList;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        levelList = findLevels();

        music = new Music("audio/title_music_intro.wav");
        music.play();
        music.setVolume(0);
        music.fade(5000, BeatBuilder.MUSIC_VOLUME, false);
        music.addListener(this);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());

        g.setColor(Color.white);

        for (int i = 0; i < levelList.size(); i++) {
            g.drawString(i + ": " + levelList.get(i), 100, 100 + i * 20);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_1)) {
            ((BeatBuilderLevel) sbg.getState(BeatBuilder.LEVEL)).loadLevel(levelList.get(0), sbg);
        } // else if (gc.getInput().isKeyPressed(gc.getInput().KEY_2)) {
//            ((BeatBuilderLevel) sbg.getState(2)).loadLevel(levelList.get(1), sbg);
//        }

        if (gc.getInput().isKeyPressed(Input.KEY_G)) {
            sbg.enterState(BeatBuilder.DELAY_CONFIG);
        }
    }

    @Override
    public int getID() {
        return MainMenu.ID;
    }

    public ArrayList<String> findLevels() {
        ArrayList<String> levelList = new ArrayList<>();

        File[] directories = new File("maps/").listFiles(File::isDirectory);

        try {
            assert directories != null;
            for (File directory : directories) {
                levelList.add(directory.getName());
            }
        } catch (NullPointerException e) {
            System.out.println("No maps found");
            return new ArrayList<>();
        }

        return levelList;
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
    public void musicSwapped(Music music, Music music1) {};

    @Override
    public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println(sbg.getCurrentStateID());
        System.out.println("Leaving MainMenu: " + this.getID());

        /*
        if (sbg.getCurrentStateID() == BeatBuilder.LEVEL) {
            music.fade(500, 0, false);
        }
        */
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println(sbg.getCurrentStateID());
        System.out.println("Entering MainMenu: " + this.getID());

        /*
        if (sbg.getCurrentStateID() == BeatBuilder.LEVEL) {
            music.fade(500, 0, false);
        }
        */
    }
}
