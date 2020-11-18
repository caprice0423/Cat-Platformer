package Maps;

import Enemies.BugEnemy; 
import Enemies.SnakeEnemy;
import Enemies.TinyMouseEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

// Represents Level 3
public class Level4Map extends Map {

	public Level4Map() {
		super("Level4.txt", new CommonTileset(), new Point(1, 11));
	}

	@Override
	public ArrayList<Enemy> loadEnemies() {
		ArrayList<Enemy> enemies = new ArrayList<>();
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(18, 10), Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(22, 10), Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(24, 7), Direction.LEFT));
		enemies.add(new DinosaurEnemy(getPositionByTileIndex(20, 1).addY(2), getPositionByTileIndex(24, 1).addY(2),
				Direction.RIGHT));
		enemies.add(new SnakeEnemy(getPositionByTileIndex(28, 2).addY(2), getPositionByTileIndex(30, 2).addY(2),
				Direction.RIGHT));
		return enemies;
	}

	@Override
	public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
		ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(12, 3), getPositionByTileIndex(14, 3), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(14, 1), getPositionByTileIndex(18, 1), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));

		enhancedMapTiles.add(new EndLevelBox(getPositionByTileIndex(32, 7)));

		return enhancedMapTiles;
	}

	/*
	 * @Override public ArrayList<NPC> loadNPCs() { ArrayList<NPC> npcs = new
	 * ArrayList<>();
	 * 
	 * npcs.add(new Walrus(getPositionByTileIndex(30, 10).subtract(new Point(0,
	 * 13)), this));
	 * 
	 * return npcs; }
	 */
}
