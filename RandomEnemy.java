import greenfoot.*;
import java.util.List;
public class RandomEnemy extends BaseEnemy {
    private int moveCounter = 0;
    private int moveTime;
    private int direction;

    public RandomEnemy(int speed) {
        super(speed, Color.RED);
        direction = Greenfoot.getRandomNumber(360);
        moveTime = Greenfoot.getRandomNumber(50) + 50;
    }

    @Override
    public void act() {
        moveCounter++;
        if (moveCounter >= moveTime) {
            direction = Greenfoot.getRandomNumber(360);
            moveCounter = 0;
            moveTime = Greenfoot.getRandomNumber(50) + 50;
        }

        setRotation(direction);
        move(speed);

        // Check bounds and bounce
        if (atWorldEdge()) {
            direction += 180;
            if (direction >= 360) {
                direction -= 360;
            }
        }

        // Check for collisions with other enemies
        checkCollision();
    }

    private void checkCollision() {
        List<BaseEnemy> enemies = getWorld().getObjects(BaseEnemy.class);
        for (BaseEnemy enemy : enemies) {
            if (enemy != this && isTouching(enemy.getClass())) {
                // Change direction upon collision
                direction = Greenfoot.getRandomNumber(360);
            }
        }
    }
}