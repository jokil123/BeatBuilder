package at.jlu.beatbuilder.applicationstates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class SplashScreen extends BasicGameState {
    public static final int ID = 0;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
        g.setColor(Color.white);
        g.drawString("BeatBuilder", gc.getWidth() / 2 - g.getFont().getWidth("BeatBuilder") / 2, gc.getHeight() / 2 - g.getFont().getHeight("BeatBuilder") / 2);
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
}
