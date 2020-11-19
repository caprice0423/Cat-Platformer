package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.LevelState;
import Level.Map;
import Level.Player;
import Level.PlayerListener;
import Maps.TestMap;
import Maps.Level2Map;
import Maps.Level4Map;
import Maps.Level3Map;
import Players.Cat;
import Utils.Stopwatch;

// This class is for when the platformer game is actually being played
public class PlayLevelScreen extends Screen implements PlayerListener {
	protected ScreenCoordinator screenCoordinator;
	protected Map map, map2, map3, map4;
	protected Player player, player2, player3, player4;
	protected PlayLevelScreenState playLevelScreenState;
	protected Stopwatch screenTimer = new Stopwatch();
	protected LevelClearedScreen levelClearedScreen;
	protected LevelLoseScreen levelLoseScreen;
	private int mapNum = 1;
	public static int playerLives;

	public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
		this.screenCoordinator = screenCoordinator;
		
		// Set up maps
		this.map = new TestMap();
		this.map2 = new Level2Map();
		this.map3 = new Level3Map();
		this.map4 = new Level4Map();
		
	}

	public void initialize() {
		// define/setup map
		if (mapNum == 1) {
			map.reset();
			this.map = new TestMap();
		} else if (mapNum == 2) {
			map2.reset();
			this.map2 = new Level2Map();
		} else if (mapNum == 3) {
			map3.reset();
			this.map3 = new Level3Map();
		} else if (mapNum == 4) {
			map4.reset();
			this.map4 = new Level4Map();
		}

		// setup player
		this.player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
		this.player2 = new Cat(map2.getPlayerStartPosition().x, map2.getPlayerStartPosition().y);
		this.player3 = new Cat(map2.getPlayerStartPosition().x, map3.getPlayerStartPosition().y);
		this.player4 = new Cat(map4.getPlayerStartPosition().x, map4.getPlayerStartPosition().y);

		this.player.setMap(map);
		this.player.addListener(this);
		this.player.setLocation(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);

		this.player2.setMap(map2);
		this.player2.addListener(this);
		this.player2.setLocation(map2.getPlayerStartPosition().x, map2.getPlayerStartPosition().y);

		this.player3.setMap(map3);
		this.player3.addListener(this);
		this.player3.setLocation(map3.getPlayerStartPosition().x, map3.getPlayerStartPosition().y);

		this.player4.setMap(map4);
		this.player4.addListener(this);
		this.player4.setLocation(map4.getPlayerStartPosition().x, map4.getPlayerStartPosition().y);

		this.playLevelScreenState = PlayLevelScreenState.RUNNING;
	}

	public void update() {
		// based on screen state, perform specific actions
		switch (playLevelScreenState) {
		// if level is "running" update player and map to keep game logic for the
		// platformer level going
		case RUNNING:
			if (mapNum == 1) {
				player.update();
				map.update(player);
			} else if (mapNum == 2) {
				player2.update();
				map2.update(player2);
			} else if (mapNum == 3) {
				player3.update();
				map3.update(player3);
			} else if (mapNum == 4) {
				player4.update();
				map4.update(player4);
			}
			break;
		// if level has been completed, bring up level cleared screen
		case LEVEL_COMPLETED:
			levelClearedScreen = new LevelClearedScreen();
			levelClearedScreen.initialize();
			screenTimer.setWaitTime(2500);
			playLevelScreenState = PlayLevelScreenState.LEVEL_WIN_MESSAGE;
			break;
		// if level cleared screen is up and the timer is up for how long it should stay
		// out, go back to main menu
		case LEVEL_WIN_MESSAGE:
			if (screenTimer.isTimeUp()) {
				levelClearedScreen = null;
				mapNum++;
				if (mapNum == 2) {
					playLevelScreenState = PlayLevelScreenState.NEXT_LEVEL;
					player2.setLevelState(LevelState.RUNNING);
					System.out.println(mapNum);
				} else if (mapNum == 3) {
					playLevelScreenState = PlayLevelScreenState.NEXT_LEVEL;
					player3.setLevelState(LevelState.RUNNING);
					System.out.println(mapNum);
				} else if (mapNum == 4) {
					playLevelScreenState = PlayLevelScreenState.NEXT_LEVEL;
					player4.setLevelState(LevelState.RUNNING);
					System.out.println(mapNum);
				}
			}

			break;

		// if player died in level, bring up level lost screen
		case PLAYER_DEAD:
			playerLives--;
			levelLoseScreen = new LevelLoseScreen(this);
			levelLoseScreen.initialize();
			playLevelScreenState = PlayLevelScreenState.LEVEL_LOSE_MESSAGE;
			break;
		// wait on level lose screen to make a decision (either resets level or sends
		// player back to main menu)
		case LEVEL_LOSE_MESSAGE:
			levelLoseScreen.update();
			break;
		// displays new map for level 2
		case NEXT_LEVEL:
			if (mapNum == 2) {
				player2.update();
				map2.update(player2);
			} else if (mapNum == 3) {
				player3.update();
				map3.update(player3);
			} else if (mapNum == 4) {
				player4.update();
				map4.update(player4);
			}
			break;
		}

	}

	public void draw(GraphicsHandler graphicsHandler) {
		// based on screen state, draw appropriate graphics
		switch (playLevelScreenState) {
		case RUNNING:
		case LEVEL_COMPLETED:
		case PLAYER_DEAD:
			if (mapNum == 1) {
				map.draw(graphicsHandler);
				player.draw(graphicsHandler);
			} else if (mapNum == 2) {
				map2.draw(graphicsHandler);
				player2.draw(graphicsHandler);
			} else if (mapNum == 3) {
				map3.draw(graphicsHandler);
				player3.draw(graphicsHandler);
			} else if (mapNum == 4) {
				map4.draw(graphicsHandler);
				player4.draw(graphicsHandler);
			} 
			break;
		case LEVEL_WIN_MESSAGE:
			levelClearedScreen.draw(graphicsHandler);
			break;
		case NEXT_LEVEL:
			if (mapNum == 2) {
				map2.draw(graphicsHandler);
				player2.draw(graphicsHandler);
			} else if (mapNum == 3) {
				map3.draw(graphicsHandler);
				player3.draw(graphicsHandler);
			} else if (mapNum == 4) {
				map4.draw(graphicsHandler);
				player4.draw(graphicsHandler);
			}
			break;
		case LEVEL_LOSE_MESSAGE:
			levelLoseScreen.draw(graphicsHandler);
			break;
		}
	}

	public PlayLevelScreenState getPlayLevelScreenState() {
		return playLevelScreenState;
	}

	@Override
	public void onLevelCompleted() {
		playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
	}

	@Override
	public void onDeath() {
		playLevelScreenState = PlayLevelScreenState.PLAYER_DEAD;
	 }

	public void resetLevel() {
		initialize();
	}

	public void goBackToMenu() {
		screenCoordinator.setGameState(GameState.MENU);
	}
	
	public static int getPlayerLives() {
		return playerLives;
	}
	
	public static void setPlayerLives(int lives) {
		playerLives = lives;
	}

	// This enum represents the different states this screen can be in
	private enum PlayLevelScreenState {
		RUNNING, LEVEL_COMPLETED, PLAYER_DEAD, LEVEL_WIN_MESSAGE, LEVEL_LOSE_MESSAGE, NEXT_LEVEL
	}
}
