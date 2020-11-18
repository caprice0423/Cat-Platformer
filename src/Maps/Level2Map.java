package Maps;

import java.util.ArrayList;

import Enemies.BugEnemy;
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

// Represents a test map to be used in a level
public class Level2Map extends Map {

	public Level2Map() {
		super("level2_map.txt", new CommonTileset(), new Point(1, 11));
	}

	@Override
	public ArrayList<Enemy> loadEnemies() {
		ArrayList<Enemy> enemies = new ArrayList<>();
		enemies.add(new BugEnemy(getPositionByTileIndex(26, 9), Direction.LEFT));
		enemies.add(new BugEnemy(getPositionByTileIndex(25, 9), Direction.LEFT));
		enemies.add(new BugEnemy(getPositionByTileIndex(24, 9), Direction.LEFT));
		enemies.add(new DinosaurEnemy(getPositionByTileIndex(7, 4).addY(2), getPositionByTileIndex(10, 5).addY(2),
				Direction.RIGHT));
		enemies.add(new DinosaurEnemy(getPositionByTileIndex(17, 3).addY(2), getPositionByTileIndex(20, 3).addY(2),
				Direction.RIGHT));
		return enemies;
	}

	@Override
	public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
		ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(11, 5), getPositionByTileIndex(15, 5), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(22, 5), getPositionByTileIndex(26, 5), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));

		enhancedMapTiles.add(new EndLevelBox(getPositionByTileIndex(32, 4)));

		return enhancedMapTiles;
	}

	@Override
	public ArrayList<NPC> loadNPCs() {
		ArrayList<NPC> npcs = new ArrayList<>();

		npcs.add(new Walrus(getPositionByTileIndex(16, 10).subtract(new Point(0, 13)), this));

		return npcs;
	}
}
