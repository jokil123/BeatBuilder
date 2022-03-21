package at.jlu.beatbuilder.applicationstates;

import org.lwjgl.Sys;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState {
    public static final int ID = 1;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gc.getInput().isKeyPressed(gc.getInput().KEY_1)) {
            ((BeatBuilderLevel) sbg.getState(2)).loadLevel("test", sbg);
        } else if (gc.getInput().isKeyPressed(gc.getInput().KEY_2)) {
            ((BeatBuilderLevel) sbg.getState(2)).loadLevel("test1", sbg);
        }
    }

    @Override
    public int getID() {
        return MainMenu.ID;
    }
}
