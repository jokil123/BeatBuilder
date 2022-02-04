package at.jlu.beatbuilder.beatgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface GameAction {
    public void update(GameContainer container, int delta);
    public void render(GameContainer container, Graphics g);
}
