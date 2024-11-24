import greenfoot.*;
import java.util.List;
public class BounceEnemy extends BaseEnemy {
    private int xSpeed;
    private int ySpeed;

    public BounceEnemy(int speed) {
        super(speed);
        GreenfootImage playerImage = new GreenfootImage("greenalien.png");
        setImage(playerImage);
        // Initialize random direction
        double angle = Math.toRadians(Greenfoot.getRandomNumber(360));
        xSpeed = (int)(Math.cos(angle) * speed);
        ySpeed = (int)(Math.sin(angle) * speed);
    }

    @Override
    public void act() {
        // Move
        setLocation(getX() + xSpeed, getY() + ySpeed);

        // Check bounds and bounce
        if (getX() <= 0 || getX() >= getWorld().getWidth() - 1) {
            xSpeed = -xSpeed;
        }
        if (getY() <= 0 || getY() >= getWorld().getHeight() - 1) {
            ySpeed = -ySpeed;
        }

        // Check for collisions with other enemies
        checkCollision();

        // Ensure staying within bounds
        stayInBounds();
    }

    private void checkCollision() {
        List<BaseEnemy> enemies = getWorld().getObjects(BaseEnemy.class);
        for (BaseEnemy enemy : enemies) {
            if (enemy != this && isTouching(enemy.getClass())) {
                // Reverse direction upon collision
                xSpeed = -xSpeed;
                ySpeed = -ySpeed;
            }
        }
    }
}