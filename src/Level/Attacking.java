package Level;

import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Direction;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

// This class is a base class for the attack method in the game -- all attacks should extend from it
public class Attacking extends MapEntity {
	protected EnemyState enemyState;
	protected Direction facingDirection;
	protected ArrayList<EnemyListener> listeners = new ArrayList<>();

	public Attacking(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
		super(x, y, spriteSheet, startingAnimation);
	}

	public Attacking(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
		super(x, y, animations, startingAnimation);
	}

	public Attacking(BufferedImage image, float x, float y, String startingAnimation) {
		super(image, x, y, startingAnimation);
	}

	public Attacking(BufferedImage image, float x, float y) {
		super(image, x, y);
	}

	public Attacking(BufferedImage image, float x, float y, float scale) {
		super(image, x, y, scale);
	}

	public Attacking(BufferedImage image, float x, float y, float scale, ImageEffect imageEffect) {
		super(image, x, y, scale, imageEffect);
	}

	public Attacking(BufferedImage image, float x, float y, float scale, ImageEffect imageEffect, Rectangle bounds) {
		super(image, x, y, scale, imageEffect, bounds);
	}

	@Override
	public void initialize() {
		super.initialize();
	}

	public void update(Player player) {
		super.update();
		ArrayList<Enemy> allEnemies = map.getActiveEnemies();
		  for (Enemy enemy : allEnemies) {
	            if(intersects(enemy)) {
	            	enemy.mapEntityStatus = MapEntityStatus.REMOVED;
	            }
	        }
	}

	public void addListener(EnemyListener listener) {
		listeners.add(listener);
	}

}
