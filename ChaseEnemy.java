import greenfoot.*;
import java.util.List;
public class ChaseEnemy extends BaseEnemy {
    public ChaseEnemy(int speed) {
        super(speed);
        GreenfootImage playerImage = new GreenfootImage("bluealien.png");
        setImage(playerImage);
    }

    @Override
    public void act() {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (player != null) {
            // Calculate direction to player
            int dx = player.getX() - getX();
            int dy = player.getY() - getY();
            double angle = Math.atan2(dy, dx);

            // Move towards player
            int x = getX() + (int)(Math.cos(angle) * speed);
            int y = getY() + (int)(Math.sin(angle) * speed);
            setLocation(x, y);
        }

        // Check for collisions with other enemies
        checkCollision();

        stayInBounds();
    }

    private void checkCollision() {
        List<BaseEnemy> enemies = getWorld().getObjects(BaseEnemy.class);
        for (BaseEnemy enemy : enemies) {
            if (enemy != this && isTouching(enemy.getClass())) {
                // Reverse direction upon collision
                int dx = getX() - enemy.getX();
                int dy = getY() - enemy.getY();
                setLocation(getX() + dx, getY() + dy); // Move away from the enemy
            }
        }
    }
}