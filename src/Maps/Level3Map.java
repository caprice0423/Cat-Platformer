package Maps;

import Enemies.TinyMouseEnemy;
import Enemies.SnakeEnemy;
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
import java.util.ArrayList;

public class Level3Map extends Map {
	public Level3Map() {
		super("level3_map.txt", new CommonTileset(), new Point(1, 11));
	}

	public ArrayList<Enemy> loadEnemies() {
		ArrayList<Enemy> enemies = new ArrayList<>();

		enemies.add(new BugEnemy(getPositionByTileIndex(5, 7), Direction.LEFT));
		enemies.add(new DinosaurEnemy(getPositionByTileIndex(23, 10).addY(2), getPositionByTileIndex(26, 10).addY(2),

		enemies.add(new BugEnemy(getPositionByTileIndex(3, 9), Direction.LEFT));
		//enemies.add(new TinyMouseEnemy(getPositionByTileIndex(14, 1), Direction.LEFT));
		enemies.add(new SnakeEnemy(getPositionByTileIndex(23, 10).addY(2), getPositionByTileIndex(27, 10).addY(2),

				Direction.RIGHT));
		
	enemies.add(new BugEnemy(getPositionByTileIndex(0, 11), Direction.LEFT));
	return enemies;
		
	}

	@Override
	public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
		ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(36, 11), getPositionByTileIndex(39, 11), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));

		enhancedMapTiles.add(new HorizontalMovingPlatform(ImageLoader.load("GreenPlatform.png"),
				getPositionByTileIndex(18, 8), getPositionByTileIndex(23, 8), TileType.JUMP_THROUGH_PLATFORM, 3,
				new Rectangle(0, 6, 16, 4), Direction.RIGHT));

		enhancedMapTiles.add(new EndLevelBox(getPositionByTileIndex(43, 8)));

		return enhancedMapTiles;
	}

}
