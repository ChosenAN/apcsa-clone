import greenfoot.*;
import java.util.List;

public class ChaseEnemy extends BaseEnemy {
    public ChaseEnemy(int speed) {
        super(speed);
        setImage(new GreenfootImage("bluealien.png"));
    }

    @Override
    public void act() {
        Player player = (Player) getWorld().getObjects(Player.class).get(0);
        if (player != null) {
            moveTowardsPlayer(player);
        }
        checkCollision();
    }

    private void moveTowardsPlayer(Player player) {
        int dx = player.getX() - getX();
        int dy = player.getY() - getY();
        double angle = Math.atan2(dy, dx);
        int x = getX() + (int)(Math.cos(angle) * speed);
        int y = getY() + (int)(Math.sin(angle) * speed);
        setLocation(x, y);
    }

    @Override
    protected void reverseDirection() {
        // ChaseEnemy does not reverse direction on collision
    }
}