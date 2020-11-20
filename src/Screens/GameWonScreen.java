package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the level lose screen
public class GameWonScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public GameWonScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You beat the game!! Congratulations!", 150, 270, "Comic Sans", 30, Color.white);
        instructions = new SpriteFont("If you want to play again, reopen the game.", 190, 300,"Comic Sans", 20, Color.white);
    }
    
    @Override
    public Level.Map getMap() {
    	return null;
    }
    
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        winMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }

	@Override
	public void update() {
		// Do nothing. The game is over.
	}
}
