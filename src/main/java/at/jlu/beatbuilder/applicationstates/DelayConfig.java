package at.jlu.beatbuilder.applicationstates;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DelayConfig extends BasicGameState {
    public static final int ID = 3;

    private float cursorLoopTime = 1000f;
    private float cursorSpeedMultiplier = 0.5f;

    private float time;
    private boolean hasHit;

    private float delay;

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

        // #e9c46a
        g.setColor(new Color(233, 196, 106));
        g.fillRect(gc.getWidth() / 2f - 4, 200, 8, 200);

        // #f4a261
        g.setColor(new Color(231, 111, 81));
        g.fillRect(gc.getWidth() / 2f + (time - cursorLoopTime / 2f) * cursorSpeedMultiplier, 200, 4, 200);

        // #f4a261
        g.setColor(new Color(244, 162, 97, 0.25f));
        g.fillRect(gc.getWidth() / 2f + delay * cursorSpeedMultiplier, 200, 4, 200);

        g.drawString("Delay: " + delay, 25, 25);
        g.drawString("Time: " + time, 25, 40);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int d) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(MainMenu.ID);
        }

        time += d;

        if (time > cursorLoopTime) {
            time = 0;
            hasHit = false;
        }

        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            hasHit = true;
            delay = cursorLoopTime / 2f - time;
        }
    }
}
