package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.Player;
import Utils.AirGroundState;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;

import java.util.HashMap;

// This class is for the blue snake enemy that shoots fireballs
// It scoots back and forth between two set points (startLocation and endLocation)
// Every so often (based on shootTimer) it will shoot a Fireball enemy
public class SnakeEnemy extends Enemy {

    // start and end location defines the two points that it scoots between
    // is only made to walk along the x axis and has no air ground state logic, so make sure both points have the same Y value
    protected Point startLocation;
    protected Point endLocation;

    protected float movementSpeed = 1.5f;
    private Direction startFacingDirection;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;

    // timer is used to determine when a fireball is to be shot out
    protected Stopwatch shootTimer = new Stopwatch();

    // can be either SCOOT or SHOOT based on what the enemy is currently set to do
    protected SnakeState snakeState;
    protected SnakeState previousSnakeState;

    public SnakeEnemy(Point startLocation, Point endLocation, Direction facingDirection) {
        super(startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("SnakeEnemy.png"), 28, 17), "SCOOT_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        snakeState = SnakeState.SCOOT;
        previousSnakeState = snakeState;
        facingDirection = startFacingDirection;
        if (facingDirection == Direction.RIGHT) {
            currentAnimationName = "SCOOT_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            currentAnimationName = "SCOOT_LEFT";
        }
        airGroundState = AirGroundState.GROUND;

        // every 2 seconds, the fireball will be shot out
        shootTimer.setWaitTime(500);
    }

    @Override
    public void update(Player player) {
        float startBound = startLocation.x;
        float endBound = endLocation.x;

        // if shoot timer is up and snake is not currently shooting, set its state to SHOOT
        if (shootTimer.isTimeUp() && snakeState != SnakeState.SHOOT) {
            snakeState = SnakeState.SHOOT;
        }

        super.update(player);

        // if snake is scooting, determine which direction to walk in based on facing direction
        if (snakeState == SnakeState.SCOOT) {
            if (facingDirection == Direction.RIGHT) {
                currentAnimationName = "SCOOT_RIGHT";
                moveXHandleCollision(movementSpeed);
            } else {
                currentAnimationName = "SCOOT_LEFT";
                moveXHandleCollision(-movementSpeed);
            }

            // if snake reaches the start or end location, it turns around
            // snake may end up going a bit past the start or end location depending on movement speed
            // this calculates the difference and pushes the enemy back a bit so it ends up right on the start or end location
            if (getX1() + getScaledWidth() >= endBound) {
                float difference = endBound - (getScaledX2());
                moveXHandleCollision(-difference);
                facingDirection = Direction.LEFT;
            } else if (getX1() <= startBound) {
                float difference = startBound - getX1();
                moveXHandleCollision(difference);
                facingDirection = Direction.RIGHT;
            }

            // if snake is shooting, it first turns read for 1 second
            // then the fireball is actually shot out
        } else if (snakeState == SnakeState.SHOOT) {
            if (previousSnakeState == SnakeState.SCOOT) {
                shootTimer.setWaitTime(500);
                currentAnimationName = facingDirection == Direction.RIGHT ? "SHOOT_RIGHT" : "SHOOT_LEFT";
            } else if (shootTimer.isTimeUp()) {

                // define where fireball will spawn on map (x location) relative to snake enemy's location
                // and define its movement speed
                int fireballX;
                int fireballX2;
                
                float movementSpeed;
                if (facingDirection == Direction.RIGHT) {
                    fireballX = Math.round(getX()) + getScaledWidth();
                    movementSpeed = 2f;
                } else {
                    fireballX = Math.round(getX());
                    movementSpeed = -2f;
                }
                
                if (facingDirection == Direction.RIGHT) {
                    fireballX2 = Math.round(getX()) + getScaledWidth();
                    movementSpeed = 2f;
                } else {
                    fireballX2 = Math.round(getX());
                    movementSpeed = -2f;
                }

                // define where fireball will spawn on the map (y location) relative to snake enemy's location
                int fireballY = Math.round(getY()) + 4;
                int fireballY2 = Math.round(getY()) + 20;
                
                // create Fireball enemy
                BlueFireball fireball = new BlueFireball(new Point(fireballX, fireballY), movementSpeed, 1000);
                BlueFireball fireball2 = new BlueFireball(new Point(fireballX2, fireballY2), movementSpeed, 1000);
                
                // add fireball enemy to the map for it to offically spawn in the level
                map.addEnemy(fireball);
                map.addEnemy(fireball2);

                // change snake back to its WALK state after shooting, reset shootTimer to wait another 2 seconds before shooting again
                snakeState = SnakeState.SCOOT;
                shootTimer.setWaitTime(500);
            }
        }
        previousSnakeState = snakeState;
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction) {
        // if snake enemy collides with something on the x axis, it turns around and walks the other way
        if (hasCollided) {
            if (direction == Direction.RIGHT) {
                facingDirection = Direction.LEFT;
                currentAnimationName = "SCOOT_LEFT";
            } else {
                facingDirection = Direction.RIGHT;
                currentAnimationName = "SCOOT_RIGHT";
            }
        }
    }

    @Override
    public HashMap<String, Frame[]> getAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("SCOOT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 200)
                            .withScale(3)
                            .withBounds(4, 2, 5, 13)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 200)
                            .withScale(3)
                            .withBounds(4, 2, 5, 13)
                            .build()
            });

            put("SCOOT_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 200)
                            .withScale(3)
                            //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 5, 13)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 200)
                            .withScale(3)
                            //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 5, 13)
                            .build()
            });

            put("SHOOT_LEFT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 0)
                            .withScale(3)
                            .withBounds(4, 2, 5, 13)
                            .build(),
            });

            put("SHOOT_RIGHT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 0)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 2, 5, 13)
                            .build(),
            });
        }};
    }

    public enum SnakeState {
        SCOOT, SHOOT
    }
}