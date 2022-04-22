package at.jlu.beatbuilder.applicationstates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DelayConfig extends BasicGameState {
    public static final int ID = 3;

    private float delay;

    private float cursorPosition;
    private float cursorSpeed;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(new Color(38, 70, 83));

        g.setColor(new Color(1f, 1f, 1f, 0.5f));
        g.setLineWidth(1f);
        g.drawRect(-1, 200, gc.getWidth() + 1, 200);

        g.setColor(new Color(1f, 1f, 1f, 0.1f));
        g.fillRect(-1, 200, gc.getWidth() + 1, 200);

        g.setColor(new Color(233, 196, 106));
        g.fillRect(gc.getWidth() / 2f - 4, 200, 8, 200);

        g.setColor(new Color(231, 111, 81));
        g.fillRect(cursorPosition + gc.getWidth() / 2f - 2, 200, 4, 200);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(MainMenu.ID);
        }

        if (cursorPosition > gc.getWidth() / 2f) {
            cursorPosition = -gc.getWidth() / 2f;

        } else {
            cursorPosition += cursorSpeed;
        }
    }
}
