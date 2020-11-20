package Maps;

import Enemies.*;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;
import java.util.ArrayList;

public class FinalLevelMap extends Map {
	
	public FinalLevelMap() {
		super("FinalLevelMap.txt", new CommonTileset(), new Point(1,9));
	}
	
	public ArrayList<Enemy> loadEnemies() {
		ArrayList<Enemy> enemies = new ArrayList<>();
		enemies.add(new DinosaurEnemy(getPositionByTileIndex(14,7).addY(18), getPositionByTileIndex(16,7).addY(18), Direction.LEFT));
		enemies.add(new DinosaurEnemy(getPositionByTileIndex(31,13).addY(16), getPositionByTileIndex(33,13).addY(16), Direction.LEFT));
		enemies.add(new SnakeEnemy(getPositionByTileIndex(1,32), getPositionByTileIndex(7,32), Direction.LEFT));
		enemies.add(new SnakeEnemy(getPositionByTileIndex(36,7), getPositionByTileIndex(46,7), Direction.LEFT));
		enemies.add(new MouseEnemy(getPositionByTileIndex(61,5), getPositionByTileIndex(67,5), Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(68,2),Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(55,30),Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(51,6),Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(52,6),Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(53,6),Direction.LEFT));
		enemies.add(new TinyMouseEnemy(getPositionByTileIndex(54,6),Direction.LEFT));
		enemies.add(new BugEnemy(getPositionByTileIndex(38,27),Direction.LEFT));
		
		return enemies;
	}

	public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
		ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
		
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(14, 32), getPositionByTileIndex(20, 32), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(24, 31), getPositionByTileIndex(29, 31), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(55, 27), getPositionByTileIndex(60, 27), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(53, 25), getPositionByTileIndex(58, 25), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		
		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(51, 23), getPositionByTileIndex(56, 23), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));
		
		enhancedMapTiles.add(new EndLevelBox(getPositionByTileIndex(68, 35)));

		return enhancedMapTiles;
	}
}
