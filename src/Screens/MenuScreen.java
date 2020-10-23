package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Rectangle;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Utils.Stopwatch;
import java.awt.Color;

// This is the class for the main menu screen
public class MenuScreen extends Screen { // implements MouseListener {
	protected ScreenCoordinator screenCoordinator;
	protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
	protected int menuItemSelected = -1;
	protected SpriteFont playGame;
	protected SpriteFont credits;
	protected SpriteFont instructions;
	protected Map background;
	protected Stopwatch keyTimer = new Stopwatch();
	protected int pointerLocationX, pointerLocationY, _x, _y;
	protected KeyLocker keyLocker = new KeyLocker();
	protected boolean isClicked = false;

	public MenuScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator; 
	//	System.out.println(Mouse.getX() + "," + Mouse.getY());
	}

	// @Override
	public void initialize() {
		playGame = new SpriteFont("PLAY GAME", 200, 150, "Comic Sans", 30, new Color(49, 207, 240));
		playGame.setOutlineColor(Color.black);
		playGame.setOutlineThickness(3);
		// playGame.addMouseListener(playGame);

		instructions = new SpriteFont("INSTRUCTIONS", 200, 200, "Comic Sans", 30, new Color(49, 207, 240));
		instructions.setOutlineColor(Color.black);
		instructions.setOutlineThickness(3);

		credits = new SpriteFont("CREDITS", 200, 250, "Comic Sans", 30, new Color(49, 207, 240));
		credits.setOutlineColor(Color.black);
		credits.setOutlineThickness(3);
		// credits.addMouseListener(credits);

		background = new TitleScreenMap();
		background.setAdjustCamera(false);
		keyTimer.setWaitTime(200);
		menuItemSelected = -1;
		keyLocker.lockKey(Key.SPACE);
		
	}

	public void update() {
	
		if ((Mouse.isClicked()) && (isClicked == false) && (Mouse.isInside(new Rectangle(200, 120, 180, 40)))) {
			isClicked = true;
			screenCoordinator.setGameState(GameState.LEVEL);
		} else if((Mouse.isClicked()) && (isClicked == false) && (Mouse.isInside(new Rectangle(200, 160, 200, 40)))){
			isClicked = true;
			screenCoordinator.setGameState(GameState.INSTRUCTIONS);
		} else if((Mouse.isClicked()) && (isClicked == false) && (Mouse.isInside(new Rectangle(200, 210, 200, 40)))){
			isClicked = true;
			screenCoordinator.setGameState(GameState.CREDITS);
		} else {
			isClicked = false;
		}


        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.DOWN) && keyTimer.isTimeUp() || Keyboard.isKeyDown(Key.S) && keyTimer.isTimeUp()) {
            keyTimer.reset();
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.UP) && keyTimer.isTimeUp() || Keyboard.isKeyDown(Key.W) && keyTimer.isTimeUp()) {
            keyTimer.reset();
            currentMenuItemHovered--;
        }

		// update background map (to play tile animations)
		background.update(null);
		
		// if down or up is pressed, change menu item "hovered" over (blue square in
		// front of text will move along with currentMenuItemHovered changing)
		if (Keyboard.isKeyDown(Key.DOWN) && keyTimer.isTimeUp() || Keyboard.isKeyDown(Key.S) && keyTimer.isTimeUp()) {
			keyTimer.reset();
			currentMenuItemHovered++;
		} else if (Keyboard.isKeyDown(Key.UP) && keyTimer.isTimeUp() || Keyboard.isKeyDown(Key.W) && keyTimer.isTimeUp()) {
			keyTimer.reset();
			currentMenuItemHovered--;
		}

		// if down is pressed on last menu item or up is pressed on first menu item,
		// "loop" the selection back around to the beginning/end
		if (currentMenuItemHovered > 2) {
			currentMenuItemHovered = 0;
		} else if (currentMenuItemHovered == 1) {
			currentMenuItemHovered = 1;
		} else if (currentMenuItemHovered == 2) {
			currentMenuItemHovered = 2;
		}
		// sets location for blue square in front of text (pointerLocation) and also
		// sets color of spritefont text based on which menu item is being hovered
		if (currentMenuItemHovered == 0) {
			playGame.setColor(new Color(255, 215, 0));
			instructions.setColor(new Color(49, 207, 240));
			credits.setColor(new Color(49, 207, 240));
			pointerLocationX = 170;
			pointerLocationY = 130;
		} else if (currentMenuItemHovered == 1) {
			playGame.setColor(new Color(49, 207, 240));
			instructions.setColor(new Color(255, 215, 0));
			credits.setColor(new Color(49, 207, 240));
			pointerLocationX = 170;
			pointerLocationY = 182;
		} else if (currentMenuItemHovered == 2) {
			playGame.setColor(new Color(49, 207, 240));
			instructions.setColor(new Color(49, 207, 240));
			credits.setColor(new Color(255, 215, 0));
			pointerLocationX = 170;
			pointerLocationY = 230;
		}

		// if space is pressed on menu item, change to appropriate screen based on which
		// menu item was chosen
		if (Keyboard.isKeyUp(Key.SPACE)) {
			keyLocker.unlockKey(Key.SPACE);
		}
		if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
			menuItemSelected = currentMenuItemHovered;
			if (menuItemSelected == 0) {
				screenCoordinator.setGameState(GameState.LEVEL);
			} else if (menuItemSelected == 1) {
				screenCoordinator.setGameState(GameState.INSTRUCTIONS);
			} else if (menuItemSelected == 2) {
				screenCoordinator.setGameState(GameState.CREDITS);
			}

		}
	}

	public void draw(GraphicsHandler graphicsHandler) {
		background.draw(graphicsHandler);
		playGame.draw(graphicsHandler);
		credits.draw(graphicsHandler);
		instructions.draw(graphicsHandler);
		graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20,
				new Color(49, 207, 240), Color.black, 2);
	}
	/*
	 * public void mousePressed(MouseEvent e) { System.out.println(e.getPoint()); }
	 * 
	 * @Override public void mouseClicked(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */
}