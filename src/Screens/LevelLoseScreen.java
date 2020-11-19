package Screens;

import Engine.*;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the level lose screen
public class LevelLoseScreen extends Screen {
    protected SpriteFont loseMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;

    public LevelLoseScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
    }

    @Override
    public void initialize() {
    	// if the player is out of lives changes the screen message
    	if (PlayLevelScreen.getPlayerLives() == 0) {
    		loseMessage = new SpriteFont("Game Over!", 350, 270, "Comic Sans", 30, Color.white);
            instructions = new SpriteFont("Press Space or Escape to go back to the main menu", 190, 300,"Comic Sans", 20, Color.white);
            keyLocker.lockKey(Key.SPACE);
            keyLocker.lockKey(Key.ESC); 
    	} else {
        loseMessage = new SpriteFont("You lose!", 350, 270, "Comic Sans", 30, Color.white);
        instructions = new SpriteFont("Press Space to try again or Escape to go back to the main menu", 120, 300,"Comic Sans", 20, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC); 
    	}
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        // if the player is out of lives the space also brings the user back to main menu
        if (Keyboard.isKeyDown(Key.SPACE)) {
        	if (PlayLevelScreen.getPlayerLives() == 0) {
        		playLevelScreen.goBackToMenu();
        	}
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC)) {
            playLevelScreen.goBackToMenu();
        }
    }
    
    @Override
    public Level.Map getMap() {
    	return null;
    }
    
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        loseMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
