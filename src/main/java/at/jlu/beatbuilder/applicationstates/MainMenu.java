package at.jlu.beatbuilder.applicationstates;

import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.util.ArrayList;

public class MainMenu extends BasicGameState {
    public static final int ID = 1;

    public ArrayList<String> levelList;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) {
        levelList = findLevels();
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
    public void update(GameContainer gc, StateBasedGame sbg, int delta) {
        if (gc.getInput().isKeyPressed(Input.KEY_1)) {
            ((BeatBuilderLevel) sbg.getState(2)).loadLevel(levelList.get(0), sbg);
        } // else if (gc.getInput().isKeyPressed(gc.getInput().KEY_2)) {
//            ((BeatBuilderLevel) sbg.getState(2)).loadLevel(levelList.get(1), sbg);
//        }
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
}
