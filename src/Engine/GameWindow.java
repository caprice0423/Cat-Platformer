package Engine;

import javax.swing.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/*
 * The JFrame that holds the GamePanel
 * Just does some setup and exposes the gamePanel's screenManager to allow an external class to setup their own content and attach it to this engine.
 */
public class GameWindow {
	private JFrame gameWindow;
	private GamePanel gamePanel;

	public GameWindow() {
		gameWindow = new JFrame("Game");
		gamePanel = new GamePanel();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		gameWindow.setContentPane(gamePanel);
		gameWindow.setResizable(true);
		gameWindow.setSize(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT);
		gameWindow.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	// When the window is resized, the amount of tiles the game draws on screen now matches the new bounds.
		        gamePanel.getScreenManager().setScreenBounds(new GameObject.Rectangle(
		        		gameWindow.getX(),gameWindow.getY(),gameWindow.getWidth(),gameWindow.getHeight()));
		        try { // This makes the camera that actually draws these tiles update too.
		        	gamePanel.getScreenManager().getScreen().getMap().getCamera().setWidth(gameWindow.getWidth());
		        	gamePanel.getScreenManager().getScreen().getMap().getCamera().setHeight(gameWindow.getHeight());
		        } catch( NullPointerException npe ) {
		        	// Level cleared and level lost screens don't have cameras for their maps because they're static visuals.
		        	// But the program shouldn't just crash if they're resized either.
		        }
		    }
		});
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // it'd be nice if this actually worked more than 1/3rd of the time
		gamePanel.setupGame();
	}

	// triggers the game loop to start as defined in the GamePanel class
	public void startGame() {
		gamePanel.startGame();
	}

	public ScreenManager getScreenManager() {
		return gamePanel.getScreenManager();
	}
	
}
