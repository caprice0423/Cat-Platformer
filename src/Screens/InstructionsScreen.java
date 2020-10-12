package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This class is for the Instructions screen
public class InstructionsScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont titleLabel;
    protected SpriteFont instructionsLabel;
    protected SpriteFont instructionsLabel2;
    protected SpriteFont objectiveLabel;
    protected SpriteFont objectiveLabel2;
    protected SpriteFont returnInstructionsLabel;

    public InstructionsScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        titleLabel = new SpriteFont("Instructions", 15, 35, "Times New Roman", 30, Color.white);
        instructionsLabel = new SpriteFont("Use the arrow keys or WASD keys on the keyboard ", 130, 140, "Times New Roman", 20, Color.white);
        instructionsLabel2 = new SpriteFont("to move left and right, jump up, and crouch down.", 130, 170, "Times New Roman",20, Color.white);
        objectiveLabel = new SpriteFont("Avoid touching the enemies and hit the golden ", 100, 250, "Times New Roman",20, Color.white);
        objectiveLabel2 = new SpriteFont("box to clear the level.", 100, 280, "Times New Roman",20, Color.white);
        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 560, "Times New Roman", 30, Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        titleLabel.draw(graphicsHandler);
        instructionsLabel.draw(graphicsHandler);
        instructionsLabel2.drawWithParsedNewLines(graphicsHandler);
        objectiveLabel.drawWithParsedNewLines(graphicsHandler);
        objectiveLabel2.drawWithParsedNewLines(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
